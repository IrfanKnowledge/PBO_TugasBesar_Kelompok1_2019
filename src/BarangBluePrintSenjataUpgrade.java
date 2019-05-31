public class BarangBluePrintSenjataUpgrade extends BarangBlueprintSenjata {

    /* private karena terdapat pembatasan minimal nilai */
    private int peningkatanKekuatan;
    private int peningkatanBatasMaxKetahanan;

    BarangBluePrintSenjataUpgrade(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi, boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual, int jumlahHasilCrafting) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, jumlahHasilCrafting);
    }

    public int getPeningkatanKekuatan() {
        return peningkatanKekuatan;
    }

    public void setPeningkatanKekuatan(int peningkatanKekuatan) {
        this.peningkatanKekuatan = peningkatanKekuatan;
    }

    public int getPeningkatanBatasMaxKetahanan() {
        return peningkatanBatasMaxKetahanan;
    }

    public void setPeningkatanBatasMaxKetahanan(int peningkatanBatasMaxKetahanan) {
        this.peningkatanBatasMaxKetahanan = peningkatanBatasMaxKetahanan;
    }
}
