import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihanLihatBarangTerbatas extends Pilihan {
    private Adegan oAdegan;
    private PilihanLihatRincianBarangTertentu oPilihanLihatRincianBarangTertentu;
    private int indeksBarangTerpilih;
    private ArrayList<Barang> daftarBarangTerpilih = new ArrayList<>();

    PilihanLihatBarangTerbatas(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        this.kembaliKeMenuSebelumnya = false;
        /* untuk kembali ke menu melihat isi kantong */
        while (!this.kembaliKeMenuSebelumnya){
            boolean kembaliKeDaftarBarangTerbatas = false;
            HashMap<Integer, ArrayList<Barang>>  hashTempBarangTerpilih = this.oAdegan.oPlayer.pilihBarangDariDaftarBarangTerbatas(this.dekripsi);
            if(hashTempBarangTerpilih == null){
                kembaliKeMenuSebelumnya = true;
            }else{
                this.daftarBarangTerpilih.clear();
                indeksBarangTerpilih = -1;
                for (Map.Entry<Integer, ArrayList<Barang>> tempBarangTerpilih: hashTempBarangTerpilih.entrySet()) {
                    this.daftarBarangTerpilih.addAll(tempBarangTerpilih.getValue());
                    this.indeksBarangTerpilih = tempBarangTerpilih.getKey();
                }
                while(!kembaliKeDaftarBarangTerbatas){
                    if(daftarBarangTerpilih.isEmpty()){
                        kembaliKeDaftarBarangTerbatas = true;
                    }else{
                        this.oPilihanLihatRincianBarangTertentu = new PilihanLihatRincianBarangTertentu("Lihat rincian barang", this.oAdegan, this.indeksBarangTerpilih, this.daftarBarangTerpilih);
                        this.oPilihanLihatRincianBarangTertentu.aksi();
                        kembaliKeDaftarBarangTerbatas = this.oPilihanLihatRincianBarangTertentu.kembaliKeMenuSebelumnya;
                    }
                }
            }
        }
    }

}
