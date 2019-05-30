import java.util.ArrayList;

public class BarangSenjata extends Barang{

    /* private karena pemberian minimal nilai kekuatan dibatasi */
    private int kekuatan = 0;

    /* private karena hanya membutuhkan proses tambah efek dan get efek saja */
    private ArrayList<Efek> daftarEfek = new ArrayList<>();

    BarangSenjata(int idBarang, String nama, String kategoriBarang, String deskripsi,
                  boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli, int kekuatan) {
        super(idBarang, nama, kategoriBarang,deskripsi, statusJual, statusBeli, hargaJual, hargaBeli);
        this.kekuatan = this.minimalKekuatan(kekuatan);
    }

    private int minimalKekuatan(int kekuatan){
        if(kekuatan < 0 ){
            return 0;
        }
        return kekuatan;
    }

    public void setKekuatan(int kekuatan) {
        this.kekuatan = this.minimalKekuatan(kekuatan);
    }

    public int getKekuatan() {
        return kekuatan;
    }

    public void tambahEfek(Efek oEfek){
        this.daftarEfek.add(oEfek);
    }

    public void tambahEfekBanyak(ArrayList<Efek> oDaftarEfek){
        this.daftarEfek.addAll(oDaftarEfek);
    }

    public ArrayList<Efek> getDaftarEfek() {
        return daftarEfek;
    }

    /* private karena hanya digunakan method cloning saja */
    private BarangSenjata prosesCloning(BarangSenjata oBarang){
        BarangSenjata barangCloning = new BarangSenjata(oBarang.idBarang, oBarang.nama, oBarang.kategoriBarang, oBarang.deskripsi,
                oBarang.statusJual, oBarang.statusBeli, oBarang.getHargaJual(), oBarang.getHargaBeli(), oBarang.kekuatan);
        barangCloning.daftarEfek.addAll(oBarang.daftarEfek);
        return barangCloning;
    }

    @Override
    public BarangSenjata cloning() {
        return prosesCloning(this);
    }
}
