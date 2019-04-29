import java.util.ArrayList;
import java.util.HashMap;

public abstract class Himpunan {
    private int no;
    private ArrayList<Barang> daftarBarang = new ArrayList<>();

    //proses instance dengan mendefinisikan banyak barang sekaligus
    Himpunan(int no, ArrayList<Barang> daftarBarang){
        this.no = no;
        this.daftarBarang = daftarBarang;
    }

    //proses instance dengan mendefinisikan 1 buah barang
    Himpunan(int no, Barang oBarang){
        this.no = no;
        //this.daftarBarang.add();
    }

    public void tambahBarang(Barang oBarang){
        this.daftarBarang.add(oBarang);
    }

    public void kurangiBarang(Barang oBarang){
        this.daftarBarang.remove(daftarBarang.size()-1);
    }
}
