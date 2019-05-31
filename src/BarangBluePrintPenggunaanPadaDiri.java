import java.util.ArrayList;
import java.util.HashMap;

public class BarangBluePrintPenggunaanPadaDiri extends BarangBlueprint {

    /* private karena membutuhkan proses khusus dan proses internal */
    private BarangPenggunaanPadaDiri hasilCrafting;

    /* private karena hanya membutuhkan proses tambah efek dan get (+ telah dimodifikasi) efek saja */
    private ArrayList<Efek> daftarEfekTambahan = new ArrayList<>();

    /* private karena proses internal,
     *  kegunaan variable berikut untuk memberi return proses crafting yang spesifik sesuai classnya,
     *  termasuk mengakses proses tertentu yang hanya dimiliki class spesifik berikut */
    private HashMap<Integer, BarangPenggunaanPadaDiri> daftarBarangPenggunaanPadaDiriUntukCrafting = new HashMap<>();

    BarangBluePrintPenggunaanPadaDiri(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                                      boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                                      int jumlahHasilCrafting) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusJual, statusBeli, hargaJual, hargaBeli, jumlahHasilCrafting);

    }

    public void setHasilCrafting(BarangPenggunaanPadaDiri hasilCrafting) {
        this.hasilCrafting = hasilCrafting.cloning();
    }

    public int getHasilCraftingId() {
        return hasilCrafting.idBarang;
    }

    public String getHasilCraftingNama() {
        return hasilCrafting.nama;
    }

    public String getHasilCraftingKategori() {
        return hasilCrafting.kategoriBarang;
    }

    public String getHasilCraftingDeskripsi() {
        return hasilCrafting.deskripsi;
    }

    public int getHasilCraftingKesehatan() {
        return hasilCrafting.getKesehatan();
    }

    public HashMap<Integer, Efek> getHasilCraftingDaftarEfek() {
        return hasilCrafting.getDaftarEfek();
    }

    public ArrayList<BarangPenggunaanPadaDiri> getHasilCrafting() {
        if(!this.statusKeberhasilanCrafting){
            System.out.println();
            System.out.println("[ Proses crafting belum dilakukan ]");

            return null;
        }else{
            this.statusKeberhasilanCrafting = false;
            return Cloning.cloning(this.hasilCrafting, this.getJumlahHasilCrafting());
        }
    }
}
