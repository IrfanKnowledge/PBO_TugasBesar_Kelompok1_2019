public class Barang {

    /* public karena tidak memerlukan proses internal */
    public int idBarang;
    public String nama;
    public String kategoriBarang;
    public String deskripsi;

    /* status ini berguna untuk membedakan mana barang yang dapat bernilai 0 namun bisa diberikan gratis oleh penjual
    *  atau barang yang tidak bisa dibeli dari penjual tapi bisa dijual, atau sebaliknya (bisa dibeli, tidak bisa dijual)*/
    public boolean statusJual;
    public boolean statusBeli;

    /* private karena pemberian minimal nilai dibatasi */
    private int hargaJual;;
    private int hargaBeli;

    Barang(int idBarang, String nama, String kategoriBarang,String deskripsi,
           boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli){
        this.idBarang = idBarang;
        this.nama = nama;
        this.kategoriBarang = kategoriBarang;
        this.kategoriBarang = "belum ditentukan";
        this.deskripsi = deskripsi;
        this.statusJual = statusJual;
        this.statusBeli = statusBeli;
        this.setHargaJual(hargaJual);
        this.setHargaBeli(hargaBeli);
    }

    public static void main(String[] args) {
        BarangPenggunaanPadaDiri barangA = new BarangPenggunaanPadaDiri(1, "Medikit", "penggunaan pada diri","Untuk menyembuhkan luka", true, true, 5000, 2500, 100);
    }

    /* Cloning barang dan turunannya (polymorphism) */
    public Barang cloning(){
        return new Barang(this.idBarang, this.nama, this.kategoriBarang, this.deskripsi, this.statusJual, this.statusBeli, this.getHargaJual(), this.getHargaBeli());
    }

    /* private karena hanya digunakan pada proses input harga jual dan harga beli, agar harga "tidak negatif" */
    private int filterHarga(int harga){
        if(harga < 0){
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
}
