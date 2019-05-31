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
        Barang oBarang = new Barang(1,"kunci", "kunci", "memilki permata berwarna merah",
                true, false, 5, 0);
        BarangPenggunaanPadaDiri oBarangPenggunaanPadaDiri = new BarangPenggunaanPadaDiri(2, "Medikit", "penggunaan pada diri",
                "Untuk menyembuhkan luka", true, false, 5000, 2500, 100);
        BarangSenjata oSenjata = new BarangSenjata(3, "shuriken", "senjata", "berukuran sedang berbentuk seperti bintang tajam",
                true, false, 5000, 10000, 10);
        BarangSenjataJarakDekat oSenjataJarakDekat = new BarangSenjataJarakDekat(4, "pipa tua", "senjata", "pipa tua berkarat berukurang sedang",
                true, false, 5000, 15000, 35, 20, 20, 3, 0, 1);
        BarangSenjataTembak oSenjataTembak = new BarangSenjataTembak(5, "Desert Eagle", "senjata", "pistol sedang dengan kekuatan cukup kuat daripada pistol biasa",
                true, false, 25000, 100000, 100, 12);
        BarangBluePrintPenggunaanPadaDiri oBlueprintMedikit = new BarangBluePrintPenggunaanPadaDiri(6, "Blueprint Medikit", "blueprint", "blueprint untuk membuat medikit",
                true, false, 5000, 10000, 3);
        BarangBluePrintSenjataBaru oBlueprintBuzzerKiller = new BarangBluePrintSenjataBaru(7, "Buzzer Killer", "blueprint","Untuk membuat senjata Buzzer Killer, sebuah senjata mematikan tajam",
                true, false, 20000, 2500, 1);
        BarangBluePrintSenjataUpgrade oBlueprintSenjataTajam = new BarangBluePrintSenjataUpgrade(8, "Fire burner", "blueprint","Untuk meng-upgrade senjata tajam menjadi ber-efek api",
                true, false, 10000, 30000, 100, 0);

        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oBarangPenggunaanPadaDiri;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oSenjata;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oSenjataJarakDekat;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oSenjataTembak;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oBlueprintMedikit;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oBlueprintBuzzerKiller;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oBlueprintSenjataTajam;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
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
