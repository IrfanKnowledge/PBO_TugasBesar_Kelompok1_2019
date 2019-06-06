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
            System.out.printf("%2d. Kembali\n", 0);
            System.out.println();
            System.out.print("Masukkan Pilihan : ");
            Scanner oScan = new Scanner(System.in);
            int input = oScan.nextInt();
            if(input < 0 || input > this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas().size()){
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia ]");
            }else if(input == 0){
                validasiKembaliKeDeskripsiSenjata = true;
            }else if(!(this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas().get(input-1).get(0) instanceof BarangSenjataTembak)){
                System.out.println();
                System.out.println("[ Barang bukan senjata tembak ]");
            }else{
                BarangSenjataTembak senjataTembakPilihan = (BarangSenjataTembak) this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas().get(input-1).get(0);
                /* proses isi atau tukar amunisi */
                if(senjataTembakPilihan.getIdAmunisiUtamaYangBisaDigunakan() != daftarBarangTerpilih.get(0).idBarang && !senjataTembakPilihan.getDaftarAmunisiYangBisaDigunakan().containsKey(daftarBarangTerpilih.get(0).idBarang)){
                    System.out.println();
                    System.out.println("[ Amunisi tidak cocok ]");

                /* proses isi amunisi */
                }else if(senjataTembakPilihan.getIdAmunisiUtamaYangBisaDigunakan() == daftarBarangTerpilih.get(0).idBarang){
                    senjataTembakPilihan.isiAmunisi(daftarBarangTerpilih);
                    for (BarangSenjata amunisiTerambil : senjataTembakPilihan.getDaftarAmunisiYangBerhasilDiambilIsiAmunisi()) {
                        this.oAdegan.oPlayer.hapusBarangDariPenyimpanan(amunisiTerambil);
                    }
                    validasiKembaliKeDeskripsiSenjata = true;
                /* proses tukar amunisi */
                }else if(senjataTembakPilihan.getDaftarAmunisiYangBisaDigunakan().containsKey(daftarBarangTerpilih.get(0).idBarang)){
                    senjataTembakPilihan.gantiAmunisiYangSedangDigunakan(daftarBarangTerpilih);
                    for (BarangSenjata amunisiTerambil : senjataTembakPilihan.getDaftarAmunisiYangBerhasilDiambilIsiAmunisi()) {
                        this.oAdegan.oPlayer.hapusBarangDariPenyimpanan(amunisiTerambil);
                    }
                    validasiKembaliKeDeskripsiSenjata = true;
                }
            }
        }
    }
}
