import java.util.ArrayList;

public class PilihanGunakanSenjata extends Pilihan {
    public Adegan oAdegan;
    int indeksBarangTerpilih;
    public ArrayList<BarangSenjata> daftarSenjataTerpilih = new ArrayList<>();

    PilihanGunakanSenjata(String dekripsi, Adegan oAdegan, int indeksBarangTerpilih ,ArrayList<BarangSenjata> daftarSenjataTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.indeksBarangTerpilih = indeksBarangTerpilih;
        this.daftarSenjataTerpilih.addAll(daftarSenjataTerpilih);
    }

    @Override
    public void aksi() {
        this.oAdegan.oPlayer.gunakanSenjataDariDaftarBarangTerbatas(indeksBarangTerpilih, daftarSenjataTerpilih);
        System.out.println();
        System.out.printf("[ %s berhasil digunakan ]\n", daftarSenjataTerpilih.get(0).nama);
    }
}
