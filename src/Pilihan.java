public abstract class Pilihan {
    private String dekripsi;

    Pilihan(String dekripsi){
        this.dekripsi = dekripsi;
    }

    public void aksi(){}
    public void aksi(Barang oBarang){}

    public String getDekripsi() {
        return dekripsi;
    }
}