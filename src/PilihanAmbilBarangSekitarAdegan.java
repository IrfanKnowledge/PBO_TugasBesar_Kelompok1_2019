import java.util.ArrayList;

public class PilihanAmbilBarangSekitarAdegan extends Pilihan {
    private Adegan oAdegan;
    private ArrayList<Barang> barangPilihan;

    PilihanAmbilBarangSekitarAdegan(String dekripsi, Adegan oAdegan, ArrayList<Barang> barangPilihan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.barangPilihan = barangPilihan;
    }

    @Override
    public void aksi() {
        if(barangPilihan != null){
            this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarang(barangPilihan);
            if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus().isEmpty()){
                this.oAdegan.hapusDaftarBarangTertentu(barangPilihan, this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus());
            }else{
                System.out.println();
                System.out.println("[ Penyimpanan Utama Penuh ]");
            }
        }
    }
}
