import java.util.ArrayList;
import java.util.Scanner;

public class PilihanLihatDeskripsiGunakanAmunisi extends Pilihan {
    private Adegan oAdegan;
    private ArrayList<BarangSenjata> daftarBarangTerpilih;

    PilihanLihatDeskripsiGunakanAmunisi(String dekripsi, Adegan oAdegan, ArrayList<BarangSenjata> daftarBarangTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.daftarBarangTerpilih = daftarBarangTerpilih;
    }

    @Override
    public void aksi() {
        boolean validasiKembaliKeDeskripsiSenjata = false;
        while(!validasiKembaliKeDeskripsiSenjata){
            System.out.println();
            System.out.println("Aksi : " + this.dekripsi);
            int i =0;
            for (ArrayList<Barang> barangTerbatas : this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas()) {
                i++;
                System.out.printf("%2d. %-20s (%d)", i, barangTerbatas.get(0).nama, barangTerbatas.size());
            }
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            Scanner oScan = new Scanner(System.in);
            int input = oScan.nextInt()
            if(input < 1 || input > this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas().size()){
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia ]");
            }else if(){
                /* proses isi amunisi */
            }else{
                /* proses tukar amunisi */
            }
        }
    }
}
