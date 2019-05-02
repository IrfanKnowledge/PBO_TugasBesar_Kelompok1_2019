import java.util.ArrayList;
import java.util.HashMap;

public class PilihanLihatBarangSekitar extends Pilihan {

    private Adegan oAdegan;

    PilihanLihatBarangSekitar(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        HashMap<String, ArrayList<ArrayList<Barang>>> oKategoriBarang = this.oAdegan.lihatBarangSekitar();
        if(oKategoriBarang != null){
            for (ArrayList<ArrayList<Barang>> : oKategoriBarang.get("Senjata")) {

            }
            this.oAdegan.oPlayer.tambahBarang(oKategoriBarang);
        }

    }
}
