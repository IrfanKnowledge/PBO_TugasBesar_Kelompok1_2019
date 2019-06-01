import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Adegan {
    /* Game ini merupakan game Single Player sehingga setiap adegan memiliki Player yang sama */
    public static Player oPlayer;

    /* Pada setiap adegan terdapat hal berikut dan memiliki kemungkinan dapat melakukan aksi penggunaan barang */
    public int idAdegan;
    public int idBarangBisaDigunakan;
    public String posisiPlayer;
    public String namaRuangan;
    public String namaLuarRuangan;
    public String namaTempat;
    public String narasi;

    /* Pada setiap adegan terdapat menu dengan opsi pilihan sebagai berikut */
    private PilihanLihatBarangSekitar oPilihanLihatBarangSekitar;
    private PilihanLihatIsiKantong oPilihanLihatIsiKantong;
    private PilihanLihatNpcSekitar oPilihanLihatNpcSekitar;
    private PilihanKeluarGame oPilihanKeluarGame;

    /* Pada setiap adegan terdapat kemungkinan daftarPilihan, barang, Npc, dan Lawan */
    private ArrayList<Pilihan> daftarPilihan = new ArrayList<>();
//    private HashMap<String, ArrayList<ArrayList<Barang>>> daftarBarang = new HashMap<>();
//    private HashMap<String, HashMap<Integer, ArrayList<Barang>>> daftarBarangTetap = new HashMap<>();
    private PengelolaanBarang oPengelolaanBarang;
    private ArrayList<Npc> daftarNpc = new ArrayList<>();
    private ArrayList<Lawan> daftarLawan = new ArrayList<>();

    /* tanpa namaLuarRuangan */
    Adegan(int idAdegan, int idBarangBisaDigunakan, String posisiPlayer, String namaRuangan, String namaLuarRuangan, String namaTempat, String narasi){
        this.idAdegan = idAdegan;
        this.idBarangBisaDigunakan = idBarangBisaDigunakan;
        this.posisiPlayer = posisiPlayer;
        this.namaRuangan = namaRuangan;
        this.namaLuarRuangan = namaLuarRuangan;
        this.namaTempat = namaTempat;
        this.narasi = narasi;

        //inisiasi pilihan awal di setiap Adegan
        this.oPilihanLihatBarangSekitar = new PilihanLihatBarangSekitar("Lihat barang sekitar tempat ini", this);
        tambahPilihan(this.oPilihanLihatBarangSekitar);
        this.oPilihanLihatIsiKantong = new PilihanLihatIsiKantong("Lihat isi kantong", this);
        tambahPilihan(this.oPilihanLihatIsiKantong);
        this.oPilihanLihatNpcSekitar = new PilihanLihatNpcSekitar("Lihat keberadaan orang sekitar");
        tambahPilihan(this.oPilihanLihatNpcSekitar);
        this.oPilihanKeluarGame = new PilihanKeluarGame("Keluar dari Game.");
        tambahPilihan(this.oPilihanKeluarGame);

        /* Jika daftar lawan tidak kosong maka tambah pilihan lihat Lawan */
//        if(!daftarLawan.isEmpty()){
//            tambahPilihan(new PilihanLihatLawanSekitar("Lihat keberadaan musuh di sekitar"));
//        }
    }

    public int getIdBarangBisaDigunakan() {
        return idBarangBisaDigunakan;
    }

    public void tambahPilihan(Pilihan oPilihan){
        this.daftarPilihan.add(oPilihan);
    }

    public void setPengelolaanBarang(PengelolaanBarang oPengelolaanBarang) {
        this.oPengelolaanBarang = oPengelolaanBarang;
    }

    public PengelolaanBarang getPengelolaanBarang() {
        return oPengelolaanBarang;
    }

    public void tambahNpc(Npc daftarNpc) {
        this.daftarNpc.add(daftarNpc);
    }

    public int getJumlahNpc() {
        return daftarNpc.size();
    }

    public void tambahLawan(Lawan oLawan){
        this.daftarLawan.add(oLawan);
    }

    public int getJumlahLawan(){
        return this.daftarLawan.size();
    }

    public void mainkan(){
        System.out.println();
        //System.out.printf("Waktu: %1.2f\n", this.oPlayer.getWaktu());
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
    public HashMap<Integer, ArrayList<Barang>> ambilSemuaBarang(){
        if(this.getPengelolaanBarang().isEmpty()){
            return null;
        }else{
            return this.getPengelolaanBarang().getDaftarBarang();
        }
    }

    /* 1. Lihat barang di sekitar - > ambil senjata satu-per-satu */
    public ArrayList<BarangSenjata> pilihBarangSenjataSekitarAdegan(){
        if(this.getPengelolaanBarang().isEmpty()){
            return null;
        }else{
            System.out.println();
            System.out.println("Aksi : Ambil senjata satu-per-satu");

            int i=0;
            for (ArrayList<BarangSenjata> oBarangSenjata : this.getPengelolaanBarang().getDaftarBarangSenjata()) {
                i++;
                System.out.printf("%2d. %-20s | kekuatan : %-2d ",
                        i,
                        oBarangSenjata.get(i).nama,
                        oBarangSenjata.get(i).getKekuatan());
                if(oBarangSenjata.get(i) instanceof BarangSenjataJarakDekat){
                    System.out.printf("| Ketahanan : %d",
                            ((BarangSenjataJarakDekat) oBarangSenjata.get(i)).getKetahanan());
                }else if(oBarangSenjata.get(i) instanceof BarangSenjataTembak){
                    System.out.printf("| Isi Amunisi : %d/%d",
                            ((BarangSenjataTembak) oBarangSenjata.get(i)).getJumlahAmunisiDigunakan(),
                            ((BarangSenjataTembak) oBarangSenjata.get(i)).getBatasMaxAmunisiDigunakan());
                }
                System.out.println();
            }
            System.out.printf("%2d. Kembali\n", 0);
            Scanner oScan = new Scanner(System.in);
            System.out.print("Masukkan Pilihan : ");
            int input = oScan.nextInt();

            if(input < 0 || input > this.getPengelolaanBarang().getDaftarBarangSenjata().size()){
                System.out.println();
                System.out.println("[ Pilihan yang anda pilih, tidak tersedia. ]");
            }else if(input == 0){
                return null;
            }else{
                return this.getPengelolaanBarang().getDaftarBarangSenjata().get(input-1);
            }
            return null;
        }
    }

    public void gunakanBarang(){
        System.out.println();
        System.out.println("Aksi : Menggunakan kunci");
        System.out.println();
        System.out.println( "[ " + this.oPlayer.getNama() + "menggunakan kunci.. ]");
    }
}
