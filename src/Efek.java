public class Efek {
    int idEfek;
    String nama;
    int durasi;
    int sisaDurasi;
    int dps;
    boolean statusStun = false;
    int nilaiKetahanan;
    int nilaiKecepatan;
    boolean statusPengelihatanMalam;
    boolean statusKamuflase;

   Efek(int idEfek, String nama, int durasi, int sisaDurasi,
        int dps, boolean statusStun){
       this.idEfek = idEfek;
       this.nama = nama;
       this.durasi = durasi;
       this.sisaDurasi = sisaDurasi;
       this.dps = dps;
       this.statusStun = statusStun;
   }

   Efek(int idEfek, String nama, int durasi, int sisaDurasi,
        int nilaiKetahanan, int nilaiKecepatan,
        boolean statusPengelihatanMalam, boolean statusKamuflase){
       this.idEfek = idEfek;
       this.nama = nama;
       this.durasi = durasi;
       this.sisaDurasi = sisaDurasi;
       this.nilaiKetahanan = nilaiKetahanan;
       this.nilaiKecepatan = nilaiKecepatan;
       this.statusPengelihatanMalam = statusPengelihatanMalam;
       this.statusKamuflase = statusKamuflase;
   }
}
