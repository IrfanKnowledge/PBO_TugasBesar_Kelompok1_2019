import java.util.ArrayList;
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

        /* untuk kembali ke menu melihat isi kantong */
        boolean validasiKembali1 = false;
        while (!validasiKembali1){

            /* untuk kembali ke menu melihat daftar kunci */
            HashMap<String, ArrayList<Integer>> temp = this.oAdegan.oPlayer.pilihKategoriIdIndeksBarang("Kunci");
            if(temp != null){
                boolean validasiKembali2 = false;
                while (!validasiKembali2){

                    /* cukup ambil satu barang */
                    for (Map.Entry<String, ArrayList<Integer>> oBarang : temp.entrySet()) {
                        Barang oBarangPilihan = this.oAdegan.oPlayer.pilihBarangSatu(oBarang.getKey(), oBarang.getValue().get(0), oBarang.getValue().get(1));

                        System.out.println();
                        System.out.println("Aksi : Melihat deskripsi kunci");

                        System.out.println();
                        System.out.printf("%-12s : %s\n", "nama", oBarangPilihan.getNama());
                        System.out.printf("%-12s : %s\n", "Deskripsi", oBarangPilihan.getDeskripsi());
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
                                if(this.oAdegan.getIdBarangBisaDigunakan() == oBarangPilihan.getIdBarang()){
                                    this.oAdegan.gunakanBarang();
                                }else{
                                    System.out.println();
                                    System.out.println("[ Kunci ini tidak cocok untuk digunakan. ]");
                                }
                                break;
                            default:
                                break;
                        }
                    }

                }
            }else{
                validasiKembali1 = true;
            }
        }

    }
}
