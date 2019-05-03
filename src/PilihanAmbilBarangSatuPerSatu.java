public class PilihanAmbilBarangSatuPerSatu extends Pilihan {
    private Adegan oAdegan;

    PilihanAmbilBarangSatuPerSatu(String dekripsi) {
        super(dekripsi);
    }

    @Override
    public void aksi() {
        System.out.println("hello");
    }

    @Override
    public String getDekripsi() {
        return super.getDekripsi();
    }
}
