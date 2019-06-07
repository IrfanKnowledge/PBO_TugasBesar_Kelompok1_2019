import java.util.ArrayList;
import java.util.Scanner;

public class PilihanLihatBarangKunci extends Pilihan {
    private Adegan oAdegan;

    PilihanLihatBarangKunci(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {

        /* untuk kembali ke menu melihat isi kantong */
        boolean validasiKembali1 = false;
        while (!validasiKembali1){

            ArrayList<Barang> oBarangKunci = this.oAdegan.oPlayer.pilihBarangDariDaftarBarangKeseluruhanByKategoriTertentu("Lihat Daftar Kunci", "kunci");
            if(oBarangKunci != null){
                /* untuk kembali ke menu melihat daftar kunci */
                boolean validasiKembali2 = false;
                while (!validasiKembali2){

                    /* cukup ambil satu barang */
                    Barang oBarangPilihan = oBarangKunci.get(0);
                    System.out.println();
                    System.out.println("Aksi : " + this.getDekripsi());
                    System.out.println();
                    System.out.printf("%-12s : %s\n", "nama", oBarangPilihan.nama);
                    System.out.printf("%-12s : %s\n", "Deskripsi", oBarangPilihan.deskripsi);
                    System.out.printf("%-12s : %s\n", "Harga beli", "-");
                    System.out.printf("%-12s : %s\n", "Harga jual", "-");
                    System.out.println();
                    System.out.printf("%2d. Gunakan Kunci\n", 1);
                    System.out.printf("%2d. Kembali\n", 0);
                    System.out.print("Masukkan Pilihan : ");
                    Scanner oScan = new Scanner(System.in);
                    switch(oScan.nextInt()){
                        case 0:
                            validasiKembali2 = true;
                            break;
                        case 1:
                            if(this.oAdegan.idBarangBisaDigunakan == oBarangPilihan.idBarang){
                                this.oAdegan.gunakanBarang();
                            }else{
                                System.out.println();
                                System.out.println("[ Kunci ini tidak cocok untuk digunakan. ]");
                            }
                            break;
                        default:
                            System.out.println();
                            System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
                            break;
                    }
                }
            }else{
                validasiKembali1 = true;
            }
        }

    }
}
