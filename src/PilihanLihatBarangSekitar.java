import java.util.ArrayList;
import java.util.HashMap;

public class PilihanLihatBarangSekitar extends Pilihan {

    private Adegan oAdegan;

    PilihanLihatBarangSekitar(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        boolean kembaliKeMenuUtama = false;
        while(!kembaliKeMenuUtama){
            HashMap<Integer, ArrayList<Barang>> barangPilihan = this.oAdegan.pilihBarangSekitarAdegan(this.dekripsi);
            if(barangPilihan != null){
                PilihanAmbilBarangSekitarAdegan oPilihanAmbilBarangSekitarAdegan = new PilihanAmbilBarangSekitarAdegan(this.dekripsi, this.oAdegan, barangPilihan);
                oPilihanAmbilBarangSekitarAdegan.aksi();
            }else{
                kembaliKeMenuUtama = true;
            }
        }
    }
}


//        boolean validasiPilihanKembali = false;
//        while(!validasiPilihanKembali){
//            System.out.println();
//            System.out.println("Aksi : Melihat barang di sekitar");
//            for(ArrayList<Barang> daftarBarangTertentu : this.oAdegan.getDaftarBarang()){
//                if(!daftarBarangTertentu.isEmpty()){
//                    System.out.printf("-%-20s (%d)\n", daftarBarangTertentu.get(0).nama, daftarBarangTertentu.size());
//                }
//            }
//            PilihanAmbilBarangSekitarAdegan oPilihanAmbilBarangSekitarAdegan =  new PilihanAmbilBarangSekitarAdegan("Ambil barang sekitar adegan", this.oAdegan);
//            /* jika barang di adegan ini kosong maka */
//            if(this.oAdegan.getDaftarBarang().isEmpty()){
//                System.out.println("");
//                System.out.println("[ Tidak ditemukan barang apapun. ]");
//                System.out.println("");
//                System.out.printf("%2d. %s\n", 0, "Kembali");
//            }else{
//                System.out.printf("%2d. %s\n", 1, oPilihanAmbilBarangSekitarAdegan.getDekripsi());
//                System.out.printf("%2d. %s\n", 0, "Kembali");
//            }
//
//            /* mengecek saja, bisa di buat komentar */
//            //System.out.println("DaftarBarang = " + this.oAdegan.getDaftarBarangById());
//
//            System.out.print("Masukkan Pilihan : ");
//            Scanner oScan = new Scanner(System.in);
//            switch (oScan.nextInt()){
//                case 0:
//                    validasiPilihanKembali = true;
//                    break;
//                case 1:
//                    /* Jika daftarBarang tidak kosong maka... */
//                    if(!this.oAdegan.getDaftarBarang().isEmpty()){
//                        /* Jalankan aksi ambil barang secara per-daftar-barang tertentu */
//                        oPilihanAmbilBarangSekitarAdegan.aksi();
//                        break;
//                    }
//
//                default:
//                    System.out.println();
//                    System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
//                    break;
//            }
//        }

/* daftar pilihan jika barang tidak kosong */
//            PilihanAmbilSemuaBarang oPilihanAmbilSemuaBarang = new PilihanAmbilSemuaBarang("Ambil semua barang", this.oAdegan);

//  System.out.printf("%2d. %s\n", 1, oPilihanAmbilSemuaBarang.getDekripsi());

//            for(Map.Entry<String, HashMap<Integer, ArrayList<Barang>>> oKategoriBarang : this.oAdegan.getPengelolaanBarang().getDaftarBarangKeseluruhanByKategori().entrySet()){
//                for (Map.Entry<Integer, ArrayList<Barang>> oDaftarBarang : oKategoriBarang.getValue().entrySet()) {
//                    if(!oDaftarBarang.getValue().isEmpty()){
//                        System.out.printf("-%-20s (%d)\n", oDaftarBarang.getValue().get(0).nama, oDaftarBarang.getValue().size());
//                    }
//                }
//            }
