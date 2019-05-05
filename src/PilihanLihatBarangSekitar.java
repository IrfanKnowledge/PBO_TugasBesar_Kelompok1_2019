import java.util.ArrayList;
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

            for(Map.Entry<String, ArrayList<ArrayList<Barang>>> isiList3 : this.oAdegan.getDaftarBarang().entrySet()){
                for (ArrayList<Barang> oDaftarBarang : isiList3.getValue()) {
                    if(!oDaftarBarang.isEmpty()){
                        System.out.printf("-%-20s (%d)\n", oDaftarBarang.get(0).getNama(), oDaftarBarang.size());
                    }
                }
            }

            /* mengecek saja, bisa di buat komentar */
            System.out.println("DaftarBarang = " + this.oAdegan.getDaftarBarang());

            /* daftar pilihan jika barang tidak kosong */
            PilihanAmbilSemuaBarang oPilihanAmbilSemuaBarang = new PilihanAmbilSemuaBarang("Ambil semua barang", this.oAdegan);
            PilihanAmbilBarangSenjataSatuPerSatu oPilihanAmbilBarangSenjataSatuPerSatu =  new PilihanAmbilBarangSenjataSatuPerSatu("Ambil senjata satu-per-satu", this.oAdegan);

            /* jika barang di adegan ini kosong maka */
            if(this.oAdegan.getDaftarBarang().isEmpty()){
                System.out.println("");
                System.out.println("[ Tidak ditemukan barang apapun. ]");
                System.out.println("");
                System.out.printf("%2d. %s\n", 0, "Kembali");
            }else {
                System.out.printf("%2d. %s\n", 1, oPilihanAmbilSemuaBarang.getDekripsi());
                System.out.printf("%2d. %s\n", 2, oPilihanAmbilBarangSenjataSatuPerSatu.getDekripsi());
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
                        oPilihanAmbilBarangSenjataSatuPerSatu.aksi();
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
