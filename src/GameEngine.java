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
        ArrayList<Barang> barangTengahRuanganList1 = new ArrayList<>();
        for(int i=0; i<5; i++){
            barangTengahRuanganList1.add(new BarangSenjataPukul(30, "Pipe", "Senjata Pukul", "Senjata", "Pipa tua berkarat yang berbentuk sedang", true, true,
                    100, 50, 30, 10));
        }

        HashMap<Integer, ArrayList<Barang>> barangTengahRuangan2 = new HashMap<>();
        barangTengahRuangan2.put(barangTengahRuanganList1.get(0).getIdBarang(), barangTengahRuanganList1);
        HashMap<String, HashMap<Integer, ArrayList<Barang>>> barangTengahRuangan3 = new HashMap<>();
        barangTengahRuangan3.put(barangTengahRuanganList1.get(0).getKategoriPenyimpanan(), barangTengahRuangan2);
        tengahRuangan.tambahBarangTetap(barangTengahRuangan3);

        oGameEgnine.oPlayer.adeganAktif = tengahRuangan;
        oGameEgnine.mulaiGame();
    }
}