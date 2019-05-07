import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarangSenjataTembak extends Barang{
    private HashMap<String, Integer> idAmunisiUtama;
    private HashMap<String, ArrayList<Integer>>  daftarIdAmunisi = new HashMap<>();
    private ArrayList<Barang> daftarAmunisi = new ArrayList<>();
    private int batasMaxAmunisi;

    //constructor tanpa daftarEfek
    BarangSenjataTembak(int idBarang, String nama, String jenis, String kategoriPenyimpanan,
                        String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan,
                        int batasMaxAmunisi, ArrayList<Barang> daftarAmunisi, HashMap<String, ArrayList<Integer>> daftarIdAmunisi, HashMap<String, Integer> idAmunisiUtama){
        super(idBarang, nama, jenis, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, kekuatan, new ArrayList<>());

        if(idAmunisiUtama != null){
            if(!idAmunisiUtama.isEmpty()){
                //inisiasi default idAmunisiUtama
                this.idAmunisiUtama = idAmunisiUtama;
            }
        }

        if(daftarAmunisi != null){
            //jika daftaramunisi kosong
            if(daftarAmunisi.isEmpty()){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(this.idAmunisiUtama.get("Amunisi"));
                daftarIdAmunisi.put("Amunisi", temp);

                //menambahkan minimal 1 buah id amunisi yang bisa digunakan senjata ini
                this.daftarIdAmunisi = daftarIdAmunisi;
            }else{
                //proses menambahkan daftar amunisi yang bisa digunakan
                this.daftarIdAmunisi = daftarIdAmunisi;
            }
        }

        //jika input batasMaxAmunisi kurang dari 0, isi dengan 1 sebagai minimal
        if(batasMaxAmunisi < 0){
            this.batasMaxAmunisi = 1;
        }else{
            this.batasMaxAmunisi = batasMaxAmunisi;
        }

        for (Map.Entry<String, Integer> oIdAmunisiUtama : this.idAmunisiUtama.entrySet()) {
            for(int i=0; i<daftarAmunisi.size(); i+=0){
                //mengecek setiap isi daftarAmunisi yang masuk, apakah memiliki id yang cocok atau tidak
                if(daftarAmunisi.get(i).getIdBarang() == oIdAmunisiUtama.getValue()){
                    this.daftarAmunisi.add(daftarAmunisi.get(i));
                    //proses menghapus objek pada urutan terakhir dalam daftarAmunisi inputan
                    daftarAmunisi.remove(i);
                }

                //jika daftarAmunisi senjata telah mencapai batasMaxAMunisi maka keluar paksa dari pengulangan ini
                if(this.daftarAmunisi.size() == batasMaxAmunisi){
                    break;
                }
            }
        }
    }

    BarangSenjataTembak(BarangSenjataTembak oBarang){
        super(oBarang);
        this.idAmunisiUtama = oBarang.idAmunisiUtama;
        this.daftarIdAmunisi = oBarang.daftarIdAmunisi;
        this.daftarAmunisi = oBarang.daftarAmunisi;
        this.batasMaxAmunisi = oBarang.batasMaxAmunisi;
    }

    @Override
    public Barang cloning() {
        return new BarangSenjataTembak(this);
    }

    @Override
    public Barang gunakanBarangSenjata() {
        if(this.daftarAmunisi.size() < 1){
            //proses gagal atau dianggap tidak bisa menembak karena habis peluru
            return null;
        }else{
            //memindahkan peluru
            Barang peluru = this.daftarAmunisi.get(this.daftarAmunisi.size()-1);

            //peluru dalam senjata berkurang 1
            this.daftarAmunisi.remove(this.daftarAmunisi.size()-1);

            //proses menembak berhasil
            return peluru;
        }
    }

    @Override
    public void isiPeluru(ArrayList<Barang> daftarAmunisi) {

        for (Map.Entry<String, Integer> oIdAmunisiUtama : this.idAmunisiUtama.entrySet()) {

            if(daftarAmunisi == null){
                System.out.println();
                System.out.printf("[ komponen amunisi yang akan digunakan null ]\n");
            }else if(daftarAmunisi.isEmpty()){
                System.out.println();
                System.out.printf("[ komponen amunisi yang akan digunakan kosong ]\n");
            }else if(daftarAmunisi.get(0).getIdBarang() != oIdAmunisiUtama.getValue()){
                System.out.println();
                System.out.printf("[ komponen yang digunakan bukanlah amunisi / bukan amunisi yang tepat ]\n");
            }else if(this.daftarAmunisi.size() == this.batasMaxAmunisi){
                System.out.println();
                System.out.printf("[ Jumlah peluru %s masih penuh ]\n", this.getNama());

            }else{
                //pengulangan sebanyak jumlah input daftarAmunisi + menghapus peluru yang diambil
                for(int i=0; i<daftarAmunisi.size(); i+=0){
                    this.daftarAmunisi.add(daftarAmunisi.get(i));

                    //jika daftarAmunisi senjata telah mencapai batasMaxAMunisi maka keluar paksa dari pengulangan ini
                    if(this.daftarAmunisi.size() == batasMaxAmunisi){
                        break;
                    }
                }
            }
        }
    }

    public void gantiPeluru(int indeks){
        if(this.daftarIdAmunisi != null){
            for (Map.Entry<String, ArrayList<Integer>> oDaftarIdAmunisi : this.daftarIdAmunisi.entrySet()) {
                this.idAmunisiUtama.put(oDaftarIdAmunisi.getKey(), oDaftarIdAmunisi.getValue().get(indeks));
            }
        }
    }

    @Override
    public HashMap<String, Integer> getIdAmunisiUtama() {
        return idAmunisiUtama;
    }

    @Override
    public HashMap<String, ArrayList<Integer>> getDaftarIdAmunisi() {
        return daftarIdAmunisi;
    }

    @Override
    public int getJumlahKebutuhanIsiPeluru(){
        return this.batasMaxAmunisi - this.daftarAmunisi.size();
    }

    @Override
    public int getBatasMaxAmunisi(){
        return this.batasMaxAmunisi;
    }

    @Override
    public int getJumlahPeluru(){
        return this.daftarAmunisi.size();
    }
}
