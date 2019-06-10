public class Barang {

    /* public karena tidak memerlukan proses internal */
    public final int idBarang;
    public String nama;
    public String kategoriBarang;
    public String deskripsi;

    public boolean statusDapatDigunakanAdeganTertentu;

    /* status ini berguna untuk membedakan mana barang yang dapat bernilai 0 namun bisa diberikan gratis oleh penjual
    *  atau barang yang tidak bisa dibeli dari penjual tapi bisa dijual, atau sebaliknya (bisa dibeli, tidak bisa dijual)*/
    public final boolean statusJual;
    public final boolean statusBeli;

    /* private karena pemberian minimal nilai dibatasi */
    private int hargaJual;;
    private int hargaBeli;

    Barang(int idBarang, String nama, String kategoriBarang,String deskripsi,
           boolean statusDapatDigunakanAdeganTertentu,
           boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli){
        this.idBarang = idBarang;
        this.nama = nama;
        this.kategoriBarang = kategoriBarang;
        this.deskripsi = deskripsi;
        this.statusDapatDigunakanAdeganTertentu = statusDapatDigunakanAdeganTertentu;
        this.statusJual = statusJual;
        this.statusBeli = statusBeli;
        this.setHargaJual(hargaJual);
        this.setHargaBeli(hargaBeli);
    }

    public static void main(String[] args) {
        Barang oBarang = new Barang(1,"kunci", "kunci", "memilki permata berwarna merah",
                true, true, false, 5, 0);
        BarangPenggunaanPadaDiri oBarangPenggunaanPadaDiri = new BarangPenggunaanPadaDiri(2, "Medikit", "penggunaan pada diri",
                "Untuk menyembuhkan luka", false, true, false, 5000, 2500, 100);
        BarangSenjata oSenjata = new BarangSenjata(3, "shuriken", "senjata", "berukuran sedang berbentuk seperti bintang tajam",
                false, true, false, 5000, 10000, 10);
        BarangSenjataJarakDekat oSenjataJarakDekat = new BarangSenjataJarakDekat(4, "pipa tua", "senjata", "pipa tua berkarat berukurang sedang",
                false, true, false, 5000, 15000, 35, 20, 20, 3, 0, 1);
        BarangSenjataTembak oSenjataTembak = new BarangSenjataTembak(5, "Desert Eagle", "senjata", "pistol sedang dengan kekuatan cukup kuat daripada pistol biasa",
                false, true, false, 25000, 100000, 100, 12);
        BarangBlueprintPenggunaanPadaDiri oBlueprintMedikit = new BarangBlueprintPenggunaanPadaDiri(6, "Blueprint Medikit", "blueprint", "blueprint untuk membuat medikit",
                false, true, false, 5000, 10000, 3);
        BarangBlueprintSenjataBaru oBlueprintBuzzerKiller = new BarangBlueprintSenjataBaru(7, "Buzzer Killer", "blueprint","Untuk membuat senjata Buzzer Killer, sebuah senjata mematikan tajam",
                false, true, false, 20000, 2500, 1);
        BarangBlueprintSenjataUpgrade oBlueprintSenjataTajam = new BarangBlueprintSenjataUpgrade(8, "Fire burner", "blueprint","Untuk meng-upgrade senjata tajam menjadi ber-efek api",
                false, true, false, 10000, 30000, 100, 0);

        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oBarangPenggunaanPadaDiri;
        ((BarangPenggunaanPadaDiri) oBarang).tambahEfek(new Efek(1, "hantaman godam", 0, 0, 0, 0, 2, 10, 0, 50, 10));
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oSenjata;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oSenjataJarakDekat;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oSenjataTembak;
        System.out.printf("%s %d %d %s %s\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli);
        oBarang = oBlueprintMedikit;
        System.out.printf("%s %d %d %s %s | %d\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli, ((BarangBlueprintPenggunaanPadaDiri) oBarang).getJumlahHasilCrafting());
        //oBarang = oBlueprintBuzzerKiller;
        //System.out.printf("%s %d %d %s %s | %d\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli, ((BarangBluePrintSenjataBaru) oBarang).getJumlahHasilCrafting());
        oBarang = oBlueprintSenjataTajam;
        System.out.printf("%s %d %d %s %s | %d\n", oBarang.nama, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.statusJual, oBarang.statusBeli, ((BarangBlueprintSenjataUpgrade) oBarang).getJumlahHasilCrafting() );
    }

    /* Cloning barang dan turunannya (polymorphism) */
    public Barang cloning(){
        return new Barang(this.idBarang, this.nama, this.kategoriBarang, this.deskripsi, this.statusDapatDigunakanAdeganTertentu, this.statusJual, this.statusBeli, this.getHargaJual(), this.getHargaBeli());
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

    public void print(){
        System.out.println();
        System.out.println("Aksi : " + this.deskripsi + " (Rincian Barang)");
        System.out.println();
        System.out.printf("%-25s : %s\n", "nama", this.nama);
        System.out.printf("%-25s : %s\n", "Deskripsi", this.deskripsi);
        if(!this.statusBeli){
            System.out.printf("%-25s : -\n", "Harga beli");
        }else{
            System.out.printf("%-25s : %s\n", "Harga beli", this.getHargaBeli());
        }
        if(!this.statusJual){
            System.out.printf("%-25s : -\n", "Harga jual");
        }else{
            System.out.printf("%-25s : %s\n", "Harga jual", this.getHargaJual());
        }
    }
}
