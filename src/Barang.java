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

    Barang(int idBarang, String nama, String kategoriBarang,String deskripsi, boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli){
        this.idBarang = idBarang;
        this.nama = nama;
        this.kategoriBarang = kategoriBarang;
        this.kategoriBarang = "belum ditentukan";
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = this.filterHarga(hargaBeli);
        this.hargaJual = this.filterHarga(hargaJual);
    }

    public static void main(String[] args) {
        BarangPenggunaanPadaDiri barangA = new BarangPenggunaanPadaDiri(1, "Medikit", "penggunaan pada diri","Untuk menyembuhkan luka", true, true, 5000, 2500, 100);
    }

    /* private karena hanya digunakan method cloning saja */
    private Barang prosesCloning(Barang oBarang){
        return new Barang(oBarang.idBarang, oBarang.nama, oBarang.kategoriBarang ,oBarang.deskripsi, oBarang.statusJual, oBarang.statusBeli, oBarang.getHargaJual(), oBarang.getHargaBeli());
    }

    /* Cloning barang dan turunannya */
    public Barang cloning(){
        return prosesCloning(this);
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
}
