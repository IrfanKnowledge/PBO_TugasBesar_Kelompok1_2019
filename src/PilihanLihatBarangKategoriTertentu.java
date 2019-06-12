import java.util.ArrayList;
import java.util.Scanner;

public class PilihanLihatBarangKategoriTertentu extends Pilihan {
    public Adegan oAdegan;
    public String kategori;


    PilihanLihatBarangKategoriTertentu(String dekripsi, Adegan oAdegan, String kategori) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.kategori = kategori;
    }

    @Override
    public void aksi() {
        this.kembaliKeMenuSebelumnya = false;
        /* untuk kembali ke menu melihat isi kantong */
        while (!this.kembaliKeMenuSebelumnya){
            ArrayList<Barang> daftarBarangPilihan = this.oAdegan.oPlayer.pilihBarangDariDaftarBarangKeseluruhanByKategoriTertentu(this.dekripsi, kategori);
            if(daftarBarangPilihan == null){
                this.kembaliKeMenuSebelumnya = true;
            }else{
                /* untuk kembali ke menu melihat daftar barang kategori tertentu */
                boolean kembaliKeDaftarBarangTertentu = false;
                while (!kembaliKeDaftarBarangTertentu){
                    /* cukup ambil satu barang */
                    Barang barangPilihan = daftarBarangPilihan.get(0);
                    System.out.println();
                    System.out.printf("Aksi : Lihat Deskripsi Barang (kategori: %s)\n", kategori);
                    System.out.println();
                    System.out.printf("%-12s : %s\n", "nama", barangPilihan.nama);
                    System.out.printf("%-12s : %s\n", "Deskripsi", barangPilihan.deskripsi);
                    if(!barangPilihan.statusJual){
                        System.out.printf("%-12s : %s\n", "Harga jual", "-");
                    }else{
                        System.out.printf("%-12s : %s\n", "Harga jual", barangPilihan.getHargaJual());
                    }
                    if(!barangPilihan.statusBeli){
                        System.out.printf("%-12s : %s\n", "Harga beli", "-");
                    }else{
                        System.out.printf("%-12s : %s\n", "Harga beli", barangPilihan.getHargaBeli());
                    }
                    System.out.println();
                    if(barangPilihan.statusDapatDigunakanAdeganTertentu){
                        System.out.printf("%2d. Gunakan Barang\n", 1);
                    }
                    System.out.printf("%2d. Kembali\n", 0);
                    System.out.print("Masukkan Pilihan : ");
                    Scanner input = new Scanner(System.in);
                    switch(input.nextInt()){
                        case 0:
                            kembaliKeDaftarBarangTertentu = true;
                            break;
                        case 1:
                            if(this.oAdegan instanceof AdeganNormal){
                                if(barangPilihan.statusDapatDigunakanAdeganTertentu){
                                    if(((AdeganNormal) this.oAdegan).idBarangBisaDigunakan != barangPilihan.idBarang){
                                        System.out.println();
                                        System.out.println("[ Kunci ini tidak cocok untuk digunakan. ]");
                                    }else{
                                        ((AdeganNormal) this.oAdegan).gunakanBarang();
                                    }
                                    break;
                                }
                            }
                        default:
                            System.out.println();
                            System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
                            break;
                    }
                }
            }
        }

    }
}
