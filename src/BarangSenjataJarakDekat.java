public class BarangSenjataJarakDekat extends BarangSenjata {

    /* private karena memiliki batas minimal nilai */
    private int ketahanan;
    private int batasMaxKetahanan;
    private int batasMaxDiperbaiki;
    private int batasMaxUpgrade;

    /* private karena proses internal */
    private int jumlahDiperbaiki;
    private int jumlahUpgrade;

    /* private karena bergantung pada proses internal */
    private boolean statusKemampuanDiperbaiki;
    private boolean statusUpgrade;

    /* private karena 3 variable berikut digunakan sepasang */
    private String kategoriBarangUntukPerbaikan;
    private int idBarangUntukPerbaikan;
    private String namaBarangUntukPerbaikan;

    BarangSenjataJarakDekat(int idBarang, String nama, String kategoriBarang, String deskripsi,
                            boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                            int kekuatan, int batasMaxKetahanan, int batasMaxDiperbaiki, int batasMaxUpgrade){
        super(idBarang, nama, kategoriBarang, deskripsi, statusJual, statusBeli, hargaJual, hargaBeli, kekuatan);

        this.setKetahanan(batasMaxKetahanan);
        this.setBatasMaxKetahanan(batasMaxKetahanan);
        this.setBatasMaxDiperbaiki(batasMaxDiperbaiki);
        this.setBatasMaxUpgrade(batasMaxUpgrade);
        this.setStatusKemampuanDiperbaiki();
        this.setStatusUpgrade();
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

    public int getJumlahDiperbaiki() {
        return jumlahDiperbaiki;
    }

    public int getJumlahUpgrade() {
        return jumlahUpgrade;
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

    private void setStatusUpgrade(){
        if(this.jumlahUpgrade >= this.batasMaxUpgrade){
            this.statusUpgrade = false;
        }else{
            this.statusUpgrade = true;
        }
    }

    public boolean isStatusUpgrade() {
        return statusUpgrade;
    }

    public void setKomponenUntukPerbaikan(String key, int value, String nama) {
        this.kategoriBarangUntukPerbaikan = key;
        this.idBarangUntukPerbaikan = value;
        this.namaBarangUntukPerbaikan = nama;
    }

    public String getKategoriBarangUntukPerbaikan() {
        return kategoriBarangUntukPerbaikan;
    }

    public int getIdBarangUntukPerbaikan() {
        return idBarangUntukPerbaikan;
    }

    /* untuk mengetahui jumlah kemampuan diperbaiki yang tersisa */
    public int jumlahKemampuanDiperbaiki(){
        return this.batasMaxDiperbaiki - this.jumlahDiperbaiki;
    }

    private BarangSenjataJarakDekat prosesCloning(BarangSenjataJarakDekat oBarang){
        BarangSenjataJarakDekat barangCloning = new BarangSenjataJarakDekat(oBarang.idBarang, oBarang.nama, oBarang.kategoriBarang ,oBarang.deskripsi,
                oBarang.statusJual, oBarang.statusBeli, oBarang.getHargaJual(), oBarang.getHargaBeli(),
                oBarang.getKekuatan(), oBarang.batasMaxKetahanan, oBarang.batasMaxDiperbaiki, oBarang.batasMaxUpgrade);

        this.jumlahDiperbaiki = oBarang.jumlahDiperbaiki;
        this.statusKemampuanDiperbaiki = oBarang.statusKemampuanDiperbaiki;
        this.kategoriBarangUntukPerbaikan = oBarang.kategoriBarangUntukPerbaikan;
        this.idBarangUntukPerbaikan = oBarang.idBarangUntukPerbaikan;

        return barangCloning;
    }

    @Override
    public BarangSenjataJarakDekat cloning() {
        return prosesCloning(this);
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
        if(!komponen.kategoriBarang.equals(this.kategoriBarangUntukPerbaikan) && komponen.idBarang != this.idBarangUntukPerbaikan){
            System.out.println("[ Barang yang digunakan untuk perbaikan tidak cocok ]");
            System.out.println(String.format("[ Dibutuhkan %s (%s)]", this.namaBarangUntukPerbaikan, this.kategoriBarangUntukPerbaikan));
            System.out.println();
        }else{
            if(!this.isStatusKemampuanDiperbaiki()){
                System.out.println("[ Senjata sudah tidak bisa diperbaiki ]");
                System.out.println();
            }else{
                if(this.ketahanan == this.batasMaxKetahanan){
                    System.out.println( String.format("[ %s ", this.nama) + "masih memiliki ketahanan 100% ]");
                    System.out.println();
                }else{
                    //mengubah nilai ketahanan menjadi nilai maksimal ketahanan senjata
                    this.ketahanan = this.batasMaxKetahanan;

                    //menambah nilai jumlah diperbaiki pada senjata
                    this.jumlahDiperbaiki++;

                    this.setStatusKemampuanDiperbaiki();
                }
            }
        }
    }

    public void upgradeSenjata(BarangBlueprint oBluePrint){
        this.setKekuatan(this.getKekuatan() + oBluePrint.getPeningkatanKekuatan());
        this.batasMaxKetahanan += oBluePrint.getPeningkatanBatasMaxKetahanan();
        this.tambahEfekBanyak(oBluePrint.getDaftarTambahanEfek());
    }
}
