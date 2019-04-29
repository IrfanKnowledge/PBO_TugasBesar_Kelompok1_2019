import java.util.*;

public class Player {
    int idPlayer;
    String nama;
    int kesehatan = 100;
    int batasMaxKesehatan = 100;
    double waktu = 7.00;
    String statusWaktu = "siang";
    double batasAwalMalam = 21.00;
    double batasAkhirMalam = 6.00;
    double uang = 0;
    int kemampuanMenawarBarang = 0;
    int kemampuanMenjualBarang = 0;
    Barang senjata;                         //Belum
    int efisiensiSenjata = 0;
    int efisiensiCrafting = 0;
    Adegan adeganAktif;                     //Belum
    ArrayList<Barang> penyimpanansDinamis;  //Belum
    int jumlahSenjataDinamis;               //Belum
    int batasMaxSenjataDinamis;             //Belum
    ArrayList<Barang> penyimpanansStatis;   //Belum
    Level levelBertahanHidup;
    int expBertahanHidup = 0;
    int pointBertahanHidup = 0;
    Level levelKekuatan;
    int expKekuatan = 0;
    int pointKekuatan = 0;
    private ArrayList<Level> daftarLevelBertahanHidup;
    private ArrayList<Level> daftarLevelKekuatan;
    private ArrayList<Skill> daftarSkill;
    private HashMap<Integer, Efek> daftarEfekDiri = new HashMap<>();

    private ArrayList<ArrayList<Barang>> daftarBarangKunci = new ArrayList<>();  //Belum
    private ArrayList<ArrayList<Barang>> daftarBarangSenjata = new ArrayList<>();  //Belum
    private ArrayList<ArrayList<Barang>> daftarBarangRamuan = new ArrayList<>();  //Belum
    private ArrayList<ArrayList<Barang>> daftarBarangBerharga = new ArrayList<>();  //Belum
    private ArrayList<ArrayList<Barang>> daftarBarangBluePrint = new ArrayList<>();  //Belum

    Hewan hewanPembantu;
    boolean isSelesai = false;

    //==============================================================================
    //ngetest saja, nanti dihapus atau dibuat komentar
    //Barang ngetest = new Barang();
    //HashMap<Integer, Barang> daftarNgetest = new HashMap<>();
    //Efek testing;
    //==============================================================================

    Player(int idPlayer, String nama){
        this.idPlayer = idPlayer;
        this.nama = nama;

        /* Proses pendefinisian setiap level bertahan hidup */
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

        /* Proses pendefinisian setiap level kekuatan */
        ArrayList<Level> daftarLevelKekuatan = new ArrayList<>();
        daftarLevelKekuatan.add(new Level(11, "Kekuatan", 1, 0));
        daftarLevelKekuatan.add(new Level(12, "Kekuatan", 2, 100));
        daftarLevelKekuatan.add(new Level(13, "Kekuatan", 3, 200));
        daftarLevelKekuatan.add(new Level(14, "Kekuatan", 4, 400));
        daftarLevelKekuatan.add(new Level(15, "Kekuatan", 5, 600));

        /* Proses pendefinisian setiap Skill yang dapat diunlock oleh Player */
        ArrayList<Skill> daftarSkill = new ArrayList<>();
        Barang medikit = new Barang(99, "Medikit", "Kesehatan", "Menambah kesehatan Player",true, true, 30, 15); //Contoh
        daftarSkill.add(new SkillCrafting(this, 1,medikit.getNama(), 1, 1, "Rancangan/BluPrint untuk membuat barang medikit.", medikit));

        /* Proses inisiasi beberapa atribut yang belum di-inisiasi */
        this.levelBertahanHidup = daftarLevelBertahanHidup.get(0);
        this.levelKekuatan = daftarLevelKekuatan.get(0);
        this.daftarLevelBertahanHidup = daftarLevelBertahanHidup;
        this.daftarLevelKekuatan = daftarLevelKekuatan;
        this.daftarSkill = daftarSkill;
    }

    public static void main(String[] args) {
        //==============================================================================
        // berikut proses percobaan
        Player A = new Player(1, "irfan");
        Player B = new Player(2, "dani");
        System.out.println(A.daftarLevelBertahanHidup.get(3).nilaiLevel);
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
            System.out.println(sementara.mencoba + " " + testdeui.get(1));
        }
        //==============================================================================
    }

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

    public void tambahBluePrint(Barang bluePrint){
        this.daftarBluePrint.add(bluePrint);
    }

    public void tambahEfekDiri(HashMap<Integer, Efek> efekLuar){
        for (Map.Entry<Integer, Efek> record : efekLuar.entrySet()) {
            this.daftarEfekDiri.put(record.getKey(), record.getValue());
        }
    }

    public void tambahEfekDiri(Efek efekLuar) {
        this.daftarEfekDiri.put(efekLuar.getIdEfek(), efekLuar);
    }

    public void pilihBarangKunci(){

    }

    public HashMap<Integer, Barang> pilihBarangSenjata(){
        //bungkus dengan tipe data HashMap, akan berguna jika memilih senjata dengan tujuan menyerang lawan
        //  yaitu sebagai bagian proses mengurangi jumlah senjata lempar milik player
        //  yang akan dilakukan pada class pilihanSerang
        HashMap<Integer, Barang> bungkus = new HashMap<>();

        //mengecek apakah daftarBarangSenjata kosong atau tidak kosong
        if(this.daftarBarangSenjata.isEmpty()){
            System.out.println("Daftar Senjata :\n" + "Anda tidak memiliki senjata apapun.");
        }else{
            boolean validasiInput = false;
            //proses menampilkan menu pemilihan senjata milik player selama validasi input masih bernilai salah
            while (!validasiInput){
                System.out.println("Daftar Senjata :");

                //proses menampilkan senjata milik player
                int i = 0;
                for (ArrayList<Barang> record : this.daftarBarangSenjata) {
                    System.out.printf("%2d %s", ++i, record.get(record.size()-1).getNama());
                    i++;
                }

                System.out.print("Masukkan pilihan : ");
                Scanner oScan = new Scanner(System.in);
                int inputMenu = oScan.nextInt();

                if(inputMenu < 1 || inputMenu > this.daftarBarangSenjata.size()){
                    System.out.println("Maaf, barang yang anda pilih tidak tersedia.");
                }else{
                    //user sudah memberikan input yang benar sesuai pilihan yang tersedia sehingga validasiInput = true
                    validasiInput = true;

                    int jumlahBarangTertentu = this.daftarBarangSenjata.get(inputMenu).size();
                    Barang barangPilihan = this.daftarBarangSenjata.get(inputMenu).get(jumlahBarangTertentu-1);
                    bungkus.put(inputMenu, barangPilihan);

                    //jika senjata yg digunakan adalah senjata lempar maka kita kurangi object senjata dalam penyimpananBarangSenjata Player
                    if(barangPilihan.getKategori().equals("Senjata Lempar")){
                        this.daftarBarangSenjata.get(inputMenu).remove(barangPilihan);
                    }
                }
            }
        }
        return bungkus;
    }

    public void pilihBarangRamuan(){

    }

    public void pilihBarangBluePrint(){

    }

    public void kurangiKesehatan(int nilaiSerangan){
        if(this.kesehatan > 0){
            this.kesehatan -= nilaiSerangan;
            if(this.kesehatan < 0){
                this.kesehatan = 0;
            }
        }
    }
}
