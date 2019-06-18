import java.util.HashMap;

public class BarangPenggunaanPadaDiri extends Barang {

    /* private karena pemberian minimal nilai kesehatan dibatasi */
    private int kesehatan = 0;

    /* private karena hanya membutuhkan proses tambah efek dan get (+ telah dimodifikasi) efek saja */
    private HashMap<Integer, Efek> daftarEfek = new HashMap<>();

    BarangPenggunaanPadaDiri(int idBarang, String nama, String kategoriBarang,String deskripsi,
                             boolean statusDapatDigunakanAdeganTertentu,
                             boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli, int kesehatan) {
        super(idBarang, nama, kategoriBarang, deskripsi, statusDapatDigunakanAdeganTertentu, statusJual, statusBeli, hargaJual, hargaBeli);
        this.setKesehatan(kesehatan);
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
    public BarangPenggunaanPadaDiri cloning() {
        BarangPenggunaanPadaDiri barangCloning = new BarangPenggunaanPadaDiri(this.idBarang, this.nama, this.kategoriBarang ,this.deskripsi, this.statusDapatDigunakanAdeganTertentu,
                this.statusJual, this.statusBeli, this.getHargaJual(), this.getHargaBeli(), this.kesehatan);
        barangCloning.daftarEfek.putAll(this.daftarEfek);
        return barangCloning;
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("%-25s : %d\n", "Kesehatan", this.getKesehatan());
    }
}
