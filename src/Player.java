import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private ArrayList<Barang> daftarBluePrint = new ArrayList<>();  //Belum
    private HashMap<Integer, Efek> daftarEfekDiri = new HashMap<>();
    Hewan hewanPembantu;
    boolean isSelesai = false;


    //ngetest saja, nanti dihapus atau dibuat komentar
    //Barang ngetest = new Barang();
    //ArrayList<Barang> daftarNgetest;

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
        Barang medikit = new Barang(99, "Medikit", "Kesehatan", 2, true, true, 30, 15); //Contoh
        daftarSkill.add(new SkillCrafting(this, 1,medikit.nama, 1, 1, "Rancangan/BluPrint untuk membuat barang medikit.", medikit));

        /* Proses inisiasi beberapa atribut yang belum di-inisiasi */
        this.levelBertahanHidup = daftarLevelBertahanHidup.get(0);
        this.levelKekuatan = daftarLevelKekuatan.get(0);
        this.daftarLevelBertahanHidup = daftarLevelBertahanHidup;
        this.daftarLevelKekuatan = daftarLevelKekuatan;
        this.daftarSkill = daftarSkill;
    }

    public static void main(String[] args) {
        // berikut proses percobaan
        Player A = new Player(1, "irfan");
        System.out.println(A.daftarLevelBertahanHidup.get(3).nilaiLevel);
        A.daftarSkill.get(0).unlockSkill(A.levelBertahanHidup, A.pointBertahanHidup);
        //if(A.daftarNgetest == null){
            //System.out.println(A.ngetest.testhehe + "MASIH NULL");
        //}

    }

    public void tambahBluePrint(Barang bluePrint){
        this.daftarBluePrint.add(bluePrint);
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

    public void kurangiKesehatan(int nilaiSerangan){
        if(this.kesehatan > 0){
            this.kesehatan -= nilaiSerangan;
            if(this.kesehatan < 0){
                this.kesehatan = 0;
            }
        }
    }

    public void tambahEfekDiri(HashMap<Integer, Efek> efekLuar){
        for (Map.Entry<Integer, Efek> record : efekLuar.entrySet()) {
            this.daftarEfekDiri.put(record.getKey(), record.getValue());
        }
    }

    public void tambahEfekDiri(Efek efekLuar) {
        this.daftarEfekDiri.put(efekLuar.idEfek, efekLuar);
    }

}
