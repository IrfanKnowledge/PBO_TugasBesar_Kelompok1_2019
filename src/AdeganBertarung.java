public class AdeganBertarung extends Adegan {

    private PilihanSerang oPilihanSerang;

    AdeganBertarung(int idAdegan, int idBarangBisaDigunakan, String posisiPlayer, String namaRuangan, String namaLuarRuangan, String namaTempat, String narasi) {
        super(idAdegan, idBarangBisaDigunakan, posisiPlayer, namaRuangan, namaLuarRuangan, namaTempat, narasi);
        oPilihanSerang = new PilihanSerang("Serang");
    }
}
