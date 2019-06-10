import java.util.Scanner;

public class PilihanLihatDeskripsiSenjataTembak extends PilihanLihatIsiKantong {
    private int indeksSenjataTembakTerpilih;
    private BarangSenjataTembak senjataTembakTerpilih;

    PilihanLihatDeskripsiSenjataTembak(String dekripsi, Adegan oAdegan, int indeksSenjataTembakTerpilih, BarangSenjataTembak senjataTembakTerpilih) {
        super(dekripsi, oAdegan);
        this.indeksSenjataTembakTerpilih = indeksSenjataTembakTerpilih;
        this.senjataTembakTerpilih = senjataTembakTerpilih;
    }

    @Override
    public void aksi() {
        System.out.printf("%-25s : %d\n", "Kekuatan", senjataTembakTerpilih.getKekuatan());
        System.out.printf("%-25s : %d/%d\n", "Isi Amunisi", senjataTembakTerpilih.getJumlahAmunisiSedangDigunakan(),
                senjataTembakTerpilih.getBatasMaxAmunisiDigunakan());
        System.out.println();
        System.out.printf("%2d. Gunakan Senjata\n", 1);
        System.out.printf("%2d. %-20s\n", 2, "Isi Amunisi");
        System.out.printf("%2d. %-20s | (senjata akan dihapus, tidak dapat dikembalikan)\n", 3, "Buang Senjata");
        System.out.printf("%2d. Kembali\n", 0);
        System.out.print("Masukkan Pilihan : ");
        Scanner oScan = new Scanner(System.in);

        switch(oScan.nextInt()){
            case 0:
                this.kembaliKeMenuSebelumnya = true;
                break;
            case 1:
                /* Proses gunakan senjata */
                this.gunakanBarang(senjataTembakTerpilih);
                break;
            case 2:
                PilihanIsiPeluru oPilihanIsiPeluru = new PilihanIsiPeluru("Isi Peluru", this.oAdegan, senjataTembakTerpilih);
                oPilihanIsiPeluru.aksi();
                if(oPilihanIsiPeluru.isStatusIsiAmunisiBerhasil()){
                    this.kembaliKeMenuSebelumnya = true;
                }
                break;
            case 3:
                System.out.println();
                System.out.println("[ Apakah anda yakin akan menghapus barang ini ? | tidak(t) / ya(y) ]");
                String input = oScan.next();
                if(!input.equals("t")){
                    this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(indeksSenjataTembakTerpilih, senjataTembakTerpilih);
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

    private void gunakanBarang(BarangSenjataTembak barangPilihan){
        this.oAdegan.oPlayer.gunakanSenjataDariDaftarBarangTerbatas(barangPilihan);
        System.out.println();
        System.out.printf("[ %s berhasil digunakan ]\n", barangPilihan.nama);
    }
}
