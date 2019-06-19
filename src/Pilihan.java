public abstract class Pilihan {
    private String dekripsi;

    Pilihan(String dekripsi){
        this.dekripsi = dekripsi;
    }

    public void aksi(){}

    public String getDekripsi() {
        return dekripsi;
    }
}