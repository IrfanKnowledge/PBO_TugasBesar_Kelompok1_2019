import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lawan {
    private int idLawan;
    public String nama;
    private int kesehatan;
    private int kekuatan;
    private Barang senjata;
    private Efek efekMenyerang;
    private HashMap<Integer, Efek> daftarEfekDiri = new HashMap<>(); //masih perlu dipikirkan ulang
    private boolean statusMelihatPlayer;

    Lawan(int idLawan, String nama, int kesehatan, int kekuatan, boolean statusMelihatPlayer) {
        this.idLawan = idLawan;
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
        this.statusMelihatPlayer = statusMelihatPlayer;
    }

    private int filterMinimalNol(int nilai) {
        if(nilai < 0){
            nilai = 0;
        }
        return nilai;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = this.filterMinimalNol(kesehatan);
    }

    public int getKesehatan() {
        return kesehatan;
    }

    private void kurangiKesehatan(int nilaiSerangan){
        this.setKesehatan(this.kesehatan - nilaiSerangan);
    }

    public void tambahSenjata(Barang senjata){
        this.senjata = senjata;
    }

    public void tambahEfekMenyerang(Efek efekMenyerang){
        this.efekMenyerang = efekMenyerang;
    }

    //masih perlu dipikirkan ulang
    public void tambahEfekDiri(HashMap<Integer, Efek> efekLuar){
        for (Map.Entry<Integer, Efek> record : efekLuar.entrySet()) {
            daftarEfekDiri.put(record.getKey(), record.getValue());
        }
    }

    //masih perlu dipikirkan ulang
    public void diSerang(BarangSenjata oSenjata){
        this.kurangiKesehatan(oSenjata.getKekuatan());
        //this.tambahEfekDiri(oSenjata.g);
    }
}
