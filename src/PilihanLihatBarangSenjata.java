import java.util.ArrayList;
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

        /* untuk kembali ke menu melihat isi kantong */
        boolean validasiKembali1 = false;
        while (!validasiKembali1){

            HashMap<String, ArrayList<Integer>> temp = this.oAdegan.oPlayer.pilihKategoriIdIndeksBarang("Senjata");
            if(temp != null){

                /* untuk kembali ke menu melihat daftar senjata */
                boolean validasiKembali2 = false;
                while (!validasiKembali2){

                    /* ambil key dan value nya */
                    for (Map.Entry<String, ArrayList<Integer>> oBarang : temp.entrySet()) {
                        Barang oBarangPilihan = this.oAdegan.oPlayer.pilihBarangSatu(oBarang.getKey(), oBarang.getValue().get(0), oBarang.getValue().get(1));

                        /* khusus jika memilih senjata lempar */
                        ArrayList<Barang> oSenjataLempar =  this.oAdegan.oPlayer.pilihBarangBanyak(oBarang.getKey(), oBarang.getValue().get(0));

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
                            System.out.printf("%2d. Jatuhkan Senjata\n", 3);
                        }else if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
                            System.out.printf("%2d. Isi Peluru\n", 2);
                            System.out.printf("%2d. Ganti Peluru\n", 3);
                            System.out.printf("%2d. Jatuhkan Senjata\n", 4);
                        }else{
                            System.out.printf("%2d. Jatuhkan Senjata\n", 2);
                        }

                        System.out.printf("%2d. Kembali\n", 0);
                        System.out.print("Masukkan Pilihan : ");
                        Scanner oScan = new Scanner(System.in);
                        switch(oScan.nextInt()){
                            case 0:
                                validasiKembali2 = true;
                                break;
                            case 1:
                                if(oBarangPilihan.getJenis().equals("Senjata Pukul") || oBarangPilihan.getJenis().equals("Senjata Tembak")){
                                    this.oAdegan.oPlayer.senjata = oBarangPilihan;
                                    System.out.println();
                                    System.out.printf("[ %s berhasil digunakan ]\n", oBarangPilihan.getNama());
                                }else{
                                    this.oAdegan.oPlayer.senjataLempar = oSenjataLempar;
                                    System.out.println();
                                    System.out.printf("[ %s berhasil digunakan ]\n", oSenjataLempar.get(0).getNama());
                                }
                                break;
                            case 2:
                                if(oBarangPilihan.getJenis().equals("Senjata Pukul")){

                                }else if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
                                }else{
                                }
                                break;
                            case 3:
                                if(oBarangPilihan.getJenis().equals("Senjata Pukul")){
                                }else if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
                                }else{
                                    System.out.println();
                                    System.out.println("[ Pilihan tidak tersedia. ]");
                                }
                                break;
                            default:
                                System.out.println();
                                System.out.println("[ Pilihan tidak tersedia. ]");
                                break;
                        }
                    }

                }
            }else {
                validasiKembali1 = true;
            }
        }
    }
}
