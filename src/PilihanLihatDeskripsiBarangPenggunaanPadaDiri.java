import java.util.Scanner;

public class PilihanLihatDeskripsiBarangPenggunaanPadaDiri extends PilihanLihatIsiKantong {
    private int indeksBarangPenggunaanPadaDiriTerpilih;
    private BarangPenggunaanPadaDiri barangPenggunaanPadaDiriTerpilih;

    PilihanLihatDeskripsiBarangPenggunaanPadaDiri(String dekripsi, Adegan oAdegan, int indeksBarangPenggunaanPadaDiriTerpilih, BarangPenggunaanPadaDiri barangPenggunaanPadaDiriTerpilih) {
        super(dekripsi, oAdegan);
        this.indeksBarangPenggunaanPadaDiriTerpilih = indeksBarangPenggunaanPadaDiriTerpilih;
        this.barangPenggunaanPadaDiriTerpilih = barangPenggunaanPadaDiriTerpilih;
    }

    @Override
    public void aksi() {
        System.out.printf("%-25s : %d\n", "Kesehatan", barangPenggunaanPadaDiriTerpilih.getKesehatan());
        System.out.println();
        System.out.printf("%2d. Gunakan Barang\n", 1);
        System.out.printf("%2d. %-20s | (barang akan dihapus, tidak dapat dikembalikan)\n", 2, "Buang Barang");
        System.out.printf("%2d. Pindahkan Barang\n", 3);
        System.out.printf("%2d. Kembali\n", 0);
        System.out.print("Masukkan Pilihan : ");
        Scanner oScan = new Scanner(System.in);

        switch(oScan.nextInt()){
            case 0:
                this.kembaliKeMenuSebelumnya = true;
                break;
            case 1:
                /* Proses gunakan senjata */
                this.gunakanBarang(barangPenggunaanPadaDiriTerpilih);
                break;
            case 2:
                System.out.println();
                System.out.println("[ Apakah anda yakin akan menghapus barang ini ? | tidak(t) / ya(y) ]");
                System.out.print("Masukkan Pilihan : ");
                String input = oScan.next();
                if(!input.equals("t")){
                    this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(indeksBarangPenggunaanPadaDiriTerpilih, barangPenggunaanPadaDiriTerpilih);
                    System.out.println();
                    System.out.println("[ Barang telah dihapus ]");
                    this.kembaliKeMenuSebelumnya = true;
                }
                break;
            case 3:
                PilihanTukatSlotBarangTerbatas oPilihanTukarSlotBarangTerbatas = new PilihanTukatSlotBarangTerbatas("Aksi : Pilih Tujuan Slot Pemindahan Barang / Penukaran", this.oAdegan, indeksBarangPenggunaanPadaDiriTerpilih);
                oPilihanTukarSlotBarangTerbatas.aksi();
                break;
            default:
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia. ]");
                break;
        }
    }

    private void gunakanBarang(BarangPenggunaanPadaDiri barangPilihan){
        this.oAdegan.oPlayer.tambahKesehatan(barangPilihan.getKesehatan());
        this.oAdegan.oPlayer.tambahEfek(barangPilihan.getDaftarEfek());
        System.out.println();
        System.out.printf("[ %s berhasil digunakan ]\n", barangPilihan.nama);
        /* setelah digunakan barang terhapus */
        this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(indeksBarangPenggunaanPadaDiriTerpilih, barangPenggunaanPadaDiriTerpilih);
        this.kembaliKeMenuSebelumnya = true;
    }
}
