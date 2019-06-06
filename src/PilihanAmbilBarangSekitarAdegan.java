import java.util.ArrayList;

public class PilihanAmbilBarangSekitarAdegan extends Pilihan {
    private Adegan oAdegan;

    PilihanAmbilBarangSekitarAdegan(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        ArrayList<Barang> barangPilihan = this.oAdegan.pilihBarangSekitarAdegan();
        if(barangPilihan != null){
            this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarang(barangPilihan);
            if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus().isEmpty()){
                this.oAdegan.getPengelolaanBarang().hapusBarang(this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus());
            }
        }
    }

    @Override
    public String getDekripsi() {
        return super.getDekripsi();
    }
}
