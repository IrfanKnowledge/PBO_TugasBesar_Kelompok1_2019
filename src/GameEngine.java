import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class GameEngine {
    private Player oPlayer;

    GameEngine(){
        this.oPlayer = new Player(1, "Abdi Mukti");
        Adegan.oPlayer = this.oPlayer;
    }

    private void mulaiGame(){
        System.out.println("Pada suatu hari..\n");
        System.out.printf("%-10s\n\n", "[Tekan Huruf Apapun kemudian Enter untuk melanjutkan]");
        Scanner oScan = new Scanner(System.in);
        oScan.next();
        System.out.println("Kemudian " + this.oPlayer.getNama() + "harus melakukan sebuah misi untuk...");
        System.out.printf("%-10s\n\n", "[Tekan Huruf Apapun kemudian Enter untuk melanjutkan]");
        oScan.next();
        System.out.println("Selamat bermain");
        while (!this.oPlayer.isSelesai()){
            this.oPlayer.adeganAktif.mainkan();
        }
    }

    public static void main(String[] args) {
        GameEngine oGameEgnine = new GameEngine();
        Adegan adeganPertama = new Adegan(1, 0,"Disebuah ruangan asing...", "Gedung Tua Asing");
        oGameEgnine.oPlayer.adeganAktif = adeganPertama;
        //oGameEgnine.mulaiGame();

    }
}
