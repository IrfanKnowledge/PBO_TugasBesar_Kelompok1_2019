import java.util.ArrayList;

public class BarangSenjata extends Barang{

    /* private karena pemberian minimal nilai kekuatan dibatasi */
    private int kekuatan = 0;

    /* private karena hanya membutuhkan proses tambah efek dan get efek saja */
    private ArrayList<Efek> daftarEfek = new ArrayList<>();

    /* public karena proses eksternal */
    public boolean statusUpgrade = false;

    BarangSenjata(int idBarang, String nama, String deskripsi, boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli, int kekuatan, boolean statusUpgrade) {
        super(idBarang, nama, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual);
        this.kekuatan = kekuatan;
        this.statusUpgrade = statusUpgrade;
    }

    public void setKekuatan(int kekuatan) {
        if(kekuatan <= 0){
            this.kekuatan = 0;
        }else{
            this.kekuatan = kekuatan;
        }
    }

    public int getKekuatan() {
        return kekuatan;
    }

    public void tambahEfek(Efek oEfek){
        this.daftarEfek.add(oEfek);
    }

    public ArrayList<Efek> getDaftarEfek() {
        return daftarEfek;
    }

    /* private karena hanya digunakan method cloning saja */
    private BarangSenjata prosesCloning(BarangSenjata oBarang){
        BarangSenjata barangCloning = new BarangSenjata(oBarang.idBarang, oBarang.nama, oBarang.deskripsi, oBarang.statusJual, oBarang.statusBeli, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.kekuatan, oBarang.statusUpgrade);
        barangCloning.daftarEfek.addAll(oBarang.daftarEfek);
        return barangCloning;
    }

    @Override
    public BarangSenjata cloning() {
        return prosesCloning(this);
    }
}
