import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihanTukarSlotBarangTerbatas extends Pilihan {
    private Adegan oAdegan;
    private int indeksBarangPertama;


    PilihanTukarSlotBarangTerbatas(String dekripsi, Adegan oAdegan, int indeksBarangPertama) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.indeksBarangPertama = indeksBarangPertama;
    }

    @Override
    public void aksi() {
        HashMap<Integer,ArrayList<Barang>> hashDaftarBarangTertentu = this.oAdegan.oPlayer.pilihBarangDariDaftarBarangTerbatas(this.dekripsi);
        if(hashDaftarBarangTertentu != null){
            for (Map.Entry<Integer, ArrayList<Barang>> daftarBarangTertentu : hashDaftarBarangTertentu.entrySet()) {
                this.oAdegan.oPlayer.getPengelolaanBarang().tukarSlotBarangPadaDaftarBarangTerbatas(indeksBarangPertama, daftarBarangTertentu.getKey());
            }
            System.out.println();
            System.out.println("[ Menukar barang berhasil ]");
        }
    }
}
