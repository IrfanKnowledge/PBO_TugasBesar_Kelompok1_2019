import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarangBluePrint extends Barang{
    private HashMap<Integer, ArrayList<Barang>> daftarKomponenCrafting = new HashMap<>();
    private HashMap<Integer, Barang> daftarSenjataUntukCrafting = new HashMap<>();
    private Barang senjataUntukCraftingTerpilih;
    private Barang hasilCrafting;
    private int jumlahHasilCrafting;

    private int peningkatanKekuatan = 0;
    private int peningkatanBatasMaxKetahanan = 0;
    private ArrayList<Efek> daftarTambahanEfek = new ArrayList<>();

    BarangBluePrint(int idBarang, String nama, String kategori, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual) {
        super(idBarang, nama, kategori, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual);
    }

    BarangBluePrint(int idBarang, String nama, String kategori, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int nilaiKesehatan){
        super(idBarang, nama, kategori, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, nilaiKesehatan);
    }

    BarangBluePrint(int idBarang, String nama, String kategori, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, HashMap<Integer, ArrayList<Barang>> daftarKomponenCrafting, HashMap<Integer, Barang> daftarSenjataUntukCrafting, Barang hasilCrafting, int jumlahHasilCrafting){
        super(idBarang, nama, kategori, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual);
        this.daftarKomponenCrafting = daftarKomponenCrafting;
        this.daftarSenjataUntukCrafting = daftarSenjataUntukCrafting;
        this.hasilCrafting = hasilCrafting;

        //Jika hasil crafting TIDAK TERMASUK kategori Senjata Pukul dan Senjata Tembak maka jumlah hasil crafting boleh lebih dari 1
        if(!this.hasilCrafting.getKategori().equals("Senjata Pukul") && !this.hasilCrafting.getKategori().equals("Senjata Tembak")){
            this.jumlahHasilCrafting = jumlahHasilCrafting;
        //jika termasuk senjata pukul dan senjata tembak maka jumlah hasil crafting hanya boleh 1
        }else{
            this.jumlahHasilCrafting = 1;
        }
    }

    //constructor untuk blueprint meng-upgrade senjata
    BarangBluePrint(int idBarang, String nama, String kategori, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, HashMap<Integer, ArrayList<Barang>> daftarKomponenCrafting, HashMap<Integer, Barang> daftarSenjataUntukCrafting, int peningkatanKekuatan, int peningkatanBatasMaxKetahanan, ArrayList<Efek> daftarTambahanEfek){
        super(idBarang, nama, kategori, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual);
        this.daftarKomponenCrafting = daftarKomponenCrafting;
        this.daftarSenjataUntukCrafting = daftarSenjataUntukCrafting;
        this.hasilCrafting = null;
        this.jumlahHasilCrafting = 1;

        this.peningkatanKekuatan = peningkatanKekuatan;
        this.peningkatanBatasMaxKetahanan = peningkatanBatasMaxKetahanan;
        this.daftarTambahanEfek = daftarTambahanEfek;
        //menandakan blueprint ini untuk upgrade
        this.setStatusUpgrade(true);
    }

    @Override
    public ArrayList<Barang> gunakanBarangBluePrint(HashMap<Integer, ArrayList<Barang>> daftarKomponenCrafting, Barang senjata, int efisiensiCrafting) {

        //untuk pencocokan kebutuhan daftarKomponenCrafting dan senjata untuk crafting dengan inputan daftarKomponenCrafting dan Senjata
        boolean statusKecocokan = true;

        //untuk pencocokan kebutuhan daftarKomponenCrafting untuk blue print ini dengan daftarKomponenCrafting inputan
        for (Map.Entry<Integer, ArrayList<Barang>> record: this.daftarKomponenCrafting.entrySet()){
            //jika inputan komponen terdapat key dari kebutuhan komponen crafting maka...
            if(daftarKomponenCrafting.containsKey(record.getKey())){
                //jika jumlah inputan komponen tertentu TIDAK MENCUKUPI jumlah kebutuhan komponen tertentu untuk crafting maka..
                if(record.getValue().size() > daftarKomponenCrafting.get(record.getKey()).size()){
                    statusKecocokan = false;
                    break;
                }
            //jika tidak terdapat maka..
            }else{
                statusKecocokan = false;
                break;
            }
        }

        //jika cocok dan daftar komponen crafting tidak kosong, kita hapus isi daftar komponen crafting
        if(statusKecocokan && !daftarKomponenCrafting.isEmpty()){
            daftarKomponenCrafting.clear();
        }

        //menampung jumlah hasil crafting
        ArrayList<Barang> daftarHasilCrafting = new ArrayList<>();

        //jika kebutuhan senjata untuk crafting tidak kosong maka...
        if(!this.daftarSenjataUntukCrafting.isEmpty()){
            //jika senjata kebutuhan crafting sudah terpilih atau tidak kosong maka..
            if(senjataUntukCraftingTerpilih != null){
                //jika idSenjata kebutuhan crafting dan inputan sama maka..
                if(senjataUntukCraftingTerpilih.getIdBarang() == senjata.getIdBarang()){

                    //jika method ini dipanggil untuk keperluan Upgrade maka...
                    if(this.isStatusUpgrade()){
                        //return blue-print ini krn blueprint ini memuat nilai peningkatan kekuatan, batasMaxKetahanan, dan daftar efek...
                        //...yang kemudian proses upgrade pada senjata akan dilakukan diluar class ini (turunan class Pilihan) dan setelahnya dipanggil method/fungsi milik senjata tersebut
                        //...dengan teknis upgrade yang diatur dalam class senjata tersebut
                        daftarHasilCrafting.add(this);
                        return daftarHasilCrafting;
                    //jika BUKAN UNTUK UPGRADE maka..
                    }else{
                        //mengosongkan senjata
                        senjata = null;
                    }

                //jika idSenjata kebutuhan crafting dan inputan TIDAK SAMA maka...
                }else{
                    statusKecocokan = false;
                }

            //jika senjata untuk crafting belum terpilih atau masih kosong maka...
            }else{
                statusKecocokan = false;
            }
        }

        //jika hasil perbandingan kebutuhan/syarat crafting tidak terpenuhi maka...
        if(!statusKecocokan){
            return null;
        }

        //Jika hasil crafting TIDAK TERMASUK kategori Senjata Pukul dan Senjata Tembak maka jumlah hasil crafting dipengaruhi skill efisiensi crafting player
        if(!this.hasilCrafting.getKategori().equals("Senjata Pukul") && !this.hasilCrafting.getKategori().equals("Senjata Tembak")){
            this.jumlahHasilCrafting += efisiensiCrafting;
        }

        //jika jumlah hasil crafting lebih dari 1, kita instance dalam jumlah lebih dari 1
        if(this.jumlahHasilCrafting > 1){

            //tambahkan 1 hasil crafting
            daftarHasilCrafting.add(this.hasilCrafting);

            //pengulangan meng-instance hasil crafting dengan nilai sejenis atau sama percis (tetapi berbeda objek)
            for(int i=daftarHasilCrafting.size(); i<=jumlahHasilCrafting; i++){
                daftarHasilCrafting.add(new Barang(this.hasilCrafting));
            }

            return daftarHasilCrafting;
        }

        return daftarHasilCrafting;
    }

    public HashMap<Integer, ArrayList<Barang>> getDaftarKomponenCrafting() {
        return daftarKomponenCrafting;
    }

    public HashMap<Integer, Barang> getDaftarSenjataUntukCrafting() {
        return daftarSenjataUntukCrafting;
    }

    public boolean setSenjataUntukCraftingTerpilih(Barang senjataUntukCraftingTerpilih) {
        if(this.daftarSenjataUntukCrafting.containsKey(senjataUntukCraftingTerpilih.getIdBarang())){
            this.senjataUntukCraftingTerpilih = senjataUntukCraftingTerpilih;
            //berhasil karena sesuai daftar tersedia
            return true;
        }else{
            //gagal karena tidak sesuai daftar tersedia
            return false;
        }
    }

    public Barang getSenjataUntukCraftingTerpilih() {
        return senjataUntukCraftingTerpilih;
    }

    public int getPeningkatanKekuatan() {
        return peningkatanKekuatan;
    }

    public int getPeningkatanBatasMaxKetahanan() {
        return peningkatanBatasMaxKetahanan;
    }

    public ArrayList<Efek> getDaftarTambahanEfek() {
        return daftarTambahanEfek;
    }
}
