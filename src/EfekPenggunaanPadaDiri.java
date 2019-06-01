public class EfekPenggunaanPadaDiri extends Efek {

    /* private karena memiliki ketentuan khusus atau batas */
    private int ketahananTambahan;
    private int durasiKetahananTambahan;
    private int durasiDelayKetahananTambahan;

    EfekPenggunaanPadaDiri(int idEfek, String nama, int dps, int durasiDps, int peluangTerkenaDps, int durasiDelayDps, int durasiStun, int peluangTerkenaStun, int durasiDelayStun, int ketahananTambahan, int durasiKetahananTambahan, int durasiDelayKetahananTambahan) {
        super(idEfek, nama, dps, durasiDps, peluangTerkenaDps, durasiDelayDps, durasiStun, peluangTerkenaStun, durasiDelayStun);

        this.setKetahananTambahan(ketahananTambahan);
        this.setDurasiKetahananTambahan(durasiKetahananTambahan);
        this.setDurasiDelayKetahananTambahan(durasiDelayKetahananTambahan);
    }

    private int filterMinimalNol(int nilai){
        if(nilai < 0){
            nilai = 0;
        }
        return nilai;
    }

    private int filterPeluang(int nilai){
        if(this.filterMinimalNol(nilai) > 100){
            nilai = 100;
        }
        return nilai;
    }

    public void setKetahananTambahan(int ketahananTambahan) {
        this.ketahananTambahan = this.filterMinimalNol(ketahananTambahan);
    }

    public int getKetahananTambahan() {
        return ketahananTambahan;
    }

    public void setDurasiKetahananTambahan(int durasiKetahananTambahan) {
        this.durasiKetahananTambahan = this.filterMinimalNol(durasiKetahananTambahan);
    }

    public int getDurasiKetahananTambahan() {
        return durasiKetahananTambahan;
    }

    public void setDurasiDelayKetahananTambahan(int durasiDelayKetahananTambahan) {
        this.durasiDelayKetahananTambahan = this.filterMinimalNol(durasiDelayKetahananTambahan);
    }

    public int getDurasiDelayKetahananTambahan() {
        return durasiDelayKetahananTambahan;
    }
}
