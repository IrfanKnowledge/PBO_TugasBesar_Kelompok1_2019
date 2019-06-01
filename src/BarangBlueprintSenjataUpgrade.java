import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarangBlueprintSenjataUpgrade extends BarangBlueprint{

    /* private karena hanya membutuhkan tambah dan get(+ modifikasi) dan proses internal */
    private HashMap<Integer, ArrayList<BarangSenjataJarakDekat>> daftarSenjataCraftingMendukung = new HashMap<>();

    /* private karena membutuhkan proses khusus */
    private BarangSenjataJarakDekat hasilCraftingSenjata;

    /* private karena terdapat pembatasan minimal nilai */
    private int peningkatanKekuatan;
    private int peningkatanBatasMaxKetahanan;

    BarangBlueprintSenjataUpgrade(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                                  boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                                  int peningkatanKekuatan, int peningkatanBatasMaxKetahanan) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusJual, statusBeli, hargaJual, hargaBeli, 1);

        this.setPeningkatanKekuatan(peningkatanKekuatan);
        this.setPeningkatanBatasMaxKetahanan(peningkatanBatasMaxKetahanan);
    }

    public void tambahDaftarSenjataCraftingMendukung(BarangSenjataJarakDekat oBarang){
        if(!this.daftarSenjataCraftingMendukung.containsKey(oBarang.idBarang)){
            ArrayList<BarangSenjataJarakDekat> tempBarang = new ArrayList<>();
            tempBarang.add(oBarang.cloning());
            this.daftarSenjataCraftingMendukung.put(oBarang.idBarang, tempBarang);
        }else{
            this.daftarSenjataCraftingMendukung.get(oBarang.idBarang).add(oBarang.cloning());
        }
    }

    public void tambahDaftarSenjataCraftingMendukung(BarangSenjataJarakDekat oBarang, int jumlahInstance){
        ArrayList<BarangSenjataJarakDekat> tempBarang = Cloning.cloning(oBarang, jumlahInstance);
        if(!this.daftarSenjataCraftingMendukung.containsKey(oBarang.idBarang)){
            this.daftarSenjataCraftingMendukung.put(oBarang.idBarang, tempBarang);
        }else{
            this.daftarSenjataCraftingMendukung.get(oBarang.idBarang).addAll(tempBarang);
        }
    }

    public void tambahDaftarSenjataCraftingMendukung(HashMap<Integer, ArrayList<BarangSenjataJarakDekat>> oBarang){
        for (Map.Entry<Integer, ArrayList<BarangSenjataJarakDekat>> isi : oBarang.entrySet()) {
            this.tambahDaftarSenjataCraftingMendukung(isi.getValue().get(0), isi.getValue().size());
        }
    }

    public HashMap<Integer, ArrayList<BarangSenjataJarakDekat>> getDaftarSenjataCraftingMendukung() {
        /* object HashMap dan ArrayList dibedakan agar tidak dapat memanipulasi daftarSenjataCraftingMendukung diluar Class ini
         * selain hanya bisa menggunakan method khusus untuk menambahkan object pada daftarSenjataCraftingMendukung,
         * kemudian isi HashMap tersebut dibuat object berbeda agar tidak dapat dimanipulasi diluar Class ini */
        HashMap<Integer, ArrayList<BarangSenjataJarakDekat>> temp = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<BarangSenjataJarakDekat>> isi : this.daftarSenjataCraftingMendukung.entrySet()) {
            temp.put(isi.getKey(), Cloning.cloning(isi.getValue().get(0), isi.getValue().size()));
        }
        return temp;
    }

    public void setHasilCraftingSenjata(BarangSenjataJarakDekat hasilCraftingSenjata) {
        this.hasilCraftingSenjata = hasilCraftingSenjata.cloning();
    }

    public BarangSenjataJarakDekat getHasilCraftingSenjataSebelumDiupgrade(){
        return this.hasilCraftingSenjata.cloning();
    }

    private int filterMinimalNol(int nilai){
        if(nilai <= 0){
            nilai = 0;
        }
        return nilai;
    }

    public void setPeningkatanKekuatan(int peningkatanKekuatan) {
        this.peningkatanKekuatan = this.filterMinimalNol(peningkatanKekuatan);
    }

    public int getPeningkatanKekuatan() {
        return peningkatanKekuatan;
    }

    public void setPeningkatanBatasMaxKetahanan(int peningkatanBatasMaxKetahanan) {
        this.peningkatanBatasMaxKetahanan = this.filterMinimalNol(peningkatanBatasMaxKetahanan);
    }

    public int getPeningkatanBatasMaxKetahanan() {
        return peningkatanBatasMaxKetahanan;
    }

    public BarangSenjataJarakDekat getHasilCrafting() {
        if(!this.statusKeberhasilanCrafting){
//            System.out.println();
//            System.out.println("[ Proses crafting belum dilakukan ]");
            return null;
        }else{
            this.statusKeberhasilanCrafting = false;
            this.hasilCraftingSenjata.upgradeSenjata(this);
            return hasilCraftingSenjata.cloning();
        }
    }
}
