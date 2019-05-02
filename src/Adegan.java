import java.util.ArrayList;
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
    private ArrayList<ArrayList<Barang>> daftarBarang = new ArrayList<>();
    private ArrayList<ArrayList<Barang>> daftarBarangTetap = new ArrayList<>();
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
    }

    Adegan(int idAdegan, int idBarangBisaDigunakan, String narasi, String namaTempat, ArrayList<ArrayList<Barang>> daftarBarang, ArrayList<Lawan> daftarLawan){
        this.idAdegan = idAdegan;
        this.idBarangBisaDigunakan = idBarangBisaDigunakan;
        this.narasi = narasi;
        this.namaTempat = namaTempat;
        this.daftarBarang = daftarBarang;
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
    }

    public void refreshBarang(){
        for (ArrayList<Barang> oDaftarBarang : this.daftarBarangTetap) {
            ArrayList<Barang> temp = new ArrayList<>();
            for (Barang oBarang : oDaftarBarang) {
                //cloning teknik polymorphism :)   (membuat object dengan data sama percis)
                temp.add(oBarang.cloning(oBarang));
            }
            this.daftarBarang.add(temp);
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

    public ArrayList<ArrayList<Barang>> lihatBarangSekitar(){
        boolean validasiPilihan = false;
        ArrayList<ArrayList<Barang>> oDaftarBarang = new ArrayList<>();
        while(!validasiPilihan){

            System.out.println();
            System.out.println("Aksi : Melihat barang di sekitar");
            int i = 0;
            for (ArrayList<Barang> oBarang : this.daftarBarang) {
                System.out.printf("-%s (%d)\n", i+1, oBarang.get(0).getNama(), oBarang.size());
                i++;
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
                    oDaftarBarang = null;
                    break;
                case 1:
                    validasiPilihan = true;
                    //Ambil semua barang
                    oDaftarBarang.addAll(this.daftarBarang);

                    //Kosongkan Barang di adegan ini
                    this.daftarBarang.clear();
                    break;
                case 2:

                    break;
                default:
                    break;
            }
        }
        return daftarBarang;
    }

    public void tambahPilihan(Pilihan oPilihan){
        this.daftarPilihan.add(oPilihan);
    }

    public void tambahBarang(ArrayList<Barang> oBarang){
        this.daftarBarangTetap.add(oBarang);

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
