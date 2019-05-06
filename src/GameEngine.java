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
//        System.out.println("Pada suatu hari..\n");
//        System.out.printf("%-10s\n\n", "[Tekan Huruf Apapun kemudian Enter untuk melanjutkan]");
//        Scanner oScan = new Scanner(System.in);
//        oScan.nextLine();
//        System.out.println();
//        System.out.println("Kemudian " + this.oPlayer.getNama() + "harus melakukan sebuah misi untuk...\n");
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


        Adegan tengahRuangan = new Adegan(1, 0, "Disebuah ruangan asing...", "Gedung Tua Asing");

        ArrayList<Barang> barangTengahRuanganList1Senjata = new ArrayList<>();
        for(int i=0; i<5; i++){
            barangTengahRuanganList1Senjata.add(new BarangSenjataPukul(30, "Pipe", "Senjata Pukul", "Senjata", "Pipa tua berkarat yang berbentuk sedang", true, true,
                    100, 50, 30, 10));
        }
        ArrayList<Barang> barangTengahRuanganList1Kunci = new ArrayList<>();
        barangTengahRuanganList1Kunci.add(new Barang(28, "Jewel Key", "Kunci Pintu", "Kunci", "Kunci yang memiliki Permata bulat", false, false,
                0, 0));

        HashMap<Integer, ArrayList<Barang>> barangTengahRuangan2Senjata = new HashMap<>();
        barangTengahRuangan2Senjata.put(barangTengahRuanganList1Senjata.get(0).getIdBarang(), barangTengahRuanganList1Senjata);
        HashMap<Integer, ArrayList<Barang>> barangTengahRuangan2Kunci = new HashMap<>();
        barangTengahRuangan2Kunci.put(barangTengahRuanganList1Kunci.get(0).getIdBarang(), barangTengahRuanganList1Kunci);

        HashMap<String, HashMap<Integer, ArrayList<Barang>>> barangTengahRuangan3 = new HashMap<>();
        barangTengahRuangan3.put(barangTengahRuanganList1Senjata.get(0).getKategoriPenyimpanan(), barangTengahRuangan2Senjata);
        barangTengahRuangan3.put(barangTengahRuanganList1Kunci.get(0).getKategoriPenyimpanan(), barangTengahRuangan2Kunci);

        tengahRuangan.tambahBarangTetap(barangTengahRuangan3);

        System.out.println(barangTengahRuangan3);
        oGameEgnine.oPlayer.adeganAktif = tengahRuangan;
        oGameEgnine.mulaiGame();
    }
}
