import java.util.HashMap;

public class BarangBlueprintSenjata extends BarangBlueprint {

    /* private karena membutuhkan proses khusus dan proses internal */
    private BarangSenjata senjataUntukCraftingTerpilih;

    /* private karena proses internal,
     *  kegunaan 3 variable berikut untuk memberi return proses crafting yang spesifik sesuai classnya,
     *  termasuk mengakses proses tertentu yang hanya dimiliki class spesifik berikut */
    private HashMap<Integer, BarangSenjata> daftarSenjataUntukCrafting = new HashMap<>();
    private HashMap<Integer, BarangSenjataJarakDekat> daftarSenjataJarakDekatUntukCrafting = new HashMap<>();
    private HashMap<Integer, BarangSenjataTembak> daftarSenjataTembakUntukCrafting = new HashMap<>();

    BarangBlueprintSenjata(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                           boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual,
                           int jumlahHasilCrafting) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, jumlahHasilCrafting);

    }

    public void setSenjataUntukCraftingTerpilih(BarangSenjata senjataUntukCraftingTerpilih) {
        this.senjataUntukCraftingTerpilih = senjataUntukCraftingTerpilih;
    }


}
