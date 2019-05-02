public class AdeganBertarung extends Adegan {

    private PilihanSerang oPilihanSerang;

    AdeganBertarung(int idAdegan, int idBarangBisaDigunakan, String narasi, String namaTempat) {
        super(idAdegan, idBarangBisaDigunakan, narasi, namaTempat);
        oPilihanSerang = new PilihanSerang("Serang");
    }
}
