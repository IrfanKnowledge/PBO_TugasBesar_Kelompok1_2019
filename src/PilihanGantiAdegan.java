public class PilihanGantiAdegan extends Pilihan{
    private Adegan oAdegan;
    private Player oPlayer;

    PilihanGantiAdegan(String dekripsi, AdeganNormal oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.oPlayer = this.oAdegan.oPlayer;
    }

    @Override
    public void aksi() {
        this.oPlayer.adeganAktif = this.oAdegan;
    }
}
