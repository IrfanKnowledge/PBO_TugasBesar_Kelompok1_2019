import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BarangBlueprintSenjata extends BarangBlueprint {

    /* private karena hanya membutuhkan tambah dan get(+ modifikasi) dan proses internal */
    private HashMap<Integer, ArrayList<BarangSenjata>> daftarSenjataCraftingMendukung = new HashMap<>();

    BarangBlueprintSenjata(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                           boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                           int jumlahHasilCrafting) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusJual, statusBeli, hargaJual, hargaBeli, jumlahHasilCrafting);
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

    private boolean validasiSenjataUntukCrafting(BarangSenjata senjataUntukCrafting){
        if(!this.getDaftarSenjataCraftingMendukung().containsKey(senjataUntukCrafting.idBarang)){
            System.out.println();
            System.out.println("[ Jenis senjata yang digunakan untuk crafting tidak cocok ]");

            return false;
        }
        return true;
    }

    public void gunakanBarangBlueprint(HashMap<Integer, ArrayList<Barang>> oDaftarKomponenCrafting, BarangSenjata senjataUntukCrafting){
        super.gunakanBarangBlueprint(oDaftarKomponenCrafting);
        if(this.statusKeberhasilanCrafting){
            if(!this.validasiSenjataUntukCrafting(senjataUntukCrafting)){
                System.out.println();
                System.out.println("[ Crafting dibatalkan ]");
            }else{
                this.statusKeberhasilanCrafting = true;
            }
        }
    }

}
