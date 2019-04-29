public class Efek {
    private int idEfek;
    private String nama;
    private int durasi;
    private int sisaDurasi;
    private int dps;
    private boolean statusStun = false;

   Efek(int idEfek, String nama, int durasi, int sisaDurasi,
        int dps, boolean statusStun){
       this.idEfek = idEfek;
       this.nama = nama;
       this.durasi = durasi;
       this.sisaDurasi = sisaDurasi;
       this.dps = dps;
       this.statusStun = statusStun;
   }

   public void gunakanEfek(){

   }

    public int getIdEfek() {
        return idEfek;
    }

    public String getNama() {
        return nama;
    }
}
