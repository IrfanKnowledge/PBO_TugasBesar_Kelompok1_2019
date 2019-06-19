import java.util.ArrayList;

public class PengelolaanBarangSederhana {
    private ArrayList<ArrayList<Barang>> daftarBarang = new ArrayList<>();

    //===================================================================================================
    /* pengaturan barang */
    //===================================================================================================
    private void prosesTambahBarang(ArrayList<Barang> daftarBarangInput){
        if(daftarBarangInput != null){
            for (Barang barangInput: daftarBarangInput) {
                ArrayList<Barang> temp = new ArrayList<>();
                if(barangInput instanceof BarangSenjataJarakDekat || barangInput instanceof BarangSenjataTembak){
                    temp.add(barangInput);
                    this.daftarBarang.add(temp);
                }else if(this.daftarBarang.isEmpty()){
                    temp.add(barangInput);
                    this.daftarBarang.add(temp);
                }else if(this.daftarBarang.get(this.daftarBarang.size()-1).isEmpty()){
                    this.daftarBarang.get(this.daftarBarang.size()-1).add(barangInput);
                }else if(this.daftarBarang.get(this.daftarBarang.size()-1).get(0).idBarang != barangInput.idBarang){
                    temp.add(barangInput);
                    this.daftarBarang.add(temp);
                }else if(this.daftarBarang.get(this.daftarBarang.size()-1).get(0).idBarang == barangInput.idBarang){
                    this.daftarBarang.get(this.daftarBarang.size()-1).add(barangInput);
                }
            }
        }
    }

    public void tambahBarang(Barang barangInput){
        if(barangInput != null){
            ArrayList<Barang> daftarBarangInput = new ArrayList<>();
            daftarBarangInput.add(barangInput);
            this.prosesTambahBarang(daftarBarangInput);
        }
    }

    public void tambahBarang(ArrayList<Barang> barangInput){
        if(daftarBarang != null){
            this.prosesTambahBarang(barangInput);
        }
    }

    public void tambahBarang(Barang barangInput, int jumlahInstanceUlang){
        if(barangInput != null){
            this.prosesTambahBarang(Cloning.cloning(barangInput, jumlahInstanceUlang));
        }
    }

    public ArrayList<ArrayList<Barang>> getDaftarBarang(){
        return this.daftarBarang;
    }

    public void hapusDaftarBarangTertentu(int indeksSumberDaftarBarangDiambil, ArrayList<Barang> sumberDaftarBarangPemberiInfoDaftarBarangYangAkanDihapus){
        if(sumberDaftarBarangPemberiInfoDaftarBarangYangAkanDihapus != null){
            for (Barang barangUntukDihapus : sumberDaftarBarangPemberiInfoDaftarBarangYangAkanDihapus){
                this.daftarBarang.get(indeksSumberDaftarBarangDiambil).remove(barangUntukDihapus);
            }
            if(this.daftarBarang.get(indeksSumberDaftarBarangDiambil).isEmpty()){
                this.daftarBarang.remove(this.daftarBarang.get(indeksSumberDaftarBarangDiambil));
            }
        }
    }

    public void kosongkanBarang(){
        this.daftarBarang.clear();
    }
}
