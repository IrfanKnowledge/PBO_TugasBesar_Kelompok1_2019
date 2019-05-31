public class BarangBluePrintSenjataUpgrade extends BarangBlueprintSenjata{

    /* private karena membutuhkan proses khusus */
    private BarangSenjataJarakDekat senjataUntukCrafting;

    /* private karena terdapat pembatasan minimal nilai */
    private int peningkatanKekuatan;
    private int peningkatanBatasMaxKetahanan;

    BarangBluePrintSenjataUpgrade(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                                  boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                                  int peningkatanKekuatan, int peningkatanBatasMaxKetahanan) {
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusJual, statusBeli, hargaJual, hargaBeli, 1);

        this.setPeningkatanKekuatan(peningkatanKekuatan);
        this.setPeningkatanBatasMaxKetahanan(peningkatanBatasMaxKetahanan);
    }

    public void setSenjataUntukCrafting(BarangSenjataJarakDekat untukCraftingSenjata) {
        this.senjataUntukCrafting = untukCraftingSenjata;
    }

    private int filterMinimalNol(int nilai){
        if(nilai <= 0){
            nilai = 0;
        }
        return nilai;
    }

    public void setPeningkatanKekuatan(int peningkatanKekuatan) {
        this.peningkatanKekuatan = this.filterMinimalNol(peningkatanKekuatan);
    }

    public int getPeningkatanKekuatan() {
        return peningkatanKekuatan;
    }

    public void setPeningkatanBatasMaxKetahanan(int peningkatanBatasMaxKetahanan) {
        this.peningkatanBatasMaxKetahanan = this.filterMinimalNol(peningkatanBatasMaxKetahanan);
    }

    public int getPeningkatanBatasMaxKetahanan() {
        return peningkatanBatasMaxKetahanan;
    }

    public BarangSenjataJarakDekat getHasilCrafting() {
        if(!this.statusKeberhasilanCrafting){
            System.out.println();
            System.out.println("[ Proses crafting belum dilakukan ]");

            return null;
        }else{
            this.statusKeberhasilanCrafting = false;
            this.senjataUntukCrafting.upgradeSenjata(this);
            return senjataUntukCrafting;
        }
    }
}
