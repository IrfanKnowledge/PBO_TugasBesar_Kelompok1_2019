import java.util.ArrayList;
import java.util.Scanner;

public class PilihanLihatDeskripsiGunakanAmunisi extends Pilihan {
    private Adegan oAdegan;
    private ArrayList<BarangSenjata> daftarAmunisiTerpilih;
    private int indeksAmunisiTerpilih;
    private boolean validasiKembaliKeDeskripsiSenjata = false;

    PilihanLihatDeskripsiGunakanAmunisi(String dekripsi, Adegan oAdegan, ArrayList<BarangSenjata> daftarAmunisiTerpilih, int indeksAmunisiTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.daftarAmunisiTerpilih = daftarAmunisiTerpilih;
        this.indeksAmunisiTerpilih = indeksAmunisiTerpilih;
    }

    @Override
    public void aksi() {
        while(!validasiKembaliKeDeskripsiSenjata){
            System.out.println();
            System.out.println("Aksi : " + this.dekripsi);
            int i =0;
            for (ArrayList<Barang> barangTerbatas : this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas()) {
                i++;
                if(barangTerbatas.isEmpty()){
                    System.out.printf("%2d. < slot kosong >\n", i);
                }else {
                    System.out.printf("%2d. %-20s (%d)\n", i, barangTerbatas.get(0).nama, barangTerbatas.size());
                }
            }
            System.out.printf("%2d. Kembali\n", 0);
            System.out.print("Masukkan Pilihan : ");
            Scanner oScan = new Scanner(System.in);
            int input = oScan.nextInt();
            if(input < 0 || input > this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas().size()){
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia ]");
            }else if(input == 0){
                validasiKembaliKeDeskripsiSenjata = true;
            }else if(this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas().get(input-1).isEmpty()){
                System.out.println();
                System.out.println("[ Slot tersebut kosong. ]");
            }else if(!(this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas().get(input-1).get(0) instanceof BarangSenjataTembak)){
                System.out.println();
                System.out.println("[ Tidak dapat digunakan ke barang tersebut ]");
            }else{
                BarangSenjataTembak senjataTembakPilihan = (BarangSenjataTembak) this.oAdegan.oPlayer.getPengelolaanBarang().getDaftarBarangTerbatas().get(input-1).get(0);
                if(senjataTembakPilihan.getIdAmunisiUtamaYangBisaDigunakan() == daftarAmunisiTerpilih.get(0).idBarang){
                    /* proses isi amunisi */
                    PilihanIsiAmunisi oPilihanIsiAmunisi = new PilihanIsiAmunisi("Isi Peluru", this.oAdegan, senjataTembakPilihan, daftarAmunisiTerpilih, indeksAmunisiTerpilih);
                    oPilihanIsiAmunisi.aksi();
                    if(oPilihanIsiAmunisi.isStatusIsiAmunisiBerhasil()){
                        validasiKembaliKeDeskripsiSenjata = true;
                    }
                /* proses tukar amunisi */
                }else if(senjataTembakPilihan.getDaftarAmunisiYangBisaDigunakan().containsKey(daftarAmunisiTerpilih.get(0).idBarang)){
//                    senjataTembakPilihan.gantiAmunisiYangSedangDigunakan(daftarAmunisiTerpilih);
//                    for (BarangSenjata amunisiTerambil : senjataTembakPilihan.getDaftarAmunisiYangBerhasilDiambilIsiAmunisi()) {
//                        this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(amunisiTerambil);
//                    }
                    validasiKembaliKeDeskripsiSenjata = true;
                }else{
                    System.out.println();
                    System.out.println("[ Amunisi tidak cocok ]");
                }
            }
        }
    }

    public boolean isValidasiKembaliKeDeskripsiSenjata(){
        return this.validasiKembaliKeDeskripsiSenjata;
    }
}
