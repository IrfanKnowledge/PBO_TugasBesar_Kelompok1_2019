import java.util.ArrayList;

public class PilihanAmbilBarangSekitarAdegan extends Pilihan {
    private Adegan oAdegan;

    PilihanAmbilBarangSekitarAdegan(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        boolean validasiKembaliKeDaftarBarangAdegan = false;
        while(!validasiKembaliKeDaftarBarangAdegan){
            ArrayList<Barang> barangPilihan = this.oAdegan.pilihBarangSekitarAdegan(this.dekripsi);
            if(barangPilihan != null){
                this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarang(barangPilihan);
                if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus().isEmpty()){
                    this.oAdegan.hapusDaftarBarangTertentu(barangPilihan, this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus());
                }else{
                    System.out.println();
                    System.out.println("[ Penyimpanan Utama Penuh ]");
                }
            }else{
                validasiKembaliKeDaftarBarangAdegan = true;
            }
        }
    }

    @Override
    public String getDekripsi() {
        return super.getDekripsi();
    }
}
