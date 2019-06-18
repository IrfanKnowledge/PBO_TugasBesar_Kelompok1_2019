import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihanGunakanAmunisi extends Pilihan {
    private Adegan oAdegan;
    private ArrayList<BarangSenjata> daftarAmunisiTerpilih;
    private int indeksAmunisiTerpilih;

    PilihanGunakanAmunisi(String dekripsi, Adegan oAdegan, int indeksAmunisiTerpilih, ArrayList<BarangSenjata> daftarAmunisiTerpilih) {
        super(dekripsi);
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
                    Barang barangTerpilih = daftarBarangTerpilih.getValue().get(0);
                    if(!(barangTerpilih instanceof BarangSenjataTembak)){
                        System.out.println();
                        System.out.println("[ Tidak dapat digunakan ke barang tersebut ]");
                    }else{
                        BarangSenjataTembak senjataTembakPilihan = (BarangSenjataTembak) barangTerpilih;
                        if(senjataTembakPilihan.getIdAmunisiUtamaYangBisaDigunakan() != daftarAmunisiTerpilih.get(0).idBarang
                                && !senjataTembakPilihan.getDaftarAmunisiYangBisaDigunakan().containsKey(daftarAmunisiTerpilih.get(0).idBarang)){
                            System.out.println();
                            System.out.println("[ Amunisi tidak cocok ]");
                        }else if(senjataTembakPilihan.getIdAmunisiUtamaYangBisaDigunakan() == daftarAmunisiTerpilih.get(0).idBarang){
                            /* proses isi amunisi */
                            PilihanIsiAmunisi oPilihanIsiAmunisi = new PilihanIsiAmunisi("Isi Peluru", this.oAdegan, senjataTembakPilihan, daftarAmunisiTerpilih, indeksAmunisiTerpilih);
                            oPilihanIsiAmunisi.aksi();
                            this.kembaliKeMenuSebelumnya = oPilihanIsiAmunisi.isStatusIsiAmunisiBerhasil();
                        }else{
                            /* proses tukar amunisi */
//                  PilihanTukarJenisAmunisi oPilihanTukarJenisAmunisi = new PilihanTukaJenisAmunisi();
                            System.out.println();
                            System.out.println("[ Fitur Tukar jenis amunisi, belum ada. Coming Soon ]");
                            this.kembaliKeMenuSebelumnya = true;
                        }
                    }
//                    break;
                }
            }
        }
    }
}
