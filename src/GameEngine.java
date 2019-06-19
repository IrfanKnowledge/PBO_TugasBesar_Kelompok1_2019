import java.util.Scanner;

public class GameEngine extends Thread {
    static Player oPlayer;
    private Barang kotakSuara = new Barang(100, "Kotak Suara", "barang bernilai", "kotak berisikan surat suara", false, false, false, 0, 0);
//    private Adegan daftarAdegan;

    GameEngine(){
        this.oPlayer = new Player(1, "Abdi Mukti", this.kotakSuara.cloning());
        Adegan.oPlayer = this.oPlayer;
    }

    @Override
    public void run() {
        System.out.println("Hello World");
    }

    private void mulaiGame(){
        System.out.println("Pada suatu hari saat pemilihan Presiden dilakukan, terjadi sebuah kerusuhan sehingga kotak suara hilang\n");
        System.out.printf("%-10s\n\n", "[Tekan Huruf Apapun kemudian Enter untuk melanjutkan]");
        Scanner oScan = new Scanner(System.in);
        oScan.nextLine();
        System.out.println();
        System.out.println("Maka dari itu " + this.oPlayer.nama + "harus melakukan sebuah misi untuk menemukan kotak suara tersebut untuk dikembalikan\n");
        System.out.printf("%-10s\n\n", "[Tekan Huruf Apapun kemudian Enter untuk melanjutkan]");
        oScan.nextLine();
        System.out.println();
        System.out.println("Selamat bermain");
        CobaSwing test = new CobaSwing();
//        test.start();
        while (!this.oPlayer.isSelesai){
//            System.out.println(test.isAlive());
            this.oPlayer.adeganAktif.mainkan();
        }
        if(this.oPlayer.getKesehatan() <= 0){
            System.out.println();
            System.out.println("[ Game Over ]");
        }else{
            System.out.println();

            System.out.println("[ The End ]");
        }
        if(this.oPlayer.isKotakSuaraTertemukan()){
            System.out.println();
            System.out.println("[ Selamat Kotak Suara Sudah Tertemukan!!!!!!]");
            System.out.println("[ Created By: ]");
            System.out.println("[ IRFAN M FAISAL ]");
            System.out.println("[ YOLA NANDA S P ]");
            System.out.println("[ RIVALDO ]");
        }
    }

    public static void main(String[] args){
        GameEngine oGameEgnine = new GameEngine();
        GUI.oPlayer=oPlayer;
        Thread1 t1= new Thread1();
        Thread1.oPlayer=oPlayer;
        t1.start();
        Barang jewelBox = new Barang(24, "Jewel Box", "barang bernilai", "kotak berisikan permata", false, true, false, 50000, 0);
        Barang oldKey = new Barang(29, "Old Key", "kunci", "Kunci usang",true, false, false, 0, 0);
        Barang metalParts = new Barang(2, "Metal Parts", "komponen crafting", "Bisa digunakan untuk memperbaiki senjata", false, false, true, 0, 1000);
        BarangPenggunaanPadaDiri medikit = new BarangPenggunaanPadaDiri(1, "Medikit", "penggunaan pada diri", "Untuk menyembuhkan luka", false, false, true, 0, 10000, 100);
        BarangPenggunaanPadaDiri resistanceBooster = new BarangPenggunaanPadaDiri(20, "Resistancce Booster", "penggunaan pada diri", "untuk membuat fisik menjadi lebih tahan terhadap serangan", false, false, false, 0, 0, 0);
        resistanceBooster.tambahEfek(new Efek(1, "Ketahanan Fisik", 0, 0, 0, 0, 0, 0, 0, 50, 10));
        BarangSenjata granade = new BarangSenjata(32, "Granade", "senjata", "saat dilemparkan akan meledak", false, true, true, true, 250000, 500000, 10);
        granade.tambahEfek(new Efek(2, "Ledakan", 0, 0,0, 0, 3, 100, 1, 0, 0));
        BarangSenjata regularArrow = new BarangSenjata(11, "Regular Arrow", "amunisi", "anak panah biasa yang tidak memiliki efek apapun", false, false, false, true, 0, 50000, 30);
        BarangSenjata increniaryArrow = new BarangSenjata(10, "Incendiary Arrow", "amunisi", "anak panah yang memiliki efek api", false, false, false, true, 0, 60000, 30);
        BarangSenjata amunisiPistol = new BarangSenjata(17, "Amunisi Pistol", "Peluru", "Peluru denngan kekuatan kecil", false, false, false, true, 0, 60000, 20);
        BarangSenjata amunisiSnipper = new BarangSenjata(18, "Amunisi Snipper Rifle", "Peluru", "Pelkamaruru denngan kekuatan kecil", false, false, false, true, 0, 60000, 10);
        BarangSenjata amunisiSubmachineGun = new BarangSenjata(19, "amunisiSubmachineGun", "Peluru", "Peluru denngan kekuatan kecil", false, false, false, true, 0, 60000, 40);
        BarangSenjata amunisiLightMachineGun = new BarangSenjata(20, "amunisiLightMachineGun", "Peluru", "Peluru denngan kekuatan kecil", false, false, false, true, 0, 60000, 50);

        //yola

        BarangSenjataTembak bow = new BarangSenjataTembak(12, "Bow", "senjata", "busur yang memanah dengan kekuatan tinggi", false, true, true, 500000, 1500000, 220, 1, increniaryArrow);
        BarangSenjataTembak pistol = new BarangSenjataTembak(13, "Pistol", "Senjata", "Ditembakan dengan kekuatan kecil", false, false, false, 200000, 0, 100, 1, amunisiPistol);
        BarangSenjataTembak SniperRifle = new BarangSenjataTembak(14, "Sniper Rifle", "Senjata", "Ditembakan dengan peluru yang lemah", false, false, false, 200000, 0, 150, 2,amunisiSnipper);
        BarangSenjataTembak SubmachineGun = new BarangSenjataTembak(15, "Submachine Gun", "Senjata", "Ditembakan agar membuat lawan kabur", false, false, false, 200000, 0, 190, 2,amunisiSubmachineGun);
        BarangSenjataTembak LightMachineGun = new BarangSenjataTembak(16, "Light Machine Gun", "Senjata", "Ditembakan agar membuat lawan sangat kesakitan", false, false, false, 400000, 0, 200, 1,amunisiLightMachineGun);

        increniaryArrow.tambahEfek(new Efek(3, "Api", 5, 10, 100, 0, 0, 0, 0, 0, 0));
        bow.tambahAmunisiYangDiperlukan(increniaryArrow);
        BarangSenjataJarakDekat pipaTua = new BarangSenjataJarakDekat(4, "pipa tua", "senjata", "pipa tua berkarat berukurang sedang",
                false, true, false, 5000, 15000, 35, 20, 5, 3, 0, 1);

        /* contoh adegan */

        /* Adegan Depan Gedung Tua Asing dan Tengah Tangga Lantai 1 */

        AdeganNormal adeganId1 = new AdeganNormal(1, 1, 1, 0, "Tangga Lantai 1", "Tangga Lantai 1", "","Gedung Tua Asing", "Inikah jalannya ??");
        AdeganBertarung adeganId2 = new AdeganBertarung(2, 2, 1, "Tengah Tengah Lantai 1", "Tangga Lantai 01", "","Gedung Tua Asing", "Berjalan menuju tangga lantai 1.. Tiba-tiba zombie itu menyerang !", adeganId1);
        adeganId1.tambahAdeganBertarung(adeganId2);
        AdeganNormal adeganId26 = new AdeganNormal(25, 1, 1, 0, "Tangga Lantai 1", "Tangga Lantai 1", "","Gedung Tua Asing", "Inikah jalannya ??");

        AdeganNormal adeganId25 = new AdeganNormal(25, 2, 1, 0, "Tengah-tengah lantai 1", "Tengah-tengah lantai 1", "","Gedung Tua Asing", "Akhirnya Zombie itu mati");
        AdeganBertarung adeganId24 = new AdeganBertarung(24, 2, 1, "Tengah Tengah Lantai 1", "Tangga Lantai 01", "","Gedung Tua Asing", "Berjalan menuju Zombie.. Serang!!!", adeganId25);
        adeganId25.tambahAdeganBertarung(adeganId24);
        adeganId25.tambahPilihan(new PilihanGantiAdegan("Menuju tangga", adeganId26));

        AdeganNormal adeganId20 = new AdeganNormal(20, 4, 1, 0, "Lorong Sebelah Kiri", "Lorong Kiri", "","Gedung Tua Asing", "Wait!!, Saya melihat tangga, tapi ditengah sana ada Zombie");
        AdeganNormal adeganId21 = new AdeganNormal(21, 5, 1, 0, "Pojok Lorong Sebelah Kanan", "Pojok Lorong Kanan", "","Gedung Tua Asing", "Ahh disini tidak ada apa-apa");
        AdeganNormal adeganId22 = new AdeganNormal(22, 6, 1, 0, "Depan Pintu", "Depan Pintu", "","Gedung Tua Asing", "Anda Berada di Depan Pintu");
        AdeganNormal adeganId23 = new AdeganNormal(23, 6, 1, 0, "Depan Pintu", "Depan Pintu", "","Gedung Tua Asing", "Saya melihat ada lorong disebelah kanan dan sebelah kiri");
        adeganId23.tambahPilihan(new PilihanGantiAdegan("Menuju Lorong Pojok Kanan", adeganId21));
        adeganId23.tambahPilihan(new PilihanGantiAdegan("Menuju Lorong Pojok Kiri", adeganId20));

        adeganId21.tambahPilihan(new PilihanGantiAdegan("Kembali Kedepan Pintu", adeganId22));
        adeganId20.tambahPilihan(new PilihanGantiAdegan("Kembali Kedepan Pintu", adeganId22));
        adeganId20.tambahPilihan(new PilihanGantiAdegan("Langsung Menuju Tangga di Lantai 1", adeganId1));
        adeganId20.tambahPilihan(new PilihanGantiAdegan("Serang Zombie itu", adeganId25));

        adeganId22.tambahPilihan(new PilihanGantiAdegan("Menuju Lorong Pojok Kanan", adeganId21));
        adeganId22.tambahPilihan(new PilihanGantiAdegan("Menuju Lorong Pojok Kiri", adeganId20));

        AdeganNormal adeganId3 = new AdeganPintu(3, 3, 1, 29, "Depan Pintu Luar Gedung Tua Asing", "", "Halaman Gedung Tua Asing", "Gedung Tua Asing", "Menuju Gedung Tua", new PilihanGantiAdegan("Masuk kedalam", adeganId23));
//        AdeganNormal adeganId3 = new AdeganPintu(3, 3, 1, 29, "Depan Pintu Luar Gedung Tua Asing", "", "Halaman Gedung Tua Asing", "Gedung Tua Asing", "Menuju Gedung Tua", new PilihanGantiAdegan("Menuju tangga di lantai 1", adeganId1));




        /* Adegan Tengah Tangga Lantai 2, tambah pilihan ganti adegan dari Tangga Lantai 1 ke Tangga Lantai 2, adegan menuju pintu kamar 02 + pilihan kembali ke Tengah Tangga Lantai 2, adegan tengah ruangan kamar 02 + pilihan menuju pintu kamar keluar kamar 02*/
        AdeganNormal adeganId5 = new AdeganNormal(5, 7, 2, 0, "Depan Tangga Menuju Lantai 1", "Lantai 02", "","Gedung Tua Asing", "Semangat, aku pasti bisa");
        adeganId1.tambahPilihan(new PilihanGantiAdegan("Menuju tangga di lantai 1 (naik ke lantai 2)", adeganId5));
        adeganId26.tambahPilihan(new PilihanGantiAdegan("Menuju tangga di lantai 1 (naik ke lantai 2)", adeganId5));

        AdeganBertarung adeganId4 = new AdeganBertarung(4, 7, 2, "", "Lantai 02", "","Gedung Tua Asing", "Ketika sudah naik lantai 2.. Tiba-tiba sekumpulan zombie langsung bermunculan dari arah atas !", adeganId5);
        adeganId5.tambahAdeganBertarung(adeganId4);
        AdeganNormal adeganId9 = new AdeganNormal(9, 6, 2, 0, "Tengah Ruangan", "Kamar 02", "", "Gedung Tua Asing", "Anda berada di ruangan Kamar 02");
        AdeganNormal adeganId10 = new AdeganPintu(10, 3, 2, 29, "Depan Pintu Keluar Ruangan", "Kamar 02", "", "Gedung Tua Asing", "Pintu di Kamar 02 di lantai 2", new PilihanGantiAdegan("Menuju kamar 02", adeganId9));
        AdeganNormal adeganId27 = new AdeganNormal(27, 6, 2, 0, "Depan Toilet", "Depan Toilet di Lantai 02", "","Gedung Tua Asing", "Didalam sini tidak ada apa-apa, Aku butuh medikit. Ternyata disini banyak sekali ruanganya");

        adeganId10.tambahPilihan(new PilihanGantiAdegan("Ke Tengah Tangga Lantai 2", adeganId5));
        adeganId9.tambahPilihan(new PilihanGantiAdegan("Menuju pintu Keluar Kamar 02", adeganId10));
        adeganId5.tambahPilihan(new PilihanGantiAdegan("Menuju pintu Masuk Kamar 02", adeganId10));
        adeganId5.tambahPilihan(new PilihanGantiAdegan("Maju kedepan menelusuri lantai", adeganId27));


        AdeganNormal adeganId7 = new AdeganNormal(7, 7, 3, 0, "Tangga Lantai 3", "Lantai 03", "","Gedung Tua Asing", "Anda Berada dilantai 3");
        adeganId5.tambahPilihan(new PilihanGantiAdegan("Menuju tangga di lantai 3 (naik ke lantai 4)", adeganId7));
        AdeganBertarung adeganId6 = new AdeganBertarung(6, 8, 3, "Tangga Lantai 3", "Lantai 03", "","Gedung Tua Asing", "Berjalan menuju tangga lantai 3.. Tiba-tiba sekumpulan zombie bermunculan dari arah atas !", adeganId7);
        adeganId7.tambahAdeganBertarung(adeganId6);
        adeganId7.tambahPilihan(new PilihanGantiAdegan("Menuju tangga di lantai 3 (naik ke lantai 4)", adeganId7));
        AdeganNormal adeganId11 = new AdeganNormal(11, 8, 3, 0, "Tengah Ruangan", "Kamar 03", "", "Gedung Tua Asing", "Anda berada di ruangan Kamar 03");
        AdeganNormal adeganId12 = new AdeganPintu(12, 3, 3, 29, "Depan Pintu Keluar Ruangan", "Kamar 03", "", "Gedung Tua Asing", "Pintu di Kamar 03 di lantai 3", new PilihanGantiAdegan("Menuju kamar 03", adeganId11));
        adeganId11.tambahPilihan(new PilihanGantiAdegan("Ke Tengah Tangga Lantai 3", adeganId7));
        adeganId12.tambahPilihan(new PilihanGantiAdegan("Menuju pintu Keluar Kamar 03", adeganId12));
        adeganId7.tambahPilihan(new PilihanGantiAdegan("Menuju pintu Masuk Kamar 03", adeganId12));

        AdeganNormal adeganId13 = new AdeganNormal(13, 9,4, 0, "Tangga Lantai 4", "Lantai 04", "","Gedung Tua Asing", "Anda Berada dilantai 4");
        //adeganId7.tambahPilihan(new PilihanGantiAdegan("Menuju tangga di lantai 3 (naik ke lantai 4)", adeganId7));
        AdeganBertarung adeganId16 = new AdeganBertarung(16, 10, 4, "Tangga Lantai 4", "Lantai 04", "","Gedung Tua Asing", "Berjalan menuju tangga lantai 3.. Tiba-tiba sekumpulan zombie bermunculan dari arah atas !", adeganId7);
        adeganId13.tambahAdeganBertarung(adeganId16);
        AdeganNormal adeganId14 = new AdeganNormal(14, 11, 4,0, "Tengah Ruangan", "Kamar 04", "", "Gedung Tua Asing", "Anda berada di ruangan Kamar 04");
        AdeganNormal adeganId15 = new AdeganPintu(15, 3, 4, 29, "Depan Pintu Keluar Ruangan", "Kamar 04", "", "Gedung Tua Asing", "Pintu di Kamar 04 di lantai 4", new PilihanGantiAdegan("Menuju kamar 03", adeganId14));
        adeganId14.tambahPilihan(new PilihanGantiAdegan("Ke Tengah Tangga Lantai 4", adeganId13));
        adeganId15.tambahPilihan(new PilihanGantiAdegan("Menuju pintu Keluar Kamar 04", adeganId15));
        adeganId13.tambahPilihan(new PilihanGantiAdegan("Menuju pintu Masuk Kamar 04", adeganId15));

        BarangSenjataJarakDekat.setKomponenUntukPerbaikan(metalParts);

        adeganId3.tambahBarang(oldKey, 1);

        adeganId1.tambahBarang(jewelBox, 2);
        adeganId1.tambahBarang(metalParts, 2);
        adeganId1.tambahBarang(medikit, 1);
        adeganId1.tambahBarang(resistanceBooster, 1);
        adeganId5.tambahBarang(granade, 5);
        adeganId5.tambahBarang(regularArrow, 50);
        adeganId5.tambahBarang(increniaryArrow, 1);
        adeganId5.tambahBarang(regularArrow, 5);
        adeganId7.tambahBarang(bow, 1);
        adeganId7.tambahBarang(pipaTua, 2);
        adeganId7.tambahBarang(regularArrow, 2);
        adeganId7.tambahBarang(pistol, 5 );
        adeganId1.tambahBarang(SniperRifle, 5 );
        adeganId1.tambahBarang(SubmachineGun, 5 );
        adeganId1.tambahBarang(LightMachineGun, 5 );
        adeganId13.tambahBarang(new Barang(100, "Kotak Suara", "barang bernilai", "kotak berisikan surat suara", false, false, false, 0, 0), 1);

        /* contoh lawan */
        Lawan lawanId1 = new Lawan(1, "Zombie biasa", 100, 10);
        lawanId1.getoPengelolaanBarangSederhana().tambahBarang(medikit, 5);
        adeganId2.tambahLawan(lawanId1);
        adeganId2.tambahLawan(new Lawan(lawanId1, pipaTua, 1));
        adeganId24.tambahLawan(lawanId1);
        adeganId24.tambahLawan(new Lawan(lawanId1, pipaTua, 1));

        //lawan lantai2
        Lawan lawanId2 = new Lawan(2, "Zombie Virus Kuat", 100, 50);
        lawanId2.getoPengelolaanBarangSederhana().tambahBarang(medikit, 5);
        adeganId4.tambahLawan(lawanId2);
        adeganId4.tambahLawan(new Lawan(lawanId2, pistol, 1));

        //lawan lantai3
        Lawan lawanId3 = new Lawan(3, "Zombie Mematikan", 100, 100);
        lawanId3.getoPengelolaanBarangSederhana().tambahBarang(medikit, 5);
        adeganId6.tambahLawan(lawanId3);
        adeganId6.tambahLawan(new Lawan(lawanId3, pistol, 1));

        //lawan lantai4
//        Lawan lawanId4 = new Lawan(4, "BOSS", 100, 150);
//        lawanId4.getoPengelolaanBarangSederhana().tambahBarang(medikit, 5);
//        adeganId8.tambahLawan(lawanId4);
//        adeganId8.tambahLawan(new Lawan(lawanId4, pistol, 1));


        oGameEgnine.oPlayer.getPengelolaanBarang().setBatasMaxClassBarangSenjataIdTertentu(10, 5);
        oGameEgnine.oPlayer.getPengelolaanBarang().setBatasMaxClassBarangSenjataIdTertentu(11, 10);
        oGameEgnine.oPlayer.getPengelolaanBarang().setBatasMaxClassBarangSenjataIdTertentu(32, 5);
        oGameEgnine.oPlayer.getPengelolaanBarang().setBatasMaxClassBarangPenggunaanPadaDiriIdTertentu(1, 5);
//        oGameEgnine.oPlayer.getPengelolaanBarang().tambahBarang(granade, 5);
        oGameEgnine.oPlayer.getPengelolaanBarang().tambahBarang(increniaryArrow, 30);
        oGameEgnine.oPlayer.getPengelolaanBarang().tambahBarang(bow, 1);
        oGameEgnine.oPlayer.adeganAktif = adeganId3;
        oGameEgnine.mulaiGame();
    }
}