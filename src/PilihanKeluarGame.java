public class PilihanKeluarGame extends Pilihan {
    PilihanKeluarGame(String dekripsi) {
        super(dekripsi);
    }

    @Override
    public void aksi() {
        //Aksi keluar dari Game dengan return 0;
        System.exit(0);
    }

}
