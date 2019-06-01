import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class Player {
    public int idPlayer;
    public String nama;
    private int kesehatan = 100;
    private int batasMaxKesehatan = 100;

    /* Waktu melekat pada diri Player, kemudian pada saat malam ketika berada pada adegan yang memiliki Musuh Kuat di malam hari,
    *  maka mereka akan muncul, dan ketika berganti hari maka semua barang-barang dan musuh akan me-refresh
    */
//    private double waktu = 7.00;
//    private double kecepatanPerubahanWaktuNormal = 0.10;
//    private String statusWaktu = "siang";
//    private double batasAwalMalam = 21.00;
//    private double batasAkhirMalam = 6.00;

    private double uang = 0;
    public Barang senjata;                         //Belum
    public ArrayList<Barang> senjataLempar;   //Belum

    /* Menandakan posisi Player berada pada Adegan tertentu */
    public Adegan adeganAktif;

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

    /* Berikut Atribut yang dipengaruhi Efek */
    private int durasiStun = 0;
    private int ketahananTambahan = 0;
    private int batasMaxKetahananTambahan = 0;
    private int durasiKetahanan = 0;
//    private double nilaiKecepatanTambahan = 0;
//    private int durasiKecepatanTambahan = 0;
//    private int durasiPengelihatanMalam = 0;
//    private int durasiKamuflase = 0;
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

    /* daftarBarangPencarian untuk teknis pencarian pada proses tertentu, daftarBarang digunakan saat user menginput pilihan
    *  sehingga daftarBarangPencarian dan daftarBarang berisikan barang yang sama.
    */
    public void tambahBarang(HashMap<String, HashMap<Integer, ArrayList<Barang>>> oBarangPilihan){

        /* Seleksi barang input dengan pengkategorian penyimpanan yang telah ditetapkan sebagai berikut.
         */
        for (Map.Entry<String, HashMap<Integer, ArrayList<Barang>>> isiList3 : oBarangPilihan.entrySet()) {
            if(isiList3.getKey().equals("Kunci") || isiList3.getKey().equals("Senjata") ||
                    isiList3.getKey().equals("Komponen Crafting") || isiList3.getKey().equals("Barang Berharga") ||
                    isiList3.getKey().equals("Blueprint") || isiList3.getKey().equals("Amunisi") ||
                    isiList3.getKey().equals("Barang Lainnya")){

                for (Map.Entry<Integer, ArrayList<Barang>> isiList2 : isiList3.getValue().entrySet()){

                    /* Jika isiList2 atau kumpulan ArrayList 1 dimensi dari barang input tersebut tidak kosong maka... */
                    if(!isiList2.getValue().isEmpty()){

                        /*
                         *  Jika dalam daftarBarangPencarian terdapat kategori barang tersebut maka...
                         */
                        if(this.daftarBarangPencarian.containsKey(isiList3.getKey())){

                            /* Jika di daftarBarangPencarian terdapat id Barang yang sama maka... */
//                            if(this.daftarBarangPencarian.get(isiList3.getKey()).containsKey(isiList2.getValue().get(0).getIdBarang())){
//
//                                /* Tambahkan semua barang ke dalam List yang memiliki id Barang sama */
//                                this.daftarBarangPencarian.get(isiList3.getKey()).get(isiList2.getValue().get(0).getIdBarang()).addAll(isiList2.getValue());
//                            }else{
//
//                                /* Tambahkan semua barang dengan membuat List Baru dengan id Barang baru*/
//                                this.daftarBarangPencarian.get(isiList3.getKey()).put(isiList2.getValue().get(0).getIdBarang(), isiList2.getValue());
//                            }
                        }else{

                            /* Tambahkan kategori yang telah ditetapkan dalam proses seleksi input,
                             *  ke daftarBarangPencarian beserta isi input tersebut
                             */
                            this.daftarBarangPencarian.put(isiList3.getKey(), isiList3.getValue());
                        }

                    }
                }
            }
        }

        /* jika daftarBarangPencarian tidak kosong maka.. */
        if(!this.daftarBarangPencarian.isEmpty()){
            /* sinkronisasi antara dattarBarangPencarian dan daftar barang */
            this.sinkronisasi();
        }

        //System.out.println("daftarBarang = " + this.daftarBarang);
        //System.out.println("daftarBarangPencarian = " + this.daftarBarangPencarian);
    }

    /* (Private karena hanya untuk proses internal)
    *   Berikut proses memasukan semua data daftarBarangPencarian ke dalam daftarBarang
    */
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

    public void ubahWaktu(double nilaiPenambah){
        if(nilaiPenambah < 0){
            nilaiPenambah = 0;
        }

        /* nilai penambah tersebut bisa ditentukan dari setiap pilihan atau tindakan di berbagai adegan */
        this.waktu += nilaiPenambah;
        this.waktu += this.kecepatanPerubahanWaktuNormal;

        /* jika memiliki efek kecepatan tambahan maka tambahkan bonus kecepatannya */
        if(this.durasiKecepatanTambahan > 0){
            this.waktu += this.nilaiKecepatanTambahan;
        }
        if(this.waktu >= 24 ){
            this.waktu -= 24;
        }
    }

    /* return berupa key sebagai kategori, value indeks 0 = idBarang, value indeks 1 = IndeksBarang*/
    public HashMap<String, ArrayList<Integer>> pilihKategoriIdIndeksBarang(String kategori){
        /*
         *  Untuk menampung kategori dan id barang  yang akan di return
         */
        HashMap<String, ArrayList<Integer>> barangPilihan = null;

        /* jika user memilih barang tertentu atau kembali maka keluar dari loop menu ini */
        boolean validasiInputBenar = false;

        //proses menampilkan menu pemilihan barang kategori tertentu milik player selama user tidak memilih "kembali"
        while (!validasiInputBenar){
            System.out.println();
            System.out.printf("Daftar %s :\n", kategori);

            /* untuk menampilkan urutan barang */
            int i = 0;

            /* pengecekan apakah daftarBarang memiliki barang kategori ini atau kategori tersebut kosong */
            if(!this.daftarBarang.containsKey(kategori)){
                System.out.println();
                System.out.printf("[ Anda tidak memiliki %s apapun. ]\n", kategori);
                System.out.println();

            /* proses menampilkan barang kategori tertentu milik player jika tersedia */
            }else{

                /* setiap kali terhitung 1, akan digunakan untuk menghapus list tersebut dengan Aman tanpa null pointer karena pengulangan berikut menggunakan foreach */
                int cekListKosong = 0;
                ArrayList<Barang> tempList1 = new ArrayList<>();

                /*  Cara menampilkan barang dibagi menjadi 3 bagian:
                *   1. Senjata, 2. Kunci, 3. Selain senjata dan kunci
                *   Sebab senjata dan kunci memiliki kekhususan tersendiri
                */
                if(kategori.equals("Senjata") ){

                    /* pengulangan menampilkan isi dari list 2 dimensi senjata (kumpulan list senjata) */
                    for (ArrayList<Barang> isiList2Senjata : this.daftarBarang.get(kategori)) {

                        /* hapus list yang kosong */
                        if(cekListKosong == 1){
                            this.daftarBarang.get(kategori).remove(tempList1);
                            cekListKosong = 0;
                        }

                        /* jika list tersebut kosong, kita simpan ke tempList1 */
                        if(isiList2Senjata.isEmpty()){
                            tempList1 = isiList2Senjata;
                            cekListKosong ++;
                        }else{

                            /* jika jenis senjata adalah Senjata Pukul atau Senjata Tembak maka..*/
                            if(isiList2Senjata.get(0).getJenis().equals("Senjata Pukul") || isiList2Senjata.get(0).getJenis().equals("Senjata Tembak")){

                                /* pengulangan menampilkan isi dari list 1 dimensi senjata tertentu */
                                for (Barang isiList1Senjata : isiList2Senjata) {

                                    if(isiList2Senjata.get(0).getJenis().equals("Senjata Pukul")){
                                        System.out.printf("%2d. %-20s | kekuatan : %d | Ketahanan : %d | Kemampuan diperbaiki : %d ", i+1,
                                                isiList1Senjata.getNama(), isiList1Senjata.getKekuatan(), isiList1Senjata.getKetahanan(),
                                                isiList1Senjata.jumlahKemampuanDiperbaiki());
                                        i++;

                                        /* jika jenis senjata adalah Senjata Tembak maka... */
                                    }else{
                                        System.out.printf("%2d. %-20s | kekuatan : %d | Peluru : %d/%d ", i+1,
                                                isiList1Senjata.getNama(), isiList1Senjata.getKekuatan(),
                                                isiList1Senjata.getJumlahPeluru(), isiList1Senjata.getBatasMaxAmunisi());
                                        i++;
                                    }

                                    if(this.senjata != null){
                                        /* jika senjata ini sedang digunakan tampilkan informasi berikut */
                                        if(this.senjata.equals(isiList1Senjata)){
                                            System.out.printf("| (Senjata sedang digunakan)");
                                        }
                                    }
                                    System.out.println();
                                }

                                /* jika senjata adalah Senjata Lempar maka... */
                            }else{
                                System.out.println(isiList2Senjata.get(0).getJenis());
                                System.out.printf("%2d. %-20s (%d) | kekuatan : %d ", i+1,
                                        isiList2Senjata.get(0).getNama(), isiList2Senjata.size(),
                                        isiList2Senjata.get(0).getKekuatan());
                                i++;

                                if(this.senjataLempar != null){
                                    /* jika senjata lempar ini sedang digunakan tampilkan informasi berikut */
                                    if(this.senjataLempar.equals(isiList2Senjata)){
                                        System.out.printf("| (Senjata Lempar sedang digunakan)");
                                    }
                                }
                                System.out.println();
                            }
                        }
                    }
                }else if(kategori.equals("Kunci")){

                    for (ArrayList<Barang> isiList2Kunci : this.daftarBarang.get(kategori)) {

                        /* hapus list yang kosong */
                        if(cekListKosong == 1){
                            this.daftarBarang.get(kategori).remove(tempList1);
                            cekListKosong = 0;
                        }

                        /* jika list tersebut kosong, kita simpan ke tempList1 */
                        if(isiList2Kunci.isEmpty()){
                            tempList1 = isiList2Kunci;
                            cekListKosong ++;
                        }else{
                            System.out.printf("%2d. %-20s\n", i+1,
                                    isiList2Kunci.get(0).getNama());
                            i++;
                        }

                    }
                }else{

                    for (ArrayList<Barang> isiList2Barang : this.daftarBarang.get(kategori)) {

                        /* hapus list yang kosong */
                        if(cekListKosong == 1){
                            this.daftarBarang.get(kategori).remove(tempList1);
                            cekListKosong = 0;
                        }

                        /* jika list tersebut kosong, kita simpan ke tempList1 */
                        if(isiList2Barang.isEmpty()){
                            tempList1 = isiList2Barang;
                            cekListKosong ++;
                        }else{
                            System.out.printf("%2d. %-20s (%d)\n", i+1,
                                    isiList2Barang.get(0).getNama(), isiList2Barang.size());
                            i++;
                        }

                    }
                }
            }

            System.out.printf("%2d. Kembali\n", 0);
            System.out.print("Masukkan pilihan : ");
            Scanner oScan = new Scanner(System.in);
            int inputMenu = oScan.nextInt();

            /* jika user memilih kembali maka beri return null (sudah di-inisiasi di atas)
               sebagai penanda mengakhiri menu ini dan kembali ke menu sebelumnya
               (diatur pada Class PilihanLihatBarang)
            */
            if(inputMenu == 0){
                validasiInputBenar = true;

            }else if(inputMenu < 0 || inputMenu > i){  // i = jumlah barang yang ditampilkan
                System.out.println();
                System.out.println("[ Maaf, barang yang anda pilih tidak tersedia. ]");
            }else{

                /* untuk menandai indeks */
                int indeks;

                /* dimulai dari -1 krn dalam foreach akan slalu diawali +1 */
                indeks = -1;

                /* instance objek hashmap */
                barangPilihan = new HashMap<>();

                /* memuat id dan indeks */
                ArrayList<Integer> barangIdIndeks = new ArrayList<>();

                /* kita reset i = 0 */
                i=0;
                if(kategori.equals("Senjata") ){
                    for (ArrayList<Barang> isiList2Senjata : this.daftarBarang.get(kategori)) {

                        /* jika jenis senjata adalah Senjata Pukul atau Senjata Tembak maka..*/
                        if(isiList2Senjata.get(0).getJenis().equals("Senjata Pukul") || isiList2Senjata.get(0).getJenis().equals("Senjata Tembak")){

                            for (Barang isiList1Senjata : isiList2Senjata) {

                                /* menandai indeks saat ini */
                                indeks++;

                                i++;
                                if(inputMenu == i){

                                    /* menambahkan id dan indeks */
                                    barangIdIndeks.add(isiList1Senjata.getIdBarang());
                                    barangIdIndeks.add(indeks);

                                    barangPilihan.put(isiList1Senjata.getKategoriPenyimpanan() , barangIdIndeks);
                                    validasiInputBenar = true;
                                }
                            }
                        }else{
                            i++;
                            if(inputMenu == i){

                                /* menambahkan id dan indeks */
                                barangIdIndeks.add(isiList2Senjata.get(0).getIdBarang());
                                /*indeks 0 karena senjata lempar dalam satu list tidak memiliki perbedaan data,
                                  maka dianggap sama sehingga tidak apa2 slalu meng-get dari indeks 0
                                */
                                barangIdIndeks.add(0);

                                barangPilihan.put(isiList2Senjata.get(0).getKategoriPenyimpanan(), barangIdIndeks);
                                validasiInputBenar = true;
                            }
                        }
                    }
                }else{
                    for (ArrayList<Barang> isiList2Barang : this.daftarBarang.get(kategori)) {
                        i++;
                        if(inputMenu == i){

                            /* menambahkan id dan indeks */
                            barangIdIndeks.add(isiList2Barang.get(0).getIdBarang());
                                /*indeks 0 karena barang2 selain senjata tembak dan pukul dalam satu list tidak memiliki perbedaan data,
                                  maka dianggap sama sehingga tidak apa2 slalu meng-get dari indeks 0
                                */
                            barangIdIndeks.add(0);

                            barangPilihan.put(isiList2Barang.get(0).getKategoriPenyimpanan(), barangIdIndeks);
                            validasiInputBenar = true;
                        }
                    }
                }

            }
        }

        /* return berupa key sebagai kategori, value indeks 0 = idBarang, value indeks 1 = IndeksBarang*/
        return barangPilihan;
    }

    public Barang pilihBarangSatu(String kategori, int idBarang, int indeksBarang){
        if(this.daftarBarangPencarian.containsKey(kategori)){
            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)) {
                return this.daftarBarangPencarian.get(kategori).get(idBarang).get(indeksBarang);
            }
        }
        return null;
    }

    public ArrayList<Barang> pilihBarangSemuanya(String kategori, int idBarang){
        if(this.daftarBarangPencarian.containsKey(kategori)){
            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)) {

                return this.daftarBarangPencarian.get(kategori).get(idBarang);
            }
        }
        return null;
    }

    public void hapusBarangSatu(String kategori, int idBarang){
        if(this.daftarBarangPencarian.containsKey(kategori)){
            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)){

                /* jika list tersebut tidak kosong maka... */
                if(!this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
                    this.daftarBarangPencarian.get(kategori).get(idBarang).remove(0);
                    if(this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
                        this.daftarBarangPencarian.get(kategori).remove(idBarang);
                    }
                }
            }
        }
    }

    public void hapusBarangSatuIndeksTertentu(String kategori, int idBarang, int indeks){
        if(this.daftarBarangPencarian.containsKey(kategori)){
            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)){

                /* jika list tersebut tidak kosong maka... */
                if(!this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
                    this.daftarBarangPencarian.get(kategori).get(idBarang).remove(indeks);
                    if(this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
                        this.daftarBarangPencarian.get(kategori).remove(idBarang);
                    }
                }
            }
        }
    }

    public void hapusBarangSemuanya(String kategori, int idBarang){
        if(this.daftarBarangPencarian.containsKey(kategori)){
            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)){
                this.daftarBarangPencarian.get(kategori).get(idBarang).clear();
                this.daftarBarangPencarian.get(kategori).remove(idBarang);
            }
        }
    }

    public void hapusBarangJumlahTertentu(String kategori, int idBarang, int jumlahBarangDihapus){
        if(this.daftarBarangPencarian.containsKey(kategori)){
            if(this.daftarBarangPencarian.get(kategori).containsKey(idBarang)){
                for(int i=0; i<this.daftarBarangPencarian.get(kategori).get(idBarang).size(); i+=0){
                    if(jumlahBarangDihapus != 0){
                        this.daftarBarangPencarian.get(kategori).get(idBarang).remove(i);
                        jumlahBarangDihapus--;
                    }else{
                        /* jika jumlahBarangDihapus sudah kosong, keluar dari for ini*/
                        break;
                    }
                }
                if(this.daftarBarangPencarian.get(kategori).get(idBarang).isEmpty()){
                    this.daftarBarangPencarian.get(kategori).remove(idBarang);
                }
            }
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
        int jumlahBarangSenjata = 0;
        /* Jika terdapat senjata maka... */
        if(this.daftarBarangPencarian.containsKey("Senjata")){
            for (Map.Entry<Integer, ArrayList<Barang>> isiList2 : this.daftarBarangPencarian.get("Senjata").entrySet()) {
                jumlahBarangSenjata += isiList2.getValue().size();
            }
            return this.batasMaxSenjataDinamis - jumlahBarangSenjata;
        }else{
            return this.batasMaxSenjataDinamis - jumlahBarangSenjata;
        }
    }

    public double getUang() {
        return uang;
    }

    public HashMap<String, HashMap<Integer, ArrayList<Barang>>> getDaftarBarangPencarian() {
        return daftarBarangPencarian;
    }

    public double getWaktu() {
        return waktu;
    }
}
