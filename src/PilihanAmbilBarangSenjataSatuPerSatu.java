import java.util.ArrayList;

public class PilihanAmbilBarangSenjataSatuPerSatu extends Pilihan {
    private Adegan oAdegan;

    PilihanAmbilBarangSenjataSatuPerSatu(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        ArrayList<BarangSenjata> oBarangSenjata = this.oAdegan.pilihBarangSenjataSekitarAdegan();
        if(oBarangSenjata != null){
            this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarangSenjata(oBarangSenjata);
        }
    }

    @Override
    public String getDekripsi() {
        return super.getDekripsi();
    }
}
