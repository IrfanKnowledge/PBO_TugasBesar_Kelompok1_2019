public class EfekDps extends Efek {
    private int dps;

    EfekDps(int idEfek, String nama, int durasiEfek, int peluangTerkenaEfek, int dps) {
        super(idEfek, nama, durasiEfek, peluangTerkenaEfek);
        this.setDps(dps);
    }

    public void setDps(int dps) {
        this.dps = this.filterMinimalNol(dps);
    }

    public int getDps() {
        return dps;
    }
}
