import java.util.ArrayList;
import java.util.HashMap;

public class AdeganNormal extends Adegan {
    public final int idBarangBisaDigunakan;

    /* Pada setiap adegan terdapat menu dengan opsi pilihan sebagai berikut */
    private PilihanLihatBarangSekitar oPilihanLihatBarangSekitar;
    private PilihanLihatIsiKantong oPilihanLihatIsiKantong;
    private PilihanLihatNpcSekitar oPilihanLihatNpcSekitar;
    private PilihanKeluarGame oPilihanKeluarGame;

    /* Pada setiap adegan terdapat kemungkinan daftarPilihan, barang, Npc, dan Lawan */
    private ArrayList<Pilihan> daftarPilihan = new ArrayList<>();
    private PengelolaanBarangSederhana oPengelolaanBarangSederhana;
    private MenuPengelolaanBarang oMenuPengelolaanBarang;
    private ArrayList<Npc> daftarNpc = new ArrayList<>();

    /* setiap adegan normal dapat memiliki adegan bertarung */
    private AdeganBertarung oAdeganBertarung;

    AdeganNormal(int idAdegan, int idBarangBisaDigunakan, String posisiPlayer, String namaRuangan, String namaLuarRuangan, String namaTempat, String narasi){
        super(idAdegan, posisiPlayer, namaRuangan, namaLuarRuangan, namaTempat, narasi);
        //inisiasi pilihan awal di setiap Adegan
        this.idBarangBisaDigunakan = idBarangBisaDigunakan;
        this.oPilihanLihatBarangSekitar = new PilihanLihatBarangSekitar("Lihat barang sekitar tempat ini", this);
        tambahPilihan(this.oPilihanLihatBarangSekitar);
        this.oPilihanLihatIsiKantong = new PilihanLihatIsiKantong("Lihat isi kantong", this);
        tambahPilihan(this.oPilihanLihatIsiKantong);
        this.oPilihanLihatNpcSekitar = new PilihanLihatNpcSekitar("Lihat keberadaan orang sekitar");
        tambahPilihan(this.oPilihanLihatNpcSekitar);
        this.oPilihanKeluarGame = new PilihanKeluarGame("Keluar dari Game");
        tambahPilihan(this.oPilihanKeluarGame);
        this.oMenuPengelolaanBarang = new MenuPengelolaanBarang();
        this.oPengelolaanBarangSederhana = new PengelolaanBarangSederhana();
    }

    AdeganNormal(int idAdegan, int idBarangBisaDigunakan, String posisiPlayer, String namaRuangan, String namaLuarRuangan, String namaTempat){
        super(idAdegan, posisiPlayer, namaRuangan, namaLuarRuangan, namaTempat);
        //inisiasi pilihan awal di setiap Adegan
        this.idBarangBisaDigunakan = idBarangBisaDigunakan;
        this.oPilihanLihatBarangSekitar = new PilihanLihatBarangSekitar("Lihat barang sekitar tempat ini", this);
        tambahPilihan(this.oPilihanLihatBarangSekitar);
        this.oPilihanLihatIsiKantong = new PilihanLihatIsiKantong("Lihat isi kantong", this);
        tambahPilihan(this.oPilihanLihatIsiKantong);
        this.oPilihanLihatNpcSekitar = new PilihanLihatNpcSekitar("Lihat keberadaan orang sekitar");
        tambahPilihan(this.oPilihanLihatNpcSekitar);
        this.oPilihanKeluarGame = new PilihanKeluarGame("Keluar dari Game");
        tambahPilihan(this.oPilihanKeluarGame);
        this.oMenuPengelolaanBarang = new MenuPengelolaanBarang();
        this.oPengelolaanBarangSederhana = new PengelolaanBarangSederhana();
    }

    //===================================================================================================
    /* Status */
    //===================================================================================================
    public void tambahAdeganBertarung(AdeganBertarung oAdeganBertarung){
        if(oAdeganBertarung != null){
            this.oAdeganBertarung = oAdeganBertarung;
        }
    }

    //===================================================================================================
    /* Menu Pilihan dan NPC */
    //===================================================================================================

    public void tambahPilihan(Pilihan oPilihan){
        this.daftarPilihan.add(oPilihan);
    }

    public void tambahNpc(Npc daftarNpc) {
        this.daftarNpc.add(daftarNpc);
    }

    public int getJumlahNpc() {
        return daftarNpc.size();
    }

    //===================================================================================================
    /* Menu Utama Adegan */
    //===================================================================================================
    public void mainkan(){
        if(this.oAdeganBertarung != null){
            if(!this.oAdeganBertarung.adeganBertarungTelahBerakhir){
                this.oPlayer.adeganAktif = this.oAdeganBertarung;
            }else{
                if(this.oPlayer.isKotakSuaraTertemukan()){
                    this.oPlayer.isSelesai = true;
                }else{
                    super.mainkan();
                    Pilihan.printDaftarPilihan(this.daftarPilihan);
                    Pilihan.pemilihanMenuUtama(this.daftarPilihan);
                }
            }
        }else{
            if(this.oPlayer.isKotakSuaraTertemukan()){
                this.oPlayer.isSelesai = true;
            }else{
                super.mainkan();
                Pilihan.printDaftarPilihan(this.daftarPilihan);
                Pilihan.pemilihanMenuUtama(this.daftarPilihan);
            }
        }
    }

    //===================================================================================================
    /* Sub-Menu Adegan atau Respon Adegan dari aksi Player */
    //===================================================================================================
    /* 1. Melihat barang di sekitar - > Ambil barang sekitar adegan */
    public HashMap<Integer, ArrayList<Barang>>  pilihBarangSekitarAdegan(String aksi){
        return this.oMenuPengelolaanBarang.pilihBarangDariDaftarBarangTertentu(aksi, this.oPengelolaanBarangSederhana.getDaftarBarang(), false);
    }

    /* aksi dari player terhadap adegan */
    public void gunakanBarang(Barang barangPilihan){
        System.out.println();
        System.out.println("Aksi : Menggunakan kunci");
        System.out.println();
        System.out.println( "[ " + this.oPlayer.nama + "menggunakan kunci.. ]");
    }

    //===================================================================================================
    /* pengaturan barang */
    //===================================================================================================
    public PengelolaanBarangSederhana getPengelolaanBarangSederhana(){
        return oPengelolaanBarangSederhana;
    }

    public void tambahBarang(Barang barangInput, int jumlahInstanceUlang){
        this.oPengelolaanBarangSederhana.tambahBarang(barangInput, jumlahInstanceUlang);
    }

    public void hapusDaftarBarangTertentu(int indeksSumberDaftarBarangDiambil, ArrayList<Barang> sumberDaftarBarangPemberiInfoDaftarBarangYangAkanDihapus){
        this.oPengelolaanBarangSederhana.hapusDaftarBarangTertentu(indeksSumberDaftarBarangDiambil, sumberDaftarBarangPemberiInfoDaftarBarangYangAkanDihapus);
    }
}