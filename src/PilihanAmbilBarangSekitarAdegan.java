import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihanAmbilBarangSekitarAdegan extends Pilihan {
    private AdeganNormal oAdegan;
    private int indeksBarangPilihan = -1;
    private ArrayList<Barang> barangPilihan = new ArrayList<>();
    private boolean barangBerhasilDitambahkanSemua;

    PilihanAmbilBarangSekitarAdegan(String dekripsi, AdeganNormal oAdegan, HashMap<Integer, ArrayList<Barang>> barangPilihan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        if(barangPilihan != null){
            for (Map.Entry<Integer, ArrayList<Barang>> temp : barangPilihan.entrySet()) {
                this.indeksBarangPilihan = temp.getKey();
                this.barangPilihan.addAll(temp.getValue());
            }
        }
    }

    @Override
    public void aksi() {
        this.barangBerhasilDitambahkanSemua = false;
        if(!barangPilihan.isEmpty()){
            this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarang(barangPilihan);
            System.out.println();
            if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus().isEmpty()){
                if(this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus().size() < barangPilihan.size()){
                    System.out.println("[ Barang berhasil terambil sebagian, karena tidak cukup. ]");
                }else{
                    System.out.println("[ Barang berhasil terambil ]");
                    this.barangBerhasilDitambahkanSemua = true;
                }
                this.oAdegan.hapusDaftarBarangTertentu(indeksBarangPilihan, this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus());
            }else{
                System.out.println("[ Penyimpanan Utama Penuh ]");
            }
        }
    }

    public boolean isBarangBerhasilDitambahkanSemua() {
        return barangBerhasilDitambahkanSemua;
    }
}
