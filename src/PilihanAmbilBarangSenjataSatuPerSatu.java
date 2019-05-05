import java.util.ArrayList;
import java.util.HashMap;

public class PilihanAmbilBarangSenjataSatuPerSatu extends Pilihan {
    private Adegan oAdegan;

    PilihanAmbilBarangSenjataSatuPerSatu(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        Barang oBarang = this.oAdegan.pilihBarangSenjataSekitarAdegan(this.oAdegan.oPlayer.getJumlahSlotSenjataKosong());
        if(oBarang != null){
            HashMap<String, HashMap<Integer, ArrayList<Barang>>> tempList3 = new HashMap<>();
            HashMap<Integer, ArrayList<Barang>> tempList2 = new HashMap<>();
            ArrayList<Barang> tempList1 = new ArrayList<>();

            tempList1.add(oBarang);
            tempList2.put(oBarang.getIdBarang(), tempList1);
            tempList3.put("Senjata", tempList2);
            this.oAdegan.oPlayer.tambahBarang(tempList3);
        }
    }

    @Override
    public String getDekripsi() {
        return super.getDekripsi();
    }
}
