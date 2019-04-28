public class Efek {
    int idEfek;
    String nama;
    int durasi;
    int sisaDurasi;
    int dps;
    boolean statusStun = false;

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
}
