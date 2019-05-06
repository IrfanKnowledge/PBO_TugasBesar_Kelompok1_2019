public class PilihanIsiPeluru extends Pilihan {
    private Adegan oAdegan;

    PilihanIsiPeluru(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {

        /* proses perubahan waktu, input 0 krn tindakan ini tidak memberikan nilai tambahan apapun,
         *  namun jika ingin dibuat memberikan nilai penambah, tambahkan saja.
         */
        this.oAdegan.oPlayer.ubahWaktu(0);
    }

    @Override
    public String getDekripsi() {
        return super.getDekripsi();
    }
}
