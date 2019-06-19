public class PilihanTukarSlotBarangTerbatas extends Pilihan {
    private Adegan oAdegan;
    private int indeksBarangPertama;


    PilihanTukarSlotBarangTerbatas(String dekripsi, Adegan oAdegan, int indeksBarangPertama) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.indeksBarangPertama = indeksBarangPertama;
    }

    @Override
    public void aksi() {
        int indeksSlotBarangTujuan = this.oAdegan.oPlayer.pilihIndeksSlotBarang();
        this.oAdegan.oPlayer.getPengelolaanBarang().tukarSlotBarangPadaDaftarBarangTerbatas(indeksBarangPertama, indeksSlotBarangTujuan);
        if(this.oAdegan.oPlayer.getIndeksSlotSenjataDiambil() == this.indeksBarangPertama){
            this.oAdegan.oPlayer.tukarIndeksSlotSenjataDiambil(indeksSlotBarangTujuan);
        }
        System.out.println();
        System.out.println("[ Menukar barang berhasil ]");
        this.kembaliKeMenuSebelumnya = true;
    }
}
