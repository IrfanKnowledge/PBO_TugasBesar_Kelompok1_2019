import java.util.ArrayList;

public class AdeganBertarung extends Adegan {

    /* Pada setiap adegan terdapat menu dengan opsi pilihan sebagai berikut */
    private PilihanSerang oPilihanSerang;
    private PilihanLihatIsiKantong oPilihanLihatIsiKantong;
    private PilihanKeluarGame oPilihanKeluarGame;
    private ArrayList<Pilihan> daftarPilihan = new ArrayList<>();
    private ArrayList<Lawan> daftarLawan = new ArrayList<>();

    /* setiap adegan bertarung terdapat adegan normal */
    private AdeganNormal oAdeganNormal;
    public boolean adeganBertarungTelahBerakhir;

    AdeganBertarung(int idAdegan, int idPosisi, int idLantai, String posisiPlayer, String namaRuangan, String namaLuarRuangan, String namaTempat, String narasi, AdeganNormal oAdeganNormal) {
        super(idAdegan, idPosisi, idLantai, posisiPlayer, namaRuangan, namaLuarRuangan, namaTempat, narasi);
        oPilihanSerang = new PilihanSerang("Serang", this);
        this.daftarPilihan.add(oPilihanSerang);
        this.oPilihanLihatIsiKantong = new PilihanLihatIsiKantong("Lihat isi kantong", this);
        tambahPilihan(this.oPilihanLihatIsiKantong);
        this.oPilihanKeluarGame = new PilihanKeluarGame("Keluar dari Game");
        tambahPilihan(this.oPilihanKeluarGame);
        this.oAdeganNormal = oAdeganNormal;
        this.adeganBertarungTelahBerakhir = false;
    }


    @Override
    public void mainkan() {
        if(this.oAdeganNormal != null){
            if(this.adeganBertarungTelahBerakhir){
                this.oPlayer.adeganAktif = this.oAdeganNormal;
            }else{
                super.mainkan();
                Pilihan.printDaftarPilihan(this.daftarPilihan);
                Pilihan.pemilihanMenuUtama(this.daftarPilihan);
            }
        }else{
            super.mainkan();
            Pilihan.printDaftarPilihan(this.daftarPilihan);
            Pilihan.pemilihanMenuUtama(this.daftarPilihan);
        }
    }

    //===================================================================================================
    /* pengaturan pilihan */
    //===================================================================================================
    public void tambahPilihan(Pilihan oPilihan){
        this.daftarPilihan.add(oPilihan);
    }

    //===================================================================================================
    /* pengaturan daftar lawan */
    //===================================================================================================
    public void tambahLawan(Lawan oLawan){
        this.daftarLawan.add(oLawan);
    }

    public int getJumlahLawan(){
        return this.daftarLawan.size();
    }

    public void printDaftarLawan(){
        int i = 0;
        for (Lawan oLawan : this.daftarLawan) {
            System.out.printf("%2d. %s | Kesehatan : %d | Kekuatan : %d\n", i+1, oLawan.nama, oLawan.getKesehatan(), oLawan.getKekuatan());
            i++;
        }
        if(this.daftarLawan.isEmpty()){
            System.out.println();
            System.out.println("[ Tidak ada lawan ]");
            System.out.println();
        }
    }

    public boolean isLawanYangDiserangMati(int input){
        if(this.daftarLawan.isEmpty()){
            return false;
        }else{
            if(this.daftarLawan.get(input-1).getKesehatan() > 0){
                return false;
            }else{
                return true;
            }
        }
    }

    public void hapusLawanYangMati(int input){
        if(this.isLawanYangDiserangMati(input)){
            this.daftarLawan.remove(input-1);
        }
    }

    public Lawan pilihLawan(int input){
        return this.daftarLawan.get(input);
    }

    public boolean isBarangDijatuhkanLawanKosong(int input){
        if(input < 1 && input > this.getJumlahLawan()){
            return true;
        }else{
            if(this.daftarLawan.get(input-1).getoPengelolaanBarangSederhana().getDaftarBarang().isEmpty()) {
                return true;
            }else{
                return false;
            }
        }
    }

    public ArrayList<ArrayList<Barang>> getBarangDijatuhkanLawan(int input){
        if(this.isBarangDijatuhkanLawanKosong(input)){
            return null;
        }else{
            return this.daftarLawan.get(input-1).getoPengelolaanBarangSederhana().getDaftarBarang();
        }
    }

    //===================================================================================================
    /* pengaturan serang dan diserang */
    //===================================================================================================
    /* Player menghampiri lawan dan menyerang */
    public void serangLawan(int input){
        this.daftarLawan.get(input-1).diSerang(this.oPlayer.gunakanSenjataPadaLawan());
        System.out.println();
        System.out.printf("[ %s menyerang dengan menggunakan %s]\n", this.oPlayer.nama, this.oPlayer.getNamaSenjataDigunakan());
        for(int i=0; i<this.daftarLawan.size() ; i++){
            System.out.println();
            this.daftarLawan.get(i).print();
        }
    }

    public void lawanMenyerang(){
        for(int i=0; i<this.daftarLawan.size(); i++){
            System.out.println();
            System.out.printf("[ %s menyerang %s ]\n", this.daftarLawan.get(i).nama, this.oPlayer.nama);
            this.oPlayer.diSerang(this.daftarLawan.get(i).getKekuatan());
            System.out.println();
            this.oPlayer.print();
        }
    }

    //===================================================================================================
    /* pengaturan reward atah perpindahan barang lawan yang terjatuh ke adegan normal */
    //===================================================================================================
    public void tambahBarangPadaAdeganNormal(ArrayList<ArrayList<Barang>> daftarBarangInput){
        for (ArrayList<Barang> daftarBarangInputTertentu: daftarBarangInput) {
            this.oAdeganNormal.getPengelolaanBarangSederhana().tambahBarang(daftarBarangInputTertentu);
        }
    }
}
