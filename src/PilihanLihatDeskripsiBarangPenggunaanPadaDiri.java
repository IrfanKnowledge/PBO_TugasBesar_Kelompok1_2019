import java.util.Scanner;

public class PilihanLihatDeskripsiBarangPenggunaanPadaDiri extends Pilihan {
    private Adegan oAdegan;
    private BarangPenggunaanPadaDiri barangTerpilih;

    PilihanLihatDeskripsiBarangPenggunaanPadaDiri(String dekripsi, Adegan oAdegan, BarangPenggunaanPadaDiri barangTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.barangTerpilih = barangTerpilih;
    }

    @Override
    public void aksi() {
        boolean validasiKembaliKeDaftarBarangTerbatas = false;
        while(!validasiKembaliKeDaftarBarangTerbatas){
            System.out.printf("%-25s : %d\n", "Kesehatan", barangTerpilih.getKesehatan());
            System.out.println();
            System.out.printf("%2d. Gunakan Barang\n", 1);
            System.out.printf("%2d. %-20s | (barang akan dihapus, tidak dapat dikembalikan)\n", 3, "Buang Barang");
            System.out.printf("%2d. Kembali\n", 0);
            System.out.print("Masukkan Pilihan : ");
            Scanner oScan = new Scanner(System.in);

            switch(oScan.nextInt()){
                case 0:
                    validasiKembaliKeDaftarBarangTerbatas = true;
                    break;
                case 1:
                    /* Proses gunakan senjata */
                    this.gunakanBarang(barangTerpilih);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("[ Apakah anda yakin akan menghapus barang ini ? | tidak(t) / ya(y) ]");
                    String input = oScan.next();
                    if(!input.equals("t")){
                        this.oAdegan.oPlayer.hapusBarangDariPenyimpanan(barangTerpilih);
                        System.out.println();
                        System.out.println("[ Barang telah dihapus ]");
                    }
                    break;

                default:
                    System.out.println();
                    System.out.println("[ Pilihan tidak tersedia. ]");
                    break;
            }
        }
    }

    private void gunakanBarang(BarangPenggunaanPadaDiri barangPilihan){
        this.oAdegan.oPlayer.tambahKesehatan(barangPilihan.getKesehatan());
        this.oAdegan.oPlayer.tambahEfek(barangPilihan.getDaftarEfek());
        System.out.println();
        System.out.printf("[ %s berhasil digunakan ]\n", barangPilihan.nama);
    }
}
