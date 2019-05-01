import java.util.ArrayList;

public class Barang {
    private int idBarang;
    private String nama;
    private String kategori;
    private String deskripsi;
    private boolean statusBeli;
    private boolean statusJual;
    private int hargaBeli;
    private int hargaJual;;
    private ArrayList<Efek> daftarEfek = new ArrayList<>();
    private int kekuatan = 0;
    private int nilaiKesehatan = 0;

    //contructor tanpa kekuatan, daftarEfek dan kesehatan
    Barang(int idBarang, String nama, String kategori, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual){
        this.idBarang = idBarang;
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    //contructor tanpa kekuatan dan daftarEfek
    Barang(int idBarang, String nama, String kategori, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int nilaiKesehatan){
        this.idBarang = idBarang;
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.nilaiKesehatan = nilaiKesehatan;
    }

    //contructor tanpa kekuatan dan nilaiKesehatan
    Barang(int idBarang, String nama, String kategori, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, ArrayList<Efek> daftarEfek){
        this.idBarang = idBarang;
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.daftarEfek = daftarEfek;
    }

    //contructor tanpa nilaiKeshatan
    Barang(int idBarang, String nama, String kategori, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, ArrayList<Efek> daftarEfek){
        this.idBarang = idBarang;
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.kekuatan = kekuatan;
        this.daftarEfek = daftarEfek;
    }

    public void tambahEfek(ArrayList<Efek> oEfek) {
        for (Efek value : oEfek) {
            this.daftarEfek.add(value);
        }
    }
    public void tambahEfek(Efek oEfek) {
        this.daftarEfek.add(oEfek);
    }

    //masih perlu dipikirkan
    public Barang gunakanBarangBluePrint(ArrayList<Barang> daftarKomponenCrafting, Barang senjata){ return null;}

    //proses pada senjata pukul
    public void gunakanBarangSenjata(){}

    //proses pada senjata tembak
    public Barang gunakanBarangSenjataTembak(){return null;}

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

    public String getKategori() {
        return kategori;
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

    public int getNilaiKesehatan() {
        return nilaiKesehatan;
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
    //=======================================================
}
