import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Adegan {
    /* Game ini merupakan game Single Player sehingga setiap adegan memiliki Player yang sama */
    public static Player oPlayer;

    /* Pada setiap adegan terdapat hal berikut dan memiliki kemungkinan dapat melakukan aksi penggunaan barang */
    private int idAdegan;
    private int idBarangBisaDigunakan;
    private String narasi;
    private String namaTempat;

    /* Pada setiap adegan terdapat menu dengan opsi pilihan sebagai berikut */
    private PilihanLihatBarangSekitar oPilihanLihatBarangSekitar;
    private PilihanLihatIsiKantong oPilihanLihatIsiKantong;
    private PilihanLihatNpcSekitar oPilihanLihatNpcSekitar;
    private PilihanKeluarGame oPilihanKeluarGame;

    /* Pada setiap adegan terdapat kemungkinan daftarPilihan, barang, Npc, dan Lawan */
    private ArrayList<Pilihan> daftarPilihan = new ArrayList<>();
    private HashMap<String, ArrayList<ArrayList<Barang>>> daftarBarang = new HashMap<>();
    private HashMap<String, HashMap<Integer, ArrayList<Barang>>> daftarBarangTetap = new HashMap<>();
    private ArrayList<Npc> daftarNpc = new ArrayList<>();
    private ArrayList<Lawan> daftarLawan = new ArrayList<>();

    /* jumlah kategori barang*/
    private int jumlahKategori = 7;

    Adegan(int idAdegan, int idBarangBisaDigunakan, String narasi, String namaTempat){
        this.idAdegan = idAdegan;
        this.idBarangBisaDigunakan = idBarangBisaDigunakan;
        this.narasi = narasi;
        this.namaTempat = namaTempat;

        //inisiasi pilihan awal di setiap Adegan
        this.oPilihanLihatBarangSekitar = new PilihanLihatBarangSekitar("Lihat barang sekitar tempat ini", this);
        tambahPilihan(this.oPilihanLihatBarangSekitar);
        this.oPilihanLihatIsiKantong = new PilihanLihatIsiKantong("Lihat isi kantong", this);
        tambahPilihan(this.oPilihanLihatIsiKantong);
        this.oPilihanLihatNpcSekitar = new PilihanLihatNpcSekitar("Lihat keberadaan orang sekitar");
        tambahPilihan(this.oPilihanLihatNpcSekitar);
        this.oPilihanKeluarGame = new PilihanKeluarGame("Keluar dari Game.");
        tambahPilihan(this.oPilihanKeluarGame);
    }

    Adegan(int idAdegan, int idBarangBisaDigunakan, String narasi, String namaTempat, HashMap<String, HashMap<Integer, ArrayList<Barang>>>  daftarBarangTetap, ArrayList<Lawan> daftarLawan){
        this.idAdegan = idAdegan;
        this.idBarangBisaDigunakan = idBarangBisaDigunakan;
        this.narasi = narasi;
        this.namaTempat = namaTempat;
        this.daftarLawan = daftarLawan;

        //inisiasi pilihan awal di setiap Adegan
        this.oPilihanLihatBarangSekitar = new PilihanLihatBarangSekitar("Lihat barang di sekitar", this);
        tambahPilihan(this.oPilihanLihatBarangSekitar);
        this.oPilihanLihatIsiKantong = new PilihanLihatIsiKantong("Lihat isi kantong", this);
        tambahPilihan(this.oPilihanLihatIsiKantong);
        this.oPilihanLihatNpcSekitar = new PilihanLihatNpcSekitar("Lihat keberadaan orang di sekitar");
        tambahPilihan(this.oPilihanLihatNpcSekitar);

        /* Jika daftar lawan tidak kosong maka tambah pilihan lihat Lawan */
        if(!daftarLawan.isEmpty()){
            tambahPilihan(new PilihanLihatLawanSekitar("Lihat keberadaan musuh di sekitar"));
        }

        this.oPilihanKeluarGame = new PilihanKeluarGame("Keluar dari Game.");
        tambahPilihan(this.oPilihanKeluarGame);

        /* menambahkan barang yang ketika di refresh saat berganti hari, barang-barang tersebut akan muncul kembali jika kosong */
        this.tambahBarangTetap(daftarBarangTetap);
    }

    public void mainkan(){
        System.out.println();
        System.out.printf("Waktu: %1.2f\n", this.oPlayer.getWaktu());
        System.out.println("Nama Tempat : " + this.namaTempat);
        System.out.println("Narasi : " + this.narasi);
        int i = 0;
        for (Pilihan oPilihan: this.daftarPilihan) {
            System.out.printf("%2d. %s\n", i+1, oPilihan.getDekripsi());
            i++;
        }
        System.out.print("Masukkan Pilihan : ");
        Scanner oScan = new Scanner(System.in);
        int input = oScan.nextInt();
        if(input < 1 || input > this.daftarPilihan.size()){
            System.out.println();
            System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
        }else{
            this.daftarPilihan.get(input-1).aksi();
        }
    }

    /* 1. Lihat barang di sekitar -> ambil semua barang */
    public HashMap<String, HashMap<Integer, ArrayList<Barang>>> ambilSemuaBarang(int jumlahSlotSenjataPlayerKosong) {

        /* jika daftarBarang tidak kosong maka... */
        if (!this.daftarBarang.isEmpty()) {

            /*  Berikut algoritma menyeleksi senjata yang bisa di ambil */
            HashMap<String, HashMap<Integer, ArrayList<Barang>>> tempList3 = new HashMap<>();
            //System.out.println(this.daftarBarang);
            for (Map.Entry<String, ArrayList<ArrayList<Barang>>> isiList3 : this.daftarBarang.entrySet()) {

                /* Jika kategorinya bukan senjata, ambil semua */
                if (!isiList3.getKey().equals("Senjata")) {

                    /* proses mengubah dari ArrayList menjadi HashMap */
                    HashMap<Integer, ArrayList<Barang>> temp2Dimensi = new HashMap<>();
                    for (ArrayList<Barang> temp1Dimensi : isiList3.getValue()) {
                        if(!temp1Dimensi.isEmpty()){
                            temp2Dimensi.put(temp1Dimensi.get(0).getIdBarang(), temp1Dimensi);
                        }
                    }
                    tempList3.put(isiList3.getKey(), temp2Dimensi);

                    /* Jika kategorinya adalah senjata maka... kita seleksi */
                } else {

                    /* Jika jumlah slot senjata Tidak Penuh maka... */
                    if (jumlahSlotSenjataPlayerKosong != 0) {

                        /* untuk menampung list 2 dimensi */
                        HashMap<Integer, ArrayList<Barang>> tempList2 = new HashMap<>();

                        /* Untuk acuan perbandingan dengan jumlah slot senjata kosong pada Player yang tersedia */
                        int jumlahAmbil = 0;

                        /* Pengulangan mengeluarkan isiList2 yang berupa List 1 dimensi dimulai dari urutan terakhir pada isiList 2 dimensi */
                        for (int i = isiList3.getValue().size() - 1; i >= 0; i--) {

                            /* Untuk menampung list 1 dimensi */
                            ArrayList<Barang> tempList1 = new ArrayList<>();

                            /* Pengulangan mengeluarkan isi List 1 dimensi dimulai dari urutan terakhir*/
                            for (int j = isiList3.getValue().get(i).size() - 1; j >= 0; j--) {

                                tempList1.add(isiList3.getValue().get(i).get(j));
                                jumlahAmbil++;

                                /* hapus barang yang telah diambil */
                                isiList3.getValue().get(i).remove(j);
                                //System.out.println(jumlahAmbil);
                                //System.out.println("slot : " + jumlahSlotSenjataPlayerKosong);
                                /* jika sudah memenuhi jumlah slot kosong yang tersedia keluar dari foreach ini */
                                if (jumlahAmbil == jumlahSlotSenjataPlayerKosong) {
                                    break;
                                }
                            }
                            tempList2.put(tempList1.get(0).getIdBarang(), tempList1);

                            /* Hapus isi urutuan List 2 dimensi yang telah kosong*/
                            if (isiList3.getValue().get(i).isEmpty()) {
                                isiList3.getValue().remove(i);
                            }

                            /* jika sudah memenuhi jumlah slot kosong yang tersedia keluar dari foreach ini */
                            if (jumlahAmbil == jumlahSlotSenjataPlayerKosong) {
                                break;
                            }
                        }

                        tempList3.put("Senjata", tempList2);
                    }
                }
            }

            /*  Hapus kategori berikut jika ada dan kosong atau sudah diambil semua (tidak dibatasi jumlah pengambilannya) */
            if(this.daftarBarang.containsKey("Kunci")){
                this.daftarBarang.remove("Kunci");
            }
            if(this.daftarBarang.containsKey("Komponen Crafting")){
                this.daftarBarang.remove("Komponen Crafting");
            }
            if(this.daftarBarang.containsKey("Barang Bernilai")){
                this.daftarBarang.remove("Barang Bernilai");
            }
            if(this.daftarBarang.containsKey("Blueprint")){
                this.daftarBarang.remove("Blueprint");
            }
            if(this.daftarBarang.containsKey("Amunisi")){
                this.daftarBarang.remove("Amunisi");
            }
            if(this.daftarBarang.containsKey("Barang Lainnya")){
                this.daftarBarang.remove("Barang Lainnya");
            }

            /* Jika senjata di adegan ini kosong atau sudah diambil semua maka kita hapus barang kategori senjata */
            System.out.println();
            if (this.daftarBarang.containsKey("Senjata")) {
                if(this.daftarBarang.get("Senjata").isEmpty()){
                    this.daftarBarang.remove("Senjata");
                    System.out.println("[ Barang berhasil diambil semua. ]");
                }else{
                    System.out.println("[ Kapasitas penyimpanan senjata tidak cukup. ]");
                }
            }

            System.out.println();
            System.out.println("oKategoriBarang = " + this.daftarBarang);
            System.out.println("tempList3 = " + tempList3);

            return  tempList3;
        }

        /* Jika daftarBarang kosong maka return null */
        return null;
    }

    /* 1. Lihat barang di sekitar - > ambil senjata satu-per-satu */
    public Barang pilihBarangSenjataSekitarAdegan(int jumlahSlotSenjataPlayerKosong){
        System.out.println();
        System.out.println("Aksi : Ambil senjata satu-per-satu");

        int jumlah = 0;
        /* Jika tidak terdapat senjata di adegan  maka... */
        if(!this.daftarBarang.containsKey("Senjata")) {
            System.out.println();
            System.out.println("[ Tidak ditemukan senjata apapun. ]");
            System.out.println();
        }else{
            for (int i=0; i<this.daftarBarang.get("Senjata").size(); i++){
                for (int j=0; j<this.daftarBarang.get("Senjata").get(i).size(); j++){
                    System.out.printf("%2d. %-20s | kekuatan : %-2d | Ketahanan : %d\n", jumlah+1,
                            this.daftarBarang.get("Senjata").get(i).get(j).getNama(),
                            this.daftarBarang.get("Senjata").get(i).get(j).getKekuatan(),
                            this.daftarBarang.get("Senjata").get(i).get(j).getKetahanan());
                    jumlah++;
                }
            }
        }
        System.out.printf("%2d. Kembali\n", 0);

        Scanner oScan = new Scanner(System.in);
        System.out.print("Masukkan Pilihan : ");
        int input = oScan.nextInt();

        /* Jika terdapat senjata di adegan maka... */
        if(this.daftarBarang.containsKey("Senjata")){

            /* jika slot senjata Player sudah penuh maka... */
            if(jumlahSlotSenjataPlayerKosong == 0){
                System.out.println();
                System.out.println("[ Kapasitas penyimpanan senjata tidak cukup. ]");
            }else{

                /* jika input kurang dari 0 atau lebih dari jumlah senjata yang tersedia maka.. */
                if(input < 0 || input > jumlah){
                    System.out.println();
                    System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");

                }else if(input > 0){
                    int urutan = 0;
                    for (int i=0; i<this.daftarBarang.get("Senjata").size(); i++){
                        for (int j=0; j<this.daftarBarang.get("Senjata").get(i).size(); j++){
                            urutan++;

                            /* Jika nilai input dan nilai urutan sama maka.. */
                            if(input == urutan){

                                /* Pindahkan/Copy Objek barang yang telah dipilih */
                                Barang temp = this.daftarBarang.get("Senjata").get(i).get(j);

                                /* Hapus Pointer Objek terpilih pada daftarBarang */
                                this.daftarBarang.get("Senjata").get(i).remove(j);

                                /* Pengecekan jika list barang 1 dimensi pada index pertama list barang 2 dimensi kosong, hapus pointer objectnya*/
                                if(this.daftarBarang.get("Senjata").get(0).isEmpty()){

                                    /* Hapus pointer object list 1 dimensi pada index pertama atau index 0 list barang 2 dimensi */
                                    this.daftarBarang.get("Senjata").remove(0);

                                    /* jika list 2 dimensi dengan kategori senjata telah kosong maka... */
                                    if(this.daftarBarang.get("Senjata").isEmpty()){

                                        /* Hapus pointer object list 2 dimensi pada kategori senjata termasuk Key atau Kategori Senjatanya */
                                        this.daftarBarang.remove("Senjata");
                                    }
                                }

                                return temp;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /* saat berganti hari, barang-barang berikut yang ditambahkan akan muncul kembali jika kosong (telah diambil),
    *  Sehingga saat berganti hari, barang di daftarBarangTetap akan di instance ulang ke dalam daftarBarang (untuk dapat diambil Player)
    */
    public void tambahBarangTetap(HashMap<String, HashMap<Integer, ArrayList<Barang>>> oBarangPilihan){

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
                         *  Jika dalam daftarBarangTetap terdapat kategori barang tersebut maka...
                         */
                        if(this.daftarBarangTetap.containsKey(isiList3.getKey())){

                            /* Jika di daftarBarangPencarian terdapat id Barang yang sama maka... */
                            if(this.daftarBarangTetap.get(isiList3.getKey()).containsKey(isiList2.getValue().get(0).getIdBarang())){

                                /* Tambahkan semua barang ke dalam List yang memiliki id Barang sama */
                                this.daftarBarangTetap.get(isiList3.getKey()).get(isiList2.getValue().get(0).getIdBarang()).addAll(isiList2.getValue());
                            }else{

                                /* Tambahkan semua barang dengan membuat List Baru dengan id Barang baru*/
                                this.daftarBarangTetap.get(isiList3.getKey()).put(isiList2.getValue().get(0).getIdBarang(), isiList2.getValue());
                            }
                        }else{

                            /* Tambahkan kategori yang telah ditetapkan dalam proses seleksi input,
                             *  ke daftarBarangTetap beserta isi input tersebut
                             */
                            this.daftarBarangTetap.put(isiList3.getKey(), isiList3.getValue());
                        }

                    }
                }
            }
        }

        /* jika daftarBarangTetap tidak kosong maka.. */
        if(!this.daftarBarangTetap.isEmpty()){
            //meng-instance ulang semua object di daftarBarangTetap dan dimuat ke daftarBarang
            refreshBarang();
        }
    }

    /* Private karena hanya untuk proses internal */
    private void refreshBarang(){
        for (Map.Entry<String, HashMap<Integer, ArrayList<Barang>>> oJenisBarang : this.daftarBarangTetap.entrySet()) {
            //Pengulangan mengisi List 2 dimensi atau dimensi ke 2 dalam daftarBarangTetap
            ArrayList<ArrayList<Barang>> tempList2 = new ArrayList<>();
            for (Map.Entry<Integer, ArrayList<Barang>> oDaftarBarang : oJenisBarang.getValue().entrySet()) {
                //Pengulangan mengisi List 1 dimensi atau untuk dimensi ke 3 dalam daftarBarangTetap
                ArrayList<Barang> tempList3 = new ArrayList<>();
                for (Barang oBarang : oDaftarBarang.getValue()) {
                    //cloning teknik polymorphism :)   (membuat object dengan data sama percis)
                    tempList3.add(oBarang.cloning());
                }
                tempList2.add(tempList3);
            }

            //setelah List 2 dimensi selesai di-isi (tempList2), masukkan hasil ke dalam daftarBarang sesuai kategori yang ada pada daftarBarangTetap
            this.daftarBarang.put(oJenisBarang.getKey(), tempList2);
        }
    }

    public void gunakanBarang(){
        System.out.println("Aksi : Menggunakan kunci");
        System.out.println();
        System.out.println( "[ " + this.oPlayer.getNama() + "menggunakan kunci.. ]");
        System.out.println();
    }

    public void tambahPilihan(Pilihan oPilihan){
        this.daftarPilihan.add(oPilihan);
    }

    public HashMap<String, ArrayList<ArrayList<Barang>>> getDaftarBarang() {
        return daftarBarang;
    }

    public int getjumlahBarang(){
        return this.daftarBarang.size();
    }

    public void tambahLawan(Lawan oLawan){
        this.daftarLawan.add(oLawan);
    }

    public int getJumlahLawan(){
        return this.daftarLawan.size();
    }

    public int getIdBarangBisaDigunakan() {
        return idBarangBisaDigunakan;
    }

    public String getNarasi() {
        return narasi;
    }

    public String getNamaTempat() {
        return namaTempat;
    }

    public int getJumlahKategori() {
        return jumlahKategori;
    }

}
