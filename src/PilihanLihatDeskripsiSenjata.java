import java.util.ArrayList;
import java.util.Scanner;

public class PilihanLihatDeskripsiSenjata extends PilihanLihatIsiKantong {
    private int indeksSenjataTerpilih;
    private ArrayList<BarangSenjata> daftarSenjataTerpilih = new ArrayList<>();

    PilihanLihatDeskripsiSenjata(String dekripsi, Adegan oAdegan, int indeksSenjataTerpilih, ArrayList<BarangSenjata> daftarSenjataTerpilih) {
        super(dekripsi, oAdegan);
        this.indeksSenjataTerpilih = indeksSenjataTerpilih;
        this.daftarSenjataTerpilih.addAll(daftarSenjataTerpilih);
    }

    @Override
    public void aksi() {
        System.out.printf("%-25s : %d\n", "Kekuatan", daftarSenjataTerpilih.get(0).getKekuatan());
        System.out.println();
        String kategori = "";
        if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarSenjataTermasukAmunisi().contains(daftarSenjataTerpilih.get(0).idBarang)){
            kategori = "Senjata";
        }else{
            kategori = "Amunisi";
        }
        System.out.printf("%2d. Gunakan %s\n", 1, kategori);
        System.out.printf("%2d. %-20s | (%s akan dihapus, tidak dapat dikembalikan)\n", 2, "Buang " + kategori, kategori);
        System.out.printf("%2d. Kembali\n", 0);
        System.out.print("Masukkan Pilihan : ");
        Scanner oScan = new Scanner(System.in);

        switch(oScan.nextInt()){
            case 0:
                this.kembaliKeMenuSebelumnya = true;
                break;
            case 1:
                /* Proses gunakan senjata */
                this.gunakanBarang(daftarSenjataTerpilih);
                break;
            case 2:
                System.out.println();
                System.out.printf("[ Apakah anda yakin akan menghapus %s ini ? | tidak(t) / ya(y) ]\n", kategori);
                System.out.print("Masukkan Pilihan : ");
                String input = oScan.next();
                if(!input.equals("t")){
                    this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(indeksSenjataTerpilih, this.oAdegan.oPlayer.getPengelolaanBarang().convertSenjataKeBarang(daftarSenjataTerpilih));
                    System.out.println();
                    System.out.println("[ Barang telah dihapus ]");
                    this.kembaliKeMenuSebelumnya = true;
                }
                break;

            default:
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia. ]");
                break;
        }
    }

    private void gunakanBarang(ArrayList<BarangSenjata> barangPilihan){
        if(!this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarSenjataTermasukAmunisi().contains(daftarSenjataTerpilih.get(0).idBarang)){
            this.oAdegan.oPlayer.gunakanSenjataDariDaftarBarangTerbatas(barangPilihan);
            System.out.println();
            System.out.printf("[ %s berhasil digunakan ]\n", barangPilihan.get(0).nama);
        }else{
            PilihanLihatDeskripsiGunakanAmunisi oPilihanLihatDeskripsiGunakanAmunisi = new PilihanLihatDeskripsiGunakanAmunisi(this.dekripsi + "(Gunakan Amunisi)", this.oAdegan, this.daftarSenjataTerpilih, indeksSenjataTerpilih);
            oPilihanLihatDeskripsiGunakanAmunisi.aksi();
            this.kembaliKeMenuSebelumnya = true;
        }
    }
}
