import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PilihanLihatBarangSekitar extends Pilihan {

    private Adegan oAdegan;

    PilihanLihatBarangSekitar(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        boolean validasiPilihanKembali = false;
        while(!validasiPilihanKembali){

            System.out.println();
            System.out.println("Aksi : Melihat barang di sekitar");

            int i = 0;
            for(Map.Entry<String, ArrayList<ArrayList<Barang>>> oKategori : this.oAdegan.getDaftarBarang().entrySet()){
                for (ArrayList<Barang> oDaftarBarang : oKategori.getValue()) {
                    if(!oDaftarBarang.isEmpty()){
                        System.out.printf("-%-10s (%d)\n", oDaftarBarang.get(0).getNama(), oDaftarBarang.size());
                        i++;
                    }
                }
            }

            /* daftar pilihan jika barang tidak kosong */
            PilihanAmbilSemuaBarang oPilihanAmbilSemuaBarang = new PilihanAmbilSemuaBarang("Ambil semua barang", this.oAdegan);

            if(this.oAdegan.getDaftarBarang().isEmpty()){
                System.out.println("Tidak ditemukan barang apapun.");
                System.out.printf("%2d. %s\n", 0, "Kembali");
            }else {
                System.out.printf("%2d. %s\n", 1, oPilihanAmbilSemuaBarang.getDekripsi());
                System.out.printf("%2d. %s\n", 2, "Ambil barang satu-per-satu");
                System.out.printf("%2d. %s\n", 0, "Kembali");
            }

            System.out.print("Masukkan Pilihan : ");
            Scanner oScan = new Scanner(System.in);
            switch (oScan.nextInt()){
                case 0:
                    validasiPilihanKembali = true;
                    break;
                case 1:
                    /* Jika daftarBarang tidak kosong maka... */
                    if(!this.oAdegan.getDaftarBarang().isEmpty()){

                        /* Jalankan aksi ambil semua barang */
                        oPilihanAmbilSemuaBarang.aksi();
                    }
                    break;
                case 2:
                    /* Jika daftarBarang tidak kosong maka... */
                    if(!this.oAdegan.getDaftarBarang().isEmpty()){

                        /* Jalankan aksi ambil barang secara satu-per-satu */
                    }
                    break;
                default:
                    System.out.println();
                    System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
                    break;
            }
        }
    }

    @Override
    public String getDekripsi() {
        return super.getDekripsi();
    }
}
