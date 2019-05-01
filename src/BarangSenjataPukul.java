import java.util.ArrayList;

public class BarangSenjataPukul extends Barang {
    private int ketahanan;
    private int batasMaxKetahanan;
    private int jumlahDiperbaiki = 0;
    private int batasMaxDiperbaiki = 3;
    private int idKomponenUntukPerbaikan = 100;
    private int jumlahKomponenUntukPerbaikan = 1;
    private boolean statusKemampuanDiperbaiki = true;

    //constructor tanpa daftarEfek
    BarangSenjataPukul(int idBarang, String nama, String kategori, String deskripsi , int jumlah, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, int batasMaxKetahanan){
        super(idBarang, nama, kategori, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, kekuatan, new ArrayList<>());
        this.ketahanan = batasMaxKetahanan;
        this.batasMaxKetahanan =  batasMaxKetahanan;
    }

    //constructor inisiasi semua atribut
    BarangSenjataPukul(int idBarang, String nama, String kategori, String deskripsi , int jumlah, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, int batasMaxKetahanan, ArrayList<Efek> daftarEfek){
        super(idBarang, nama, kategori, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, kekuatan, daftarEfek);
        this.ketahanan = batasMaxKetahanan;
        this.batasMaxKetahanan =  batasMaxKetahanan;
    }

    @Override
    public void gunakanBarangSenjata() {
        //mengecek nilai ketahanan
        if(this.ketahanan > 0){
            //mengurangi ketahanan senjata
            this.ketahanan -= 1;
        }else{
            //jika ketahanan senjata sudah 0 maka senjata dianggap tidak efektif, sehingga kekuatan menurun drastis
            this.setKekuatan(1);
        }
    }

    @Override
    public boolean perbaikiBarang(Barang komponen){
        //mengecek barang tersebut (komponen/bahan untuk memperbaiki senjata atau bukan barang tersebut) dengan idBarang yang sesuai
        if(komponen.getIdBarang() == this.idKomponenUntukPerbaikan){
            //mengecek bisa atau tidaknya diperbaiki (mencapai batas maksimal diperbaiki atau tidak)
            if(!this.statusKemampuanDiperbaiki){
                //gagal
                return false;
            }else{
                if(komponen == null){
                    // gagal krn komponen kosong
                    return false;
                }
                //mengubah nilai ketahanan menjadi nilai maksimal ketahanan senjata
                this.ketahanan = this.batasMaxKetahanan;
                //menambah nilai jumlah diperbaiki pada senjata
                this.jumlahDiperbaiki += 1;

                //mengecek bisa atau tidaknya diperbaiki (mencapai batas maksimal diperbaiki atau tidak)
                if(this.jumlahDiperbaiki == this.batasMaxDiperbaiki){
                    //mengubah kemampuan diperbaiki menjadi false atau tidak bisa diperbaiki
                    this.statusKemampuanDiperbaiki = false;
                }

                //proses mengubah nilai komponen menjadi null krn dianggap sudah digunakan
                komponen = null;

                //berhasil
                return true;
            }
        }else{
            //barang tidak cocok
            return false;
        }
    }

    public void upgradeSenjata(BarangBluePrint oBluePrint){
        this.setKekuatan(this.getKekuatan() + oBluePrint.getPeningkatanKekuatan());
        this.batasMaxKetahanan += oBluePrint.getPeningkatanBatasMaxKetahanan();
        this.setDaftarEfek(oBluePrint.getDaftarTambahanEfek());
    }

    public boolean isStatusKemampuanDiperbaiki() {
        return statusKemampuanDiperbaiki;
    }

    public int getIdKomponenUntukPerbaikan() {
        return idKomponenUntukPerbaikan;
    }

    public int getJumlahKomponenUntukPerbaikan() {
        return jumlahKomponenUntukPerbaikan;
    }
}
