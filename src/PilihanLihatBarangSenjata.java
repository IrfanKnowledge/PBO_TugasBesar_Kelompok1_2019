import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PilihanLihatBarangSenjata extends Pilihan {
    private Adegan oAdegan;

    PilihanLihatBarangSenjata(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        HashMap<String, Integer> temp = this.oAdegan.oPlayer.pilihKategoriIdBarang("Senjata");
        if(temp != null){
            boolean validasiKembali = false;
            while (!validasiKembali){

                /* cukup ambil satu barang */
                for (Map.Entry<String, Integer> oBarang : temp.entrySet()) {
                    Barang oBarangPilihan = this.oAdegan.oPlayer.pilihBarangSatu(oBarang.getKey(), oBarang.getValue());

                    System.out.println();
                    System.out.println("Aksi : Melihat deskripsi senjata");

                    System.out.println();
                    if(oBarangPilihan.getJenis().equals("Senjata Pukul")){
                        System.out.printf("%-25s : %s\n", "nama", oBarangPilihan.getNama());
                        System.out.printf("%-25s : %s\n", "Deskripsi", oBarangPilihan.getDeskripsi());
                        System.out.printf("%-25s : %s\n", "Harga beli", oBarangPilihan.getHargaBeli());
                        System.out.printf("%-25s : %s\n", "Harga jual", oBarangPilihan.getHargaJual());
                        System.out.printf("%-25s : %d\n", "Kekuatan", oBarangPilihan.getKekuatan());
                        System.out.printf("%-25s : %d\n", "Ketahanan", oBarangPilihan.getKetahanan());
                        System.out.printf("%-25s : %s\n", "Kemampuan diperbaiki", oBarangPilihan.jumlahKemampuanDiperbaiki());
                    }else if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
                        System.out.printf("%-12s : %s\n", "nama", oBarangPilihan.getNama());
                        System.out.printf("%-12s: %s\n", "Deskripsi", oBarangPilihan.getDeskripsi());
                        System.out.printf("%-12s : %s\n", "Harga beli", oBarangPilihan.getHargaBeli());
                        System.out.printf("%-12s : %s\n", "Harga jual", oBarangPilihan.getHargaJual());
                        System.out.printf("%-12s : %d\n", "Kekuatan", oBarangPilihan.getKekuatan());
                        System.out.printf("%-12s : %d / %d\n", "Isi peluru", oBarangPilihan.getJumlahPeluru(), oBarangPilihan.getBatasMaxAmunisi());
                    }
                    System.out.println();

                    System.out.printf("%2d. Gunakan Senjata\n", 1);

                    if(oBarangPilihan.getJenis().equals("Senjata Pukul")){
                        System.out.printf("%2d. Perbaiki Barang\n", 2);
                    }else if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
                        System.out.printf("%2d. Isi Peluru\n", 2);
                        System.out.printf("%2d. Ganti Peluru\n", 2);
                    }

                    System.out.printf("%2d. Jatuhkan Senjata\n", 0);
                    System.out.printf("%2d. Kembali\n", 0);
                    System.out.print("Masukkan Pilihan : ");
                    Scanner oScan = new Scanner(System.in);
                    switch(oScan.nextInt()){
                        case 0:
                            validasiKembali = true;
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                }

            }
        }
    }
}
