import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihanAmbilBarangSekitarAdegan extends Pilihan {
    private Adegan oAdegan;
    private ArrayList<Barang> barangPilihan = new ArrayList<>();
    private int indeksBarangPilihan = -1;

    PilihanAmbilBarangSekitarAdegan(String dekripsi, Adegan oAdegan, HashMap<Integer, ArrayList<Barang>> barangPilihan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        if(barangPilihan != null){
            for (Map.Entry<Integer, ArrayList<Barang>> temp : barangPilihan.entrySet()) {
                this.barangPilihan.addAll(temp.getValue());
                this.indeksBarangPilihan = temp.getKey();
            }
        }
    }

    @Override
    public void aksi() {
        if(!barangPilihan.isEmpty()){
            this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarang(barangPilihan);
            System.out.println();
            if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus().isEmpty()){
                if(this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus().size() < barangPilihan.size()){
                    System.out.println("[ Barang berhasil terambil sebagian, karena tidak cukup. ]");
                }else{
                    System.out.println("[ Barang berhasil terambil ]");
                }
                this.oAdegan.hapusDaftarBarangTertentu(indeksBarangPilihan, this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus());
            }else{
                System.out.println("[ Penyimpanan Utama Penuh ]");
            }
        }
    }
}
