public abstract class Pilihan {
    private String dekripsi;

    Pilihan(String dekripsi){
        this.dekripsi = dekripsi;
    }

    public abstract void aksi();
}