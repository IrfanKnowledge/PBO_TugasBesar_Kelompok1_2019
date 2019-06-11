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

    public MenuPengelolaanBarang(){}

    public ArrayList<Barang> pilihBarangKeseluruhanByKategoriTertentu(String aksi, String kategori){
        boolean validasiPilihan = false;
        int input = 0;
        HashMap<Integer, Barang> tempPilihan = new HashMap<>();
        while(!validasiPilihan){
            int jumlahSlotTergunakan = 0;
            System.out.println();
            System.out.println("Aksi : " + aksi);
            if(!this.oPengelolaanBarang.getDaftarBarangKeseluruhanByKategori().containsKey(kategori)){
                System.out.println();
                System.out.printf("[ Barang %s tidak ada atau kosong ]\n", kategori);
                System.out.println();
            }else{
                int i=0;
                for (Map.Entry<Integer, ArrayList<Barang>> oDaftarBarang: this.oPengelolaanBarang.getDaftarBarangKeseluruhanByKategori().get(kategori).entrySet()) {
                    if(oDaftarBarang.getValue().isEmpty()){
                        continue;
                    }
                    System.out.println(String.format("%2d. %-20s | (%d)",
                            ++i, oDaftarBarang.getValue().get(0).nama, oDaftarBarang.getValue().size()));
                    tempPilihan.put(i, oDaftarBarang.getValue().get(0));
                    jumlahSlotTergunakan++;
                }
            }
            System.out.printf("%2d. Kembali\n", 0);
            Scanner oScan = new Scanner(System.in);
            System.out.print("Masukkan Pilihan : ");
            input = oScan.nextInt();

            if(input < 0 || input > jumlahSlotTergunakan){
                System.out.println();
                System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
            }else if(input == 0){
                validasiPilihan = true;
            }else{
                validasiPilihan = true;
            }
        }
        if(input == 0){
            return null;
        }else{
            return this.oPengelolaanBarang.getDaftarBarangKeseluruhanByKategori().get(kategori).get(tempPilihan.get(input).idBarang);
        }
    }

    private void printBarang(ArrayList<ArrayList<Barang>> daftarBarangInput, boolean statusDaftarBarangBersifatTerbatas){
        int i=0;
        ArrayList<ArrayList<Barang>> daftarYangKosong = new ArrayList<>();
        for (ArrayList<Barang> daftarBarangTertentu : daftarBarangInput) {
            if(daftarBarangTertentu.isEmpty()){
                if(!statusDaftarBarangBersifatTerbatas){
                    daftarYangKosong.add(daftarBarangTertentu);
                    continue;
                }else{
                    System.out.printf("%2d. < slot kosong >\n", ++i);
                }
            }else{
                System.out.printf("%2d. %-20s (%d)", ++i, daftarBarangTertentu .get(0).nama, daftarBarangTertentu .size());
                if(daftarBarangTertentu .get(0) instanceof BarangSenjata){
                    System.out.printf(" | kekuatan : %d ", ((BarangSenjata) daftarBarangTertentu .get(0)).getKekuatan());
                }
                if(daftarBarangTertentu .get(0) instanceof BarangSenjataJarakDekat){
                    System.out.printf(" | Ketahanan : %d/%d",
                            ((BarangSenjataJarakDekat) daftarBarangTertentu.get(0)).getKetahanan(),
                            ((BarangSenjataJarakDekat) daftarBarangTertentu.get(0)).getBatasMaxKetahanan());
                }
                if(daftarBarangTertentu .get(0) instanceof BarangSenjataTembak){
                    System.out.printf("| Isi Amunisi : %d/%d",
                            ((BarangSenjataTembak) daftarBarangTertentu .get(0)).getJumlahAmunisiSedangDigunakan(),
                            ((BarangSenjataTembak) daftarBarangTertentu .get(0)).getBatasMaxAmunisiDigunakan());
                }
                System.out.println();
            }
        }
        if(!daftarYangKosong.isEmpty() && daftarYangKosong.size() >= daftarBarangInput.size()){
            System.out.println();
            System.out.println("[ kosong ]");
            System.out.println();
        }
        if(!daftarYangKosong.isEmpty()){
            for (ArrayList<Barang> daftarBarangYangDihapus : daftarYangKosong) {
                daftarBarangInput.remove(daftarBarangYangDihapus);
            }
        }
    }

    private HashMap<Integer, ArrayList<Barang>> pilihBarang(String aksi, ArrayList<ArrayList<Barang>> daftarBarangInput, boolean statusDaftarBarangBersifatTerbatas){
        boolean validasiPilihan = false;
        int input = 0;
        while(!validasiPilihan){
            System.out.println();
            System.out.println("Aksi : " + aksi);
            if(daftarBarangInput.isEmpty()){
                System.out.println();
                System.out.println("[ Tidak ada barang yang dapat diambil ]");
                System.out.println();
            }else{
                this.printBarang(daftarBarangInput, statusDaftarBarangBersifatTerbatas);
            }
            System.out.printf("%2d. Kembali\n", 0);
            Scanner oScan = new Scanner(System.in);
            System.out.print("Masukkan Pilihan : ");
            input = oScan.nextInt();

            if(input < 0 || input > daftarBarangInput.size()) {
                System.out.println();
                System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
            }else if(input == 0){
                validasiPilihan = true;
            }else if(daftarBarangInput.get(input-1).isEmpty()){
                System.out.println();
                System.out.println("[ Slot tersebut kosong. ]");
            }else{
                validasiPilihan = true;
            }
        }
        if(input == 0){
            return null;
        }else{
            HashMap<Integer, ArrayList<Barang>> temp = new HashMap<>();
            temp.put((input-1), daftarBarangInput.get(input-1));
            return temp;
        }
    }

    public int pilihIndeksSlotBarangDariDaftarBarangTertentu(String aksi, ArrayList<ArrayList<Barang>> daftarBarangInput){
        boolean validasiPilihan = false;
        int input = 0;
        while (!validasiPilihan) {
            this.printBarang(daftarBarangInput, true);
            System.out.printf("%2d. Kembali\n", 0);
            Scanner oScan = new Scanner(System.in);
            System.out.print("Masukkan Pilihan : ");
            input = oScan.nextInt();

            if(input < 1 || input > daftarBarangInput.size()) {
                System.out.println();
                System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
            }else{
                validasiPilihan = true;
            }
        }
        return input-1;
    }

    public HashMap<Integer, ArrayList<Barang>>  pilihBarangDariDaftarBarangTertentu(String aksi, ArrayList<ArrayList<Barang>> daftarBarangInput, boolean statusDaftarBarangBersifatTerbatas){
        return this.pilihBarang(aksi, daftarBarangInput, statusDaftarBarangBersifatTerbatas);
    }
}
