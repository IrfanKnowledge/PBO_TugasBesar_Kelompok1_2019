import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PilihanLihatBarangKunci extends Pilihan {
    private Adegan oAdegan;

    PilihanLihatBarangKunci(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        HashMap<String, Integer> temp = this.oAdegan.oPlayer.pilihKategoriIdBarang("Kunci");
        if(temp != null){
            boolean validasiKembali = false;
            while (!validasiKembali){

                /* cukup ambil satu barang */
                for (Map.Entry<String, Integer> oBarang : temp.entrySet()) {
                    Barang oBarangPilihan = this.oAdegan.oPlayer.pilihBarangSatu(oBarang.getKey(), oBarang.getValue());

                    System.out.println();
                    System.out.println("Aksi : Melihat deskripsi kunci");

                    System.out.println();
                    System.out.printf("%-12s : %s\n", "nama", oBarangPilihan.getNama());
                    System.out.printf("%-12s : %s\n", "Deskripsi", oBarangPilihan.getDeskripsi());
                    System.out.printf("%-12s : %s\n", "Harga beli", "-");
                    System.out.printf("%-12s : %s\n", "Harga jual", "-");
                    System.out.println();

                    System.out.println("1. Gunakan Kunci");
                    System.out.println("0. Kembali");
                    System.out.print("Masukkan Pilihan : ");
                    Scanner oScan = new Scanner(System.in);
                    switch(oScan.nextInt()){
                        case 0:
                            validasiKembali = true;
                            break;
                        case 1:
                            break;
                        default:
                            break;
                    }
                }

            }
        }
    }
}
