import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PengelolaanBarang {

    /* search by kategori, barang dikelompokkan per-kategori dan per-id */
    private HashMap<String, HashMap<Integer, ArrayList<Barang>>> hashDaftarBarangByKategori = new HashMap<>();

    /* berisikan barang yang telah dibatas jumlah per-listnya */
    private ArrayList<ArrayList<Barang>> arrDaftarBarangTerbatas = new ArrayList<>();
    private HashMap<Integer, Integer> hashDaftarPembatasBarang = new HashMap<>();
    private ArrayList<Barang> daftarBarangUntukDihapus = new ArrayList<>();
    private int batasMaxDaftarBarangTerbatas;
    private boolean statusTambahBarangBerhasil = false;
    private boolean statusKombinasiAmunisiBerhasil = false;

    PengelolaanBarang(int batasMaxSlotDaftarBarangTerbatas){
        this.setBatasMaxDaftarBarangTerbatas(batasMaxSlotDaftarBarangTerbatas);
        /* Class ini sudah di desain memiliki 2 cara penyimpanan barang, terbatas dan tidak terbatas,
        *  namun penyimpanan barang tidak terbatas menampung semua barang termasuk yang ada pada
        *  penyimpanan terbatas agar memberi kemudahan akses barang.
        *  Sehingga secara default, agar user lebih mudah melihat penyimpanan terbatas
        *  maka slot kosong diperlihatkan */
        for (int i=0; i<this.batasMaxDaftarBarangTerbatas; i++) {
            this.arrDaftarBarangTerbatas.add(new ArrayList<>());
        }
    }

    private int filterMinimalSatu(int nilai){
        if(nilai < 1){
            nilai = 1;
        }
        return nilai;
    }

    //===================================================================================================
    /* get status */
    //===================================================================================================
    public boolean isBarangKeseluruhanEmpty(){
        return this.hashDaftarBarangByKategori.isEmpty();
    }

    public boolean isStatusTambahBarangBerhasil() {
        return statusTambahBarangBerhasil;
    }

    public boolean isStatusKombinasiAmunisiBerhasil() {
        return statusKombinasiAmunisiBerhasil;
    }

    //===================================================================================================
    /* convert/casting daftar barang ke daftar senjata atau sebaliknya */
    //===================================================================================================
    static public ArrayList<BarangSenjata> convertBarangKeSenjata(ArrayList<Barang> daftarBarangInput){
        if(daftarBarangInput == null){
            return null;
        }
        ArrayList<BarangSenjata> tempDaftarBarangInput = new ArrayList<>();
        for (Barang barangInput : daftarBarangInput) {
            if(barangInput instanceof BarangSenjata){
                tempDaftarBarangInput.add(((BarangSenjata) barangInput));
            }else{
                return null;
            }
        }
        return tempDaftarBarangInput;
    }

    static public ArrayList<Barang> convertSenjataKeBarang(ArrayList<BarangSenjata> daftarBarangInput){
        if(daftarBarangInput == null){
            return null;
        }
        ArrayList<Barang> tempDaftarBarangInput = new ArrayList<>();
        for (BarangSenjata barangInput : daftarBarangInput) {
            tempDaftarBarangInput.add(barangInput);
        }
        return tempDaftarBarangInput;
    }

    //===================================================================================================
    /* set pembatas */
    //===================================================================================================
    public void setBatasMaxDaftarBarangTerbatas(int jumlaMax){
        this.batasMaxDaftarBarangTerbatas = this.filterMinimalSatu(jumlaMax);
    }

    public void setBatasMaxClassBarangPenggunaanPadaDiriIdTertentu(int id, int jumlahMax){
        this.hashDaftarPembatasBarang.put(this.filterMinimalSatu(id), this.filterMinimalSatu(jumlahMax));
    }

    public void setBatasMaxClassBarangSenjataIdTertentu(int id, int jumlahMax){
        this.hashDaftarPembatasBarang.put(this.filterMinimalSatu(id), this.filterMinimalSatu(jumlahMax));
    }

    //===================================================================================================
    /* get jumlah slot daftar barang terbatas dan jumlah slot kosong */
    //===================================================================================================
    public int getBatasMaxSlotDaftarBarangTerbatas(){
        return this.batasMaxDaftarBarangTerbatas;
    }
    public int getJumlahSlotKosongPadaDaftarBarangTerbatas(){
        int jumlah = 0;
        for (ArrayList<Barang> daftarBarangTertentu : this.arrDaftarBarangTerbatas) {
            if(daftarBarangTertentu.isEmpty()){
                jumlah++;
            }
        }
        return jumlah;
    }

    //===================================================================================================
    /* tambah daftar barang */
    //===================================================================================================
    private void prosesTambahBarang(ArrayList<Barang> daftarBarangInput){
        /* agar bisa menandai setiap penambahan barang => berhasil atau tidak */
        this.daftarBarangUntukDihapus.clear();
        if(daftarBarangInput != null){
            int idIndeksAwalBarangInput = -1;
            int indeksCekTerakhirSlotKosong = 0;
            int indeksCekTerakhirSlotTertentuBelumPenuh = 0;
            if(!daftarBarangInput.isEmpty()){
                idIndeksAwalBarangInput = daftarBarangInput.get(0).idBarang;
            }
            for (Barang barangInput : daftarBarangInput) {
                /* agar dalam 1 daftarBarangInput konsisten memiliki 1 buah idBarang */
                if(barangInput.idBarang != idIndeksAwalBarangInput){
                    break;
                }
                /* berikut proses pengaturan penyimpanan terbatas jika barang termasuk 2 Class berikut */
                if(barangInput instanceof BarangPenggunaanPadaDiri || barangInput instanceof BarangSenjata){
                    if(barangInput instanceof BarangSenjataJarakDekat || barangInput instanceof BarangSenjataTembak){
                        /* jika semua slot sudah penuh atau habis maka... */
                        if(getJumlahSlotKosongPadaDaftarBarangTerbatas() <= 0){
                            /* keluar dari for */
                            break;
                        }
                        for (int i=indeksCekTerakhirSlotKosong; i<this.arrDaftarBarangTerbatas.size(); i++) {
                            if(this.arrDaftarBarangTerbatas.get(i).isEmpty()){
//                                ArrayList<Barang> temp = new ArrayList<>();
//                                temp.add(barangInput);
                                this.arrDaftarBarangTerbatas.get(i).add(barangInput);
                                indeksCekTerakhirSlotKosong = (i+1);
                                /* keluar dari for */
                                break;
                            }
                        }
                    }else{
                        /* pengulangan mencari slot-barang yang memiliki id sama dan
                        *  batasMax penumpukan barang tertentu yang belum penuh
                        *  (Misal: Amunisi pistol 9mm dalam 1 slot dapat berisikan 60 amunisi/peluru)*/
                        boolean statusKetemu = false;
                        for(int i=indeksCekTerakhirSlotTertentuBelumPenuh; i<this.arrDaftarBarangTerbatas.size(); i++){
                            /* jika terdapat slot yang tidak kosong (terisi) maka... */
                            if(!this.arrDaftarBarangTerbatas.get(i).isEmpty()){
                                /* jika dalam slot tersebut memiliki idBarang yang sama dan masih belum penuh maka... */
                                if(this.arrDaftarBarangTerbatas.get(i).get(0).idBarang == barangInput.idBarang
                                        && this.arrDaftarBarangTerbatas.get(i).size() < this.hashDaftarPembatasBarang.getOrDefault(barangInput.idBarang, 1)){
                                    statusKetemu = true;
                                    this.arrDaftarBarangTerbatas.get(i).add(barangInput);
                                    /* jika dalam slot tersebut memiliki idBarang yang sama dan masih belum penuh maka... */
                                    if(this.arrDaftarBarangTerbatas.get(i).size() < this.hashDaftarPembatasBarang.getOrDefault(barangInput.idBarang, 1)){
                                        indeksCekTerakhirSlotTertentuBelumPenuh = i;
                                        /* keluar dari for */
                                        break;
                                    }else{
                                        indeksCekTerakhirSlotTertentuBelumPenuh = (i+1);
                                        /* keluar dari for */
                                        break;
                                    }
                                /* jika slot tersebut idBarangnya tidak sama atau slot tersebut sudah penuh maka... */
                                }else{
                                    indeksCekTerakhirSlotTertentuBelumPenuh = (i+1);
                                }
                            /* jika slot tersebut kosong maka */
                            }else{
                                /* jika slot tertentu kosong maka tambahkan 1 barang */
                                statusKetemu = true;
                                this.arrDaftarBarangTerbatas.get(i).add(barangInput);
                                if(this.arrDaftarBarangTerbatas.get(i).size() >= this.hashDaftarPembatasBarang.getOrDefault(barangInput.idBarang, 1)){
                                    indeksCekTerakhirSlotTertentuBelumPenuh = (i+1);
                                }
                                /* keluar dari for */
                                break;
                            }
                    }

                        /* jika tidak ada id sama dalam penyimpanan terbatas atau per-slot id yang sama sudah penuh
                        *  dan penyimpanan terbatas masih memiliki ruang maka...  */
                        if(!statusKetemu && this.getJumlahSlotKosongPadaDaftarBarangTerbatas() != 0){
                            ArrayList<Barang> tempListBarang = new ArrayList<>();
                            tempListBarang.add(barangInput);
                            this.arrDaftarBarangTerbatas.add(tempListBarang);
                        /* jika tidak ada id sama dalam penyimpanan terbatas
                        *  atau per-slot id yang sama sudah penuh
                        *  dan jumlah slot penyimpanan terbatas sudah penuh/habis maka continue */
                        }else if(!statusKetemu){
                            continue;
                        }
                    }
                }
                if(!this.hashDaftarBarangByKategori.containsKey(barangInput.kategoriBarang)){
                    HashMap<Integer, ArrayList<Barang>> tempHash = new HashMap<>();
                    ArrayList<Barang> tempArr = new ArrayList<>();
                    tempArr.add(barangInput);
                    tempHash.put(barangInput.idBarang, tempArr);
                    this.hashDaftarBarangByKategori.put(barangInput.kategoriBarang, tempHash);
                }else{
                    if(!this.hashDaftarBarangByKategori.get(barangInput.kategoriBarang).containsKey(barangInput.idBarang)){
                        ArrayList<Barang> tempArr = new ArrayList<>();
                        tempArr.add(barangInput);
                        this.hashDaftarBarangByKategori.get(barangInput.kategoriBarang).put(barangInput.idBarang, tempArr);
                    }else{
                        this.hashDaftarBarangByKategori.get(barangInput.kategoriBarang).get(barangInput.idBarang).add(barangInput);
                    }
                }
                this.daftarBarangUntukDihapus.add(barangInput);
            }
        }
        if(this.daftarBarangUntukDihapus.isEmpty()){
            this.statusTambahBarangBerhasil = false;
        }else{
            this.statusTambahBarangBerhasil = true;
        }
    }


    public void tambahBarang(Barang barangInput, int jumlahInstance){
        if(barangInput != null){
            ArrayList<Barang> daftarBarangInput = Cloning.cloning(barangInput, jumlahInstance);
            this.prosesTambahBarang(daftarBarangInput);
        }else{
            this.statusTambahBarangBerhasil = false;
        }
    }

    public void tambahBarang(Barang barangInput){
        if(barangInput != null){
            ArrayList<Barang> tempBarangInput = new ArrayList<>();
            tempBarangInput.add(barangInput);
            this.prosesTambahBarang(tempBarangInput);
        }else{
            this.statusTambahBarangBerhasil = false;
        }
    }

    public void tambahBarang(ArrayList<Barang> daftarBarangInput){
        if(daftarBarangInput != null){
            this.prosesTambahBarang(daftarBarangInput);
        }else{
            this.statusTambahBarangBerhasil = false;
        }
    }

    public void tambahBarang(HashMap<String, HashMap<Integer, ArrayList<Barang>>> daftarBarangInput){
        if(daftarBarangInput != null){
            for (Map.Entry<String, HashMap<Integer, ArrayList<Barang>>> kategoriBarangInput : daftarBarangInput.entrySet()) {
                for (Map.Entry<Integer, ArrayList<Barang>> idbarangInput : kategoriBarangInput.getValue().entrySet()) {
                    this.prosesTambahBarang(idbarangInput.getValue());
                }
            }
        }
    }

    //===================================================================================================
    /* get daftar barang */
    //===================================================================================================
    public HashMap<String, HashMap<Integer, ArrayList<Barang>>> getDaftarBarangKeseluruhanByKategori() {
        return hashDaftarBarangByKategori;
    }

    public ArrayList<ArrayList<Barang>> getDaftarBarangTerbatas() {
        return this.arrDaftarBarangTerbatas;
    }

    public ArrayList<Barang> getDaftarBarangUntukDihapus() {
        return daftarBarangUntukDihapus;
    }

    //===================================================================================================
    /* pilih barang */
    //===================================================================================================
    public Barang pilihBarangDariDaftarBarangTidakTerbatas(String kategoriBarang, int idBarang){
        if(!this.hashDaftarBarangByKategori.containsKey(kategoriBarang)){
            return null;
        }else if(!this.hashDaftarBarangByKategori.get(kategoriBarang).containsKey(idBarang)){
            return null;
        }else{
            return this.hashDaftarBarangByKategori.get(kategoriBarang).get(idBarang).get(0);
        }
    }

    public  ArrayList<Barang> pilihBarangDariDaftarBarangTidakTerbatasDenganJumlahBanyak(String kategoriBarang, int idBarang){
        if(!this.hashDaftarBarangByKategori.containsKey(kategoriBarang)){
            return null;
        }else{
            return this.hashDaftarBarangByKategori.get(kategoriBarang).getOrDefault(idBarang, null);
        }
    }

    public HashMap<Integer, ArrayList<Barang>>  pilihBarangDariPenyimpananTerbatasDenganJumlahBanyak(int idBarang){
        int indeks = 0;
        for (ArrayList<Barang> barangTertentu : this.arrDaftarBarangTerbatas) {
            if(!barangTertentu.isEmpty()){
                if(barangTertentu.get(0).idBarang == idBarang){
                    HashMap<Integer, ArrayList<Barang>> temp = new HashMap<>();
                    temp.put(indeks, barangTertentu);
                    return temp;
                }
            }
            indeks++;
        }
        return null;
    }

    //===================================================================================================
    /* hapus barang */
    //===================================================================================================
    private void prosesHapusBarangDariDaftarBarangTerbatas(int indeksSumberBarangDiambil, Barang barangInput){
        /* class ini sudah dirancang agar jika daftar tertentu kosong, jangan menghapus daftar tersebut */
        this.arrDaftarBarangTerbatas.get(indeksSumberBarangDiambil).remove(barangInput);
    }

    private void prosesHapusBarangDariDaftarBarangTidakTerbatas(Barang barangInput){
        if(barangInput != null){
            if(this.hashDaftarBarangByKategori.containsKey(barangInput.kategoriBarang)){
                if(this.hashDaftarBarangByKategori.get(barangInput.kategoriBarang).containsKey(barangInput.idBarang)){
                    this.hashDaftarBarangByKategori.get(barangInput.kategoriBarang).get(barangInput.idBarang).remove(barangInput);
                    if(this.hashDaftarBarangByKategori.get(barangInput.kategoriBarang).get(barangInput.idBarang).isEmpty()){
                        this.hashDaftarBarangByKategori.get(barangInput.kategoriBarang).remove(barangInput.idBarang);
                    }
                    if(this.hashDaftarBarangByKategori.get(barangInput.kategoriBarang).isEmpty()){
                        this.hashDaftarBarangByKategori.remove(barangInput.kategoriBarang);
                    }
                }
            }
        }
    }


    public void hapusBarangDariDaftarBarangTerbatas(int indeksSumberBarangDiambil, Barang barangInput){
        if(barangInput instanceof BarangPenggunaanPadaDiri || barangInput instanceof BarangSenjata){
            this.prosesHapusBarangDariDaftarBarangTerbatas(indeksSumberBarangDiambil, barangInput);
        }
    }

    public void hapusBarangDariDaftarBarangTerbatas(int indeksSumberBarangDiambil , ArrayList<Barang> daftarBarangInput){
        if(daftarBarangInput != null){
            for (Barang barangInput : daftarBarangInput) {
                this.hapusBarangDariDaftarBarangTerbatas(indeksSumberBarangDiambil, barangInput);
            }
        }
    }

    public void hapusBarangDariPenyimpananTidakTerbatas(Barang barangInput){
        if(barangInput != null){
            if(!(barangInput instanceof BarangPenggunaanPadaDiri) && !(barangInput instanceof BarangSenjata) ){
                this.prosesHapusBarangDariDaftarBarangTidakTerbatas(barangInput);
            }
        }
    }

    public void hapusBarangDariPenyimpananTidakTerbatas(ArrayList<Barang> daftarBarangInput){
        if(daftarBarangInput != null){
            if(!daftarBarangInput.isEmpty()){
                for (Barang barangInput : daftarBarangInput) {
                    this.hapusBarangDariPenyimpananTidakTerbatas(barangInput);
                }
            }
        }
    }

    //===================================================================================================
    /* fitur tukar slot barang */
    //===================================================================================================
    public void tukarSlotBarangPadaDaftarBarangTerbatas(int indeksBarangSatu, int indeksBarangDua){
        ArrayList<Barang> temp = new ArrayList<>();
        temp.addAll(this.arrDaftarBarangTerbatas.get(indeksBarangSatu));
        this.arrDaftarBarangTerbatas.get(indeksBarangSatu).clear();
        this.arrDaftarBarangTerbatas.get(indeksBarangSatu).addAll(this.arrDaftarBarangTerbatas.get(indeksBarangDua));
        this.arrDaftarBarangTerbatas.get(indeksBarangDua).clear();
        this.arrDaftarBarangTerbatas.get(indeksBarangDua).addAll(temp);
    }

    //===================================================================================================
    /* fitur kombinasi amunisi */
    //===================================================================================================
    public void kombinasiAmunisiPadaDaftarBarangTerbatas(int indeksTujuanPadaDaftarBarangTerbatas, int indeksDaftarAmunisiDiambil,ArrayList<Barang> daftarAmunisi){
        if(this.arrDaftarBarangTerbatas.get(indeksTujuanPadaDaftarBarangTerbatas).get(0).idBarang != daftarAmunisi.get(0).idBarang){
            this.statusKombinasiAmunisiBerhasil = false;
        }else{
            this.daftarBarangUntukDihapus.clear();
            int i = 0;
            while(this.arrDaftarBarangTerbatas.get(indeksTujuanPadaDaftarBarangTerbatas).size() < this.hashDaftarPembatasBarang.getOrDefault(this.arrDaftarBarangTerbatas.get(indeksTujuanPadaDaftarBarangTerbatas).get(0).idBarang, 1)
                    && i < daftarAmunisi.size()){
                this.arrDaftarBarangTerbatas.get(indeksTujuanPadaDaftarBarangTerbatas).add(daftarAmunisi.get(i));
                this.daftarBarangUntukDihapus.add(daftarAmunisi.get(i));
                i++;
            }
            if(this.daftarBarangUntukDihapus.isEmpty()){
                this.statusKombinasiAmunisiBerhasil = false;
            }else{
                this.hapusBarangDariDaftarBarangTerbatas(indeksDaftarAmunisiDiambil, this.daftarBarangUntukDihapus);
                this.statusKombinasiAmunisiBerhasil = true;
            }
        }
    }





    public void print(){
        int i =0;
        for (ArrayList<Barang> barangTerbatasTertentu : this.arrDaftarBarangTerbatas) {
            i++;
            if(barangTerbatasTertentu.isEmpty()){
                System.out.printf("%2d. < slot kosong >\n", i);
            }else {
                System.out.printf("%2d. %-20s (%d)\n", i, barangTerbatasTertentu.get(0).nama, barangTerbatasTertentu.size());
            }
        }
    }
}


/* private karena butuh proses atau ketentuan tertentu */
/* search by id, barang dikelompokkan per-id */
//    private HashMap<Integer, ArrayList<Barang>> hashDaftarBarangById = new HashMap<>();
/* search by indeks, barang dikelompokkan per-kategori tertentu dan per-id */
//    private ArrayList<ArrayList<Barang>> arrDaftarBarangByKategori = new ArrayList<>();
//    private boolean statusSinkronisasiArrDaftarBarang;
/* search by indeks khusus senjata, karena penataan barang berbeda yaitu dihitung per-slot penyimpanan */
//    private ArrayList<ArrayList<BarangSenjata>> arrDaftarBarangSenjata = new ArrayList<>();

//    public void setBatasMaxBarangSenjata(int batasMaxBarangSenjata) {
//        this.batasMaxBarangSenjata = this.filterMinimalSatu(batasMaxBarangSenjata);
//    }
//
//    public int getBatasMaxBarangSenjata() {
//        return batasMaxBarangSenjata;
//    }
//
//    public void setBatasMaxClassBarangSenjataPerList(int batasMaxClassBarangSenjataPerList) {
//        this.batasMaxClassBarangSenjataPerList = this.filterMinimalSatu(batasMaxClassBarangSenjataPerList);
//    }

//    public int getBatasMaxClassBarangSenjataPerList() {
//        return batasMaxClassBarangSenjataPerList;
//    }

//    public void setBatasMaxClassBarangSenjataJarakDekatPerList(int batasMaxClassBarangSenjataJarakDekatPerList) {
//        this.batasMaxClassBarangSenjataJarakDekatPerList = this.filterMinimalSatu(batasMaxClassBarangSenjataJarakDekatPerList);
//    }
//
//    public int getBatasMaxClassBarangSenjataJarakDekatPerList() {
//        return batasMaxClassBarangSenjataJarakDekatPerList;
//    }
//
//    public void setBatasMaxClassBarangSenjataTembakPerList(int batasMaxClassBarangSenjataTembakPerList) {
//        this.batasMaxClassBarangSenjataTembakPerList = this.filterMinimalSatu(batasMaxClassBarangSenjataTembakPerList);
//    }
//
//    public int getBatasMaxClassBarangSenjataTembakPerList() {
//        return batasMaxClassBarangSenjataTembakPerList;
//    }

//    public boolean isBarangSlotSenjataKosong(){
//        return this.arrDaftarBarangSenjata.isEmpty();
//    }

//    public boolean isBarangSenjataPenuh(){
//        if(this.arrDaftarBarangSenjata.size() >= this.getBatasMaxBarangSenjata()){
//            return false;
//        }else{
//            return true;
//        }
//    }

//    private void hapusBarangSenjata(BarangSenjata oBarangSenjata){
//        for (int i=0; i<this.arrDaftarBarangSenjata.size(); i++){
//            this.arrDaftarBarangSenjata.get(i).remove(oBarangSenjata);
//        }
//    }

//    public void tambahBarang(HashMap<String, HashMap<Integer, ArrayList<Barang>>> daftarBarang){
//        for (Map.Entry<String, HashMap<Integer, ArrayList<Barang>>> kategoriBarang : daftarBarang.entrySet()) {
////            /* jika tidak terdapat kategori barang berikut maka... */
////            if(!this.hashDaftarBarangByKategori.containsKey(kategoriBarang.getKey())){
////                for (Map.Entry<Integer, ArrayList<Barang>> idBarang : kategoriBarang.getValue().entrySet()) {
////                    if(idBarang.getValue().get(0) instanceof BarangPenggunaanPadaDiri || idBarang.getValue().get(0) instanceof BarangSenjata){
////
////                        /* jika id barang tersebut ada pada pembatas barang maka... */
////                        if(this.hashDaftarPembatasBarang.getOrDefault(idBarang.getValue().get(0).idBarang, null) != null){
////                            boolean statusIdBarangSerupa = false;
////                            int tempIdBarangInput = idBarang.getValue().get(0).idBarang;
////                            /* pengulangan mengeluarkan list daftarBarang yang Terbatas dengan anggapan per-list sudah
////                             *  dikelompokkan per-id barang,
////                             *  dengan tujuan mencari idBarang serupa dan menambahkan ke list tersebut jika masih tersedia ruang */
////                            for (ArrayList<Barang> listBarang : this.arrDaftarBarangTerbatas) {
////                                /* jika terdapat id barang tersebut dan kurang dari pembatas maka... */
////                                if(listBarang.get(0).idBarang == tempIdBarangInput && listBarang.size() < this.hashDaftarPembatasBarang.get(tempIdBarangInput)){
////                                    statusIdBarangSerupa = true;
////                                    while (listBarang.size() != this.hashDaftarPembatasBarang.get(tempIdBarangInput) && !idBarang.getValue().isEmpty()) {
////                                        /* tambahkan 1 barang */
////                                        listBarang.add(idBarang.getValue().get(0));
//////                                        this.daftarBarangUntukDihapus.add(idBarang.getValue().get(0));
////                                        idBarang.getValue().remove(0);
////                                    }
////                                }
////                            }
////                            while (!idBarang.getValue().isEmpty() && this.arrDaftarBarangTerbatas.size() != this.batasMaxDaftarBarangTerbatas){
////                                ArrayList<Barang> temp = new ArrayList<>();
////                                while (!idBarang.getValue().isEmpty() && temp.size() < this.hashDaftarPembatasBarang.get(tempIdBarangInput)) {
////                                    /* tambahkan 1 barang */
////                                    temp.add(idBarang.getValue().get(0));
//////                                        this.daftarBarangUntukDihapus.add(idBarang.getValue().get(0));
////                                    idBarang.getValue().remove(0);
////                                }
////                                this.arrDaftarBarangTerbatas.add(temp);
////                            }
////
////                        /* jika tidak ada dalam pembatas maka hanya dianggap memiliki pembatas 1 buah barang per-1 slot */
////                        }else{
////                            ArrayList<Barang> temp = new ArrayList<>();
////                            temp.add(idBarang.getValue().get(0));
////                            idBarang.getValue().remove(0);
////                            this.arrDaftarBarangTerbatas.add(temp);
//////                            this.daftarBarangUntukDihapus.add(idBarang.getValue().get(0));
////                        }
////                    }else{
////                        //tambah ke HashMap
////                    }
////
////
////                    /* pengulangan seleksi barang */
////                    for (Barang oBarang : idBarang.getValue()) {
////                        /* jika barang = Class BarangPenggunaanPadaDiri dan Class BarangSenjata (bukan Turunannya) maka... */
////                    }
////
////
////
////                    /* jika tidak terdapat id barang berikut maka... */
////                    if(!this.hashDaftarBarangByKategori.get(kategoriBarang.getKey()).containsKey(idBarang.getKey())){
////                        this.hashDaftarBarangByKategori.get(kategoriBarang.getKey()).put(idBarang.getKey(), idBarang.getValue());
////                    }else{
////                        this.hashDaftarBarangByKategori.get(kategoriBarang.getKey()).get(idBarang.getKey()).addAll(idBarang.getValue());
////                    }
////                }
////                this.hashDaftarBarangByKategori.put(kategoriBarang.getKey() ,kategoriBarang.getValue());
////            }else{
//////                for (Map.Entry<Integer, ArrayList<Barang>> idBarang : kategoriBarang.getValue().entrySet()) {
//////                    /* jika tidak terdapat id barang berikut maka... */
//////                    if(!this.hashDaftarBarangByKategori.get(kategoriBarang.getKey()).containsKey(idBarang.getKey())){
//////                        this.hashDaftarBarangByKategori.get(kategoriBarang.getKey()).put(idBarang.getKey(), idBarang.getValue());
//////                    }else{
//////                        this.hashDaftarBarangByKategori.get(kategoriBarang.getKey()).get(idBarang.getKey()).addAll(idBarang.getValue());
//////                    }
//////                }
////            }
//        }
//    }

//    private void prosesTambahBarang(ArrayList<Barang> daftarBarang){
////        if(daftarBarang.get(0) instanceof BarangSenjata){
////            if(daftarBarang.get(0) instanceof BarangSenjataJarakDekat || daftarBarang.get(0) instanceof BarangSenjataTembak){
////                if(this.arrDaftarBarangSenjata.size() == this.getBatasMaxBarangSenjata()){
////                    daftarBarang.clear();
////                }else{
////                    int selisih = this.getBatasMaxBarangSenjata() - this.arrDaftarBarangSenjata.size();
////                    while (daftarBarang.size() != selisih){
////                        daftarBarang.remove(0);
////                    }
////                }
////            }else{
////                ArrayList<BarangSenjata> daftarBarangSenjata = this.convertBarangKeSenjata(daftarBarang);
////                int indeksBarangSenjataTidakPenuh = this.cariIndeksBarangSenjataTidakPenuh(daftarBarangSenjata);
////                if(indeksBarangSenjataTidakPenuh == -1){
////
////                }else{
////
////                }
////            }
////        }
//
//        /* Jika tidak terdapat kategori barang berikut maka... */
//        if(!this.hashDaftarBarangByKategori.containsKey(daftarBarang.get(0).kategoriBarang)){
//            HashMap<Integer, ArrayList<Barang>> temp = new HashMap<>();
//            temp.put(daftarBarang.get(0).idBarang, daftarBarang);
//            /* Tambahkan HasMap baru dengan kategori berikut dan value List daftarBarang */
//            this.hashDaftarBarangByKategori.put(daftarBarang.get(0).kategoriBarang, temp);
//
//        /* Jika terdapat kategori barang tersebut maka... */
//        }else{
//            /* Jika tidak terdapat id barang berikut dengan kategori barang tersebut maka... */
//            if(!this.hashDaftarBarangByKategori.get(daftarBarang.get(0).kategoriBarang).containsKey(daftarBarang.get(0).idBarang)){
//                this.hashDaftarBarangByKategori.get(daftarBarang.get(0).kategoriBarang).put(daftarBarang.get(0).idBarang, daftarBarang);
//
//            /* Jika terdapat id barang tersebut dengan kategori barang tersebut maka... */
//            }else{
//                this.hashDaftarBarangByKategori.get(daftarBarang.get(0).kategoriBarang).get(daftarBarang.get(0).idBarang).addAll(daftarBarang);
//            }
//        }
//    }

//    private int cariIndeksBarangSenjataTidakPenuh(ArrayList<BarangSenjata> daftarSenjata){
//        int iterasi = 0;
//        int indeksBarangTidakPenuh = -1;
//        for (ArrayList<BarangSenjata> oDaftarSenjata : this.arrDaftarBarangSenjata) {
//            if(oDaftarSenjata.get(0).idBarang == daftarSenjata.get(0).idBarang){
//                if(oDaftarSenjata.size() < this.getBatasMaxBarangSenjata()){
//                    indeksBarangTidakPenuh = iterasi;
//                    /* keluar dari for */
//                    break;
//                }
//            }
//            iterasi++;
//        }
//        return indeksBarangTidakPenuh;
//    }

//    private void sinkronisasiSemuaBarangKeSenjata(ArrayList<BarangSenjata> daftarSenjata){
////        if(!(daftarSenjata.get(0) instanceof  BarangSenjataTembak) && !(daftarSenjata.get(0) instanceof  BarangSenjataJarakDekat)){
////            int indeksBarangTidakPenuh = this.cariIndeksBarangSenjataTidakPenuh(daftarSenjata);
////
////            /* dengan asumsi BarangSenjata merupakan senjata yang dilemparkan,
////             *  dan mengambil penyimpanan barang untuk 1 slot secara bertumpuk
////             *  misal 1 slot bisa di-isi 10 shuriken */
////            if(daftarSenjata.size() > this.getBatasMaxClassBarangSenjataPerList()){
////                while (!daftarSenjata.isEmpty()){
////                    ArrayList<BarangSenjata> temp = new ArrayList<>();
////                    if(indeksBarangTidakPenuh != -1){
////                        temp = this.arrDaftarBarangSenjata.get(indeksBarangTidakPenuh);
////                    }
////                    /* pengulangan sebanyak jumlah daftar senjata */
////                    for(int i=0; i>=daftarSenjata.size()-1; i+=0){
////                        /* jika list telah mencapai batas maksimal maka keluar dari for */
////                        if( temp.size() ==  this.getBatasMaxClassBarangSenjataPerList() ){
////                            /* keluar dari for */
////                            break;
////                        }
////                        temp.add(daftarSenjata.get(i));
////                        /* arraylist input method ini telah dibedakan dengan penyimpanan pada hashmap
////                        *  dalam method convertBarangKeSenjata, sehingga aman jika diremove */
////                        daftarSenjata.remove(i);
////                    }
////                    this.arrDaftarBarangSenjata.add(temp);
////                }
////            }else{
////                this.arrDaftarBarangSenjata.add(daftarSenjata);
////            }
////        }else{
////            /*  dengan asumsi BarangSenjataTembak dan JarakDekat
////             *  mengambil penyimpanan barang untuk 1 slot secara terpisah,
////             *  misal 1 slot hanya bisa di-isi 1 pistol atau pedang */
////            int i=0;
////            while (this.arrDaftarBarangSenjata.size() != this.getBatasMaxBarangSenjata() && !daftarSenjata.isEmpty()){
////                ArrayList<BarangSenjata> temp = new ArrayList<>();
////                temp.add(daftarSenjata.get(i));
////                this.arrDaftarBarangSenjata.add(temp);
////                daftarSenjata.remove(i);
////                i++;
////            }
////            if(!daftarSenjata.isEmpty()){
////                for (Barang senjata : daftarSenjata) {
////                    this.hapusBarangDariDaftarBarangTerbatas(senjata);
////                }
////            }
////        }
////    }
