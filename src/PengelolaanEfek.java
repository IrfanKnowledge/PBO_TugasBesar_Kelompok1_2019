import java.util.HashMap;

public class PengelolaanEfek {
    private int durasiStun;
    private int peluangTerkenaStun; //satuan persen
    private int presentaseKetahananTambahan;
    private int durasiKetahananTambahan;
    private HashMap<Integer, Efek> daftarEfekDpsAktif = new HashMap<>();
    private HashMap<Integer, Efek> daftarEfekDelay = new HashMap<>();

    public void tambahEfek(Efek oEfek){
        boolean statusEfekTermasukDaftarEfekDelay = false;
        if(oEfek.getDurasiDelayStun() > 0 && oEfek.getDurasiStun() > this.durasiStun){
            statusEfekTermasukDaftarEfekDelay = true;
        }else{
            if(oEfek.getDurasiDelayStun() <= 0 && oEfek.getDurasiStun() > this.durasiStun){
                this.durasiStun = durasiStun;
            }
        }
        if(oEfek.getDurasiKetahananTambahan() > this.durasiKetahananTambahan){
            this.durasiKetahananTambahan = this.durasiKetahananTambahan;
            this.presentaseKetahananTambahan = oEfek.getPresentaseKetahananTambahan();
        }
        if(!this.daftarEfekDpsAktif.containsKey(oEfek.idEfek)){
            if(oEfek.getDps() > 0){
                this.daftarEfekDpsAktif.get(oEfek.idEfek).setDurasiDps(oEfek.getDurasiDps());
                this.daftarEfekDpsAktif.get(oEfek.idEfek).setDps(oEfek.getDps());
            }
            statusEfekTermasukDaftarEfekDelay = true;
        }else{
            if(oEfek.getDurasiDps() > this.daftarEfekDpsAktif.get(oEfek.idEfek).getDps()
                    && oEfek.getDps() > 0
                    && oEfek.getDps() > this.daftarEfekDpsAktif.get(oEfek.idEfek).getDps()){
                this.daftarEfekDpsAktif.get(oEfek.idEfek).setDurasiDps(oEfek.getDurasiDps());
                this.daftarEfekDpsAktif.get(oEfek.idEfek).setDps(oEfek.getDps());
            }
        }
        this.daftarEfekDelay.put(oEfek.idEfek, oEfek);
    }

    public void tambahEfek(HashMap<Integer, Efek> daftarEfek){

    }
}
