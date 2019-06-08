public class GameEngine {
    private Player oPlayer;
//    private Adegan daftarAdegan;

    GameEngine(){
        this.oPlayer = new Player(1, "Abdi Mukti");
        Adegan.oPlayer = this.oPlayer;
    }

    private void mulaiGame(){
//        System.out.println("Pada suatu hari..\n");
//        System.out.printf("%-10s\n\n", "[Tekan Huruf Apapun kemudian Enter untuk melanjutkan]");
//        Scanner oScan = new Scanner(System.in);
//        oScan.nextLine();
//        System.out.println();
//        System.out.println("Kemudian " + this.oPlayer.nama + "harus melakukan sebuah misi untuk...\n");
//        System.out.printf("%-10s\n\n", "[Tekan Huruf Apapun kemudian Enter untuk melanjutkan]");
//        oScan.nextLine();
//        System.out.println();
//        System.out.println("Selamat bermain");
        while (!this.oPlayer.isSelesai()){
            this.oPlayer.adeganAktif.mainkan();
        }
    }

    public static void main(String[] args){
        GameEngine oGameEgnine = new GameEngine();
        Barang kunci = new Barang(29, "Old Key", "kunci", "Kunci usang",false, false, false, 0, 0);
        Barang metalParts = new Barang(2, "Metal Parts", "komponen crafting", "Bisa digunakan untuk memperbaiki senjata", false, false, true, 0, 1000);
        BarangPenggunaanPadaDiri medikit = new BarangPenggunaanPadaDiri(1, "Medikit", "penggunaan pada diri", "Untuk menyembuhkan luka", false, false, true, 0, 10000, 100);
        BarangPenggunaanPadaDiri resistanceBooster = new BarangPenggunaanPadaDiri(20, "Resistancce Booster", "penggunaan pada diri", "untuk membuat fisik menjadi lebih tahan terhadap serangan", false, false, false, 0, 0, 0);
        resistanceBooster.tambahEfek(new Efek(1, "Ketahanan Fisik", 0, 0, 0, 0, 0, 0, 0, 50, 10));
        BarangSenjata granade = new BarangSenjata(32, "Granade", "senjata", "saat dilemparkan akan meledak", false, true, true, 250000, 500000, 100);
        granade.tambahEfek(new Efek(2, "Ledakan", 0, 0,0, 0, 3, 100, 1, 0, 0));
        BarangSenjataTembak bow = new BarangSenjataTembak(12, "Bow", "senjata", "busur yang memanah dengan kekuatan tinggi", false, true, true, 500000, 1500000, 220, 1);
        BarangSenjata regularArrow = new BarangSenjata(11, "Regular Arrow", "amunisi", "anak panah biasa yang tidak memiliki efek apapun", false, false, true, 0, 50000, 30);
        BarangSenjata increniaryArrow = new BarangSenjata(10, "Incendiary Arrow", "amunisi", "anak panah yang memiliki efek api", false, false, true, 0, 60000, 30);
        increniaryArrow.tambahEfek(new Efek(3, "Api", 50, 10, 100, 0, 0, 0, 0, 0, 0));
        bow.tambahAmunisiYangDiperlukan(regularArrow);
        bow.tambahAmunisiYangDiperlukan(increniaryArrow);
        bow.setAmunisiUtamaYangBisaDigunakan(regularArrow);
        BarangSenjataJarakDekat pipaTua = new BarangSenjataJarakDekat(4, "pipa tua", "senjata", "pipa tua berkarat berukurang sedang",
                false, true, false, 5000, 15000, 35, 20, 5, 3, 0, 1);

        /* contoh adegan */
        Adegan adeganId1 = new Adegan(1, 0, "Tengah Ruangan", "Kamar 03", "","Gedung Tua Asing", "Akhirnya sampai di ruangan yang sepertinya terlihat aman");
        Adegan adeganId2 = new Adegan(2, 28, "Depan Pintu", "Kamar 03", "","Gedung Tua Asing", "Berjalan menuju pintu kuning.. Hmm Pintu ini terkunci");

        BarangSenjataJarakDekat.setKomponenUntukPerbaikan(kunci);
        adeganId1.tambahBarang(kunci, 1);
        adeganId1.tambahBarang(metalParts, 2);
        adeganId1.tambahBarang(medikit, 1);
        adeganId1.tambahBarang(resistanceBooster, 1);
        adeganId1.tambahBarang(granade, 1);
        adeganId1.tambahBarang(regularArrow, 2);
        adeganId1.tambahBarang(increniaryArrow, 1);
        adeganId1.tambahBarang(bow, 1);
//        adeganId1.tambahBarang(pipaTua, 2);
        adeganId1.tambahBarang(regularArrow, 2);
        oGameEgnine.oPlayer.getPengelolaanBarang().setBatasMaxClassBarangSenjataIdTertentu(11, 5);
        oGameEgnine.oPlayer.adeganAktif = adeganId1;
        oGameEgnine.mulaiGame();
    }
}
