import java.util.Random;

public class Efek {

    public int idEfek;
    public String nama;

    /* private karena memiliki ketentuan khusus atau batas */
    private int dps;
    private int durasiDps;
    private int peluangTerkenaDps;  //satuan persen
    private int durasiDelayDps;
    private int durasiStun;
    private int peluangTerkenaStun; //satuan persen
    private int durasiDelayStun;
    private int presentaseKetahananTambahan;
    private int durasiKetahananTambahan;

//    private double nilaiKecepatan;
//    private int durasiKecepatan;
//    private int durasiPengelihatanMalam;
//    private int durasiKamuflase;

    Efek(int idEfek, String nama,
         int dps, int durasiDps, int presentasePeluangTerkenaDps, int durasiDelayDps,
         int durasiStun, int presentasePeluangTerkenaStun, int durasiDelayStun,
         int presentaseKetahananTambahan, int durasiKetahananTambahan){

       this.idEfek = idEfek;
       this.nama = nama;
       this.setDps(dps);
       this.setDurasiDps(durasiDps);
       this.setPresentasePeluangTerkenaDps(presentasePeluangTerkenaDps);
       this.setDurasiStun(durasiStun);
       this.setPresentasePeluangTerkenaStun(presentasePeluangTerkenaStun);
       this.setDurasiDelayStun(durasiDelayStun);
       this.setPresentaseKetahananTambahan(presentaseKetahananTambahan);
       this.setDurasiKetahananTambahan(durasiKetahananTambahan);
//       this.nilaiKecepatan = nilaiKecepatan;
//       this.durasiKecepatan = durasiKecepatan;
//       this.durasiPengelihatanMalam = durasiPengelihatanMalam;
//       this.durasiKamuflase = durasiKamuflase;
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

    public void setDps(int dps) {
        this.dps = this.filterMinimalNol(dps);
    }

    public int getDps() {
        return dps;
    }

    public void setDurasiDps(int durasiDps) {
        this.durasiDps = this.filterMinimalNol(durasiDps);
    }

    public int getDurasiDps() {
        return durasiDps;
    }

    public void setPresentasePeluangTerkenaDps(int presentasePeluangTerkenaDps) {
        this.peluangTerkenaDps = this.filterPeluang(presentasePeluangTerkenaDps);
    }

    public int getPeluangTerkenaDps() {
        return peluangTerkenaDps;
    }

    public void setDurasiStun(int durasiStun) {
        this.durasiStun = this.filterMinimalNol(durasiStun);
    }

    public int getDurasiStun() {
        return durasiStun;
    }

    public void setPresentasePeluangTerkenaStun(int presentasePeluangTerkenaStun) {
        this.peluangTerkenaStun = this.filterPeluang(presentasePeluangTerkenaStun);
    }

    public int getPeluangTerkenaStun() {
        return peluangTerkenaStun;
    }

    public void setDurasiDelayDps(int durasiDelayDps) {
        this.durasiDelayDps = this.filterMinimalNol(durasiDelayDps);
    }

    public int getDurasiDelayDps() {
        return durasiDelayDps;
    }

    public void setDurasiDelayStun(int durasiDelayStun) {
        this.durasiDelayStun = this.filterMinimalNol(durasiDelayStun);
    }

    public int getDurasiDelayStun() {
        return durasiDelayStun;
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

    private boolean peluangBerhasil(int presentasePeluang){
        Random pengacak = new Random();
        if(presentasePeluang == 0){
            return false;
        }
        int hasil = pengacak.nextInt(presentasePeluang);
        if(hasil <= presentasePeluang){
            return true;
        }else{
            return false;
        }
    }

    public boolean isPeluangDpsBerhasil(){
        if(this.durasiDps <= 0){
            return false;
        }
        if(this.durasiDelayDps > 0){
            this.setDurasiDelayDps(this.durasiDelayDps - 1);
            return false;
        }
        this.setDurasiDps(this.durasiDps - 1);
        if(this.peluangBerhasil(this.peluangTerkenaDps)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isPeluangStunBerhasil(){
        if(this.durasiStun <= 0){
            return false;
        }
        if(this.durasiDelayStun > 0){
            this.setDurasiDelayStun(this.durasiDelayStun - 1);
            return false;
        }
        this.setDurasiStun(this.durasiStun - 1);
        if(this.peluangBerhasil(this.peluangTerkenaStun)){
            return true;
        }else{
            return false;
        }
    }

    public int gunakanKetahananTambahan(int nilaiSerangan){
        if(nilaiSerangan < 0){
            nilaiSerangan = 0;
        }
        if(this.durasiKetahananTambahan <= 0){
            return nilaiSerangan;
        }
        this.setDurasiKetahananTambahan(this.durasiKetahananTambahan - 1);
        return nilaiSerangan - ((nilaiSerangan * this.presentaseKetahananTambahan) / 100);
    }

    public boolean isSemuaDurasiNol(){
        if(this.durasiDps <= 0 && this.durasiStun <= 0
                && this.durasiDelayDps <= 0 && this.durasiDelayStun <= 0
                && this.durasiKetahananTambahan <= 0){
            return true;
        }else{
            return false;
        }
    }
}
