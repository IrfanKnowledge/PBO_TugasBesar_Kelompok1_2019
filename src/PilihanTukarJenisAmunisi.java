import java.util.ArrayList;
import java.util.Scanner;

public class PilihanTukarJenisAmunisi extends Pilihan {
    private Adegan oAdegan;
    private BarangSenjataTembak senjataTembakTerpilih;
    int indeksAmunisiTerpilih;
    private ArrayList<BarangSenjata> daftarAmunisiTerpilih;
    PilihanTukarJenisAmunisi(String dekripsi, Adegan oAdegan, BarangSenjataTembak senjataTembakTerpilih, int indeksAmunisiTerpilih, ArrayList<BarangSenjata> daftarAmunisiTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.senjataTembakTerpilih = senjataTembakTerpilih;
        this.indeksAmunisiTerpilih = indeksAmunisiTerpilih;
        this.daftarAmunisiTerpilih = daftarAmunisiTerpilih;
    }

    @Override
    public void aksi() {
        this.senjataTembakTerpilih.gantiAmunisiYangSedangDigunakan(this.daftarAmunisiTerpilih);
        this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(this.indeksAmunisiTerpilih, PengelolaanBarang.convertSenjataKeBarang(this.senjataTembakTerpilih.getDaftarAmunisiYangBerhasilDiambilIsiAmunisi()));
        this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarang(PengelolaanBarang.convertSenjataKeBarang(this.senjataTembakTerpilih.getDaftarAmunisiSedangDigunakanYangDikeluarkan()));
        boolean statusAmunisiDikeluarkanMemilikiTempatUntukDisimpan = false;
        System.out.println();
        if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus().isEmpty()){
            if(this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus().size() < this.senjataTembakTerpilih.getDaftarAmunisiSedangDigunakanYangDikeluarkan().size()){
                System.out.println("[ Amunisi yang dikeluarkan masih memiliki sisa, karena penyimpanan utama tidak cukup. ]");
            }else{
                System.out.println("[ Barang berhasil ditukarkan ]");
                statusAmunisiDikeluarkanMemilikiTempatUntukDisimpan = true;
            }
            this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(this.indeksAmunisiTerpilih, this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangUntukDihapus());
        }else{
            System.out.println("[ Amunisi yang dikeluarkan tidak dapat disimpan dimanapun, karena penyimpanan utama tidak cukup. ]");
        }
        if(!statusAmunisiDikeluarkanMemilikiTempatUntukDisimpan){
            System.out.println();
            System.out.printf("%2d. Urungkan Tukar Amunisi\n", 1);
            System.out.printf("%2d. Hapus Amunisi Yang Dikeluarkan\n", 2);
            Scanner oScan = new Scanner(System.in);
            System.out.print("Masukkan Pilihan : ");
            int input = oScan.nextInt();
            if(input < 1 || input > 2){
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia. ]");
            }else if(input == 1){
                /*coming soon*/
            }else if(input == 2){
                /*coming soon*/
            }
        }
    }
}
