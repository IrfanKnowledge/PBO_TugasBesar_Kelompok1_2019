import java.util.ArrayList;
import java.util.Scanner;

public class PilihanHapusBarang extends Pilihan{
    private Adegan oAdegan;
    private int indeksBarangTerpilih;
    private ArrayList<Barang> daftarBarangTerpilih = new ArrayList<>();
    PilihanHapusBarang(String dekripsi, int indeksBarangTerpilih, ArrayList<Barang> barangTerpilih) {
        super(dekripsi);
        this.indeksBarangTerpilih = indeksBarangTerpilih;
        this.daftarBarangTerpilih = barangTerpilih;
    }

    @Override
    public void aksi() {
        System.out.println();
        System.out.println("[ Apakah anda yakin akan menghapus senjata ini ? | tidak(t) / ya(y) ]");
        System.out.printf("Masukkan Pilihan : ");
        Scanner oScan = new Scanner(System.in);
        String input = oScan.next();
        if(!input.equals("t")){
            this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(this.indeksBarangTerpilih, this.daftarBarangTerpilih);
            System.out.println();
            System.out.println("[ Senjata telah dihapus ]");
            this.kembaliKeMenuSebelumnya = true;
        }
    }
}
