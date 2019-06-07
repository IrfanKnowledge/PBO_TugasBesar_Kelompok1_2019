import java.util.ArrayList;
import java.util.Scanner;

public class PilihanLihatDeskripsiSenjataTembak extends Pilihan {
    private Adegan oAdegan;
    private BarangSenjataTembak barangTerpilih;
    private boolean validasiKembaliKeDaftarBarangTerbatas = false;

    PilihanLihatDeskripsiSenjataTembak(String dekripsi, Adegan oAdegan, BarangSenjataTembak barangTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.barangTerpilih = barangTerpilih;
    }

    @Override
    public void aksi() {
        System.out.printf("%-25s : %d\n", "Kekuatan", barangTerpilih.getKekuatan());
        System.out.printf("%-25s : %d/%d\n", "Isi Amunisi", barangTerpilih.getJumlahAmunisiSedangDigunakan(),
                barangTerpilih.getBatasMaxAmunisiDigunakan());
        System.out.println();
        System.out.printf("%2d. Gunakan Senjata\n", 1);
        System.out.printf("%2d. %-20s\n", 2, "Isi Amunisi");
        System.out.printf("%2d. %-20s\n", 3, "Ganti Amunisi");
        System.out.printf("%2d. %-20s | (senjata akan dihapus, tidak dapat dikembalikan)\n", 4, "Buang Senjata");
        System.out.printf("%2d. Kembali\n", 0);
        System.out.print("Masukkan Pilihan : ");
        Scanner oScan = new Scanner(System.in);

        switch(oScan.nextInt()){
            case 0:
                this.validasiKembaliKeDaftarBarangTerbatas = true;
                break;
            case 1:
                /* Proses gunakan senjata */
                this.gunakanBarang(barangTerpilih);
                break;
            case 2:
                this.isiPeluru(barangTerpilih);
            case 3:
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

    private void gunakanBarang(BarangSenjataTembak barangPilihan){
        this.oAdegan.oPlayer.simpanKembaliSenjataYangDigunakan();
        this.oAdegan.oPlayer.gunakanSenjataDariPenyimpanan(barangPilihan);
        System.out.println();
        System.out.printf("[ %s berhasil digunakan ]\n", barangPilihan.nama);
    }

    private void isiPeluru(BarangSenjataTembak barangPilihan){
        /* cek kondisi jumlah peluru senjata, masih penuh atau tidak  */
        if(barangPilihan.getJumlahKebutuhanIsiAmunisi() == 0){
            System.out.println();
            System.out.printf("[ %s memiliki jumlah peluru yang masih penuh ]\n", barangPilihan.nama);
        }else{
            ArrayList<Barang> daftarAmunisi = this.oAdegan.oPlayer.getPengelolaanBarang().pilihBarangBanyak(barangPilihan.getKategoriAmunisiUtamaYangDiperlukan(), barangPilihan.getIdAmunisiUtamaYangBisaDigunakan());
            if(daftarAmunisi == null){
                System.out.println();
                System.out.println("[ Persediaan Amunisi kosong. ]");
            }else{
                /* ambil jumlah kebutuhan isi peluru*/
                int jumlahKebutuhanPeluru = barangPilihan.getJumlahKebutuhanIsiAmunisi();

                /* proses isi peluru */
                barangPilihan.isiAmunisi(this.oAdegan.oPlayer.getPengelolaanBarang().convertBarangKeSenjata(daftarAmunisi));

                /* hapus daftarAmunisi yang ada di penyimpanan Player sesuai kebutuhan amunisi yang diperoleh */
                for (BarangSenjata amunisiTerambil : barangPilihan.getDaftarAmunisiYangBerhasilDiambilIsiAmunisi()) {
                    this.oAdegan.oPlayer.hapusBarangDariPenyimpanan(amunisiTerambil);
                }
                if(!barangPilihan.getDaftarAmunisiSedangDigunakanYangDikeluarkan().isEmpty()){
                    ArrayList<Barang> amunisiTerambil = new ArrayList<>();
                    amunisiTerambil.addAll(this.oAdegan.oPlayer.getPengelolaanBarang().convertSenjataKeBarang(barangPilihan.getDaftarAmunisiSedangDigunakanYangDikeluarkan()));
                    this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarang(amunisiTerambil);
                }
                System.out.println();
                System.out.println("[ Isi amunisi berhasil dilakukan ]");
            }
        }
    }

    public boolean isValidasiKembaliKeDaftarBarangTerbatas() {
        return validasiKembaliKeDaftarBarangTerbatas;
    }
}
