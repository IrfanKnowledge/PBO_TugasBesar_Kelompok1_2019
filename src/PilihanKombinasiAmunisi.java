import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihanKombinasiAmunisi extends Pilihan {
    private Adegan oAdegan;
    private int indeksAmunisiTerpilih;
    private ArrayList<BarangSenjata> daftarAmunisiTerpilih;

    public PilihanKombinasiAmunisi(String deskripsi,Adegan oAdegan, int indeksAmunisiTerpilih, ArrayList<BarangSenjata> daftarAmunisiTerpilih) {
        super(deskripsi);
        this.oAdegan = oAdegan;
        this.indeksAmunisiTerpilih = indeksAmunisiTerpilih;
        this.daftarAmunisiTerpilih = daftarAmunisiTerpilih;
    }

    @Override
    public void aksi() {
        this.kembaliKeMenuSebelumnya = false;
        while(!this.kembaliKeMenuSebelumnya){
            HashMap<Integer, ArrayList<Barang>> hashBarangTerpilih = this.oAdegan.oPlayer.pilihBarangDariDaftarBarangTerbatas(this.dekripsi);
            if(hashBarangTerpilih == null){
                this.kembaliKeMenuSebelumnya = true;
            }else{
                for (Map.Entry<Integer, ArrayList<Barang>> daftarBarangTerpilih : hashBarangTerpilih.entrySet()) {
                    this.oAdegan.oPlayer.getPengelolaanBarang().kombinasiAmunisiPadaDaftarBarangTerbatas(daftarBarangTerpilih.getKey(), this.indeksAmunisiTerpilih, PengelolaanBarang.convertSenjataKeBarang(this.daftarAmunisiTerpilih));
                }
                if(!this.oAdegan.oPlayer.getPengelolaanBarang().isStatusKombinasiAmunisiBerhasil()){
                    System.out.println();
                    System.out.println("[ Barang tidak cocok atau Amunisi Tersebut Penuh ]");
                }else{
                    System.out.println();
                    System.out.println("[ Kombinasi amunisi berhasil ]");
                    this.kembaliKeMenuSebelumnya = true;
                }
            }
        }
    }
}
