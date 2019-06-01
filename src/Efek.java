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
//    private double nilaiKecepatan;
//    private int durasiKecepatan;
//    private int durasiPengelihatanMalam;
//    private int durasiKamuflase;

    Efek(int idEfek, String nama,
         int dps, int durasiDps, int peluangTerkenaDps, int durasiDelayDps,
         int durasiStun, int peluangTerkenaStun, int durasiDelayStun){

       this.idEfek = idEfek;
       this.nama = nama;
       this.setDps(dps);
       this.setDurasiDps(durasiDps);
       this.setPeluangTerkenaDps(peluangTerkenaDps);
       this.setDurasiStun(durasiStun);
       this.setPeluangTerkenaStun(peluangTerkenaStun);
       this.setDurasiDelayStun(durasiDelayStun);
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

    public void setPeluangTerkenaDps(int peluangTerkenaDps) {
        this.peluangTerkenaDps = this.filterPeluang(peluangTerkenaDps);
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

    public void setPeluangTerkenaStun(int peluangTerkenaStun) {
        this.peluangTerkenaStun = this.filterPeluang(peluangTerkenaStun);
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
}
