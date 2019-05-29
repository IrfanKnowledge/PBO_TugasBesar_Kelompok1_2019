import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PilihanLihatIsiKantong extends Pilihan {
    private Adegan oAdegan;

    PilihanLihatIsiKantong(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public String getDekripsi() {
        return super.getDekripsi();
    }

    @Override
    public void aksi() {
        boolean validasiKembali = false;
        while (!validasiKembali){
            System.out.println();
            System.out.println("Aksi : Melihat isi kantong");

            int i=0;
            System.out.printf("%2d. %s\n", i+1, "Kunci"); i++;
            System.out.printf("%2d. %s\n", i+1, "Senjata"); i++;
            System.out.printf("%2d. %s\n", i+1, "Amunisi"); i++;
            System.out.printf("%2d. %s\n", i+1, "Komponen Crafting"); i++;
            System.out.printf("%2d. %s\n", i+1, "Blueprint"); i++;
            System.out.printf("%2d. %s\n", i+1, "Barang Bernilai Jual"); i++;
            System.out.printf("%2d. %s\n", i+1, "Barang Lainnya"); i++;
            System.out.printf("%2d. Kembali\n", 0);

            System.out.println("Masukkan Pilihan : ");
            Scanner oScan = new Scanner(System.in);

            switch (oScan.nextInt()){
                case 0:
                    validasiKembali = true;
                    break;
                case 1:
                    (new PilihanLihatBarangKunci("Lihat Kunci", this.oAdegan)).aksi();
                    break;
                case 2:
                    (new PilihanLihatBarangSenjata("Lihat Senjata", this.oAdegan)).aksi();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;

                default:
                    System.out.println();
                    System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
                    break;
            }
        }

    }
}
