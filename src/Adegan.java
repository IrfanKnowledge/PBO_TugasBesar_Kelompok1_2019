import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Adegan {
    /* Game ini merupakan game Single Player sehingga setiap adegan memiliki Player yang sama */
    public static Player oPlayer;

    /* Pada setiap adegan terdapat hal berikut dan memiliki kemungkinan dapat melakukan aksi penggunaan barang */
    public final int idAdegan;
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
    private ArrayList<ArrayList<Barang>> daftarBarang = new ArrayList<>();
    private MenuPengelolaanBarang oMenuPengelolaanBarang;
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
        this.oPilihanKeluarGame = new PilihanKeluarGame("Keluar dari Game");
        tambahPilihan(this.oPilihanKeluarGame);

        this.oMenuPengelolaanBarang = new MenuPengelolaanBarang();

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

    //===================================================================================================
    /* Menu Utama Adegan */
    //===================================================================================================
    public void mainkan(){
        System.out.println();
        //System.out.printf("Waktu: %1.2f\n", this.oPlayer.getWaktu());
        System.out.println("Nama : " + this.oPlayer.getNama());
        System.out.println("Senjata : " + this.oPlayer.getNamaSenjataDigunakan());
        System.out.println("Kesehatan : " + this.oPlayer.getKesehatan());
        System.out.println("Daftar Efek : " + this.oPlayer.getDaftarEfekDiri());
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

    //===================================================================================================
    /* Sub-Menu Adegan atau Respon Adegan dari aksi Player */
    //===================================================================================================
    /* 1. Melihat barang di sekitar - > Ambil barang sekitar adegan */
    public HashMap<Integer, ArrayList<Barang>>  pilihBarangSekitarAdegan(String aksi){
        return this.oMenuPengelolaanBarang.pilihBarangDariDaftarBarangTertentu(aksi, this.daftarBarang, false);
    }

    /* aksi dari player terhadap adegan */
    public void gunakanBarang(){
        System.out.println();
        System.out.println("Aksi : Menggunakan kunci");
        System.out.println();
        System.out.println( "[ " + this.oPlayer.nama + "menggunakan kunci.. ]");
    }

    //===================================================================================================
    /* pengaturan barang */
    //===================================================================================================
    private void prosesTambahBarang(ArrayList<Barang> daftarBarangInput){
        if(daftarBarangInput != null){
            for (Barang barangInput: daftarBarangInput) {
                ArrayList<Barang> temp = new ArrayList<>();
                if(barangInput instanceof BarangSenjataJarakDekat || barangInput instanceof BarangSenjataTembak){
                    temp.add(barangInput);
                    this.daftarBarang.add(temp);
                }else if(this.daftarBarang.isEmpty()){
                    temp.add(barangInput);
                    this.daftarBarang.add(temp);
                }else if(this.daftarBarang.get(this.daftarBarang.size()-1).isEmpty()){
                    this.daftarBarang.get(this.daftarBarang.size()-1).add(barangInput);
                }else if(this.daftarBarang.get(this.daftarBarang.size()-1).get(0).idBarang != barangInput.idBarang){
                    temp.add(barangInput);
                    this.daftarBarang.add(temp);
                }else if(this.daftarBarang.get(this.daftarBarang.size()-1).get(0).idBarang == barangInput.idBarang){
                    this.daftarBarang.get(this.daftarBarang.size()-1).add(barangInput);
                }
            }
        }
    }

    public void tambahBarang(Barang barangInput){
        if(barangInput != null){
            ArrayList<Barang> daftarBarangInput = new ArrayList<>();
            daftarBarangInput.add(barangInput);
            this.prosesTambahBarang(daftarBarangInput);
        }
    }

    public void tambahBarang(ArrayList<Barang> barangInput){
        if(daftarBarang != null){
            this.prosesTambahBarang(barangInput);
        }
    }

    public void tambahBarang(Barang barangInput, int jumlahInstanceUlang){
        if(barangInput != null){
            this.prosesTambahBarang(Cloning.cloning(barangInput, jumlahInstanceUlang));
        }
    }

    public ArrayList<ArrayList<Barang>> getDaftarBarang(){
        return this.daftarBarang;
    }

    public void hapusDaftarBarangTertentu(int indeksSumberDaftarBarangDiambil, ArrayList<Barang> sumberDaftarBarangPemberiInfoDaftarBarangYangAkanDihapus){
        if(sumberDaftarBarangPemberiInfoDaftarBarangYangAkanDihapus != null){
            for (Barang barangUntukDihapus : sumberDaftarBarangPemberiInfoDaftarBarangYangAkanDihapus){
                this.daftarBarang.get(indeksSumberDaftarBarangDiambil).remove(barangUntukDihapus);
            }
            if(this.daftarBarang.get(indeksSumberDaftarBarangDiambil).isEmpty()){
                this.daftarBarang.remove(this.daftarBarang.get(indeksSumberDaftarBarangDiambil));
            }
        }
    }
}


//    /* 1. Lihat barang di sekitar -> ambil semua barang */
//    public HashMap<String, HashMap<Integer, ArrayList<Barang>>> ambilSemuaBarang(){
//        if(this.getPengelolaanBarang().isBarangKeseluruhanEmpty()){
//            return null;
//        }else{
//            return this.getPengelolaanBarang().getDaftarBarangKeseluruhanByKategori();
//        }
//    }

    /* 1. Melihat barang di sekitar - > Ambil barang sekitar adegan */
//    public ArrayList<Barang> pilihBarangSekitarAdegan(String aksi){
//        return this.oMenuPengelolaanBarang.pilihBarangDariDaftarBarangTertentu("Aksi : " + aksi);
//    }
