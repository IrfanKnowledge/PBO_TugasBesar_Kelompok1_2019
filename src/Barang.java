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
    private int nilaiKesehatan = 0;

    //contructor tanpa efek dan kesehatan
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

    //contructor tanpa daftarEfek
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

    //contructor tanpa nilaiKesehatan
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

    public void tambahEfekBanyak(ArrayList<Efek> oEfek) {
        for (Efek value : oEfek) {
            this.daftarEfek.add(value);
        }
    }

    public void tambahEfekSatu(Efek oEfek) {
        this.daftarEfek.add(oEfek);
    }

    public Barang gunakanBarangBluePrint(ArrayList<Barang> daftarKomponenCrafting, Barang senjata){ return null;}

    //proses pada senjata tembak dan pukul
    public void gunakanBarangSenjata(){}

    //proses pada senjata tembak
    public void isiPeluru(Barang amunisi){}

    //proses pada senjata pukul
    public boolean perbaikiBarang(Barang komponen){return false;}

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

    public int getKekuatan(){
        return 0;
    }

    public ArrayList<Efek> getDaftarEfek() {
        return daftarEfek;
    }

    public int getNilaiKesehatan() {
        return nilaiKesehatan;
    }
}
