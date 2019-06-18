import java.util.Random;

public abstract class Efek {

    public int idEfek;
    public String nama;

    /* private karena memiliki ketentuan khusus atau batas */
    private int durasiEfek;
    private int peluangTerkenaEfek;

    private int durasiStun;
    private int peluangTerkenaStun; //satuan persen
    private int durasiDelayStun;

//    private double nilaiKecepatan;
//    private int durasiKecepatan;
//    private int durasiPengelihatanMalam;
//    private int durasiKamuflase;

    Efek(int idEfek, String nama, int durasiEfek, int peluangTerkenaEfek){
       this.idEfek = idEfek;
       this.nama = nama;
       this.setDurasiEfek(durasiEfek);
       this.setPeluangTerkenaEfek(peluangTerkenaEfek);
//       this.nilaiKecepatan = nilaiKecepatan;
//       this.durasiKecepatan = durasiKecepatan;
//       this.durasiPengelihatanMalam = durasiPengelihatanMalam;
//       this.durasiKamuflase = durasiKamuflase;
    }

    public int filterMinimalNol(int nilai){
        if(nilai < 0){
            nilai = 0;
        }
        return nilai;
    }

    public void setDurasiEfek(int durasiEfek){
        this.durasiEfek = this.filterMinimalNol(durasiEfek);
    }

    public int getDurasiEfek() {
        return durasiEfek;
    }

    public boolean isDurasiNol(){
        if(this.durasiEfek > 0){
            return false;
        }
        return true;
    }

    public void setPeluangTerkenaEfek(int peluangTerkenaEfek){
        if(this.filterMinimalNol(peluangTerkenaEfek) > 100){
            peluangTerkenaEfek = 100;
        }
        this.peluangTerkenaStun = peluangTerkenaEfek;
    }

    public int getPeluangTerkenaEfek() {
        return peluangTerkenaEfek;
    }

    public boolean isPeluangBerhasilTerkenaEfek(){
        Random pengacak = new Random();
        int hasil = pengacak.nextInt(100);
        if(hasil == 0 || this.peluangTerkenaEfek <= 0){
            return false;
        }
        if(hasil <= this.peluangTerkenaEfek){
            return true;
        }else{
            return false;
        }
    }

    public void gunakanEfek(){
        this.setDurasiEfek(this.durasiEfek - 1);
    }
}
