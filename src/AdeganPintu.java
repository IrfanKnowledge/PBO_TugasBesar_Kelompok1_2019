public class AdeganPintu extends AdeganNormal {
    private boolean isTerkunci = true;
    private String deskripsiPintu;
    public String narasiTerkunci;
    public String narasiTerbuka;
    public PilihanGantiAdegan pilihanMenujuAdeganLain;

    AdeganPintu(int idAdegan, int idPosisi, int idLantai, int idBarangBisaDigunakan, String posisiPlayer, String namaRuangan, String namaLuarRuangan, String namaTempat, String deskripsiPintu, PilihanGantiAdegan pilihanMenujuAdeganLain) {
        super(idAdegan, idPosisi, idLantai, idBarangBisaDigunakan, posisiPlayer, namaRuangan, namaLuarRuangan, namaTempat);
        this.deskripsiPintu = deskripsiPintu;
        this.narasiTerkunci = narasiTerkunci = this.oPlayer.nama + " mendekati pintu " + this.deskripsiPintu  + ". " + this.oPlayer.nama + " mencoba membuka pintu. \"Pintu ini terkunci!\"";
        this.narasiTerbuka =  this.oPlayer.nama + " mencoba membuka pintu dan terbuka!";
        this.narasi = this.narasiTerkunci;
        this.pilihanMenujuAdeganLain = pilihanMenujuAdeganLain;
    }

    public void gunakanBarang(Barang barangPilihan){
        if(this.isTerkunci){
            super.gunakanBarang(barangPilihan); //panggil parent
            this.isTerkunci = false;
            this.narasi = this.narasiTerbuka;
            tambahPilihan(this.pilihanMenujuAdeganLain);
        }
    }

    public boolean isTerkunci() {
        return isTerkunci;
    }
}
