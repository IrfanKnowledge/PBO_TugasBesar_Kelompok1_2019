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
    private HashMap<String, ArrayList<ArrayList<Barang>>> daftarBarangTetap = new HashMap<>();
    private ArrayList<Npc> daftarNpc = new ArrayList<>();
    private ArrayList<Lawan> daftarLawan = new ArrayList<>();

    Adegan(int idAdegan, int idBarangBisaDigunakan, String narasi, String namaTempat){
        this.idAdegan = idAdegan;
        this.idBarangBisaDigunakan = idBarangBisaDigunakan;
        this.narasi = narasi;
        this.namaTempat = namaTempat;

        //inisiasi pilihan awal di setiap Adegan
        this.oPilihanLihatBarangSekitar = new PilihanLihatBarangSekitar("Lihat barang sekitar tempat ini", this);
        tambahPilihan(this.oPilihanLihatBarangSekitar);
        this.oPilihanLihatIsiKantong = new PilihanLihatIsiKantong("Lihat isi kantong");
        tambahPilihan(this.oPilihanLihatIsiKantong);
        this.oPilihanLihatNpcSekitar = new PilihanLihatNpcSekitar("Lihat keberadaan orang sekitar");
        tambahPilihan(this.oPilihanLihatNpcSekitar);
        this.oPilihanKeluarGame = new PilihanKeluarGame("Keluar dari Game.");
        tambahPilihan(this.oPilihanKeluarGame);

        /* Di setiap adegan telah ditetapkan hanya memiliki barang dengan kategori Penyimpanan sebagai berikut */
        this.daftarBarangTetap.put("Kunci", new ArrayList<>());
        this.daftarBarangTetap.put("Senjata", new ArrayList<>());
        this.daftarBarangTetap.put("Komponen Crafting", new ArrayList<>());
        this.daftarBarangTetap.put("Barang Bernilai", new ArrayList<>());
        this.daftarBarangTetap.put("Blueprint", new ArrayList<>());
        this.daftarBarangTetap.put("Amunisi", new ArrayList<>());
        this.daftarBarangTetap.put("Barang Lainnya", new ArrayList<>());
    }

    Adegan(int idAdegan, int idBarangBisaDigunakan, String narasi, String namaTempat, HashMap<String, ArrayList<ArrayList<Barang>>>  daftarBarangTetap, ArrayList<Lawan> daftarLawan){
        this.idAdegan = idAdegan;
        this.idBarangBisaDigunakan = idBarangBisaDigunakan;
        this.narasi = narasi;
        this.namaTempat = namaTempat;
        this.daftarLawan = daftarLawan;

        //inisiasi pilihan awal di setiap Adegan
        this.oPilihanLihatBarangSekitar = new PilihanLihatBarangSekitar("Lihat barang di sekitar", this);
        tambahPilihan(this.oPilihanLihatBarangSekitar);
        this.oPilihanLihatIsiKantong = new PilihanLihatIsiKantong("Lihat isi kantong");
        tambahPilihan(this.oPilihanLihatIsiKantong);
        this.oPilihanLihatNpcSekitar = new PilihanLihatNpcSekitar("Lihat keberadaan orang di sekitar");
        tambahPilihan(this.oPilihanLihatNpcSekitar);

        /* Jika daftar lawan tidak kosong maka tambah pilihan lihat Lawan */
        if(!daftarLawan.isEmpty()){
            tambahPilihan(new PilihanLihatLawanSekitar("Lihat keberadaan musuh di sekitar"));
        }

        this.oPilihanKeluarGame = new PilihanKeluarGame("Keluar dari Game.");
        tambahPilihan(this.oPilihanKeluarGame);

        this.tambahBarang(daftarBarangTetap);
    }

    /* Private karena hanya untuk proses internal */
    private void refreshBarang(){
        for (Map.Entry<String, ArrayList<ArrayList<Barang>>> oJenisBarang : this.daftarBarangTetap.entrySet()) {
            //Pengulangan mengisi List 2 dimensi atau dimensi ke 2 dalam daftarBarangTetap
            ArrayList<ArrayList<Barang>> tempList2 = new ArrayList<>();
            for (ArrayList<Barang> oDaftarBarang : oJenisBarang.getValue()) {
                //Pengulangan mengisi List 1 dimensi atau untuk dimensi ke 3 dalam daftarBarangTetap
                ArrayList<Barang> tempList3 = new ArrayList<>();
                for (Barang oBarang : oDaftarBarang) {
                    //cloning teknik polymorphism :)   (membuat object dengan data sama percis)
                    tempList3.add(oBarang.cloning());
                }
                tempList2.add(tempList3);
            }

            //setelah List 2 dimensi selesai di-isi (tempList2), masukkan hasil ke dalam daftarBarang sesuai kategori yang ada pada daftarBarangTetap
            this.daftarBarang.put(oJenisBarang.getKey(), tempList2);
        }
    }

    public void mainkan(){
        System.out.println();
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
            System.out.println("Pilihan tidak tersedia.");
        }else{
            this.daftarPilihan.get(input-1).aksi();
        }
    }

    public HashMap<String, ArrayList<ArrayList<Barang>>> lihatBarangSekitar(){
        boolean validasiPilihan = false;
        HashMap<String, ArrayList<ArrayList<Barang>>> daftarBarangPilih = new HashMap<>();
        while(!validasiPilihan){

            System.out.println();
            System.out.println("Aksi : Melihat barang di sekitar");
            int i = 0;
            for(Map.Entry<String, ArrayList<ArrayList<Barang>>> oKategori : this.daftarBarang.entrySet()){
                for (ArrayList<Barang> oDaftarBarang : oKategori.getValue()) {
                    System.out.printf("-%s (%d)\n", i+1, oDaftarBarang.get(0).getNama(), oDaftarBarang.size());
                    i++;
                }
            }
            if(this.daftarBarang.isEmpty()){
                System.out.println("Tidak ditemukan barang apapun.");
                System.out.printf("%2d. %s", 0, "Kembali\n");
            }else {
                System.out.printf("%2d. %s", 1, "Ambil semua barang\n");
                System.out.printf("%2d. %s", 2, "Ambil barang satu per-satu\n");
                System.out.printf("%2d. %s", 0, "Kembali\n");
            }
            System.out.print("Masukkan Pilihan : ");
            Scanner oScan = new Scanner(System.in);
            switch (oScan.nextInt()){
                case 0:
                    validasiPilihan = true;
                    daftarBarangPilih = null;
                    break;
                case 1:
                    validasiPilihan = true;

                    /* Kita Copy Object Daftar Barang, kemudian kita seleksi di Class PilihanLihatBarangSekitar */
                    daftarBarangPilih = this.daftarBarang;
                    break;
                case 2:
                    validasiPilihan = true;
                    break;
                default:
                    break;
            }
        }
        return daftarBarangPilih;
    }

    public void tambahPilihan(Pilihan oPilihan){
        this.daftarPilihan.add(oPilihan);
    }

    public void tambahBarang(HashMap<String, ArrayList<ArrayList<Barang>>> oBarangPilihan){
        /* Pengkategorian penyimpanan telah ditetapkan sebagai berikut.
        */
        for (Map.Entry<String, ArrayList<ArrayList<Barang>>> isiList3 : oBarangPilihan.entrySet()) {
            if(isiList3.getKey().equals("Kunci") || isiList3.getKey().equals("Senjata") ||
                    isiList3.getKey().equals("Komponen Crafting") || isiList3.getKey().equals("Barang Berharga") ||
                    isiList3.getKey().equals("Blueprint") || isiList3.getKey().equals("Amunisi") ||
                    isiList3.getKey().equals("Barang Lainnya")){
                this.daftarBarangTetap.get(isiList3.getKey()).addAll(isiList3.getValue());
            }
        }

        //meng-instance ulang semua object di daftarBarangTetap dan dimuat ke daftarBarang
        refreshBarang();
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
}
