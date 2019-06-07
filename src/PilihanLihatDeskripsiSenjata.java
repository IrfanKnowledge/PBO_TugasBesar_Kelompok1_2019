import java.util.ArrayList;
import java.util.Scanner;

public class PilihanLihatDeskripsiSenjata extends Pilihan {
    private Adegan oAdegan;
    private ArrayList<BarangSenjata> daftarBarangTerpilih;
    private boolean validasiKembaliKeDaftarBarangTerbatas = false;

    PilihanLihatDeskripsiSenjata(String dekripsi, Adegan oAdegan, ArrayList<Barang> daftarBarangTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.daftarBarangTerpilih = this.oAdegan.oPlayer.getPengelolaanBarang().convertBarangKeSenjata(daftarBarangTerpilih);
    }


    @Override
    public void aksi() {
        System.out.printf("%-25s : %d\n", "Kekuatan", daftarBarangTerpilih.get(0).getKekuatan());
        System.out.println();
        if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarSenjataTermasukAmunisi().contains(daftarBarangTerpilih.get(0).idBarang)){
            System.out.printf("%2d. Gunakan Senjata\n", 1);
            System.out.printf("%2d. %-20s | (senjata akan dihapus, tidak dapat dikembalikan)\n", 2, "Buang Senjata");
        }else{
            System.out.printf("%2d. Gunakan Amunisi\n", 1);
            System.out.printf("%2d. %-20s | (Amunisi akan dihapus, tidak dapat dikembalikan)\n", 2, "Buang Amunisi");
        }
        System.out.printf("%2d. Kembali\n", 0);
        System.out.print("Masukkan Pilihan : ");
        Scanner oScan = new Scanner(System.in);

        switch(oScan.nextInt()){
            case 0:
                this.validasiKembaliKeDaftarBarangTerbatas = true;
                break;
            case 1:
                /* Proses gunakan senjata */
                this.gunakanBarang(daftarBarangTerpilih);
                break;
            case 2:
                System.out.println();
                System.out.println("[ Apakah anda yakin akan menghapus barang ini ? | tidak(t) / ya(y) ]");
                String input = oScan.next();
                if(!input.equals("t")){
                    this.oAdegan.oPlayer.hapusBarangDariPenyimpanan(this.oAdegan.oPlayer.getPengelolaanBarang().convertSenjataKeBarang(daftarBarangTerpilih));
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

    private void gunakanBarang(ArrayList<BarangSenjata> barangPilihan){
        if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarSenjataTermasukAmunisi().contains(daftarBarangTerpilih.get(0).idBarang)){
            this.oAdegan.oPlayer.simpanKembaliSenjataYangDigunakan();
            this.oAdegan.oPlayer.gunakanSenjataDariPenyimpanan(barangPilihan);
            System.out.println();
            System.out.printf("[ %s berhasil digunakan ]\n", barangPilihan.get(0).nama);
        }else{
            PilihanLihatDeskripsiGunakanAmunisi oPilihanLihatDeskripsiGunakanAmunisi = new PilihanLihatDeskripsiGunakanAmunisi(this.dekripsi + "(Gunakan Amunisi)", this.oAdegan, this.daftarBarangTerpilih);
            oPilihanLihatDeskripsiGunakanAmunisi.aksi();
        }
    }

    public boolean isValidasiKembaliKeDaftarBarangTerbatas() {
        return validasiKembaliKeDaftarBarangTerbatas;
    }
}
