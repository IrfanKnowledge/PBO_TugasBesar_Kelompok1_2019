import java.util.ArrayList;

public class BarangSenjataPukul extends Barang {
    private int kekuatan;
    private int ketahanan;
    private int batasMaxKetahanan;
    private int jumlahDiperbaiki = 0;
    private int batasMaxDiperbaiki = 3;

    //constructor tanpa daftarEfek
    BarangSenjataPukul(int idBarang, String nama, String kategori, String deskripsi , int jumlah, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, int batasMaxKetahanan){
        super(idBarang, nama, kategori, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, kekuatan);
        this.ketahanan = batasMaxKetahanan;
        this.batasMaxKetahanan =  batasMaxKetahanan;
    }

    //constructor inisiasi semua atribut
    BarangSenjataPukul(int idBarang, String nama, String kategori, String deskripsi , int jumlah, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, int batasMaxKetahanan, ArrayList<Efek> daftarEfek){
        super(idBarang, nama, kategori, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, daftarEfek);
        this.kekuatan = kekuatan;
        this.ketahanan = batasMaxKetahanan;
        this.batasMaxKetahanan =  batasMaxKetahanan;
    }

    @Override
    public void tambahEfekBanyak(ArrayList<Efek> oEfek) {
        super.tambahEfekBanyak(oEfek);
    }

    @Override
    public void tambahEfekSatu(Efek oEfek) {
        super.tambahEfekSatu(oEfek);
    }

    @Override
    public void gunakanBarangSenjata() {
        //mengecek nilai ketahanan
        if(this.ketahanan > 0){
            //mengurangi ketahanan senjata
            this.ketahanan -= 1;
        }else{
            //jika ketahanan senjata sudah 0 maka senjata dianggap tidak efektif, sehingga kekuatan menurun drastis
            this.kekuatan = 1;
        }
    }

    @Override
    public boolean perbaikiBarang(Barang komponen) {
        //mengecek barang tersebut (komponen/bahan untuk memperbaiki senjata atau bukan barang tersebut) dengan idBarang yang sesuai (misal 100)
        if(komponen.getIdBarang() == 100){
            //mengecek bisa atau tidaknya diperbaiki
            if(this.jumlahDiperbaiki == this.batasMaxDiperbaiki){
                //gagal
                return false;
            }else{
                //mengubah nilai ketahanan menjadi nilai maksimal ketahanan senjata
                this.ketahanan = this.batasMaxKetahanan;
                //menambah nilai jumlah diperbaiki pada senjata
                this.jumlahDiperbaiki += 1;

                //berhasil
                return true;
            }
        }else{
            //barang tidak cocok
            return false;
        }
    }

    @Override
    public int getKekuatan() {
        return this.kekuatan;
    }

    @Override
    public ArrayList<Efek> getDaftarEfek() {
        return super.getDaftarEfek();
    }
}
