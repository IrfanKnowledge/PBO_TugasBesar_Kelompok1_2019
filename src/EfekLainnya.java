public class EfekLainnya extends Efek {
    private int delay;
    private int nilaiKetahanan;
    private int nilaiKecepatan;
    private boolean statusPengelihatanMalam;
    private boolean statusKamuflase;

    EfekLainnya(int idEfek, String nama, int durasi, int sisaDurasi,
                int dps, boolean statusStun,
                int delay, int nilaiKetahanan, int nilaiKecepatan,
                boolean statusPengelihatanMalam, boolean statusKamuflase){
        super(idEfek, nama, durasi, sisaDurasi, dps, statusStun);
        this.delay = delay;
        this.nilaiKetahanan = nilaiKetahanan;
        this.nilaiKecepatan = nilaiKecepatan;
        this.statusPengelihatanMalam = statusPengelihatanMalam;
        this.statusKamuflase = statusKamuflase;
    }

    @Override
    public void gunakanEfek() {

    }
}