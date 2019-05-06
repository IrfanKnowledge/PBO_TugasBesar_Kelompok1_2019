import java.util.ArrayList;
import java.util.HashMap;

public class PilihanAmbilSemuaBarang extends Pilihan {
    private Adegan oAdegan;

    PilihanAmbilSemuaBarang(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        /* Mengambil semua barang dengan proses seleksi yang melibatkan parameter/inputan jumlahSlotSenjataKosong milik Player */
        HashMap<String, HashMap<Integer, ArrayList<Barang>>> oBarangPilihan = this.oAdegan.ambilSemuaBarang(this.oAdegan.oPlayer.getJumlahSlotSenjataKosong());

        /* Jika hasil ambil semua barang tidak kosong maka.. */
        if(oBarangPilihan != null){

            /* tambahkan barang tersebut ke Player */
            this.oAdegan.oPlayer.tambahBarang(oBarangPilihan);

            /* proses perubahan waktu, input 0 krn tindakan ini tidak memberikan nilai tambahan apapun,
             *  namun jika ingin dibuat memberikan nilai penambah, tambahkan saja.
             */
            this.oAdegan.oPlayer.ubahWaktu(0);
        }
    }
}
