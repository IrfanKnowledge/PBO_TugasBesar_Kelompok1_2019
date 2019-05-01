public class Efek {
    private int idEfek;
    private String nama;
    private int dps;
    private int durasiDps;
    private int peluangDps;     //satuan persen
    private int durasiStun;
    private int peluangStun;    //satuan persen
    private int durasiDelay;
    private int nilaiKetahanan;
    private int durasiKetahanan;
    private int nilaiKecepatan;
    private int durasiKecepatan;
    private int durasiPengelihatanMalam;
    private int durasiKamuflase;

   Efek(int idEfek, String nama, int dps, int durasiDps, int peluangDps,
        int durasiStun, int peluangStun,
        int durasiDelay, int nilaiKetahanan, int durasiKetahanan,
        int nilaiKecepatan, int durasiKecepatan,
        int durasiPengelihatanMalam, int durasiKamuflase){

       this.idEfek = idEfek;
       this.nama = nama;
       this.dps = dps;
       this.durasiDps = durasiDps;
       this.peluangDps = peluangDps;
       this.durasiStun = durasiStun;
       this.peluangStun = peluangStun;
       this.durasiDelay = durasiDelay;
       this.nilaiKetahanan = nilaiKetahanan;
       this.durasiKetahanan = durasiKetahanan;
       this.nilaiKecepatan = nilaiKecepatan;
       this.durasiKecepatan = durasiKecepatan;
       this.durasiPengelihatanMalam = durasiPengelihatanMalam;
       this.durasiKamuflase = durasiKamuflase;

       if(peluangDps > 30){
           this.peluangDps = 30;
       }
       if(peluangStun > 30){
           this.peluangStun = 30;
       }
       if(peluangDps < 0){
           this.peluangDps = 0;
       }
       if(peluangStun < 0){
           this.peluangStun = 0;
       }

       if(durasiStun > 2){
           this.durasiStun = 2;
       }
   }

   public Efek gunakanEfek(){
       return this;
   }

    public int getIdEfek() {
        return idEfek;
    }

    public int getDps() {
        return dps;
    }

    public int getDurasiDps() {
        return durasiDps;
    }

    public int getDurasiStun() {
        return durasiStun;
    }

    public int getDurasiDelay() {
        return durasiDelay;
    }

    public int getNilaiKetahanan() {
        return nilaiKetahanan;
    }

    public int getDurasiKetahanan() {
        return durasiKetahanan;
    }

    public int getNilaiKecepatan() {
        return nilaiKecepatan;
    }

    public int getDurasiKecepatan() {
        return durasiKecepatan;
    }

    public int getDurasiPengelihatanMalam() {
        return durasiPengelihatanMalam;
    }

    public int getDurasiKamuflase() {
        return durasiKamuflase;
    }

    public int getPeluangDps() {
        return peluangDps;
    }

    public int getPeluangStun() {
        return peluangStun;
    }
}
