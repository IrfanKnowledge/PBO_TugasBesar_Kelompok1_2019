import java.util.ArrayList;

public class PilihanLihatBarangSekitar extends Pilihan {

    private Adegan oAdegan;

    PilihanLihatBarangSekitar(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        ArrayList<ArrayList<Barang>> daftarBarang = this.oAdegan.lihatBarangSekitar();
        if(daftarBarang != null){
            this.oAdegan.oPlayer.tambahBarang(daftarBarang);
        }

    }
}
