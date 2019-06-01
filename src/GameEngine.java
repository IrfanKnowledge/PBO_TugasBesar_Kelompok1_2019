import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameEngine {
    private Player oPlayer;
//    private Adegan daftarAdegan;

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

        adeganId1.tambahBarang(new Barang(1,"kunci", "kunci", "memilki permata berwarna merah",
                true, false, 5, 0), 1);
        adeganId1.tambahBarang(new BarangSenjataJarakDekat(4, "pipa tua", "senjata", "pipa tua berkarat berukurang sedang",
                true, false, 5000, 15000, 35, 20, 20, 3, 0, 1),
                2);

        oGameEgnine.oPlayer.adeganAktif = adeganId1;
        oGameEgnine.mulaiGame();
    }
}
