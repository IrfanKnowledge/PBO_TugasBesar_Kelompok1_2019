import java.util.Scanner;

public class PilihanLihatDeskripsiSenjataJarakDekat extends Pilihan {
    private Adegan oAdegan;
    private BarangSenjataJarakDekat barangTerpilih;
    private boolean validasiKembaliKeDaftarBarangTerbatas = false;

    PilihanLihatDeskripsiSenjataJarakDekat(String dekripsi, Adegan oAdegan, BarangSenjataJarakDekat barangTerpilih){
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.barangTerpilih = barangTerpilih;
    }

    @Override
    public void aksi() {
        System.out.printf("%-25s : %d\n", "Kekuatan", barangTerpilih.getKekuatan());
        System.out.printf("%-25s : %d / %d\n", "Ketahanan", barangTerpilih.getKetahanan(), barangTerpilih.getBatasMaxKetahanan());
        System.out.printf("%-25s : %d\n", "Kemampuan diperbaiki", barangTerpilih.getJumlahKemampuanDiperbaiki());
        System.out.println();
        System.out.printf("%2d. Gunakan Senjata\n", 1);
        System.out.printf("%2d. %-20s\n", 2, "Perbaiki Senjata");
        System.out.printf("%2d. %-20s | (senjata akan dihapus, tidak dapat dikembalikan)\n", 3, "Buang Senjata");
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
                this.perbaikiSenjata(barangTerpilih);
                break;
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

    private void gunakanBarang(BarangSenjataJarakDekat barangPilihan){
        this.oAdegan.oPlayer.simpanKembaliSenjataYangDigunakan();
        this.oAdegan.oPlayer.gunakanSenjataDariPenyimpanan(barangPilihan);
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
            Barang komponenCrafting = this.oAdegan.oPlayer.getPengelolaanBarang().pilihBarang(barangPilihan.getKategoriBarangUntukPerbaikan(), barangPilihan.getIdBarangUntukPerbaikan());
            if(komponenCrafting != null){
                /* proses memperbaiki barang */
                barangPilihan.perbaikiBarang(komponenCrafting);
                if(!barangPilihan.isStatusPerbaikiBarangBerhasil()){
                    System.out.println();
                    System.out.println("[ Perbaiki barang gagal (id barang komponen tidak sesuai) ]");
                }else{
                    System.out.println();
                    System.out.println("[ Perbaiki barang berhasil ]");
                }
                /* hapus satu komponen crafting tersebut di penyimpanan */
                this.oAdegan.oPlayer.getPengelolaanBarang().hapusBarang(komponenCrafting);
            }else{
                System.out.println();
                System.out.println("[ Persediaan Komponen Crafting untuk perbaikan senjata, kosong. ]");
            }
        }

    }

    public boolean isValidasiKembaliKeDaftarBarangTerbatas() {
        return validasiKembaliKeDaftarBarangTerbatas;
    }
}
