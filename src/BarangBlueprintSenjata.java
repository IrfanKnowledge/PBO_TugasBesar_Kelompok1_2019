import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BarangBlueprintSenjata extends BarangBlueprint {

    /* private karena hanya membutuhkan tambah dan get(+ modifikasi) dan proses internal */
    private HashMap<Integer, ArrayList<BarangSenjata>> daftarSenjataCraftingMendukung = new HashMap<>();

    public boolean statusKeberhasilanCrafting;

    BarangBlueprintSenjata(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                           boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual,
                           int jumlahHasilCrafting) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, jumlahHasilCrafting);
    }

    public void tambahDaftarSenjataCraftingMendukung(BarangSenjata oBarang){
        if(!this.daftarSenjataCraftingMendukung.containsKey(oBarang.idBarang)){
            ArrayList<BarangSenjata> tempBarang = new ArrayList<>();
            tempBarang.add(oBarang.cloning());
            this.daftarSenjataCraftingMendukung.put(oBarang.idBarang, tempBarang);
        }else{
            this.daftarSenjataCraftingMendukung.get(oBarang.idBarang).add(oBarang.cloning());
        }
    }

    public void tambahDaftarSenjataCraftingMendukung(BarangSenjata oBarang, int jumlahInstance){
        ArrayList<BarangSenjata> tempBarang = Cloning.cloning(oBarang, jumlahInstance);
        if(!this.daftarSenjataCraftingMendukung.containsKey(oBarang.idBarang)){
            this.daftarSenjataCraftingMendukung.put(oBarang.idBarang, tempBarang);
        }else{
            this.daftarSenjataCraftingMendukung.get(oBarang.idBarang).addAll(tempBarang);
        }
    }

    public void tambahDaftarSenjataCraftingMendukung(HashMap<Integer, ArrayList<BarangSenjata>> oBarang){
        for (Map.Entry<Integer, ArrayList<BarangSenjata>> isi : oBarang.entrySet()) {
            this.tambahDaftarSenjataCraftingMendukung(isi.getValue().get(0), isi.getValue().size());
        }
    }

    public HashMap<Integer, ArrayList<BarangSenjata>> getDaftarSenjataCraftingMendukung() {
        /* object HashMap dan ArrayList dibedakan agar tidak dapat memanipulasi daftarSenjataCraftingMendukung diluar Class ini
         * selain hanya bisa menggunakan method khusus untuk menambahkan object pada daftarSenjataCraftingMendukung,
         * kemudian isi HashMap tersebut dibuat object berbeda agar tidak dapat dimanipulasi diluar Class ini */
        HashMap<Integer, ArrayList<BarangSenjata>> temp = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<BarangSenjata>> isi : this.daftarSenjataCraftingMendukung.entrySet()) {
            temp.put(isi.getKey(), Cloning.cloning(isi.getValue().get(0), isi.getValue().size()));
        }
        return temp;
    }

    private boolean validasiDaftarKomponenCrafting(HashMap<Integer, ArrayList<Barang>> oDaftarKomponenCrafting){
        if(oDaftarKomponenCrafting.isEmpty()){
            System.out.println();
            System.out.println("[ Daftar komponen crafting yang akan digunakan kosong ]");

            return false;
        }else{
            for (Map.Entry<Integer, ArrayList<Barang>> isi : this.getDaftarKomponenCraftingDiperlukan().entrySet()) {
                if(!oDaftarKomponenCrafting.containsKey(isi.getKey())){
                    System.out.println();
                    System.out.println(String.format("[ komponen %s tidak ada dalam daftar komponen crafting yang akan digunakan ]", isi.getValue().get(0).nama));

                    return false;
                }else{
                    int selisih = isi.getValue().size() - oDaftarKomponenCrafting.get(isi.getKey()).size();
                    if(selisih > 0) {
                        System.out.println();
                        System.out.println(String.format("[ Jumlah komponen %s untuk crafting yang digunakan kurang ]", isi.getValue().get(0).nama));
                        System.out.println(String.format("[ Kurang sebanyak %d buah ]", selisih));

                        return false;
                    }
                }
            }
            return true;
        }
    }

    private boolean validasiSenjataUntukCrafting(BarangSenjata senjataUntukCrafting){
        if(!this.getDaftarSenjataCraftingMendukung().containsKey(senjataUntukCrafting.idBarang)){
            System.out.println();
            System.out.println("[ Jenis senjata yang digunakan untuk crafting tidak cocok ]");

            return false;
        }
        return true;
    }

    public void gunakanBarangBlueprint(HashMap<Integer, ArrayList<Barang>> oDaftarKomponenCrafting, BarangSenjata senjataUntukCrafting){
        /* jika lolos validasi, daftar komponen inputan di anggap:
         *  tidak kosong, memiliki komponen yang dibutuhkan termasuk jumlahnya,
         *  kemudian senjata inputan dianggap sesuai dengan data senjata crafting mendukung
         *  jika tidak maka return null */
        if(!this.validasiDaftarKomponenCrafting(oDaftarKomponenCrafting) || !this.validasiSenjataUntukCrafting(senjataUntukCrafting)){
            System.out.println();
            System.out.println("[ Crafting dibatalkan ]");
        }else{
            for (Map.Entry<Integer, ArrayList<Barang>> isi : this.getDaftarKomponenCraftingDiperlukan().entrySet()) {
                /* pengulangan mengurangi/me-remove object daftar komponen inputan
                 *  berdasarkan kebutuhan (jumlahnya sudah dipastikan cukup, pada method validasi di atas) */
                for(int i=0; i>=isi.getValue().size(); i+=0){
                    oDaftarKomponenCrafting.get(isi.getKey()).remove(0);
                }
            }
            this.statusKeberhasilanCrafting = true;
        }
    }

}
