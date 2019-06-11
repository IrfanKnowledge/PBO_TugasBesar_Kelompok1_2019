import java.util.ArrayList;

public class PilihanLihatRincianBarangTertentu extends Pilihan {
    private Adegan oAdegan;
    private int indeksBarangTerpilih;
    private ArrayList<Barang> daftarBarangTerpilih;
    private ArrayList<Pilihan> daftarPilihan = new ArrayList<>();

    PilihanLihatRincianBarangTertentu(String dekripsi, Adegan oAdegan, int indeksBarangTerpilih, ArrayList<Barang> daftarBarangTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.indeksBarangTerpilih = indeksBarangTerpilih;
        this.daftarBarangTerpilih = daftarBarangTerpilih;
        if(daftarBarangTerpilih.get(0) instanceof BarangSenjataTembak){
            this.daftarPilihan.add(new PilihanGunakanSenjata("Gunakan Barang", this.oAdegan, PengelolaanBarang.convertBarangKeSenjata(daftarBarangTerpilih)));
            this.daftarPilihan.add(new PilihanIsiAmunisi("Isi Amunisi", this.oAdegan, (BarangSenjataTembak) daftarBarangTerpilih.get(0)));
            this.daftarPilihan.add(new PilihanTukarSlotBarangTerbatas("Tukar Slot Barang", this.oAdegan, this.indeksBarangTerpilih));
            this.daftarPilihan.add(new PilihanHapusBarang("Hapus Barang", this.indeksBarangTerpilih, daftarBarangTerpilih));
        }else if(daftarBarangTerpilih.get(0) instanceof BarangSenjataJarakDekat){
            this.daftarPilihan.add(new PilihanGunakanSenjata("Gunakan Barang", this.oAdegan, PengelolaanBarang.convertBarangKeSenjata(daftarBarangTerpilih)));
            this.daftarPilihan.add(new PilihanPerbaikiSenjata("Perbaiki Barang", this.oAdegan, (BarangSenjataJarakDekat) daftarBarangTerpilih.get(0)));
            this.daftarPilihan.add(new PilihanTukarSlotBarangTerbatas("Tukar Slot Barang", this.oAdegan, this.indeksBarangTerpilih));
            this.daftarPilihan.add(new PilihanHapusBarang("Hapus Barang", this.indeksBarangTerpilih, daftarBarangTerpilih));
        }else if(daftarBarangTerpilih.get(0) instanceof BarangSenjata){
            this.daftarBarangTerpilih.addAll(daftarBarangTerpilih);
            if(((BarangSenjata) daftarBarangTerpilih.get(0)).senjataDapatDigunakanLangsung){
                this.daftarPilihan.add(new PilihanGunakanSenjata("Gunakan Barang", this.oAdegan, PengelolaanBarang.convertBarangKeSenjata(daftarBarangTerpilih)));
            }else{
                ArrayList<BarangSenjata> tempDaftarAmunisi = PengelolaanBarang.convertBarangKeSenjata(daftarBarangTerpilih);
                this.daftarPilihan.add(new PilihanGunakanAmunisi("Gunakan Barang", this.oAdegan, this.indeksBarangTerpilih, tempDaftarAmunisi));
                this.daftarPilihan.add(new PilihanKombinasiAmunisi("Kombinasikan", this.oAdegan, this.indeksBarangTerpilih, tempDaftarAmunisi));
            }
            this.daftarPilihan.add(new PilihanTukarSlotBarangTerbatas("Tukar Slot Barang", this.oAdegan, this.indeksBarangTerpilih));
            this.daftarPilihan.add(new PilihanHapusBarang("Hapus Barang", this.indeksBarangTerpilih, daftarBarangTerpilih));
        }else if(daftarBarangTerpilih.get(0) instanceof BarangPenggunaanPadaDiri){
            this.daftarPilihan.add(new PilihanGunakanBarangPenggunaanPadaDiri("Gunakan Barang", this.indeksBarangTerpilih, (BarangPenggunaanPadaDiri) daftarBarangTerpilih.get(0)));
            this.daftarPilihan.add(new PilihanTukarSlotBarangTerbatas("Tukar Slot Barang", this.oAdegan, this.indeksBarangTerpilih));
            this.daftarPilihan.add(new PilihanHapusBarang("Hapus Barang", this.indeksBarangTerpilih, daftarBarangTerpilih));
        }else{
            System.out.println();
            System.out.println("[ Isi ArrayList tidak termasuk Class BarangSenjata atau Class BarangPenggunaanPadaDiri ]");
            System.out.println();
            System.exit(0);
        }
        /* Belum */
//        PilihanTukarSlotBarangTerbatas oPilihanTukarSlotBarangTerbatas = new PilihanTukarSlotBarangTerbatas("Aksi : Pilih Tujuan Slot Pemindahan Barang / Penukaran", this.oAdegan, indeksBarangPenggunaanPadaDiriTerpilih);
//        oPilihanTukarSlotBarangTerbatas.aksi();
    }

    @Override
    public void aksi() {
        this.daftarBarangTerpilih.get(0).print();
        System.out.println();
        this.printDaftarPilihan(this.daftarPilihan);
        this.pemilihan(this.daftarPilihan);
    }
}
