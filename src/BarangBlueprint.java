import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BarangBlueprint extends Barang{

    /* private karena terdapat pembatasan minimal nilai */
    private int jumlahHasilCrafting;

    /* private karena hanya membutuhkan tambah dan get(+ modifikasi) dan proses internal */
    private HashMap<Integer, ArrayList<Barang>> daftarKomponenCraftingDiperlukan = new HashMap<>();

    /* private karena hanya membutuhkan proses tambah efek dan get (+ telah dimodifikasi) efek saja */
    private HashMap<Integer, Efek> daftarEfekTambahan = new HashMap<>();

    BarangBlueprint(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                    boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual,
                    int jumlahHasilCrafting) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual);

        this.setJumlahHasilCrafting(jumlahHasilCrafting);
    }

    public void setJumlahHasilCrafting(int hasilCrafting){
        if(hasilCrafting <= 0){
            this.jumlahHasilCrafting = 1;
        }else{
            this.jumlahHasilCrafting = hasilCrafting;
        }
    }

    public int getJumlahHasilCrafting(){
        return jumlahHasilCrafting;
    }

    public void tambahdaftarKomponenCraftingDiperlukan(Barang oBarang){
        if(!this.daftarKomponenCraftingDiperlukan.containsKey(oBarang.idBarang)){
            ArrayList<Barang> tempBarang = new ArrayList<>();
            tempBarang.add(oBarang.cloning());
            this.daftarKomponenCraftingDiperlukan.put(oBarang.idBarang, tempBarang);
        }else{
            this.daftarKomponenCraftingDiperlukan.get(oBarang.idBarang).add(oBarang.cloning());
        }
    }

    public void tambahDaftarKomponenCraftingDiperlukan(Barang oBarang, int jumlahInstance){
        ArrayList<Barang> tempBarang = Cloning.cloning(oBarang, jumlahInstance);
        if(!this.daftarKomponenCraftingDiperlukan.containsKey(oBarang.idBarang)){
            this.daftarKomponenCraftingDiperlukan.put(oBarang.idBarang, tempBarang);
        }else{
            this.daftarKomponenCraftingDiperlukan.get(oBarang.idBarang).addAll(tempBarang);
        }
    }

    public void tambahDaftarKomponenCraftingDiperlukan(HashMap<Integer, ArrayList<Barang>> oBarang){
        for (Map.Entry<Integer, ArrayList<Barang>> isi : oBarang.entrySet()) {
            this.tambahDaftarKomponenCraftingDiperlukan(isi.getValue().get(0), isi.getValue().size());
        }
    }

    public HashMap<Integer, ArrayList<Barang>> getDaftarKomponenCraftingDiperlukan() {
        /* object HashMap dan ArrayList dibedakan agar tidak dapat memanipulasi daftarKomponenCraftingDiperlukan diluar Class ini
         * selain hanya bisa menggunakan method khusus untuk menambahkan object pada daftarKomponenCraftingDiperlukan,
         * kemudian isi HashMap tersebut dibuat object berbeda agar tidak dapat dimanipulasi diluar Class ini */
        HashMap<Integer, ArrayList<Barang>> temp = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Barang>> isi : this.daftarKomponenCraftingDiperlukan.entrySet()) {
            temp.put(isi.getKey(), Cloning.cloning(isi.getValue().get(0), isi.getValue().size()));
        }
        return temp;
    }

    public void tambahEfek(int id, Efek oEfek){
        this.daftarEfekTambahan.put(id, oEfek);
    }

    public void tambahEfek(HashMap<Integer, Efek> oDaftarEfek){
        this.daftarEfekTambahan.putAll(oDaftarEfek);
    }

    public HashMap<Integer, Efek> getDaftarEfekTambahan() {
        /* object HashMap dibedakan agar tidak dapat memanipulasi daftarEfekTambahan diluar Class ini
         * selain hanya bisa menggunakan method khusus untuk menambahkan object pada daftarEfekTambahan */
        HashMap<Integer, Efek> temp = new HashMap<>();
        temp.putAll(this.daftarEfekTambahan);
        return temp;
    }

    /* ===================METHOD BERIKUT PERLU DIREVISI===================== */
//    private ArrayList<Barang> gunakanBarangBlueprint(HashMap<Integer, ArrayList<Barang>> daftarKomponenCraftingDigunakan, Barang senjata, int efisiensiCrafting) {
//        //untuk pencocokan kebutuhan daftarKomponenCraftingDigunakan dan senjata untuk crafting dengan inputan daftarKomponenCraftingDigunakan dan Senjata
//        boolean statusKecocokan = true;
//
//        //untuk pencocokan kebutuhan daftarKomponenCraftingDigunakan untuk blue print ini dengan daftarKomponenCrafting inputan
//        for (Map.Entry<Integer, ArrayList<Barang>> record: this.daftarKomponenCrafting.entrySet()){
//            //jika inputan komponen terdapat key dari kebutuhan komponen crafting maka...
//            if(daftarKomponenCrafting.containsKey(record.getKey())){
//                //jika jumlah inputan komponen tertentu TIDAK MENCUKUPI jumlah kebutuhan komponen tertentu untuk crafting maka..
//                if(record.getValue().size() > daftarKomponenCrafting.get(record.getKey()).size()){
//                    statusKecocokan = false;
//                    break;
//                }
//            //jika tidak terdapat maka..
//            }else{
//                statusKecocokan = false;
//                break;
//            }
//        }
//
//        //jika cocok dan daftar komponen crafting tidak kosong, kita hapus isi daftar komponen crafting
//        if(statusKecocokan && !daftarKomponenCrafting.isEmpty()){
//            daftarKomponenCrafting.clear();
//        }
//
//        //menampung jumlah hasil crafting
//        ArrayList<Barang> daftarHasilCrafting = new ArrayList<>();
//
//        //jika kebutuhan senjata untuk crafting tidak kosong maka...
//        if(!this.daftarSenjataUntukCrafting.isEmpty()){
//            //jika senjata kebutuhan crafting sudah terpilih atau tidak kosong maka..
//            if(senjataUntukCraftingTerpilih != null){
//                //jika idSenjata kebutuhan crafting dan inputan sama maka..
//                if(senjataUntukCraftingTerpilih.getIdBarang() == senjata.getIdBarang()){
//
//                    //jika method ini dipanggil untuk keperluan Upgrade maka...
//                    if(this.isStatusUpgrade()){
//                        //return blue-print ini krn blueprint ini memuat nilai peningkatan kekuatan, batasMaxKetahanan, dan daftar efek...
//                        //...yang kemudian proses upgrade pada senjata akan dilakukan diluar class ini (turunan class Pilihan) dan setelahnya dipanggil method/fungsi milik senjata tersebut
//                        //...dengan teknis upgrade yang diatur dalam class senjata tersebut
//                        daftarHasilCrafting.add(this);
//                        return daftarHasilCrafting;
//                    //jika BUKAN UNTUK UPGRADE maka..
//                    }else{
//                        //mengosongkan senjata
//                        senjata = null;
//                    }
//
//                //jika idSenjata kebutuhan crafting dan inputan TIDAK SAMA maka...
//                }else{
//                    statusKecocokan = false;
//                }
//
//            //jika senjata untuk crafting belum terpilih atau masih kosong maka...
//            }else{
//                statusKecocokan = false;
//            }
//        }
//
//        //jika hasil perbandingan kebutuhan/syarat crafting tidak terpenuhi maka...
//        if(!statusKecocokan){
//            return null;
//        }
//
//        //Jika hasil crafting TIDAK TERMASUK jenis Senjata Pukul dan Senjata Tembak maka jumlah hasil crafting dipengaruhi skill efisiensi crafting player
//        if(!this.hasilCrafting.getJenis().equals("Senjata Pukul") && !this.hasilCrafting.getJenis().equals("Senjata Tembak")){
//            this.jumlahHasilCrafting += efisiensiCrafting;
//        }
//
//        //jika jumlah hasil crafting lebih dari 1, kita instance dalam jumlah lebih dari 1
//        if(this.jumlahHasilCrafting > 1){
//
//            //tambahkan 1 hasil crafting
//            daftarHasilCrafting.add(this.hasilCrafting);
//
//            //pengulangan meng-instance hasil crafting dengan nilai sejenis atau sama percis (tetapi berbeda objek)
//            for(int i=daftarHasilCrafting.size(); i<=jumlahHasilCrafting; i++){
//                daftarHasilCrafting.add(new Barang(this.hasilCrafting));
//            }
//
//            return daftarHasilCrafting;
//        }
//
//        return daftarHasilCrafting;
//    }
}
