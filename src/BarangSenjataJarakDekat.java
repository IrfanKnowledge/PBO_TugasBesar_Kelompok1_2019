public class BarangSenjataJarakDekat extends BarangSenjata {

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
    private boolean statusBisaUpgrade;

    /* private karena hanya untuk set, get tertentu, dan untuk proses internal,
    *  static karena semua senjataJarakDekat memiliki komponen untuk perbaikan yang sama */
    static private Barang komponenBarangUntukPerbaikan;

    BarangSenjataJarakDekat(int idBarang, String nama, String kategoriBarang, String deskripsi,
                            boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                            int kekuatan, int batasMaxKetahanan, int ketahanan,
                            int batasMaxDiperbaiki, int jumlahDiperbaiki, int batasMaxUpgrade){
        super(idBarang, nama, kategoriBarang, deskripsi, statusJual, statusBeli, hargaJual, hargaBeli, kekuatan);

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


    /* minimal sebuah senjata  bisa dipake 1x pukulan */
    public void setBatasMaxKetahanan(int batasMaxKetahanan) {
        this.batasMaxKetahanan = this.filterMinimalSatu(batasMaxKetahanan);
    }

    public int getBatasMaxKetahanan() {
        return batasMaxKetahanan;
    }

    /* diperbolehkan membuat senjata yang sudah tidak bisa diperbaiki lagi */
    public void setBatasMaxDiperbaiki(int batasMaxDiperbaiki) {
        this.batasMaxDiperbaiki = this.filterMinimalNol(batasMaxDiperbaiki);
        if(this.batasMaxDiperbaiki == 0){
            this.statusKemampuanDiperbaiki = false;
        }
    }

    public int getBatasMaxDiperbaiki() {
        return batasMaxDiperbaiki;
    }

    /* bisa terdapat senjataJarakDekat yang tidak bisa di upgrade */
    public void setBatasMaxUpgrade(int batasMaxUpgrade) {
        this.batasMaxUpgrade = this.filterMinimalNol(batasMaxUpgrade);
    }

    public int getBatasMaxUpgrade() {
        return batasMaxUpgrade;
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

    public int getJumlahUpgrade() {
        return jumlahUpgrade;
    }

    /* untuk mengetahui jumlah kemampuan diperbaiki yang tersisa */
    public int jumlahKemampuanDiperbaiki(){
        return this.batasMaxDiperbaiki - this.jumlahDiperbaiki;
    }

    private void setStatusKemampuanDiperbaiki(){
        if(this.jumlahKemampuanDiperbaiki() <= 0){
            this.statusKemampuanDiperbaiki = false;
        }else{
            this.statusKemampuanDiperbaiki = true;
        }
    }

    public boolean isStatusKemampuanDiperbaiki() {
        return statusKemampuanDiperbaiki;
    }

    private void setstatusBisaUpgrade(){
        if(this.jumlahUpgrade >= this.batasMaxUpgrade){
            this.statusBisaUpgrade = false;
        }else{
            this.statusBisaUpgrade = true;
        }
    }

    public boolean isStatusBisaUpgrade() {
        return statusBisaUpgrade;
    }

    static public void setKomponenUntukPerbaikan(Barang oBarang) {
        komponenBarangUntukPerbaikan = oBarang.cloning();
    }

    public String getKategoriBarangUntukPerbaikan() {
        return komponenBarangUntukPerbaikan.kategoriBarang;
    }

    public int getIdBarangUntukPerbaikan() {
        return komponenBarangUntukPerbaikan.idBarang;
    }

    public String getNamaBarangUntukPerbaikan() {
        return komponenBarangUntukPerbaikan.nama;
    }

    @Override
    public BarangSenjataJarakDekat cloning() {
        BarangSenjataJarakDekat barangCloning = new BarangSenjataJarakDekat(this.idBarang, this.nama, this.kategoriBarang ,this.deskripsi,
                this.statusJual, this.statusBeli, this.getHargaJual(), this.getHargaBeli(),
                this.getKekuatan(), this.batasMaxKetahanan, this.batasMaxDiperbaiki, this.batasMaxUpgrade,
                this.jumlahDiperbaiki, this.batasMaxUpgrade);

        return barangCloning;
    }

    public void gunakanBarangSenjata() {
        if(this.ketahanan > 0){
            this.ketahanan -= 1;
        }else{
            /* jika ketahanan senjata sudah 0 maka senjata dianggap tidak efektif, sehingga kekuatan menurun drastis */
            this.setKekuatan(1);
        }
    }

    public void perbaikiBarang(Barang komponen){
        if(komponen.idBarang != komponenBarangUntukPerbaikan.idBarang && !komponen.kategoriBarang.equals(komponenBarangUntukPerbaikan.kategoriBarang) ){
            System.out.println();
            System.out.println("[ Barang yang digunakan untuk perbaikan tidak cocok ]");
            System.out.println(String.format("[ Dibutuhkan %s (%s)]", komponenBarangUntukPerbaikan.nama, komponenBarangUntukPerbaikan.kategoriBarang));
        }else{
            if(!this.isStatusKemampuanDiperbaiki()){
                System.out.println();
                System.out.println("[ Senjata sudah tidak bisa diperbaiki ]");
            }else{
                if(this.ketahanan == this.batasMaxKetahanan){
                    System.out.println();
                    System.out.println( String.format("[ %s ", this.nama) + "masih memiliki ketahanan 100% ]");
                }else{
                    //mengubah nilai ketahanan menjadi nilai maksimal ketahanan senjata
                    this.setKetahanan(this.batasMaxKetahanan);

                    //menambah nilai jumlah diperbaiki pada senjata
                    this.setJumlahDiperbaiki(this.jumlahDiperbaiki + 1);

                    this.setStatusKemampuanDiperbaiki();
                }
            }
        }
    }

    public void upgradeSenjata(BarangBlueprintSenjataUpgrade oBluePrint){
        if(!isStatusBisaUpgrade()){
            System.out.println();
            System.out.println("[ Senjata ini sudah tidak dapat diupgrade ]");
        }else{
            this.setKekuatan(this.getKekuatan() + oBluePrint.getPeningkatanKekuatan());
            this.setBatasMaxKetahanan(this.batasMaxKetahanan + oBluePrint.getPeningkatanBatasMaxKetahanan());
            this.tambahEfek(oBluePrint.getDaftarEfekTambahan());

            this.jumlahUpgrade++;
            this.setstatusBisaUpgrade();
        }
    }
}
