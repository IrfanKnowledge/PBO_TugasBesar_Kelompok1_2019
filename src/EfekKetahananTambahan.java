public class EfekKetahananTambahan extends Efek {
    private int presentaseKetahananTambahan;
    private int durasiKetahananTambahan;
    private int seranganYangAkanDikurangi;
    private int hasilSeranganYangTelahDikurangi;

    EfekKetahananTambahan(int idEfek, String nama, int durasiEfek, int peluangTerkenaEfek, int durasiKetahananTambahan, int presentaseKetahananTambahan) {
        super(idEfek, nama, durasiEfek, peluangTerkenaEfek);
        this.setDurasiKetahananTambahan(presentaseKetahananTambahan);
        this.setPresentaseKetahananTambahan(durasiKetahananTambahan);
    }

    public void setPresentaseKetahananTambahan(int presentaseKetahananTambahan) {
        this.presentaseKetahananTambahan = this.filterMinimalNol(presentaseKetahananTambahan);
    }

    public int getPresentaseKetahananTambahan() {
        return presentaseKetahananTambahan;
    }

    public void setDurasiKetahananTambahan(int durasiKetahananTambahan) {
        this.durasiKetahananTambahan = this.filterMinimalNol(durasiKetahananTambahan);
    }

    public int getDurasiKetahananTambahan() {
        return durasiKetahananTambahan;
    }

    public int gunakanEfek(){
        super.gunakanEfek();
        this.hasilSeranganYangTelahDikurangi = this.filterMinimalNol(this.seranganYangAkanDikurangi);
    }


}
