import java.util.HashMap;
import java.util.Map;

public class BarangSenjataNonLempar extends Barang {
    private int kekuatan;
    private int ketahanan;
    private int batasMaxKetahanan;
    private HashMap<Integer, Efek> daftarEfek;
    private int jumlahDiperbaiki = 0;
    private int batasMaxDiperbaiki = 3;

    BarangSenjataNonLempar(int idBarang, String nama, String kategori, int jumlah, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, int batasMaxKetahanan){
        super(idBarang, nama, kategori, jumlah, statusBeli, statusJual, hargaBeli, hargaJual);
        this.kekuatan = kekuatan;
        this.ketahanan = batasMaxKetahanan;
        this.batasMaxKetahanan =  batasMaxKetahanan;
    }

    @Override
    public void gunakanBarang(Player oPlayer) {
        //mengecek nilai ketahanan
        if(this.ketahanan > 0){
            //mengurangi kesehatan dengan senjata yang belum rusak
            oPlayer.kurangiKesehatan(this.kekuatan);
            //mengurangi ketahanan senjata
            this.ketahanan -= 1;
        }else{
            //mengurangi kesehatan dengan senjata yang sudah rusak
            oPlayer.kurangiKesehatan(1);
        }
        //mengecek jumlah efek senjata
        if(this.daftarEfek.size() > 0) {
            //menambahkan efek pada target yang diserang
            oPlayer.tambahEfekDiri(daftarEfek);
        }
    }

    @Override
    public void gunakanBarang(Lawan oLawan) {
        //mengecek nilai ketahanan
        if(this.ketahanan > 0){
            //mengurangi kesehatan dengan senjata yang belum rusak
            oLawan.kurangiKesehatan(this.kekuatan);
            //mengurangi ketahanan senjata
            this.ketahanan -= 1;
        }else{
            //mengurangi kesehatan dengan senjata yang sudah rusak
            oLawan.kurangiKesehatan(1);
        }
        //mengecek jumlah efek senjata
        if(this.daftarEfek.size() > 0) {
            //menambahkan efek pada target yang diserang
            oLawan.tambahEfekDiri(daftarEfek);
        }
    }

    @Override
    public void perbaikiBarang() {
        //mengecek bisa atau tidaknya diperbaiki
        if(this.jumlahDiperbaiki == this.batasMaxDiperbaiki){
            System.out.printf("Maaf, senjata %s sudah tidak bisa diperbaiki\n", this.nama);
        }else{
            //mengubah nilai ketahanan menjadi nilai maksimal ketahanan senjata
            this.ketahanan = this.batasMaxKetahanan;
            //menambah nilai jumlah diperbaiki pada senjata
            this.jumlahDiperbaiki += 1;
        }
    }

    public void tambahEfek(HashMap<Integer, Efek> oEfek) {
        for (Map.Entry<Integer, Efek> record : oEfek.entrySet()) {
            this.daftarEfek.put(record.getKey(), record.getValue());
        }
    }

    public void tambahEfek(Efek oEfek){
        this.daftarEfek.put(oEfek.idEfek, oEfek);
    }
}
