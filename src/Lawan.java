import java.util.HashMap;
import java.util.Map;

public class Lawan {
    public final int idLawan;
    public String nama;
    private int kesehatan;
    private int kekuatan;
    public BarangSenjata senjata;
    private Efek efekMenyerang;
    private HashMap<Integer, Efek> daftarEfekDiri = new HashMap<>(); //masih perlu dipikirkan ulang

    Lawan(int idLawan, String nama, int kesehatan, int kekuatan) {
        this.idLawan = idLawan;
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
    }

    private int filterMinimalNol(int nilai) {
        if(nilai < 0){
            nilai = 0;
        }
        return nilai;
    }

    //===================================================================================================
    /* pengaturan kesehatan */
    //===================================================================================================
    public void setKesehatan(int kesehatan) {
        this.kesehatan = this.filterMinimalNol(kesehatan);
    }

    public int getKesehatan() {
        return kesehatan;
    }

    private void kurangiKesehatan(int nilaiSerangan){
        this.setKesehatan(this.kesehatan - nilaiSerangan);
    }


    //===================================================================================================
    /* pengaturan efek */
    //===================================================================================================
    public void tambahEfekMenyerang(Efek efekMenyerang){
        this.efekMenyerang = efekMenyerang;
    }

    //masih perlu dipikirkan ulang
    public void tambahEfekDiri(HashMap<Integer, Efek> efekLuar){
        for (Map.Entry<Integer, Efek> efekTertentu : efekLuar.entrySet()) {
            daftarEfekDiri.put(efekTertentu.getKey(), efekTertentu.getValue());
        }
    }

    //===================================================================================================
    /* pengaturan diserang */
    //===================================================================================================
    public void diSerang(BarangSenjata oSenjata){
        this.kurangiKesehatan(oSenjata.getKekuatan());
        this.tambahEfekDiri(oSenjata.getDaftarEfek());
    }

    //===================================================================================================
    /* pengaturan print diri */
    //===================================================================================================
    public void print(){
        System.out.printf("%-15s : %s\n", "Nama : ", this.nama);
        System.out.printf("%-15s : %s\n", "Kesehatan : ", this.kesehatan);
        System.out.printf("%-15s : %s\n", "Kekuatan : ", this.kekuatan);
    }

    //===================================================================================================
    /* pengaturan kekuatan */
    //===================================================================================================

    public int getKekuatan() {
        if(this.senjata != null){
            return kekuatan + senjata.getKekuatan();
        }
        return kekuatan;
    }
}
