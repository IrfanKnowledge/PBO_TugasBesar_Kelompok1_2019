import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lawan {
    private int idLawan;
    private String nama;
    private int kesehatan;
    private int kekuatan;
    private Barang senjata;
    private Efek efekMenyerang;
    private HashMap<Integer, Efek> daftarEfekDiri = new HashMap<>(); //masih perlu dipikirkan ulang
    private boolean statusMelihatPlayer;

    Lawan(int idLawan, String nama, int kesehatan, int kekuatan, boolean statusMelihatPlayer){
        this.idLawan = idLawan;
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
        this.statusMelihatPlayer = statusMelihatPlayer;
    }

    Lawan(int idLawan, String nama, int kesehatan, int kekuatan, boolean statusMelihatPlayer, Barang senjata){
        this.idLawan = idLawan;
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
        this.statusMelihatPlayer = statusMelihatPlayer;
        this.senjata = senjata;
    }

    Lawan(int idLawan, String nama, int kesehatan, int kekuatan, boolean statusMelihatPlayer, Efek efekMenyerang){
        this.idLawan = idLawan;
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
        this.statusMelihatPlayer = statusMelihatPlayer;
        this.efekMenyerang = efekMenyerang;
    }

    Lawan(int idLawan, String nama, int kesehatan, int kekuatan, boolean statusMelihatPlayer, Efek efekMenyerang, Barang senjata){
        this.idLawan = idLawan;
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
        this.statusMelihatPlayer = statusMelihatPlayer;
        this.efekMenyerang = efekMenyerang;
        this.senjata = senjata;
    }

    private void kurangiKesehatan(int nilaiSerangan){
        if(this.kesehatan > 0){
            this.kesehatan -= nilaiSerangan;
            if(this.kesehatan < 0){
                this.kesehatan = 0;
            }
        }
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

    public void gunakanEfekDiri(){

    }

    //masih perlu dipikirkan ulang
    public void diSerang(Barang oSenjata){
        this.kurangiKesehatan(oSenjata.getKekuatan());
        //this.tambahEfekDiri(oSenjata.g);
    }

    public void printIdentitas(){

    }
}
