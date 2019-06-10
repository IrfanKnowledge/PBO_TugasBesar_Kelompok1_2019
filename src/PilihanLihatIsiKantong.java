import java.util.ArrayList;
import java.util.Scanner;

public class PilihanLihatIsiKantong extends Pilihan {
    public Adegan oAdegan;
    public boolean kembaliKeMenuSebelumnya;
    private ArrayList<Pilihan> daftarPilihan = new ArrayList<>();

    PilihanLihatIsiKantong(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.daftarPilihan.add(new PilihanLihatBarangKategoriTertentu("Lihat Daftar Kunci", this.oAdegan, "kunci"));
        this.daftarPilihan.add(new PilihanLihatBarangTerbatas("Lihat Penyimpanan Utama", oAdegan));
        this.daftarPilihan.add(new PilihanLihatBarangKategoriTertentu("Lihat Daftar Komponen Crafting", this.oAdegan, "komponen crafting"));
        /* blurprint belum dibuat pilihannya */
//        this.daftarPilihan.add();
        this.daftarPilihan.add(new PilihanLihatBarangKategoriTertentu("Lihat Daftar Barang Bernilai", this.oAdegan, "barang bernilai"));
    }

    @Override
    public void aksi() {
        this.kembaliKeMenuSebelumnya = false;
        while (!this.kembaliKeMenuSebelumnya){
            System.out.println();
            System.out.println("Aksi : " + this.getDekripsi());
            int i=0;
            for (Pilihan oPilihan : this.daftarPilihan) {
                System.out.printf("%2d. %s\n", ++i, oPilihan.dekripsi);
            }
            System.out.printf("%2d. Kembali\n", 0);
            System.out.print("Masukkan Pilihan : ");
            Scanner oScan = new Scanner(System.in);
            int input = oScan.nextInt();
            if(input < 0 || input > this.daftarPilihan.size()){
                System.out.println();
                System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
                break;
            }else if(input == 0){
                this.kembaliKeMenuSebelumnya = true;
            }else{
                this.daftarPilihan.get(input-1).aksi();
            }
        }
    }

    public ArrayList<Pilihan> getDaftarPilihan() {
        return daftarPilihan;
    }
}
