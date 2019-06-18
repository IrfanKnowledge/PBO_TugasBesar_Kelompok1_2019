import java.util.ArrayList;

public class PilihanGunakanBarangPenggunaanPadaDiri extends Pilihan {
    private Adegan oAdegan;
    private int indeksBarangPenggunaanPadaDiriTerpilih;
    private BarangPenggunaanPadaDiri barangPenggunaanPadaDiriTerpilih;
    PilihanGunakanBarangPenggunaanPadaDiri(String dekripsi, int indeksBarangPenggunaanPadaDiriTerpilih,BarangPenggunaanPadaDiri barangPenggunaanPadaDiriTerpilih) {
        super(dekripsi);
        this.indeksBarangPenggunaanPadaDiriTerpilih = indeksBarangPenggunaanPadaDiriTerpilih;
        this.barangPenggunaanPadaDiriTerpilih = barangPenggunaanPadaDiriTerpilih;
    }

    @Override
    public void aksi() {
        this.oAdegan.oPlayer.tambahKesehatan(barangPenggunaanPadaDiriTerpilih.getKesehatan());
        this.oAdegan.oPlayer.tambahEfek(barangPenggunaanPadaDiriTerpilih.getDaftarEfek());
        System.out.println();
        System.out.printf("[ %s berhasil digunakan ]\n", barangPenggunaanPadaDiriTerpilih.nama);
        /* setelah digunakan barang terhapus */
        this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(indeksBarangPenggunaanPadaDiriTerpilih, barangPenggunaanPadaDiriTerpilih);
        this.kembaliKeMenuSebelumnya = true;
    }
}
