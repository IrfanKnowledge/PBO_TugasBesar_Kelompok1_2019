import java.util.HashMap;

public class BarangBluePrintSenjataBaru extends BarangBlueprintSenjata{

    /* private karena membutuhkan proses khusus */
    private BarangSenjata hasilCraftingSenjata;

    BarangBluePrintSenjataBaru(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int jumlahHasilCrafting) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, jumlahHasilCrafting);
    }

    public void setHasilCraftingSenjata(BarangSenjata oHasilCrafting) {
        this.hasilCraftingSenjata = oHasilCrafting.cloning();
    }

    public void setHasilCraftingSenjata(BarangSenjataJarakDekat oHasilCrafting) {
        this.hasilCraftingSenjata = oHasilCrafting.cloning();
    }
    public void setHasilCraftingSenjata(BarangSenjataTembak oHasilCrafting) {
        this.hasilCraftingSenjata = oHasilCrafting.cloning();
    }

    public int getHasilCraftingId() {
        return hasilCraftingSenjata.idBarang;
    }

    public String getHasilCraftingNama() {
        return hasilCraftingSenjata.nama;
    }

    public String getHasilCraftingKategori() {
        return hasilCraftingSenjata.kategoriBarang;
    }

    public String getHasilCraftingDeskripsi() {
        return hasilCraftingSenjata.deskripsi;
    }

    public HashMap<Integer, Efek> getHasilCraftingDaftarEfek() {
        /* object HashMap dibedakan agar tidak dapat memanipulasi daftarEfek diluar Class ini
         * selain hanya bisa menggunakan method khusus untuk menambahkan object pada daftarEfek */
        return this.hasilCraftingSenjata.getDaftarEfek();
    }

    public BarangSenjata getHasilCraftingSenjata(){
        if(!this.statusKeberhasilanCrafting){
            System.out.println();
            System.out.println("[ Proses crafting belum dilakukan ]");

            return null;
        }else {
            this.statusKeberhasilanCrafting = false;
            return this.hasilCraftingSenjata;
        }
    }
}
