import java.util.HashMap;

public class BarangSenjata extends Barang{

    /* private karena pemberian minimal nilai kekuatan dibatasi */
    private int kekuatan = 0;

    /* private karena hanya membutuhkan proses tambah efek dan get (+ telah dimodifikasi) efek saja */
    private HashMap<Integer, Efek> daftarEfek = new HashMap<>();

    BarangSenjata(int idBarang, String nama, String kategoriBarang, String deskripsi,
                  boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli, int kekuatan) {
        super(idBarang, nama, kategoriBarang,deskripsi, statusJual, statusBeli, hargaJual, hargaBeli);
        this.setKekuatan(kekuatan);
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

    public void tambahEfek(int id, Efek oEfek){
        this.daftarEfek.put(id, oEfek);
    }

    public void tambahEfek(HashMap<Integer, Efek> oDaftarEfek){
        this.daftarEfek.putAll(oDaftarEfek);
    }

    public HashMap<Integer, Efek> getDaftarEfek() {
        /* object HashMap dibedakan agar tidak dapat memanipulasi daftarEfek diluar Class ini
         * selain hanya bisa menggunakan method khusus untuk menambahkan object pada daftarEfek */
        HashMap<Integer, Efek> temp = new HashMap<>();
        temp.putAll(this.daftarEfek);
        return temp;
    }

    @Override
    public BarangSenjata cloning() {
        BarangSenjata barangCloning = new BarangSenjata(this.idBarang, this.nama, this.kategoriBarang, this.deskripsi,
                this.statusJual, this.statusBeli, this.getHargaJual(), this.getHargaBeli(), this.kekuatan);
        barangCloning.daftarEfek.putAll(this.daftarEfek);

        return barangCloning;
    }
}
