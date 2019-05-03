public class PilihanIsiPeluru extends Pilihan {

    PilihanIsiPeluru(String dekripsi) {
        super(dekripsi);
    }

    @Override
    public void aksi() {

    }

    @Override
    public void aksi(Barang oBarang) {
        Adegan.oPlayer.pilihBarangSenjata();
    }

    @Override
    public String getDekripsi() {
        return super.getDekripsi();
    }
}
