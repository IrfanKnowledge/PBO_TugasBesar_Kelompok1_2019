import java.util.ArrayList;
import java.util.HashMap;

public class PengelolaanBarang {

    /* private karena butuh proses atau ketentuan tertentu */
    private HashMap<Integer, ArrayList<Barang>> hashDaftarBarangSemuaBarang = new HashMap<>();
    private ArrayList<ArrayList<BarangSenjata>> arrDaftarBarangSenjata = new ArrayList<>();

    private int batasMaxClassBarangSenjataPerList;
    private int batasMaxClassBarangSenjataJarakDekatPerList;
    private int batasMaxClassBarangSenjataTembakPerList;

    /* sementara, hanya ClassBarangSenjata yang dapat dirubah */
    PengelolaanBarang(int batasMaxClassBarangSenjataPerList){
        this.setBatasMaxClassBarangSenjataPerList(batasMaxClassBarangSenjataPerList);
        this.batasMaxClassBarangSenjataJarakDekatPerList = 1;
        this.batasMaxClassBarangSenjataTembakPerList = 1;
//        this.setBatasMaxClassBarangSenjataJarakDekatPerList(1);
//        this.setBatasMaxClassBarangSenjataTembakPerList(1);
    }

    private int filterMinimalSatu(int nilai){
        if(nilai < 1){
            nilai = 1;
        }
        return nilai;
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

    private void prosesTambahBarang(ArrayList<Barang> daftarBarang){
        if(!this.hashDaftarBarangSemuaBarang.containsKey(daftarBarang.get(0).idBarang)){
            this.hashDaftarBarangSemuaBarang.put(daftarBarang.get(0).idBarang, daftarBarang);
        }else{
            this.hashDaftarBarangSemuaBarang.get(daftarBarang.get(0).idBarang).addAll(daftarBarang);
        }
    }

    private void sinkronisasiSemuaBarangKeSenjata(ArrayList<BarangSenjata> daftarSenjata){
        if(!(daftarSenjata.get(0) instanceof  BarangSenjataTembak) && !(daftarSenjata.get(0) instanceof  BarangSenjataJarakDekat)){
            /* dengan asumsi BarangSenjata merupakan senjata yang dilemparkan,
             *  dan mengambil penyimpanan barang untuk 1 slot secara bertumpuk
             *  misal 1 slot bisa di-isi 10 shuriken */
            if(daftarSenjata.size() > this.getBatasMaxClassBarangSenjataPerList()){
                /* ulangi selama daftarSenjata tidak kosong */
                while (!daftarSenjata.isEmpty()){
                    ArrayList<BarangSenjata> temp = new ArrayList<>();
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
                this.arrDaftarBarangSenjata.add(temp);
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

    public void tambahBarang(Barang oBarang, int jumlahInstance){
        ArrayList<Barang> daftarBarang = Cloning.cloning(oBarang, jumlahInstance);
        this.prosesTambahBarang(daftarBarang);

        /* agar dua list memiliki objectSenjata yang sama */
        if(oBarang instanceof BarangSenjata){
            this.sinkronisasiSemuaBarangKeSenjata(this.convertBarangKeSenjata(daftarBarang));
        }
    }

    public void tambahBarang(ArrayList<Barang> daftarBarang){
        this.prosesTambahBarang(daftarBarang);

        /* agar dua list memiliki objectSenjata yang sama */
        if(daftarBarang.get(0) instanceof BarangSenjata){
            this.sinkronisasiSemuaBarangKeSenjata(this.convertBarangKeSenjata(daftarBarang));
        }
    }

    public HashMap<Integer, ArrayList<Barang>> getDaftarBarang() {
        return this.hashDaftarBarangSemuaBarang;
    }

    public ArrayList<ArrayList<BarangSenjata>> getDaftarBarangSenjata() {
        return this.arrDaftarBarangSenjata;
    }

    public int getJumlahBarang(){
        return this.hashDaftarBarangSemuaBarang.size();
    }

    public boolean isEmpty(){
        return this.hashDaftarBarangSemuaBarang.isEmpty();
    }


}
