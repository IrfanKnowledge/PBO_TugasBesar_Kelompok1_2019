import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class Player {
    private int idPlayer;
    private String nama;
    private int kesehatan = 100;
    private int batasMaxKesehatan = 100;

    /* Waktu melekat pada diri Player, kemudian pada saat malam ketika berada pada adegan yang memiliki Musuh Kuat di malam hari,
    *  maka mereka akan muncul, dan ketika berganti hari maka semua barang-barang dan musuh akan me-refresh
    */
    private double waktu = 7.00;
    private String statusWaktu = "siang";
    private double batasAwalMalam = 21.00;
    private double batasAkhirMalam = 6.00;

    private double uang = 0;
    private Barang senjata;                         //Belum

    /* Menandakan posisi Player berada pada Adegan tertentu */
    public Adegan adeganAktif;

    /* Berikut Atribut yang dipengaruhi Level */
    private Level levelBertahanHidup;               //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private int expBertahanHidup = 0;               //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private int pointBertahanHidup = 0;             //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private Level levelKekuatan;                    //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private int expKekuatan = 0;                    //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private int pointKekuatan = 0;                  //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private ArrayList<Level> daftarLevelBertahanHidup;  //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private ArrayList<Level> daftarLevelKekuatan;       //Untuk Skill dan level, diakhir saja, utamakan hal lain

    /* Berikut Atribut yang dipengaruhi Skill */
    private int efisiensiSenjata = 0;               //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private int efisiensiCrafting = 0;              //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private int kemampuanMenawarBarang = 0;         //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private int kemampuanMenjualBarang = 0;         //Untuk Skill dan level, diakhir saja, utamakan hal lain
    private ArrayList<Skill> daftarSkill;           //Untuk Skill dan level, diakhir saja, utamakan hal lain

    /* Berikut Atribut yang dipengaruhi Efek */
    private int durasiStun = 0;
    private int nilaiKetahanan = 0;
    private int batasMaxKetahanan = 0;
    private int durasiKetahanan = 0;
    private int nilaiKecepatanTambahan = 0;
    private int durasiKecepatanTambahan = 0;
    private int durasiPengelihatanMalam = 0;
    private int durasiKamuflase = 0;
    private HashMap<Integer, Efek> daftarEfekDiri = new HashMap<>();

    private HashMap<String, HashMap<Integer, ArrayList<Barang>>> penyimpanansStatis;   //Belum
    /* Berikut daftar barang yang dipisahkan seperti berikut agar memudahkan proses penyajian daftar barang
    *  pada daftarBarang digunakan saat Player melihat dan memilih barang
    *  sedangkan pada daftarBarangPencarian di bawah, untuk proses mencari barang tanpa melibatkan inputan Player
    *  yaitu mencari berdasarkan id barang
    */
    private HashMap<String, ArrayList<ArrayList<Barang>>> daftarBarang = new HashMap<>();

    /*untuk membatasi jumlah senjata dalam kantong */
    private int batasMaxSenjataDinamis = 4;

    /* Berikut daftar barang untuk memudahkan proses pencarian tertentu, tanpa melibatkan input pemain dalam memilih barang (misal saat crafting)
    *  sehingga daftar barang berikut dengan daftarBarang di atas adalah sama,
    *  atau berguna saat menambahkan barang dengan cepat dan tetap menjaga bentuk penyajian seperti misal -Medikit (5),
    *  angka 5 menandakan jumlah medikit yang tersedia.
    */
    private HashMap<String, HashMap<Integer, ArrayList<Barang>>> daftarBarangPencarian = new HashMap<>();

    Hewan hewanPembantu; //belum

    /* Menandakan Game ini telah selesai atau tidak */
    private boolean isSelesai = false;

    Player(int idPlayer, String nama){
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

        this.idPlayer = idPlayer;
        this.nama = nama;

        /* Berikut Kategori-Kategori yang dimiliki Player selama Game Berjalan */
        this.daftarBarang.put("Kunci", new ArrayList<>());
        this.daftarBarang.put("Senjata", new ArrayList<>());
        this.daftarBarang.put("Komponen Crafting", new ArrayList<>());
        this.daftarBarang.put("Barang Bernilai", new ArrayList<>());
        this.daftarBarang.put("Blueprint", new ArrayList<>());
        this.daftarBarang.put("Amunisi", new ArrayList<>());
        this.daftarBarang.put("Barang Lainnya", new ArrayList<>());
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
        HashMap<Coba, Coba> testing = new HashMap<>();

        Coba satu = new Coba("sedotan");
        Coba dua = new Coba("garpu");

        testing.put(satu, satu);
        testing.put(dua, dua);

        ArrayList<Coba> testdeui = new ArrayList<>();
        testdeui.add(satu);
        testdeui.add(dua);
        Coba sementara = testdeui.get(1);
        testdeui.remove(testdeui.size()-1);

        if(testdeui.size() > 0){
            System.out.println(sementara.mencoba + " " + testdeui.get(0));
        }

        Random pengacak = new Random();
        for (int i=0; i<5; i++){
            System.out.println(pengacak.nextInt(10));
        }
        //==============================================================================
    }


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

    public void tambahBarang(HashMap<String, ArrayList<ArrayList<Barang>>> oKategori){
        for (Map.Entry<String, ArrayList<ArrayList<Barang>>> isiList3: oKategori.entrySet()) {

            /* Seleksi kategori */
            if(isiList3.getKey().equals("Kunci") || isiList3.getKey().equals("Senjata") ||
                    isiList3.getKey().equals("Komponen Crafting") || isiList3.getKey().equals("Barang Berharga") ||
                    isiList3.getKey().equals("Blueprint") || isiList3.getKey().equals("Amunisi") ||
                    isiList3.getKey().equals("Barang Lainnya")){

                for (ArrayList<Barang> isiList2 : isiList3.getValue()){
                    if(this.daftarBarangPencarian.get(isiList3.getKey()).containsKey(isiList2.get(0).getIdBarang())){
                        this.daftarBarangPencarian.get(isiList3.getKey()).get(isiList2.get(0).getIdBarang()).addAll(isiList2);
                    }else{
                        this.daftarBarangPencarian.get(isiList3.getKey()).put(isiList2.get(0).getIdBarang(), isiList2);
                    }
                }
            }
        }

        /* sinkronisasi antara dattarBarangPencarian dan daftar barang */
        this.sinkronisasi();
    }

    /* Private karena hanya untuk proses internal */
    private void sinkronisasi(){
        for (Map.Entry<String, HashMap<Integer, ArrayList<Barang>>> isiList3: this.daftarBarangPencarian.entrySet()) {
            ArrayList<ArrayList<Barang>> tempList2 = new ArrayList<>();
            for (Map.Entry<Integer, ArrayList<Barang>> isiList2 : isiList3.getValue().entrySet()) {
                tempList2.add(isiList2.getValue());
            }

            /* daftarBarang memiliki Object di dimensi List ke 3 yang sama percis seperti daftarBarangPencarian
            *  sehingga apabila Object daftarBarang di dimensi list ke 3 berubah, pada daftarBarangPencarian akan berubah,
            *  begitu juga sebaliknya.
            */
            this.daftarBarang.put(isiList3.getKey(), tempList2);
        }
    }

    public void kurangiKesehatan(int nilaiSerangan){
        if(this.kesehatan > 0){
            this.kesehatan -= nilaiSerangan;
            if(this.kesehatan < 0){
                this.kesehatan = 0;
            }
        }
    }

    public HashMap<Integer, Barang> pilihBarangSenjata(){
        //bungkus dengan tipe data HashMap, akan berguna jika memilih senjata dengan tujuan menyerang lawan
        //  yaitu sebagai bagian proses mengurangi jumlah senjata lempar milik player
        //  yang akan dilakukan pada class pilihanSerang
        HashMap<Integer, Barang> bungkus = new HashMap<>();

        //mengecek apakah daftarBarangSenjata kosong atau tidak kosong
        if(this.daftarBarang.get("Senjata").isEmpty()){
            System.out.println("Daftar Senjata :\n" + "Anda tidak memiliki senjata apapun.");
        }else{
            boolean validasiInput = false;
            //proses menampilkan menu pemilihan senjata milik player selama validasi input masih bernilai salah
            while (!validasiInput){
                System.out.println("Daftar Senjata :");

                //proses menampilkan senjata milik player
                int i = 0;
                for (ArrayList<Barang> record : this.daftarBarang.get("Senjata")) {
                    System.out.printf("%2d %s", ++i, record.get(record.size()-1).getNama());
                    i++;
                }

                System.out.print("Masukkan pilihan : ");
                Scanner oScan = new Scanner(System.in);
                int inputMenu = oScan.nextInt();

                if(inputMenu < 1 || inputMenu > this.daftarBarang.get("Senjata").size()){
                    System.out.println("Maaf, barang yang anda pilih tidak tersedia.");
                }else{
                    //user sudah memberikan input yang benar sesuai pilihan yang tersedia sehingga validasiInput = true
                    validasiInput = true;

                    int jumlahBarangTertentu = this.daftarBarang.get("Senjata").get(inputMenu-1).size();

                    //Mengambil barang urutan terakhir
                    Barang barangPilihan = this.daftarBarang.get("Senjata").get(inputMenu-1).get(jumlahBarangTertentu-1);
                    bungkus.put(barangPilihan.getIdBarang(), barangPilihan);

                    //jika senjata yg digunakan adalah senjata lempar maka kita kurangi object senjata dalam penyimpananBarangSenjata Player
                    if(barangPilihan.getJenis().equals("Senjata Lempar")){
                        this.daftarBarang.get("Senjata").get(inputMenu-1).remove(barangPilihan);
                    }
                }
            }
        }
        return bungkus;
    }

    public void pilihBarangRamuan(){

    }

    public void pilihBarangBlueprint(){

    }

    public String getNama() {
        return nama;
    }

    public boolean isSelesai() {
        return isSelesai;
    }

    public int getJumlahSlotSenjataKosong(){
        return this.daftarBarang.get("Senjata").size() - this.batasMaxSenjataDinamis;
    }

    public Barang getSenjata() {
        return senjata;
    }

    public double getUang() {
        return uang;
    }
}
