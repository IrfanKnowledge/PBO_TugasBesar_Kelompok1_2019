import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuPengelolaanBarang {

    /* penggunaannya diatur dalam method proses internal */
    private PengelolaanBarang oPengelolaanBarang;

    public MenuPengelolaanBarang(PengelolaanBarang oPengelolaanBarang) {
        this.oPengelolaanBarang = oPengelolaanBarang;
    }

    public ArrayList<Barang> pilihBarangKeseluruhanByKategoriTertentu(String aksi, String kategori){
        System.out.println();
        System.out.println("Aksi : " + aksi);
        if(!this.oPengelolaanBarang.getDaftarBarangKeseluruhanByKategori().containsKey(kategori)){
            System.out.println();
            System.out.println("[ Barang %s tidak ada atau kosong ]");
            return null;
        }else{
            HashMap<Integer, Barang> tempPilihan = new HashMap<>();
            int i=0;
            for (Map.Entry<Integer, ArrayList<Barang>> oDaftarBarang: this.oPengelolaanBarang.getDaftarBarangKeseluruhanByKategori().get(kategori).entrySet()) {
                System.out.printf(String.format("%2d. %-20s | (%-3d)",
                        ++i, oDaftarBarang.getValue().get(0).nama, oDaftarBarang.getValue().size()));
                tempPilihan.put(i, oDaftarBarang.getValue().get(0));
            }
            System.out.printf("%2d. Kembali\n", 0);
            Scanner oScan = new Scanner(System.in);
            System.out.print("Masukkan Pilihan : ");
            int input = oScan.nextInt();

            if(input < 0 || input > this.oPengelolaanBarang.getDaftarBarangKeseluruhanByKategori().get(kategori).size()){
                System.out.println();
                System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
                return null;
            }else if(input == 0){
                return null;
            }else{
                return this.oPengelolaanBarang.getDaftarBarangKeseluruhanByKategori().get(kategori).get(tempPilihan.get(input).idBarang);
            }
        }
    }

    public ArrayList<Barang> pilihBarangDariDaftarBarangTerbatas(String aksi){
        System.out.println();
        System.out.println("Aksi : " + aksi);
        int i=0;
        for (ArrayList<Barang> oDaftarBarang: this.oPengelolaanBarang.getDaftarBarangTerbatas()) {
            i++;
            System.out.printf("%2d. %-20s (%d)", i, oDaftarBarang.get(0).nama, oDaftarBarang.size());
            if(oDaftarBarang.get(0) instanceof BarangSenjata){
                System.out.printf(" | kekuatan : %d ", ((BarangSenjata) oDaftarBarang.get(0)).getKekuatan());
            }
            if(oDaftarBarang.get(0) instanceof BarangSenjataJarakDekat){
                System.out.printf(" | Ketahanan : %d",
                        ((BarangSenjataJarakDekat) oDaftarBarang.get(0)).getKetahanan());
            }
            if(oDaftarBarang.get(0) instanceof BarangSenjataTembak){
                System.out.printf("| Isi Amunisi : %d/%d",
                        ((BarangSenjataTembak) oDaftarBarang.get(0)).getJumlahAmunisiDigunakan(),
                        ((BarangSenjataTembak) oDaftarBarang.get(0)).getBatasMaxAmunisiDigunakan());
            }
            System.out.println();
        }

        System.out.printf("%2d. Kembali\n", 0);
        Scanner oScan = new Scanner(System.in);
        System.out.print("Masukkan Pilihan : ");
        int input = oScan.nextInt();

        if(input < 0 || input > this.oPengelolaanBarang.getBatasMaxSlotDaftarBarangTerbatas()){
            System.out.println();
            System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
            return null;
        }else if(input == 0){
            return null;
        }else{
            return this.oPengelolaanBarang.getDaftarBarangTerbatas().get(input-1);
        }
    }
}
