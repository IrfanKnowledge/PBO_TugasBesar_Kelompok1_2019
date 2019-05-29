public class Barang {

    /* public karena proses eksternal */
    public int idBarang;
    public String nama;

    /* private karena pemberian kategori penyimpanan sudah disediakan */
    private String kategoriPenyimpanan;

    /* public karena proses eksternal */
    public String deskripsi;
    public boolean statusJual;
    public boolean statusBeli;

    /* private karena pemberian minimal nilai dibatasi */
    private int hargaJual;;
    private int hargaBeli;

    Barang(int idBarang, String nama, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual){
        this.idBarang = idBarang;
        this.nama = nama;
        this.kategoriPenyimpanan = "belum ditentukan";
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = this.filterHarga(hargaBeli);
        this.hargaJual = this.filterHarga(hargaJual);
    }

    //constructor untuk mengcloning Barang
    Barang(Barang cloning){
        this.idBarang = cloning.idBarang;
        this.nama = cloning.nama;
        this.kategoriPenyimpanan = cloning.kategoriPenyimpanan;
        this.deskripsi = cloning.deskripsi;
        this.statusJual = cloning.statusJual;
        this.statusBeli = cloning.statusBeli;
        this.hargaJual = cloning.hargaJual;
        this.hargaBeli = cloning.hargaBeli;
    }

    public static void main(String[] args) {
        Barang barangA = new Barang(1, "Medikit", "Untuk menyembuhkan luka", true, true, 5000, 2500);
    }

    /* Cloning barang dan turunannya */
    public Barang cloning(){
        return new Barang(this);
    }

    /* private karena hanya digunakan pada proses input harga jual dan harga beli, agar harga "tidak negatif" */
    private int filterHarga(int harga){
        if(harga <= 0){
            return 0;
        }
        return harga;
    }

    public void setHargaJual(int hargaJual) {
        this.hargaJual = this.filterHarga(hargaJual);
    }

    public void setHargaBeli(int hargaBeli) {
        this.hargaBeli = this.filterHarga(hargaBeli);
    }

    public int getHargaJual() {
        return hargaJual;
    }

    public int getHargaBeli() {
        return hargaBeli;
    }

    public void setKategoriPenyimpananKunci() {
        this.kategoriPenyimpanan = "kunci";
    }

    public void setKategoriPenyimpananSenjata() {
        this.kategoriPenyimpanan = "senjata";
    }

    public void setKategoriPenyimpananAmunisi() {
        this.kategoriPenyimpanan = "amunisi";
    }

    public void setKategoriPenyimpananPenggunaanPadaDiri() {
        this.kategoriPenyimpanan = "penggunaan pada diri";
    }

    public void setKategoriPenyimpananBlueprint() {
        this.kategoriPenyimpanan = "blueprint";
    }

    public void setKategoriPenyimpananKomponenCrafting() {
        this.kategoriPenyimpanan = "komponen crafting";
    }

    public void setKategoriPenyimpananBarangBernilaiJual() {
        this.kategoriPenyimpanan = "barang bernilai jual";
    }

}
