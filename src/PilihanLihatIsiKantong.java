import java.util.ArrayList;
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
            System.out.printf("%2d. %s\n", ++i, "Penyimpanan Utama");
            System.out.printf("%2d. %s\n", ++i, "Komponen Crafting");
            System.out.printf("%2d. %s\n", ++i, "Blueprint");
            System.out.printf("%2d. %s\n", ++i, "Barang Bernilai Jual");
            System.out.printf("%2d. %s\n", ++i, "Cek Barang");
            System.out.printf("%2d. Kembali\n", 0);

            System.out.print("Masukkan Pilihan : ");
            Scanner oScan = new Scanner(System.in);

            switch (oScan.nextInt()){
                case 0:
                    validasiKembali = true;
                    break;
                case 1:
                    (new PilihanLihatBarangKunci("Lihat Daftar Kunci", this.oAdegan)).aksi();
                    break;
                case 2:
                    (new PilihanLihatBarangTerbatas("Lihat Penyimpanan Utama", this.oAdegan)).aksi();
                    break;
                case 3:
                    System.out.println(this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangKeseluruhanByKategori());
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    for (ArrayList<Barang>  daftarBarang : this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas()) {
                        for (Barang barangTertentu : daftarBarang) {
                            System.out.println(barangTertentu.nama);
                        }
                    }
                    break;
                default:
                    System.out.println();
                    System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
                    break;
            }
        }

    }
}
