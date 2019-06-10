import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PilihanLihatDeskripsiSenjataJarakDekat extends PilihanLihatIsiKantong {
    private int indeksSenjataJarakDekatTerpilih;
    private BarangSenjataJarakDekat senjataJarakDekatTerpilih;
    private ArrayList<Pilihan> daftarPilihan = new ArrayList<>();

    PilihanLihatDeskripsiSenjataJarakDekat(String dekripsi, Adegan oAdegan, int indeksSenjataJarakDekatTerpilih, BarangSenjataJarakDekat senjataJarakDekatTerpilih){
        super(dekripsi, oAdegan);
        this.oAdegan = oAdegan;
        this.indeksSenjataJarakDekatTerpilih = indeksSenjataJarakDekatTerpilih;
        this.senjataJarakDekatTerpilih = senjataJarakDekatTerpilih;
        ArrayList<BarangSenjata> daftarSenjata = new ArrayList<>();
        daftarSenjata.add(senjataJarakDekatTerpilih);
        this.daftarPilihan.add(new PilihanGunakanSenjata("Gunakan Barang", this.oAdegan, daftarSenjata));
    }

    @Override
    public void aksi() {
        System.out.printf("%-25s : %d\n", "Kekuatan", senjataJarakDekatTerpilih.getKekuatan());
        System.out.printf("%-25s : %d / %d\n", "Ketahanan", senjataJarakDekatTerpilih.getKetahanan(), senjataJarakDekatTerpilih.getBatasMaxKetahanan());
        System.out.printf("%-25s : %d\n", "Kemampuan diperbaiki", senjataJarakDekatTerpilih.getJumlahKemampuanDiperbaiki());
        System.out.println();
        System.out.printf("%2d. Gunakan Senjata\n", 1);
        System.out.printf("%2d. %-20s\n", 2, "Perbaiki Senjata");
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
                this.gunakanBarang(senjataJarakDekatTerpilih);
                break;
            case 2:
                this.perbaikiSenjata(senjataJarakDekatTerpilih);
                break;
            case 3:
                System.out.println();
                System.out.println("[ Apakah anda yakin akan menghapus senjata ini ? | tidak(t) / ya(y) ]");
                System.out.printf("Masukkan Pilihan : ");
                String input = oScan.next();
                if(!input.equals("t")){
                    this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(indeksSenjataJarakDekatTerpilih, senjataJarakDekatTerpilih);
                    System.out.println();
                    System.out.println("[ Senjata telah dihapus ]");
                    this.kembaliKeMenuSebelumnya = true;
                }
                break;

            default:
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia. ]");
                break;
        }
    }

    private void gunakanBarang(BarangSenjataJarakDekat barangPilihan){
        this.oAdegan.oPlayer.gunakanSenjataDariDaftarBarangTerbatas(barangPilihan);
        System.out.println();
        System.out.printf("[ %s berhasil digunakan ]\n", barangPilihan.nama);
    }

    private void perbaikiSenjata(BarangSenjataJarakDekat barangPilihan){
        if(!barangPilihan.isStatusKemampuanDiperbaiki()){
            System.out.println();
            System.out.println("[ Senjata sudah tidak bisa diperbaiki ]");
        }else if(barangPilihan.isStatusKetahananMasihMaksimal()){
            System.out.println();
            System.out.println(String.format("[ %s ", barangPilihan.nama) + "masih memiliki ketahanan 100% ]");
        }else{
            /* ambil kebutuhan komponen Crafting untuk perbaikan */
            Barang komponenCrafting = this.oAdegan.oPlayer.getPengelolaanBarang().pilihBarangDariDaftarBarangTidakTerbatas(barangPilihan.getKategoriBarangUntukPerbaikan(), barangPilihan.getIdBarangUntukPerbaikan());
            if(komponenCrafting != null){
                /* proses memperbaiki barang */
                barangPilihan.perbaikiBarang(komponenCrafting);
                if(!barangPilihan.isStatusPerbaikiBarangBerhasil()){
                    System.out.println();
                    System.out.println("[ Perbaiki senjata gagal (id barang komponen tidak sesuai) ]");
                }else{
                    System.out.println();
                    System.out.println("[ Perbaiki senjata berhasil ]");
                }
                /* hapus satu komponen crafting tersebut di penyimpanan */
                this.oAdegan.oPlayer.getPengelolaanBarang().hapusBarangDariPenyimpananTidakTerbatas(komponenCrafting);
            }else{
                System.out.println();
                System.out.println("[ Persediaan Komponen Crafting untuk perbaikan senjata, kosong. ]");
            }
        }

    }
}
