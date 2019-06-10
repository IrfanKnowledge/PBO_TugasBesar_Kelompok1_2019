public class BarangSenjataJarakDekat extends BarangSenjata {

    /* private karena hanya untuk set, get tertentu, dan untuk proses internal,
     *  static karena semua senjataJarakDekatTerpilih memiliki komponen untuk perbaikan yang sama */
    static private Barang komponenBarangUntukPerbaikan;

    /* private karena memiliki batas minimal nilai */
    private int batasMaxKetahanan;
    private int batasMaxDiperbaiki;
    private int batasMaxUpgrade;

    /* private karena memiliki batas minimal nilai dan maksimal nilai*/
    private int ketahanan;
    private int jumlahDiperbaiki;

    /* private karena proses internal */
    private int jumlahUpgrade;

    /* private karena bergantung pada proses internal */
    private boolean statusKemampuanDiperbaiki;
    private boolean statusPerbaikiBarangBerhasil;
    private boolean statusBisaUpgrade;
    private boolean statusUpgradeBarangBerhasil;

    BarangSenjataJarakDekat(int idBarang, String nama, String kategoriBarang, String deskripsi,
                            boolean statusDapatDigunakanAdeganTertentu,
                            boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                            int kekuatan, int batasMaxKetahanan, int ketahanan,
                            int batasMaxDiperbaiki, int jumlahDiperbaiki, int batasMaxUpgrade){
        super(idBarang, nama, kategoriBarang, deskripsi, statusDapatDigunakanAdeganTertentu, statusJual, statusBeli, hargaJual, hargaBeli, kekuatan);

        this.setBatasMaxKetahanan(batasMaxKetahanan);
        this.setKetahanan(ketahanan);
        this.setBatasMaxDiperbaiki(batasMaxDiperbaiki);
        this.setJumlahDiperbaiki(jumlahDiperbaiki);
        this.setBatasMaxUpgrade(batasMaxUpgrade);
        this.jumlahUpgrade = 0;
        this.setStatusKemampuanDiperbaiki();
        this.setstatusBisaUpgrade();
    }

    private int filterMinimalNol(int nilai){
        if(nilai < 0){
            return 0;
        }
        return nilai;
    }

    private int filterMinimalSatu(int nilai){
        if(nilai < 1){
            return 1;
        }
        return nilai;
    }

    //===================================================================================================
    /* status */
    //===================================================================================================
    public boolean isStatusKemampuanDiperbaiki(){
        return statusKemampuanDiperbaiki;
    }

    public boolean isStatusKetahananMasihMaksimal(){
        if(this.ketahanan < this.batasMaxKetahanan){
            return false;
        }
        return true;
    }

    public boolean isStatusPerbaikiBarangBerhasil() {
        return statusPerbaikiBarangBerhasil;
    }

    public boolean isStatusBisaUpgrade() {
        return statusBisaUpgrade;
    }

    //===================================================================================================
    /* set dan get perbaikan, ketahanan, dan upgrade */
    //===================================================================================================
    static public void setKomponenUntukPerbaikan(Barang oBarang) {
        /* dibuat berbeda karena object ini digunakan hanya untuk mendapatkan info yang diperlukan saja */
        komponenBarangUntukPerbaikan = oBarang.cloning();
    }

    public int getIdBarangUntukPerbaikan() {
        if(komponenBarangUntukPerbaikan == null){
            return -1;
        }
        return komponenBarangUntukPerbaikan.idBarang;
    }

    public String getNamaBarangUntukPerbaikan() {
        if(komponenBarangUntukPerbaikan == null){
            return null;
        }
        return komponenBarangUntukPerbaikan.nama;
    }

    public String getKategoriBarangUntukPerbaikan() {
        if(komponenBarangUntukPerbaikan == null){
            return null;
        }
        return komponenBarangUntukPerbaikan.kategoriBarang;
    }

    public String getDeskripsiBarangUntukPerbaikan(){
        if(komponenBarangUntukPerbaikan == null){
            return null;
        }
        return komponenBarangUntukPerbaikan.deskripsi;
    }

    /* diperbolehkan membuat senjata yang sudah tidak bisa diperbaiki lagi */
    private void setStatusKemampuanDiperbaiki(){
        if(this.getJumlahKemampuanDiperbaiki() <= 0 || this.batasMaxDiperbaiki <= 0){
            this.statusKemampuanDiperbaiki = false;
        }else{
            this.statusKemampuanDiperbaiki = true;
        }
    }

    public void setBatasMaxDiperbaiki(int batasMaxDiperbaiki) {
        this.batasMaxDiperbaiki = this.filterMinimalNol(batasMaxDiperbaiki);
        this.setStatusKemampuanDiperbaiki();
    }

    public int getBatasMaxDiperbaiki() {
        return batasMaxDiperbaiki;
    }

    public void setJumlahDiperbaiki(int jumlahDiperbaiki) {
        if(jumlahDiperbaiki > this.batasMaxDiperbaiki){
            this.jumlahDiperbaiki = this.batasMaxDiperbaiki;
        }else{
            this.jumlahDiperbaiki = filterMinimalNol(jumlahDiperbaiki);
        }
    }

    public int getJumlahDiperbaiki() {
        return jumlahDiperbaiki;
    }

    /* untuk mengetahui jumlah kemampuan diperbaiki yang tersisa */
    public int getJumlahKemampuanDiperbaiki(){
        return this.batasMaxDiperbaiki - this.jumlahDiperbaiki;
    }

    /* minimal sebuah senjata  bisa dipake 1x pukulan */
    public void setBatasMaxKetahanan(int batasMaxKetahanan) {
        this.batasMaxKetahanan = this.filterMinimalSatu(batasMaxKetahanan);
    }
    public int getBatasMaxKetahanan() {
        return batasMaxKetahanan;
    }

    public void setKetahanan(int ketahanan) {
        if(ketahanan > this.batasMaxKetahanan){
            this.ketahanan = this.batasMaxKetahanan;
        }else{
            this.ketahanan = this.filterMinimalNol(ketahanan);
        }
    }

    public int getKetahanan() {
        return ketahanan;
    }

    private void setstatusBisaUpgrade(){
        if(this.jumlahUpgrade >= this.batasMaxUpgrade || this.batasMaxUpgrade <= 0){
            this.statusBisaUpgrade = false;
        }else{
            this.statusBisaUpgrade = true;
        }
    }

    /* bisa terdapat senjataJarakDekatTerpilih yang tidak bisa di upgrade */
    public void setBatasMaxUpgrade(int batasMaxUpgrade) {
        this.batasMaxUpgrade = this.filterMinimalNol(batasMaxUpgrade);
        this.setstatusBisaUpgrade();
    }

    public int getBatasMaxUpgrade() {
        return batasMaxUpgrade;
    }

    public int getJumlahUpgrade() {
        return jumlahUpgrade;
    }

    @Override
    public BarangSenjataJarakDekat cloning() {
        BarangSenjataJarakDekat barangCloning = new BarangSenjataJarakDekat(
                this.idBarang, this.nama, this.kategoriBarang , this.deskripsi,
                this.statusDapatDigunakanAdeganTertentu,
                this.statusJual, this.statusBeli, this.getHargaJual(), this.getHargaBeli(),
                this.getKekuatan(), this.batasMaxKetahanan, this.ketahanan,this.batasMaxDiperbaiki,
                this.jumlahDiperbaiki, this.batasMaxUpgrade);

        return barangCloning;
    }


    //===================================================================================================
    /* proses gunakan, perbaiki, dan upgrade*/
    //===================================================================================================
    public void gunakanBarangSenjata() {
        if(this.ketahanan > 0){
            this.ketahanan -= 1;
        }else{
            /* jika ketahanan senjata sudah 0 maka senjata dianggap tidak efektif, sehingga kekuatan menurun drastis */
            this.setKekuatan(1);
        }
    }


    public void perbaikiBarang(Barang komponen){
        if(komponen == null){
            this.statusPerbaikiBarangBerhasil = false;
        }else if(komponenBarangUntukPerbaikan == null){
            this.statusUpgradeBarangBerhasil = false;
        }else if(komponen.idBarang != komponenBarangUntukPerbaikan.idBarang) {
            this.statusPerbaikiBarangBerhasil = false;
        }else if(!this.statusKemampuanDiperbaiki) {
            this.statusPerbaikiBarangBerhasil = false;
        }else if(this.isStatusKetahananMasihMaksimal()) {
            this.statusPerbaikiBarangBerhasil = false;
        }else{
            //mengubah nilai ketahanan menjadi nilai maksimal ketahanan senjata
            this.setKetahanan(this.batasMaxKetahanan);

            //menambah nilai jumlah diperbaiki pada senjata
            this.setJumlahDiperbaiki(this.jumlahDiperbaiki + 1);

            this.setStatusKemampuanDiperbaiki();
            this.statusPerbaikiBarangBerhasil = true;
        }
    }

    public void upgradeSenjata(BarangBlueprintSenjataUpgrade oBluePrint){
        if(oBluePrint == null){
            this.statusUpgradeBarangBerhasil = false;
        }else if(!isStatusBisaUpgrade()){
            this.statusUpgradeBarangBerhasil = false;
        }else{
            this.setKekuatan(this.getKekuatan() + oBluePrint.getPeningkatanKekuatan());
            this.setBatasMaxKetahanan(this.batasMaxKetahanan + oBluePrint.getPeningkatanBatasMaxKetahanan());
            this.tambahEfek(oBluePrint.getDaftarEfekTambahan());

            this.jumlahUpgrade++;
            this.setstatusBisaUpgrade();
            this.statusUpgradeBarangBerhasil = true;
        }
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("%-25s : %d / %d\n", "Ketahanan", this.getKetahanan(), this.getBatasMaxKetahanan());
        System.out.printf("%-25s : %d\n", "Kemampuan diperbaiki", this.getJumlahKemampuanDiperbaiki());
    }
}
