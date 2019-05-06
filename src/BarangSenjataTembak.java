import java.util.ArrayList;

public class BarangSenjataTembak extends Barang{
    private int idAmunisiUtama;
    private ArrayList<Integer> daftarIdAmunisi = new ArrayList<>();
    private ArrayList<Barang> daftarAmunisi = new ArrayList<>();
    private int batasMaxAmunisi;

    //constructor tanpa daftarEfek
    BarangSenjataTembak(int idBarang, String nama, String jenis, String kategoriPenyimpanan, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int kekuatan, int batasMaxAmunisi, ArrayList<Barang> daftarAmunisi, ArrayList<Integer> daftarIdAmunisi, int idAmunisiUtama){
        super(idBarang, nama, jenis, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, kekuatan, new ArrayList<>());

        //inisiasi default idAmunisiUtama
        this.idAmunisiUtama = idAmunisiUtama;

        //jika daftaramunisi kosong
        if(daftarAmunisi.isEmpty()){
            //menambahkan minimal 1 buah id amunisi yang bisa digunakan senjata ini
            this.daftarIdAmunisi.add(this.idAmunisiUtama);
        }

        //proses menambahkan daftar amunisi yang bisa digunakan
        this.daftarIdAmunisi = daftarIdAmunisi;



        //jika input batasMaxAmunisi kurang dari 0, isi dengan 1 sebagai minimal
        if(batasMaxAmunisi < 0){
            this.batasMaxAmunisi = 1;
        }else{
            this.batasMaxAmunisi = batasMaxAmunisi;
        }

        //pengulangan sebanyak jumlah input daftarAmunisi + menghapus peluru yang diambil
        for(int i=daftarAmunisi.size()-1; i>=0; i--){
            //mengecek setiap isi daftarAmunisi yang masuk, apakah memiliki id yang cocok atau tidak
            if(daftarAmunisi.get(i).getIdBarang() == this.idAmunisiUtama){
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
    public boolean isiPeluru(ArrayList<Barang> daftarAmunisi) {
        if(this.daftarAmunisi.size() == this.batasMaxAmunisi){
            //proses gagal krn daftarAmunisi masih penuh
            return false;
        }else{
            //mengecek id sesuai atau tidak (amunisi atau bukan)
            if (daftarAmunisi.get(0).getIdBarang() != this.idAmunisiUtama){
                //proses gagal krn barang bukan amunisi (id tidak sesuai)
                return false;
            }else{
                //mengecek kosong atau tidaknya daftarAmunisi
                if(daftarAmunisi.isEmpty()){
                    //proses gagal krn amunisi dianggap kosong
                    return false;
                }else{
                    //pengulangan sebanyak jumlah input daftarAmunisi + menghapus peluru yang diambil
                    for(int i=daftarAmunisi.size()-1; i>=0; i--){
                        this.daftarAmunisi.add(daftarAmunisi.get(i));
                        //proses menghapus objek pada urutan terakhir dalam daftarAmunisi inputan
                        daftarAmunisi.remove(i);

                        //jika daftarAmunisi senjata telah mencapai batasMaxAMunisi maka keluar paksa dari pengulangan ini
                        if(this.daftarAmunisi.size() == batasMaxAmunisi){
                            break;
                        }
                    }
                    //proses isi peluru berhasil
                    return true;
                }
            }
        }
    }

    public int getIdAmunisiUtama() {
        return idAmunisiUtama;
    }

    public ArrayList<Integer> getDaftarIdAmunisi() {
        return daftarIdAmunisi;
    }

    public int getJumlahKebutuhanIsiPeluru(){
        return this.batasMaxAmunisi - this.daftarAmunisi.size();
    }

    public int getBatasMaxAmunisi(){
        return this.batasMaxAmunisi;
    }
    public int getJumlahPeluru(){
        return this.daftarAmunisi.size();
    }
}
