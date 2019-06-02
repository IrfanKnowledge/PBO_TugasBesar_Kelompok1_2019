import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Player {
    public int idPlayer;
    public String nama;
    private int kesehatan = 100;
    private int batasMaxKesehatan = 100;
    private double uang = 0;

    /* Menandakan posisi Player berada pada Adegan tertentu */
    public Adegan adeganAktif;

    /* Berikut Atribut yang dipengaruhi Efek */
    private int durasiStun = 0;
    private int ketahananTambahan = 0;
    private int batasMaxKetahananTambahan = 0;
    private int durasiKetahanan = 0;
    private HashMap<Integer, Efek> daftarEfekDiri = new HashMap<>();

    public Barang senjata;                         //Belum
    public ArrayList<BarangSenjata> senjataLempar; //Belum

    private PengelolaanBarang oPengelolaanBarang;
    private MenuPengelolaanBarang oMenuPengelolaanBarang;

    /* Menandakan Game ini telah selesai atau tidak */
    private boolean isSelesai = false;

    Player(int idPlayer, String nama){
        this.idPlayer = idPlayer;
        this.nama = nama;
        this.oPengelolaanBarang = new PengelolaanBarang(10, 8);
        this.oMenuPengelolaanBarang = new MenuPengelolaanBarang(this.oPengelolaanBarang);
    }

    public static void main(String[] args) {
        //==============================================================================
        // berikut proses percobaan
        Player A = new Player(1, "irfan");
        Player B = new Player(2, "dani");
        //System.out.println(A.daftarLevelBertahanHidup.get(3).nilaiLevel);
        //A.daftarSkill.get(0).unlockSkill(A.levelBertahanHidup, A.pointBertahanHidup);
        //if(A.testing == null){
            //System.out.println(A.testing + "MASIH NULL");
        //}
        Random pengacak = new Random();
        for (int i=0; i<5; i++){
            System.out.println(pengacak.nextInt(10));
        }
        //==============================================================================
    }
    public String getNama() {
        return nama;
    }

    public boolean isSelesai() {
        return isSelesai;
    }

    public PengelolaanBarang getPengelolaanBarang() {
        return oPengelolaanBarang;
    }

    public void kurangiKesehatan(int nilaiSerangan){
        if(this.kesehatan > 0){
            this.kesehatan -= nilaiSerangan;
            if(this.kesehatan < 0){
                this.kesehatan = 0;
            }
        }
    }

    public ArrayList<Barang> pilihBarangKeseluruhanByKategoriTertentu(String aksi, String kategoriBarang){
        return this.oMenuPengelolaanBarang.pilihBarangKeseluruhanByKategoriTertentu(aksi, kategoriBarang);
    }

    public ArrayList<BarangSenjata> pilihBarangSenjata(String aksi){
        return this.oMenuPengelolaanBarang.pilihBarangSenjata(aksi);
    }

//    /* return berupa key sebagai kategori, value indeks 0 = idBarang, value indeks 1 = IndeksBarang*/
//    public HashMap<String, ArrayList<Integer>> pilihKategoriIdIndeksBarang(String kategori){
//        /* return berupa key sebagai kategori, value indeks 0 = idBarang, value indeks 1 = IndeksBarang*/
//        return barangPilihan;
//    }
//
//    public Barang pilihBarangSatu(String kategori, int idBarang, int indeksBarang){
//        if(this.daftarBarangPencarian.containsKey(kategori)){
//            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)) {
//                return this.daftarBarangPencarian.get(kategori).get(idBarang).get(indeksBarang);
//            }
//        }
//        return null;
//    }
//
//    public ArrayList<Barang> pilihBarangSemuanya(String kategori, int idBarang){
//        if(this.daftarBarangPencarian.containsKey(kategori)){
//            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)) {
//
//                return this.daftarBarangPencarian.get(kategori).get(idBarang);
//            }
//        }
//        return null;
//    }
//
//    public void hapusBarangSatu(String kategori, int idBarang){
//        if(this.daftarBarangPencarian.containsKey(kategori)){
//            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)){
//
//                /* jika list tersebut tidak kosong maka... */
//                if(!this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
//                    this.daftarBarangPencarian.get(kategori).get(idBarang).remove(0);
//                    if(this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
//                        this.daftarBarangPencarian.get(kategori).remove(idBarang);
//                    }
//                }
//            }
//        }
//    }
//
//    public void hapusBarangSatuIndeksTertentu(String kategori, int idBarang, int indeks){
//        if(this.daftarBarangPencarian.containsKey(kategori)){
//            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)){
//
//                /* jika list tersebut tidak kosong maka... */
//                if(!this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
//                    this.daftarBarangPencarian.get(kategori).get(idBarang).remove(indeks);
//                    if(this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
//                        this.daftarBarangPencarian.get(kategori).remove(idBarang);
//                    }
//                }
//            }
//        }
//    }
//
//    public void hapusBarangSemuanya(String kategori, int idBarang){
//        if(this.daftarBarangPencarian.containsKey(kategori)){
//            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)){
//                this.daftarBarangPencarian.get(kategori).get(idBarang).clear();
//                this.daftarBarangPencarian.get(kategori).remove(idBarang);
//            }
//        }
//    }
//
//    public void hapusBarangJumlahTertentu(String kategori, int idBarang, int jumlahBarangDihapus){
//        if(this.daftarBarangPencarian.containsKey(kategori)){
//            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)){
//                for(int i=0; i<this.daftarBarangPencarian.get(kategori).get(idBarang).size(); i+=0){
//                    if(jumlahBarangDihapus != 0){
//                        this.daftarBarangPencarian.get(kategori).get(idBarang).remove(i);
//                        jumlahBarangDihapus--;
//                    }else{
//                        /* jika jumlahBarangDihapus sudah kosong, keluar dari for ini*/
//                        break;
//                    }
//                }
//                if(this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
//                    this.daftarBarangPencarian.get(kategori).remove(idBarang);
//                }
//            }
//        }
//    }

    //==================================================================================
    /* Method untuk unlock skill */
    /*
    public void ubahBatasMaxKesehatan(int batasMaxKesehatan){
        this.batasMaxKesehatan = batasMaxKesehatan;
    }

    public void ubahBatasMaxSenjataDinamis(int batasMaxSenjataDinamis){
        this.batasMaxSenjataDinamis = batasMaxSenjataDinamis;
    }

    public void ubahKemampuanMenawarBarang(int kemampuanMenawarBarang){
        this.kemampuanMenawarBarang = kemampuanMenawarBarang;
    }

    public void ubahEfisiensiSenjata(int efisiensiSenjata){
        this.efisiensiSenjata = efisiensiSenjata;
    }

    public void ubahKemampuanMenjualBarang(int kemampuanMenjualBarang){
        this.kemampuanMenjualBarang = kemampuanMenjualBarang;
    }

    public void ubahEfisiensiCrafting(int efisiensiCrafting){
        this.efisiensiCrafting = efisiensiCrafting;
    }
    */
    //==================================================================================

    /* Proses pendefinisian setiap level bertahan hidup */
        /*
        ArrayList<Level> daftarLevelBertahanHidup = new ArrayList<>();
        daftarLevelBertahanHidup.add(new Level(1, "Bertahan Hidup", 1, 0));
        daftarLevelBertahanHidup.add(new Level(2, "Bertahan Hidup", 2, 100));
        daftarLevelBertahanHidup.add(new Level(3, "Bertahan Hidup", 3, 200));
        daftarLevelBertahanHidup.add(new Level(4, "Bertahan Hidup", 4, 400));
        daftarLevelBertahanHidup.add(new Level(5, "Bertahan Hidup", 5, 600));
        daftarLevelBertahanHidup.add(new Level(6, "Bertahan Hidup", 6, 800));
        daftarLevelBertahanHidup.add(new Level(7, "Bertahan Hidup", 7, 1000));
        daftarLevelBertahanHidup.add(new Level(8, "Bertahan Hidup", 8, 1200));
        daftarLevelBertahanHidup.add(new Level(9, "Bertahan Hidup", 9, 1400));
        daftarLevelBertahanHidup.add(new Level(10, "Bertahan Hidup", 10, 1600));
        */

    /* Proses pendefinisian setiap level kekuatan */
        /*
        ArrayList<Level> daftarLevelKekuatan = new ArrayList<>();
        daftarLevelKekuatan.add(new Level(11, "Kekuatan", 1, 0));
        daftarLevelKekuatan.add(new Level(12, "Kekuatan", 2, 100));
        daftarLevelKekuatan.add(new Level(13, "Kekuatan", 3, 200));
        daftarLevelKekuatan.add(new Level(14, "Kekuatan", 4, 400));
        daftarLevelKekuatan.add(new Level(15, "Kekuatan", 5, 600));
        */

    /* Proses pendefinisian setiap Skill yang dapat diunlock oleh Player */
        /*
        ArrayList<Skill> daftarSkill = new ArrayList<>();
        Barang medikit = new Barang(99, "Medikit", "Kesehatan", "Menambah kesehatan Player",true, true, 30, 15); //Contoh
        daftarSkill.add(new SkillCrafting(this, 1,medikit.getNama(), 1, 1, "Rancangan/BluPrint untuk membuat barang medikit.", medikit));
        */

    /* Proses inisiasi beberapa atribut yang belum di-inisiasi */
        /*
        this.levelBertahanHidup = daftarLevelBertahanHidup.get(0);
        this.levelKekuatan = daftarLevelKekuatan.get(0);
        this.daftarLevelBertahanHidup = daftarLevelBertahanHidup;
        this.daftarLevelKekuatan = daftarLevelKekuatan;
        this.daftarSkill = daftarSkill;
        */


    /* Berikut Atribut yang dipengaruhi Level */
//    private Level levelBertahanHidup;               //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private int expBertahanHidup = 0;               //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private int pointBertahanHidup = 0;             //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private Level levelKekuatan;                    //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private int expKekuatan = 0;                    //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private int pointKekuatan = 0;                  //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private ArrayList<Level> daftarLevelBertahanHidup;  //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private ArrayList<Level> daftarLevelKekuatan;       //Untuk Skill dan level, diakhir saja, utamakan hal lain

    /* Berikut Atribut yang dipengaruhi Skill */
//    private int efisiensiSenjata = 0;               //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private int efisiensiCrafting = 0;              //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private int kemampuanMenawarBarang = 0;         //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private int kemampuanMenjualBarang = 0;         //Untuk Skill dan level, diakhir saja, utamakan hal lain
//    private ArrayList<Skill> daftarSkill;           //Untuk Skill dan level, diakhir saja, utamakan hal lain


//        private double nilaiKecepatanTambahan = 0;
//    private int durasiKecepatanTambahan = 0;
//    private int durasiPengelihatanMalam = 0;
//    private int durasiKamuflase = 0;

    /* Waktu melekat pada diri Player, kemudian pada saat malam ketika berada pada adegan yang memiliki Musuh Kuat di malam hari,
     *  maka mereka akan muncul, dan ketika berganti hari maka semua barang-barang dan musuh akan me-refresh
     */
//    private double waktu = 7.00;
//    private double kecepatanPerubahanWaktuNormal = 0.10;
//    private String statusWaktu = "siang";
//    private double batasAwalMalam = 21.00;
//    private double batasAkhirMalam = 6.00;

    //    private HashMap<String, HashMap<Integer, ArrayList<Barang>>> penyimpanansStatis;   //Belum
    /* Berikut daftar barang yang dipisahkan seperti berikut agar memudahkan proses penyajian daftar barang
     *  pada daftarBarang digunakan saat Player melihat dan memilih barang
     *  sedangkan pada daftarBarangPencarian di bawah, untuk proses mencari barang tanpa melibatkan inputan Player
     *  yaitu mencari berdasarkan id barang
     */
//    private HashMap<String, ArrayList<ArrayList<Barang>>> daftarBarang = new HashMap<>();

    /*untuk membatasi jumlah senjata dalam kantong */
//    private int batasMaxSenjataDinamis = 4;

    /* Berikut daftar barang untuk memudahkan proses pencarian tertentu, tanpa melibatkan input pemain dalam memilih barang (misal saat crafting)
     *  sehingga daftar barang berikut dengan daftarBarang di atas adalah sama,
     *  atau berguna saat menambahkan barang dengan cepat dan tetap menjaga bentuk penyajian seperti misal -Medikit (5),
     *  angka 5 menandakan jumlah medikit yang tersedia.
     */
//    private HashMap<String, HashMap<Integer, ArrayList<Barang>>> daftarBarangPencarian = new HashMap<>();

//    Hewan hewanPembantu; //belum


//    public void ubahWaktu(double nilaiPenambah){
//        if(nilaiPenambah < 0){
//            nilaiPenambah = 0;
//        }
//
//        /* nilai penambah tersebut bisa ditentukan dari setiap pilihan atau tindakan di berbagai adegan */
//        this.waktu += nilaiPenambah;
//        this.waktu += this.kecepatanPerubahanWaktuNormal;
//
//        /* jika memiliki efek kecepatan tambahan maka tambahkan bonus kecepatannya */
//        if(this.durasiKecepatanTambahan > 0){
//            this.waktu += this.nilaiKecepatanTambahan;
//        }
//        if(this.waktu >= 24 ){
//            this.waktu -= 24;
//        }
//    }


    //    public double getUang() {
//        return uang;
//    }

//    public HashMap<String, HashMap<Integer, ArrayList<Barang>>> getDaftarBarangPencarian() {
//        return daftarBarangPencarian;
//    }

//    public double getWaktu() {
//        return waktu;
//    }

//    public int getJumlahSlotSenjataKosong(){
//        int jumlahBarangSenjata = 0;
//        /* Jika terdapat senjata maka... */
//        if(this.daftarBarangPencarian.containsKey("Senjata")){
//            for (Map.Entry<Integer, ArrayList<Barang>> isiList2 : this.daftarBarangPencarian.get("Senjata").entrySet()) {
//                jumlahBarangSenjata += isiList2.getValue().size();
//            }
//            return this.batasMaxSenjataDinamis - jumlahBarangSenjata;
//        }else{
//            return this.batasMaxSenjataDinamis - jumlahBarangSenjata;
//        }
//    }
}
