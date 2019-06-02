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
            System.out.println("Aksi : " + this.getDekripsi());

            int i=0;
            System.out.printf("%2d. %s\n", ++i, "Kunci");
            System.out.printf("%2d. %s\n", ++i, "Senjata");
            System.out.printf("%2d. %s\n", ++i, "Amunisi");
            System.out.printf("%2d. %s\n", ++i, "Komponen Crafting");
            System.out.printf("%2d. %s\n", ++i, "Blueprint");
            System.out.printf("%2d. %s\n", ++i, "Barang Bernilai Jual");
            System.out.printf("%2d. %s\n", ++i, "Barang Lainnya");
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
