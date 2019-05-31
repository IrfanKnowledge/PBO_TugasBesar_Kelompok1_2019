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
                           boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual,
                           int jumlahHasilCrafting) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, jumlahHasilCrafting);

    }
}
