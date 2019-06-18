import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BarangBlueprint extends Barang{

    /* private karena membutuhkan proses khusus */
    private BarangSenjata hasilCraftingSenjata;

    /* private karena terdapat pembatasan minimal nilai */
    private int jumlahHasilCrafting;

    /* private karena hanya membutuhkan tambah dan get(+ modifikasi) dan proses internal */
    private HashMap<Integer, ArrayList<Barang>> daftarKomponenCraftingDiperlukan = new HashMap<>(); //untuk mengetahui komponen yang harus ada
    private HashMap<Integer, ArrayList<Barang>> daftarKomponenCraftingDigunakan = new HashMap<>();  //untuk diproses

    /* private karena hanya membutuhkan proses tambah efek dan get (+ telah dimodifikasi) efek saja */
    private HashMap<Integer, Efek> daftarEfekTambahan = new HashMap<>();

    public boolean statusKeberhasilanCrafting;

    BarangBlueprint(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                    boolean statusDapatDigunakanAdeganTertentu,
                    boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                    int jumlahHasilCrafting) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusDapatDigunakanAdeganTertentu, statusJual, statusBeli, hargaJual, hargaBeli);

        /* tidak dibuat method khusus karena terdapat class turunan yang sudah ditetapkan hanya 1 hasilnya */
        if(jumlahHasilCrafting <= 0){
            this.jumlahHasilCrafting = 1;
        }else{
            this.jumlahHasilCrafting = jumlahHasilCrafting;
        }
    }

    public int getJumlahHasilCrafting(){
        return jumlahHasilCrafting;
    }

    private void prosesTambahKomponen(Barang oBarang, HashMap<Integer, ArrayList<Barang>> oDaftarKomponen){
        if(!this.daftarKomponenCraftingDiperlukan.containsKey(oBarang.idBarang)){
            ArrayList<Barang> tempBarang = new ArrayList<>();
            tempBarang.add(oBarang.cloning());
            oDaftarKomponen.put(oBarang.idBarang, tempBarang);
        }else{
            oDaftarKomponen.get(oBarang.idBarang).add(oBarang.cloning());
        }
    }

    private void prosesTambahKomponen(Barang oBarang, int jumlahInstance, HashMap<Integer, ArrayList<Barang>> oDaftarKomponen){
        ArrayList<Barang> tempBarang = Cloning.cloning(oBarang, jumlahInstance);
        if(!oDaftarKomponen.containsKey(oBarang.idBarang)){
            oDaftarKomponen.put(oBarang.idBarang, tempBarang);
        }else{
            oDaftarKomponen.get(oBarang.idBarang).addAll(tempBarang);
        }
    }

    private HashMap<Integer, ArrayList<Barang>> prosesGetKomponen(HashMap<Integer, ArrayList<Barang>> oBarang){
        /* object HashMap dan ArrayList dibedakan agar tidak dapat memanipulasi daftarKomponenCraftingDiperlukan diluar Class ini
         * selain hanya bisa menggunakan method khusus untuk menambahkan object pada daftarKomponenCraftingDiperlukan,
         * kemudian isi HashMap tersebut dibuat object berbeda agar tidak dapat dimanipulasi diluar Class ini */
        HashMap<Integer, ArrayList<Barang>> temp = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Barang>> isi : oBarang.entrySet()) {
            temp.put(isi.getKey(), Cloning.cloning(isi.getValue().get(0), isi.getValue().size()));
        }
        return temp;
    }

    private void prosesTambahKomponen(HashMap<Integer, ArrayList<Barang>> oBarang, HashMap<Integer, ArrayList<Barang>> tempatKomponenDisimpan){
        for (Map.Entry<Integer, ArrayList<Barang>> isi : oBarang.entrySet()) {
            this.prosesTambahKomponen(isi.getValue().get(0), isi.getValue().size(), tempatKomponenDisimpan);
        }
    }

    public void tambahDaftarKomponenCraftingDiperlukan(Barang oBarang){
        this.prosesTambahKomponen(oBarang, this.daftarKomponenCraftingDiperlukan);
    }

    public void tambahDaftarKomponenCraftingDiperlukan(Barang oBarang, int jumlahInstance){
        this.prosesTambahKomponen(oBarang, jumlahInstance, this.daftarKomponenCraftingDiperlukan);
    }

    public void tambahDaftarKomponenCraftingDiperlukan(HashMap<Integer, ArrayList<Barang>> oBarang){
        this.prosesTambahKomponen(oBarang,this.daftarKomponenCraftingDiperlukan);
    }

    public HashMap<Integer, ArrayList<Barang>> getDaftarKomponenCraftingDiperlukan() {
        return this.prosesGetKomponen(this.daftarKomponenCraftingDiperlukan);
    }

    public void tambahdaftarKomponenCraftingDigunakan(Barang oBarang){
        this.prosesTambahKomponen(oBarang, this.daftarKomponenCraftingDigunakan);
    }

    public void tambahDaftarKomponenCraftingDigunakan(Barang oBarang, int jumlahInstance){
        this.prosesTambahKomponen(oBarang, jumlahInstance, this.daftarKomponenCraftingDigunakan);
    }

    public void tambahDaftarKomponenCraftingDigunakan(HashMap<Integer, ArrayList<Barang>> oBarang){
        this.prosesTambahKomponen(oBarang, this.daftarKomponenCraftingDigunakan);
    }

    public HashMap<Integer, ArrayList<Barang>> getDaftarKomponenCraftingDigunakan() {
        return this.prosesGetKomponen(this.daftarKomponenCraftingDigunakan);
    }

    public boolean validasiDaftarKomponenCrafting(){
        if(this.getDaftarKomponenCraftingDigunakan().isEmpty()){
//            System.out.println();
//            System.out.println("[ Daftar komponen crafting yang akan digunakan kosong ]");
            return false;
        }else{
            for (Map.Entry<Integer, ArrayList<Barang>> isi : this.getDaftarKomponenCraftingDiperlukan().entrySet()) {
                if(!this.getDaftarKomponenCraftingDigunakan().containsKey(isi.getKey())){
//                    System.out.println();
//                    System.out.println(String.format("[ komponen %s tidak ada dalam daftar komponen crafting yang akan digunakan ]", isi.getValue().get(0).nama));
                    return false;
                }else{
                    int selisih = isi.getValue().size() - this.getDaftarKomponenCraftingDigunakan().get(isi.getKey()).size();
                    if(selisih > 0) {
//                        System.out.println();
//                        System.out.println(String.format("[ Jumlah komponen %s untuk crafting yang digunakan kurang ]", isi.getValue().get(0).nama));
//                        System.out.println(String.format("[ Kurang sebanyak %d buah ]", selisih));
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /* jika lolos validasi (validasi digunakan dilaur class ini sebelum menggunakan method ini), daftar komponen inputan di anggap:
     *  tidak kosong, memiliki komponen yang dibutuhkan termasuk jumlahnya,
     *  kemudian senjata inputan dianggap sesuai dengan data senjata crafting mendukung,
     * */
    public void gunakanBarangBlueprint(){
        for (Map.Entry<Integer, ArrayList<Barang>> isi : this.getDaftarKomponenCraftingDiperlukan().entrySet()) {
            /* pengulangan mengurangi/me-remove object daftar komponen inputan
             *  berdasarkan kebutuhan (jumlahnya sudah dipastikan cukup, pada method validasi di atas) */
            for(int i=0; i>=isi.getValue().size(); i+=0){
                this.getDaftarKomponenCraftingDigunakan().get(isi.getKey()).remove(0);
            }
        }
        this.statusKeberhasilanCrafting = true;
    }

    public void tambahEfek(Efek oEfek){
        this.daftarEfekTambahan.put(oEfek.idEfek, oEfek);
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
