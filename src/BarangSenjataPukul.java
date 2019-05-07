import java.util.ArrayList;
import java.util.HashMap;

public class BarangSenjataPukul extends Barang {
    private int ketahanan;
    private int batasMaxKetahanan;
    private int jumlahDiperbaiki = 0;
    private int batasMaxDiperbaiki = 3;
    private HashMap<String, Integer> komponenUntukPerbaikan;

    private boolean statusKemampuanDiperbaiki = true;

    //constructor tanpa daftarEfek
    BarangSenjataPukul(int idBarang, String nama, String jenis, String kategoriPenyimpanan, String deskripsi,
                       boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, int batasMaxKetahanan){
        super(idBarang, nama, jenis, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, kekuatan, new ArrayList<>());
        this.ketahanan = batasMaxKetahanan;
        this.komponenUntukPerbaikan = new HashMap<>();
        this.komponenUntukPerbaikan.put("Komponen Crafting", 2);
    }

    //constructor inisiasi semua atribut
    BarangSenjataPukul(int idBarang, String nama, String jenis, String kategoriPenyimpanan, String deskripsi,
                       boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, int batasMaxKetahanan, ArrayList<Efek> daftarEfek){
        super(idBarang, nama, jenis, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, kekuatan, daftarEfek);
        this.ketahanan = batasMaxKetahanan;
        this.komponenUntukPerbaikan.put("Komponen Crafting", 2);
    }

    //constructor untuk cloning
    BarangSenjataPukul(BarangSenjataPukul oBarang){
        super(oBarang);
        this.ketahanan = oBarang.ketahanan;
        this.batasMaxKetahanan = oBarang.batasMaxKetahanan;
        this.jumlahDiperbaiki = oBarang.jumlahDiperbaiki;
        this.statusKemampuanDiperbaiki = oBarang.statusKemampuanDiperbaiki;
        this.komponenUntukPerbaikan = oBarang.getKomponenUntukPerbaikan();
    }

    @Override
    public Barang cloning() {
        return new BarangSenjataPukul(this);
    }

    @Override
    public Barang gunakanBarangSenjata() {
        //mengecek nilai ketahanan
        if(this.ketahanan > 0){
            //mengurangi ketahanan senjata
            this.ketahanan -= 1;
        }else{
            //jika ketahanan senjata sudah 0 maka senjata dianggap tidak efektif, sehingga kekuatan menurun drastis
            this.setKekuatan(1);
        }
        return this;
    }

    @Override
    public void perbaikiBarang(Barang komponen){
        //mengecek barang tersebut (komponen/bahan untuk memperbaiki senjata atau bukan barang tersebut) dengan idBarang yang sesuai
        if(komponen.getIdBarang() == this.komponenUntukPerbaikan.get("Komponen Crafting")){
            //mengecek bisa atau tidaknya diperbaiki (mencapai batas maksimal diperbaiki atau tidak)
            if(!this.statusKemampuanDiperbaiki){
                //gagal
                System.out.println("[ Senjata sudah tidak bisa diperbaiki ]");
                System.out.println();
            }else if(komponen == null){
                System.out.println();
                System.out.println("[ Komponen Crafting untuk perbaikan null ]");
            }else if(this.ketahanan == this.batasMaxKetahanan){
                System.out.println();
                System.out.printf("[ %s masih memiliki ketahanan 100% ]\n", this.getNama());
            }else{
                //mengubah nilai ketahanan menjadi nilai maksimal ketahanan senjata
                this.ketahanan = this.batasMaxKetahanan;

                //menambah nilai jumlah diperbaiki pada senjata
                this.jumlahDiperbaiki += 1;

                //mengecek bisa atau tidaknya diperbaiki (mencapai batas maksimal diperbaiki atau tidak)
                if(this.jumlahDiperbaiki == this.batasMaxDiperbaiki){
                    //mengubah kemampuan diperbaiki menjadi false atau tidak bisa diperbaiki
                    this.statusKemampuanDiperbaiki = false;
                }
            }

        }else{
            System.out.println();
            System.out.println("[ Komponen Crafting untuk perbaikan tidak cocok ]");
        }
    }

    public void upgradeSenjata(Barang oBluePrint){
        this.setKekuatan(this.getKekuatan() + oBluePrint.getPeningkatanKekuatan());
        this.batasMaxKetahanan += oBluePrint.getPeningkatanBatasMaxKetahanan();
        this.setDaftarEfek(oBluePrint.getDaftarTambahanEfek());
    }

    @Override
    public boolean isStatusKemampuanDiperbaiki() {
        if(this.ketahanan == batasMaxKetahanan){
            statusKemampuanDiperbaiki = false;
        }else if(this.jumlahDiperbaiki == this.batasMaxDiperbaiki){
            statusKemampuanDiperbaiki = false;
        }else{
            statusKemampuanDiperbaiki = true;
        }
        return statusKemampuanDiperbaiki;
    }

    @Override
    public HashMap<String, Integer> getKomponenUntukPerbaikan() {
        return komponenUntukPerbaikan;
    }

    @Override
    public int getKetahanan() {
        return ketahanan;
    }

    @Override  /* untuk mengetahui jumlah kemampuan diperbaiki yang tersisa */
    public int jumlahKemampuanDiperbaiki(){
        return this.batasMaxDiperbaiki - this.jumlahDiperbaiki;
    }
}
