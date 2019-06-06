//import java.util.ArrayList;
//import java.util.HashMap;

public class PilihanAmbilSemuaBarang extends Pilihan {
    private Adegan oAdegan;

    PilihanAmbilSemuaBarang(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        /* Mengambil semua barang dengan proses seleksi yang melibatkan parameter/inputan jumlahSlotSenjataKosong milik Player */
//        HashMap<String, HashMap<Integer, ArrayList<Barang>>> oBarangPilihan = this.oAdegan.ambilSemuaBarang();
//
//        /* Jika hasil ambil semua barang tidak kosong maka.. */
//        if(oBarangPilihan != null){
//
//            /* tambahkan barang tersebut ke Player */
//            this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarang(oBarangPilihan);
//        }
    }
}
