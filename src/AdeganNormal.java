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
    private ArrayList<ArrayList<Barang>> daftarBarang = new ArrayList<>();
    private MenuPengelolaanBarang oMenuPengelolaanBarang;
    private ArrayList<Npc> daftarNpc = new ArrayList<>();


    /* tanpa namaLuarRuangan */
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

    //===================================================================================================
    /* Menu Utama Adegan */
    //===================================================================================================
    public void mainkan(){
        super.mainkan();
        Pilihan.printDaftarPilihan(this.daftarPilihan);
        Pilihan.pemilihanMenuUtama(this.daftarPilihan);
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