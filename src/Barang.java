import java.util.ArrayList;
import java.util.HashMap;

public class Barang {
    private int idBarang;
    private String nama;
    private String jenis;
    private String kategoriPenyimpanan;
    private String deskripsi;
    private boolean statusBeli;
    private boolean statusJual;
    private int hargaBeli;
    private int hargaJual;;
    private ArrayList<Efek> daftarEfek = new ArrayList<>();
    private int kekuatan = 0;
    private int nilaiKesehatan = 0;
    private boolean statusUpgrade = false;

    //contructor tanpa kekuatan, daftarEfek dan kesehatan
    Barang(int idBarang, String nama, String jenis, String kategoriPenyimpanan, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual){
        this.idBarang = idBarang;
        this.nama = nama;
        this.jenis = jenis;
        this.kategoriPenyimpanan = kategoriPenyimpanan;
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    //contructor tanpa kekuatan dan daftarEfek
    Barang(int idBarang, String nama, String jenis, String kategoriPenyimpanan,
           String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int nilaiKesehatan){
        this.idBarang = idBarang;
        this.nama = nama;
        this.jenis = jenis;
        this.kategoriPenyimpanan = kategoriPenyimpanan;
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.nilaiKesehatan = nilaiKesehatan;
    }

    //contructor tanpa kekuatan dan nilaiKesehatan
    Barang(int idBarang, String nama, String jenis, String kategoriPenyimpanan,
           String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, ArrayList<Efek> daftarEfek){
        this.idBarang = idBarang;
        this.nama = nama;
        this.jenis = jenis;
        this.kategoriPenyimpanan = kategoriPenyimpanan;
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.daftarEfek = daftarEfek;
    }

    //contructor tanpa nilaiKeshatan
    Barang(int idBarang, String nama, String jenis, String kategoriPenyimpanan,
           String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, ArrayList<Efek> daftarEfek){
        this.idBarang = idBarang;
        this.nama = nama;
        this.jenis = jenis;
        this.kategoriPenyimpanan = kategoriPenyimpanan;
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.kekuatan = kekuatan;
        this.daftarEfek = daftarEfek;

        //jika barang ini adalah termasuk jenis senjata pukul maka bisa di upgrade
        if(jenis.equals("Senjata Pukul")){
            this.statusUpgrade = true;
        }
    }

    //constructor untuk mengcloning Barang
    Barang(Barang cloning){
        this.idBarang = cloning.getIdBarang();
        this.nama = cloning.getNama();
        this.jenis = cloning.getDeskripsi();
        this.deskripsi = cloning.getDeskripsi();
        this.statusBeli = cloning.isStatusBeli();
        this.statusJual = cloning.isStatusJual();
        this.hargaBeli = cloning.getHargaBeli();
        this.hargaJual = cloning.getHargaJual();
        this.nilaiKesehatan = cloning.getNilaiKesehatan();
        this.kekuatan = cloning.getKekuatan();
        this.daftarEfek = cloning.getDaftarEfek();
        this.statusUpgrade = cloning.isStatusUpgrade();
    }

    public void tambahEfek(ArrayList<Efek> oEfek) {
        for (Efek value : oEfek) {
            this.daftarEfek.add(value);
        }
    }
    public void tambahEfek(Efek oEfek) {
        this.daftarEfek.add(oEfek);
    }

    /* Cloning barang dan turunannya */
    public Barang cloning(){
        return new Barang(this);
    }

    //proses pada blue print
    public ArrayList<Barang> gunakanBarangBlueprint(HashMap<Integer, ArrayList<Barang>> daftarKomponenCrafting, Barang senjata, int efisiensiCrafting){ return null;}

    //proses pada senjata pukul dan tembak
    public Barang gunakanBarangSenjata(){return null;}

    //proses pada senjata pukul
    public boolean perbaikiBarang(Barang komponen){return false;}

    //proses pada senjata tembak
    public boolean isiPeluru(ArrayList<Barang> daftarAmunisi){ return false;}

    public int getIdBarang() {
        return idBarang;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public boolean isStatusBeli() {
        return statusBeli;
    }

    public boolean isStatusJual() {
        return statusJual;
    }

    public int getHargaBeli() {
        return hargaBeli;
    }

    public int getHargaJual() {
        return hargaJual;
    }

    public void setKekuatan(int kekuatan) { this.kekuatan = kekuatan; }

    public int getKekuatan(){
        return this.kekuatan;
    }

    public ArrayList<Efek> getDaftarEfek() {
        return daftarEfek;
    }

    public void setDaftarEfek(ArrayList<Efek> daftarEfek) {
        this.daftarEfek = daftarEfek;
    }

    public int getNilaiKesehatan() {
        return nilaiKesehatan;
    }

    public boolean isStatusUpgrade() {
        return statusUpgrade;
    }

    public void setStatusUpgrade(boolean statusUpgrade){
        this.statusUpgrade = statusUpgrade;
    }

    public String getKategoriPenyimpanan() {
        return kategoriPenyimpanan;
    }

    //=======================================================
            /* untuk class child saja */
    public boolean isStatusKemampuanDiperbaiki() {
        return false;
    }

    public int getIdKomponenUntukPerbaikan() {
        return 0;
    }

    public int getJumlahKomponenUntukPerbaikan() {
        return 0;
    }

    public int getIdAmunisiUtama() {
        return 0;
    }

    public ArrayList<Integer> getDaftarIdAmunisi() {
        return null;
    }

    public int getJumlahKebutuhanIsiPeluru(){
        return 0;
    }

    public HashMap<Integer, ArrayList<Barang>> getDaftarKomponenCrafting() {
        return null;
    }

    public HashMap<Integer, Barang> getDaftarSenjataUntukCrafting() {
        return null;
    }

    public boolean setSenjataUntukCraftingTerpilih(Barang senjataUntukCraftingTerpilih) {
        return false;
    }

    public int getPeningkatanKekuatan() {
        return 0;
    }

    public int getPeningkatanBatasMaxKetahanan() {
        return 0;
    }

    public ArrayList<Efek> getDaftarTambahanEfek() {
        return null;
    }

    //=======================================================
}
