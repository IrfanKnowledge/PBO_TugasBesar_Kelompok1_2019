public abstract class Pilihan {
    public String dekripsi;

    Pilihan(String dekripsi){
        this.dekripsi = dekripsi;
    }

    public void aksi(){}

    public String getDekripsi() {
        return dekripsi;
    }
}