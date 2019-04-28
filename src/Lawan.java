import java.util.HashMap;
import java.util.Map;

public class Lawan {
    int idLawan;
    String nama;
    int kesehatan;
    int kekuatan;
    BarangSenjataNonLempar senjata;
    Efek efekMenyerang;
    private HashMap<Integer, Efek> daftarEfekDiri = new HashMap<>();
    boolean statusMelihatPlayer;

    Lawan(int idLawan, int kesehatan, int kekuatan, boolean statusMelihatPlayer){
        this.idLawan = idLawan;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
        this.statusMelihatPlayer = statusMelihatPlayer;
    }

    public void kurangiKesehatan(int nilaiSerangan){
        if(this.kesehatan > 0){
            this.kesehatan -= nilaiSerangan;
            if(this.kesehatan < 0){
                this.kesehatan = 0;
            }
        }
    }

    public void gunakanEfekDiri(){

    }

    public void tambahSenjata(BarangSenjataNonLempar senjata){
        this.senjata = senjata;
    }

    public void tambahEfekMenyerang(Efek efekMenyerang){
        this.efekMenyerang = efekMenyerang;
    }

    public void tambahEfekDiri(HashMap<Integer, Efek> efekLuar){
        for (Map.Entry<Integer, Efek> record : efekLuar.entrySet()) {
            daftarEfekDiri.put(record.getKey(), record.getValue());
        }
    }

    public void serang(Player oPlayer){
        //mengecek senjata lawan
        if(this.senjata != null){
            //menggunakan senjata lawan
            this.senjata.gunakanBarang(oPlayer);
        }else{
            //mengecek kekuatan lawan seperti serangan gigitan dll..
            if(this.kekuatan != 0){
                //menyerang dengan gigitan dll..
                oPlayer.kurangiKesehatan(this.kekuatan);
                //mengecek serangan lawan yang memiliki efek (ada atau tidak)
                if(this.efekMenyerang != null){
                    //memberikan efek kepada target yang diserang
                    oPlayer.tambahEfekDiri(this.efekMenyerang);
                }
            }else{
                //mengecek serangan lawan yang memiliki efek (ada atau tidak)
                if(this.efekMenyerang != null){
                    //memberikan efek kepada target yang diserang
                    oPlayer.tambahEfekDiri(this.efekMenyerang);
                }
            }
        }
    }
}
