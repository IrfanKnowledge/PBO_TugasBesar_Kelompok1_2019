import java.util.ArrayList;

public interface Cloning {
    static  ArrayList<Barang> cloning(Barang oBarang, int jumlahInstance){
        if(jumlahInstance <= 0){
            jumlahInstance = 1;
        }
        ArrayList<Barang> tempBarang = new ArrayList<>();
        for(int i=0; i <jumlahInstance; i++){
            tempBarang.add(oBarang.cloning());
        }
        return tempBarang;
    }

    static ArrayList<BarangPenggunaanPadaDiri> cloning(BarangPenggunaanPadaDiri oBarang, int jumlahInstance){
        if(jumlahInstance <= 0){
            jumlahInstance = 1;
        }
        ArrayList<BarangPenggunaanPadaDiri> tempBarang = new ArrayList<>();
        for(int i=0; i <jumlahInstance; i++){
            tempBarang.add(oBarang.cloning());
        }
        return tempBarang;
    }

    static ArrayList<BarangSenjata> cloning(BarangSenjata oBarang, int jumlahInstance){
        if(jumlahInstance <= 0){
            jumlahInstance = 1;
        }
        ArrayList<BarangSenjata> tempBarang = new ArrayList<>();
        for(int i=0; i <jumlahInstance; i++){
            tempBarang.add(oBarang.cloning());
        }
        return tempBarang;
    }

    static ArrayList<BarangSenjataJarakDekat> cloning(BarangSenjataJarakDekat oBarang, int jumlahInstance){
        if(jumlahInstance <= 0){
            jumlahInstance = 1;
        }
        ArrayList<BarangSenjataJarakDekat> tempBarang = new ArrayList<>();
        for(int i=0; i <jumlahInstance; i++){
            tempBarang.add(oBarang.cloning());
        }
        return tempBarang;
    }
}
