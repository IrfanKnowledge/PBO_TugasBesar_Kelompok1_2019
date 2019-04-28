import java.util.ArrayList;

public class Barang {
    int idBarang;
    String nama;
    String kategori;
    int jumlah;
    boolean statusBeli;
    boolean statusJual;
    int hargaBeli;
    int hargaJual;;

    Barang(int idBarang, String nama, String kategori, int jumlah, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual){
        this.idBarang = idBarang;
        this.nama = nama;
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.statusBeli = statusBeli;
        this.statusJual = statusJual;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    //ngetest saja, nanti dihapus atau dibuat komentar
    //protected String testhehe = "hello world barang";
    //Barang(){}

    public void gunakanBarang(Player oPlayer){ }
    public void gunakanBarang(Lawan oLawan){}
    public void perbaikiBarang(){}
}
