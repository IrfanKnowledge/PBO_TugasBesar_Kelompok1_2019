import java.util.ArrayList;
import java.util.HashMap;

public class PengelolaanBarang {

        /* private karena butuh proses atau ketentuan tertentu */
    /* search by id, barang dikelompokkan per-id */
//    private HashMap<Integer, ArrayList<Barang>> hashDaftarBarangById = new HashMap<>();
    /* search by indeks, barang dikelompokkan per-kategori tertentu dan per-id */
//    private ArrayList<ArrayList<Barang>> arrDaftarBarangByKategori = new ArrayList<>();
//    private boolean statusSinkronisasiArrDaftarBarang;

    /* search by kategori, barang dikelompokkan per-kategori dan per-id */
    private HashMap<String, HashMap<Integer, ArrayList<Barang>>> hashDaftarBarangByKategori = new HashMap<>();
    /* search by indeks khusus senjata, karena penataan barang berbeda yaitu dihitung per-slot penyimpanan */
    private ArrayList<ArrayList<BarangSenjata>> arrDaftarBarangSenjata = new ArrayList<>();

    private int batasMaxBarangSenjata;
    private int batasMaxClassBarangSenjataPerList;
    private int batasMaxClassBarangSenjataJarakDekatPerList;
    private int batasMaxClassBarangSenjataTembakPerList;

    /* sementara, hanya ClassBarangSenjata dan batasMaxBarangSenjata yang dapat dirubah */
    PengelolaanBarang(int batasMaxClassBarangSenjataPerList, int batasMaxBarangSenjata){

        this.setBatasMaxBarangSenjata(batasMaxBarangSenjata);
        this.setBatasMaxClassBarangSenjataPerList(batasMaxClassBarangSenjataPerList);
        this.batasMaxClassBarangSenjataJarakDekatPerList = 1;
        this.batasMaxClassBarangSenjataTembakPerList = 1;
//        this.setBatasMaxClassBarangSenjataJarakDekatPerList(1);
//        this.setBatasMaxClassBarangSenjataTembakPerList(1);
//        this.statusSinkronisasiArrDaftarBarang = false;
    }

    private int filterMinimalSatu(int nilai){
        if(nilai < 1){
            nilai = 1;
        }
        return nilai;
    }

    public void setBatasMaxBarangSenjata(int batasMaxBarangSenjata) {
        this.batasMaxBarangSenjata = this.filterMinimalSatu(batasMaxBarangSenjata);
    }

    public int getBatasMaxBarangSenjata() {
        return batasMaxBarangSenjata;
    }

    public void setBatasMaxClassBarangSenjataPerList(int batasMaxClassBarangSenjataPerList) {
        this.batasMaxClassBarangSenjataPerList = this.filterMinimalSatu(batasMaxClassBarangSenjataPerList);
    }

    public int getBatasMaxClassBarangSenjataPerList() {
        return batasMaxClassBarangSenjataPerList;
    }

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

    public boolean isBarangKeseluruhanEmpty(){
        return this.hashDaftarBarangByKategori.isEmpty();
    }

    public int getJumlahBarangKeseluruhan(){
        return this.hashDaftarBarangByKategori.size();
    }

    public int getJumlahBarangSenjata(){
        return this.arrDaftarBarangSenjata.size();
    }

    public int getJumlahSlotTersediaBarangSenjata(){
        int temp = this.getBatasMaxBarangSenjata() - this.arrDaftarBarangSenjata.size();
        if(temp < 0){
            temp = 0;
        }
        return  temp;
    }

    public boolean isBarangSenjataKosong(){
        return this.arrDaftarBarangSenjata.isEmpty();
    }

    public boolean isBarangSenjataPenuh(){
        if(this.arrDaftarBarangSenjata.size() >= this.getBatasMaxBarangSenjata()){
            return false;
        }else{
            return true;
        }
    }

    private void prosesTambahBarang(ArrayList<Barang> daftarBarang){
        /* Jika tidak terdapat kategori barang berikut maka... */
        if(!this.hashDaftarBarangByKategori.containsKey(daftarBarang.get(0).kategoriBarang)){
            HashMap<Integer, ArrayList<Barang>> temp = new HashMap<>();
            temp.put(daftarBarang.get(0).idBarang, daftarBarang);
            /* Tambahkan HasMap baru dengan kategori berikut dan value List daftarBarang */
            this.hashDaftarBarangByKategori.put(daftarBarang.get(0).kategoriBarang, temp);

        /* Jika terdapat kategori barang tersebut maka... */
        }else{
            /* Jika tidak terdapat id barang berikut dengan kategori barang tersebut maka... */
            if(!this.hashDaftarBarangByKategori.get(daftarBarang.get(0).kategoriBarang).containsKey(daftarBarang.get(0).idBarang)){
                this.hashDaftarBarangByKategori.get(daftarBarang.get(0).kategoriBarang).put(daftarBarang.get(0).idBarang, daftarBarang);

            /* Jika terdapat id barang tersebut dengan kategori barang tersebut maka... */
            }else{
                this.hashDaftarBarangByKategori.get(daftarBarang.get(0).kategoriBarang).get(daftarBarang.get(0).idBarang).addAll(daftarBarang);
            }
        }
    }

    private void sinkronisasiSemuaBarangKeSenjata(ArrayList<BarangSenjata> daftarSenjata){
        if(!(daftarSenjata.get(0) instanceof  BarangSenjataTembak) && !(daftarSenjata.get(0) instanceof  BarangSenjataJarakDekat)){
            int iterasi = 0;
            boolean statusTerdapatIdBarangInputTidakPenuh = false;
            int indeksBarangTidakPenuh = -1;
            for (ArrayList<BarangSenjata> oDaftarSenjata : this.arrDaftarBarangSenjata) {
                if(oDaftarSenjata.get(0).idBarang == daftarSenjata.get(0).idBarang){
                    if(oDaftarSenjata.size() < this.getBatasMaxBarangSenjata()){
                        indeksBarangTidakPenuh = iterasi;
                        statusTerdapatIdBarangInputTidakPenuh = true;
                        /* keluar dari for */
                        break;
                    }
                }
                iterasi++;
            }

            /* dengan asumsi BarangSenjata merupakan senjata yang dilemparkan,
             *  dan mengambil penyimpanan barang untuk 1 slot secara bertumpuk
             *  misal 1 slot bisa di-isi 10 shuriken */
            if(daftarSenjata.size() > this.getBatasMaxClassBarangSenjataPerList()){
                /* ulangi selama daftarSenjata tidak kosong */
                while (!daftarSenjata.isEmpty()){
                    ArrayList<BarangSenjata> temp = new ArrayList<>();
                    if(statusTerdapatIdBarangInputTidakPenuh){
                        temp = this.arrDaftarBarangSenjata.get(indeksBarangTidakPenuh);
                    }
                    /* pengulangan sebanyak jumlah daftar senjata */
                    for(int i=0; i>=daftarSenjata.size()-1; i+=0){
                        temp.add(daftarSenjata.get(i));
                        /* arraylist input method ini telah dibedakan dengan penyimpanan pada hashmap
                        *  dalam method convertBarangKeSenjata, sehingga aman jika diremove */
                        daftarSenjata.remove(i);
                        /* jika list telah mencapai batas maksimal maka keluar dari for */
                        if( temp.size() ==  this.getBatasMaxClassBarangSenjataPerList() ){
                            /* keluar dari for */
                            break;
                        }
                    }
                    this.arrDaftarBarangSenjata.add(temp);
                }
            }else{
                this.arrDaftarBarangSenjata.add(daftarSenjata);
            }
        }else{
            /*  dengan asumsi BarangSenjataTembak dan JarakDekat
             *  mengambil penyimpanan barang untuk 1 slot secara terpisah,
             *  misal 1 slot hanya bisa di-isi 1 pistol atau pedang */
            for (int i=0; i<daftarSenjata.size(); i++){
                ArrayList<BarangSenjata> temp = new ArrayList<>();
                temp.add(daftarSenjata.get(i));
            }
        }
    }

    private ArrayList<BarangSenjata> convertBarangKeSenjata(ArrayList<Barang> daftarBarang){
        ArrayList<BarangSenjata> tempDua = new ArrayList<>();
        for (Barang isi : daftarBarang) {
            tempDua.add(((BarangSenjata) isi));
        }
        return tempDua;
    }

    private ArrayList<Barang> convertSenjataKeBarang(ArrayList<BarangSenjata> daftarBarang){
        ArrayList<Barang> tempDua = new ArrayList<>();
        for (BarangSenjata isi : daftarBarang) {
            tempDua.add(isi);
        }
        return tempDua;
    }

    public void tambahBarang(Barang oBarang, int jumlahInstance){
        ArrayList<Barang> daftarBarang = Cloning.cloning(oBarang, jumlahInstance);
        this.prosesTambahBarang(daftarBarang);

        /* agar dua list memiliki objectSenjata yang sama */
        if(oBarang instanceof BarangSenjata){
            this.sinkronisasiSemuaBarangKeSenjata(this.convertBarangKeSenjata(daftarBarang));
        }
    }

    public void tambahBarang(Barang oBarang){
        ArrayList<Barang> temp = new ArrayList<>();
        temp.add(oBarang);
        this.prosesTambahBarang(temp);

        /* agar dua list memiliki objectSenjata yang sama */
        if(temp.get(0) instanceof BarangSenjata){
            this.sinkronisasiSemuaBarangKeSenjata(this.convertBarangKeSenjata(temp));
        }
    }

    public void tambahBarang(ArrayList<Barang> daftarBarang){
        this.prosesTambahBarang(daftarBarang);

        /* agar dua list memiliki objectSenjata yang sama */
        if(daftarBarang.get(0) instanceof BarangSenjata){
            this.sinkronisasiSemuaBarangKeSenjata(this.convertBarangKeSenjata(daftarBarang));
        }
    }

    public void tambahBarangSenjata(ArrayList<BarangSenjata> daftarBarang){
        this.prosesTambahBarang((this.convertSenjataKeBarang(daftarBarang)));
    }

    public HashMap<String, HashMap<Integer, ArrayList<Barang>>> getDaftarBarangKeseluruhanByKategori() {
        return hashDaftarBarangByKategori;
    }

    public ArrayList<ArrayList<BarangSenjata>> getDaftarBarangSenjata() {
        return this.arrDaftarBarangSenjata;
    }

    public Barang pilihBarang(Barang barangYangDicari){
        if(!this.hashDaftarBarangByKategori.containsKey(barangYangDicari.kategoriBarang)){
            return null;
        }else {
            if(!this.hashDaftarBarangByKategori.get(barangYangDicari.kategoriBarang).containsKey(barangYangDicari.idBarang)){
                return null;
            }else {
                return this.hashDaftarBarangByKategori.get(barangYangDicari.kategoriBarang).get(barangYangDicari.idBarang).get(0);
            }
        }
    }

    public Barang pilihBarang(String kategoriBarang, int idBarang){
        if(!this.hashDaftarBarangByKategori.containsKey(kategoriBarang)){
            return null;
        }else {
            if(!this.hashDaftarBarangByKategori.get(kategoriBarang).containsKey(idBarang)){
                return null;
            }else {
                return this.hashDaftarBarangByKategori.get(kategoriBarang).get(idBarang).get(0);
            }
        }
    }

    public void hapusBarang(Barang oBarang){
        if(oBarang instanceof BarangSenjata){
            this.hapusBarangSenjata((BarangSenjata) oBarang);
        }
        if(this.hashDaftarBarangByKategori.containsKey(oBarang.kategoriBarang)){
            if(this.hashDaftarBarangByKategori.get(oBarang.kategoriBarang).containsKey(oBarang.idBarang)){
                this.hashDaftarBarangByKategori.get(oBarang.kategoriBarang).get(oBarang.idBarang).remove(oBarang);
            }
        }
    }

    public void hapusBarangSenjata(BarangSenjata oBarangSenjata){
        for (int i=0; i<this.arrDaftarBarangSenjata.size(); i++){
            this.arrDaftarBarangSenjata.get(0).remove(oBarangSenjata);
        }
    }
}
