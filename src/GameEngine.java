import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameEngine {
    private Player oPlayer;
    private Adegan daftarAdegan;

    GameEngine(){
        this.oPlayer = new Player(1, "Abdi Mukti");
        Adegan.oPlayer = this.oPlayer;
    }

    private void mulaiGame(){
        System.out.println("Pada suatu hari..\n");
        System.out.printf("%-10s\n\n", "[Tekan Huruf Apapun kemudian Enter untuk melanjutkan]");
        Scanner oScan = new Scanner(System.in);
        oScan.nextLine();
        System.out.println();
        System.out.println("Kemudian " + this.oPlayer.getNama() + "harus melakukan sebuah misi untuk...\n");
        System.out.printf("%-10s\n\n", "[Tekan Huruf Apapun kemudian Enter untuk melanjutkan]");
        oScan.nextLine();
        System.out.println();
        System.out.println("Selamat bermain");
        while (!this.oPlayer.isSelesai()){
            this.oPlayer.adeganAktif.mainkan();
        }
    }

    public static void main(String[] args){
        GameEngine oGameEgnine = new GameEngine();

        /* contoh adegan */
        Adegan adeganId1 = new Adegan(1, 0, "Tengah Ruangan", "Kamar 03", "","Gedung Tua Asing", "Akhirnya sampai di ruangan yang sepertinya terlihat aman");
        Adegan adeganId2 = new Adegan(2, 28, "Depan Pintu", "Kamar 03", "","Gedung Tua Asing", "Berjalan menuju pintu kuning.. Hmm Pintu ini terkunci");

        HashMap<String, HashMap<Integer, ArrayList<Barang>>> barangSemuaBarang = new HashMap<>();

        /* Senjata */
        HashMap<Integer, ArrayList<Barang>> barangSenjata = new HashMap<>();
        ArrayList<Barang> listBarangSenjata = new ArrayList<>();
        HashMap<String, Integer> idAmunisi = new HashMap<>();
        idAmunisi.put("Amunisi", 5);
        ArrayList<Barang> daftarAmunisi = new ArrayList<>();
        for(int i=0; i<3; i++){
            daftarAmunisi.add(new Barang(5, "Pistol Ammo", "Peluru Pistol", "Amunisi", "Peluru untuk pistol secara umum", true, false, 100000, 0, 0, null));
        }
        //listBarangSenjata.add(new BarangSenjataTembak(5, "9mm Pistol", "Senjata Tembak", "Senjata", "Pistol berwarna hitam, berukuran sedang.", true, true, 1000000, 500000, 50, 8, daftarAmunisi, null, idAmunisi));
        //barangSenjata.put(5, listBarangSenjata);
        listBarangSenjata = new ArrayList<>();
        listBarangSenjata.add(new BarangSenjataPukul(30, "Pipe", "Senjata Pukul", "Senjata", "Pipa tua berkarat yang berbentuk sedang", true, true, 10000, 5000, 25, 15));
        barangSenjata.put(30, listBarangSenjata);
        listBarangSenjata = new ArrayList<>();
        //listBarangSenjata.add(new Barang(31, "Shuriken", "Senjata Lempar", "Senjata", "Senjata berwarna hitam kecil yang sering digunkan ninja.", true, true, 1000, 500, 25, null));
        //barangSenjata.put(31, listBarangSenjata);
        barangSemuaBarang.put("Senjata", barangSenjata);

        /* Kunci */
        HashMap<Integer, ArrayList<Barang>> barangKunci = new HashMap<>();
        ArrayList<Barang> listBarangKunci = new ArrayList<>();
        listBarangKunci.add(new Barang(28, "Jewel Key", "Peluru Pistol", "Amunisi", "Kunci kecil yang memiliki permata bulat", false, false, 0, 0));
        barangKunci.put(28, listBarangKunci);
        barangSemuaBarang.put("Kunci", barangKunci);

        /* Komponen Crafting */
        HashMap<Integer, ArrayList<Barang>> barangKomponenCrafting = new HashMap<>();
        ArrayList<Barang> listBarangKomponenCrafting = new ArrayList<>();
        listBarangKomponenCrafting.add(new Barang(2, "Metal Parts", "", "Komponen Crafting", "Benda metal biasa digunakan untuk memperbaiki barang", true, true, 500, 250));
        barangKomponenCrafting.put(2, listBarangKomponenCrafting);
        barangSemuaBarang.put("Komponen Crafting", barangKomponenCrafting);

        /* Amunisi */
        HashMap<Integer, ArrayList<Barang>> barangAmunisi = new HashMap<>();
        ArrayList<Barang> listBarangAmunisi = new ArrayList<>();
        for(int i=0; i<2; i++){
            listBarangAmunisi.add(new Barang(5, "Pistol Ammo", "Peluru Pistol", "Amunisi", "Peluru untuk pistol secara umum", true, false, 100000, 0, 0, null));
        }
        barangAmunisi.put(5, listBarangAmunisi);
        barangSemuaBarang.put("Amunisi", barangAmunisi);

        /* menambahkan barang ke Adegan Tengah Ruangan */
        adeganId1.tambahBarangTetap(barangSemuaBarang);
        //System.out.println(barangSenjata);
        //System.out.println(barangSemuaBarang);

//        ArrayList<Barang> barangTengahRuanganList1Senjata = new ArrayList<>();
//        for(int i=0; i<5; i++){
//            barangTengahRuanganList1Senjata.add(new BarangSenjataPukul(30, "Pipe", "Senjata Pukul", "Senjata", "Pipa tua berkarat yang berbentuk sedang", true, true,
//                    100, 50, 30, 10));
//        }
//        ArrayList<Barang> barangTengahRuanganList1Kunci = new ArrayList<>();
//        barangTengahRuanganList1Kunci.add(new Barang(28, "Jewel Key", "Kunci Pintu", "Kunci", "Kunci yang memiliki Permata bulat", false, false,
//                0, 0));

//        HashMap<Integer, ArrayList<Barang>> barangTengahRuangan2Senjata = new HashMap<>();
//        barangTengahRuangan2Senjata.put(barangTengahRuanganList1Senjata.get(0).getIdBarang(), barangTengahRuanganList1Senjata);
//        HashMap<Integer, ArrayList<Barang>> barangTengahRuangan2Kunci = new HashMap<>();
//        barangTengahRuangan2Kunci.put(barangTengahRuanganList1Kunci.get(0).getIdBarang(), barangTengahRuanganList1Kunci);
//
//        HashMap<String, HashMap<Integer, ArrayList<Barang>>> barangTengahRuangan3 = new HashMap<>();
//        barangTengahRuangan3.put(barangTengahRuanganList1Senjata.get(0).getKategoriPenyimpanan(), barangTengahRuangan2Senjata);
//        barangTengahRuangan3.put(barangTengahRuanganList1Kunci.get(0).getKategoriPenyimpanan(), barangTengahRuangan2Kunci);
//
//        tengahRuangan.tambahBarangTetap(barangTengahRuangan3);

//        System.out.println(barangTengahRuangan3);

//        oGameEgnine.oPlayer.adeganAktif = tengahRuangan;

        //System.out.println(barangSemuaBarang);
        oGameEgnine.oPlayer.adeganAktif = adeganId1;
        oGameEgnine.mulaiGame();
    }
}
