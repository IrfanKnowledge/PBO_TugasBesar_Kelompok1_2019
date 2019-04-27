import java.util.ArrayList;

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
    Barang senjata;                         //Belum
    Adegan adeganAktif;                     //Belum
    ArrayList<Barang> penyimpanansDinamis;  //Belum
    int jumlahSenjataDinamis;               //Belum
    int jumlahMaxSenjataDinamis;            //Belum
    ArrayList<Barang> penyimpanansStatis;   //Belum
    Level levelBertahanHidup;
    int expBertahanHidup = 0;
    int pointBertahanHidup = 0;
    Level levelKekuatan;
    int expKekuatan = 0;
    int pointKekuatan = 0;
    ArrayList<Level> daftarLevelBertahanHidup;
    ArrayList<Level> daftarLevelKekuatan;
    ArrayList<Skill> daftarSkill;           //Belum
    ArrayList<Barang> daftarBluePrint;      //Belum
    ArrayList<Efek> daftarEfekDiri = new ArrayList<>();
    Hewan hewanPembantu;
    boolean isSelesai = false;

    Player(int idPlayer, String nama){
        this.idPlayer = idPlayer;
        this.nama = nama;

        /* Proses pendefinisian tiap level bertahan hidup */
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

        /* Proses pendefinisian tiap level kekuatan */
        ArrayList<Level> daftarLevelKekuatan = new ArrayList<>();
        daftarLevelKekuatan.add(new Level(11, "Kekuatan", 1, 0));
        daftarLevelKekuatan.add(new Level(12, "Kekuatan", 2, 100));
        daftarLevelKekuatan.add(new Level(13, "Kekuatan", 3, 200));
        daftarLevelKekuatan.add(new Level(14, "Kekuatan", 4, 400));
        daftarLevelKekuatan.add(new Level(15, "Kekuatan", 5, 600));

        this.levelBertahanHidup = daftarLevelBertahanHidup.get(0);
        this.levelKekuatan = daftarLevelKekuatan.get(0);
        this.daftarLevelBertahanHidup = daftarLevelBertahanHidup;
        this.daftarLevelKekuatan = daftarLevelKekuatan;
    }

    public static void main(String[] args) {
        Player A = new Player(1, "irfan");
        System.out.println(A.daftarLevelBertahanHidup.get(3).nilaiLevel);
    }
}
