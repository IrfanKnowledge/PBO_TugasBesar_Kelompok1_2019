import java.util.ArrayList;

public class BarangPenggunaanPadaDiri extends Barang {

    /* private karena pemberian minimal nilai kesehatan dibatasi */
    private int kesehatan = 0;

    /* private karena hanya membutuhkan proses tambah efek dan get efek saja */
    private ArrayList<Efek> daftarEfek = new ArrayList<>();

    BarangPenggunaanPadaDiri(int idBarang, String nama, String kategoriBarang,String deskripsi, boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli, int kesehatan) {
        super(idBarang, nama, kategoriBarang,deskripsi, statusJual, statusBeli, hargaJual, hargaBeli);
        this.kesehatan = kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        if(kesehatan < 0){
            this.kesehatan = 0;
        }else{
            this.kesehatan = kesehatan;
        }
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void tambahEfek(Efek oEfek){
        this.daftarEfek.add(oEfek);
    }

    public ArrayList<Efek> getDaftarEfek() {
        return daftarEfek;
    }

    /* private karena hanya digunakan method cloning saja */
    private BarangPenggunaanPadaDiri prosesCloning(BarangPenggunaanPadaDiri oBarang){
        BarangPenggunaanPadaDiri barangCloning = new BarangPenggunaanPadaDiri(oBarang.idBarang, oBarang.nama, oBarang.kategoriBarang ,oBarang.deskripsi,
                oBarang.statusJual, oBarang.statusBeli, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.kesehatan);
        barangCloning.daftarEfek.addAll(oBarang.daftarEfek);
        return barangCloning;
    }

    @Override
    public BarangPenggunaanPadaDiri cloning() {
        return prosesCloning(this);
    }
}
