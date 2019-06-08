import java.util.HashMap;

public class BarangSenjata extends Barang{

    /* private karena pemberian minimal nilai kekuatan dibatasi */
    private int kekuatan = 0;

    /* private karena hanya membutuhkan proses tambah efek dan get (+ telah dimodifikasi) efek saja */
    private HashMap<Integer, Efek> daftarEfek = new HashMap<>();

    BarangSenjata(int idBarang, String nama, String kategoriBarang, String deskripsi,
                  boolean statusDapatDigunakanAdeganTertentu,
                  boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli, int kekuatan) {
        super(idBarang, nama, kategoriBarang, deskripsi, statusDapatDigunakanAdeganTertentu, statusJual, statusBeli, hargaJual, hargaBeli);
        this.setKekuatan(kekuatan);
    }

    public void setKekuatan(int kekuatan) {
        if(kekuatan < 0 ){
            kekuatan = 0;
        }
        this.kekuatan = kekuatan;
    }

    public int getKekuatan() {
        return kekuatan;
    }

    public void tambahEfek(Efek oEfek){
        this.daftarEfek.put(oEfek.idEfek, oEfek);
    }

    public void tambahEfek(HashMap<Integer, Efek> oDaftarEfek){
        this.daftarEfek.putAll(oDaftarEfek);
    }

    public HashMap<Integer, Efek> getDaftarEfek() {
        /* object HashMap dibedakan agar tidak dapat memanipulasi HashMap tersebut diluar Class ini */
        HashMap<Integer, Efek> temp = new HashMap<>();
        temp.putAll(this.daftarEfek);
        return temp;
    }

    @Override
    public BarangSenjata cloning() {
        BarangSenjata barangCloning = new BarangSenjata(this.idBarang, this.nama, this.kategoriBarang, this.deskripsi, this.statusDapatDigunakanAdeganTertentu,
                this.statusJual, this.statusBeli, this.getHargaJual(), this.getHargaBeli(), this.kekuatan);
        barangCloning.daftarEfek.putAll(this.daftarEfek);

        return barangCloning;
    }
}
