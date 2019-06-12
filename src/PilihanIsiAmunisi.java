import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihanIsiAmunisi extends Pilihan {
    private Adegan oAdegan;
    private BarangSenjataTembak senjataTembakTerpilih;
    private boolean statusIsiAmunisiBerhasil = false;

    ArrayList<BarangSenjata> daftarAmunisi = new ArrayList<>();
    int indeksAmunisiTerpilih = -1;

    PilihanIsiAmunisi(String dekripsi, Adegan oAdegan, BarangSenjataTembak senjataTembakTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.senjataTembakTerpilih = senjataTembakTerpilih;
    }

    PilihanIsiAmunisi(String dekripsi, Adegan oAdegan, BarangSenjataTembak senjataTembakTerpilih, ArrayList<BarangSenjata> daftarAmunisi, int indeksAmunisiTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.senjataTembakTerpilih = senjataTembakTerpilih;
        this.daftarAmunisi.addAll(daftarAmunisi);
        this.indeksAmunisiTerpilih = indeksAmunisiTerpilih;
    }

    @Override
    public void aksi() {
        if(this.senjataTembakTerpilih != null){
            /* cek kondisi jumlah peluru senjata, masih penuh atau tidak  */
            if(senjataTembakTerpilih.getJumlahKebutuhanIsiAmunisi() == 0){
                System.out.println();
                System.out.printf("[ %s memiliki jumlah peluru yang masih penuh ]\n", senjataTembakTerpilih.nama);
            }else{
                /* jika kosong maka... */
                if(daftarAmunisi.isEmpty()){
                    /* ambil daftar amunisi yang dibutuhkan */
                    HashMap<Integer, ArrayList<Barang>> tempDaftarAmunisi = this.oAdegan.oPlayer.getPengelolaanBarang().pilihBarangDariDaftarBarangTerbatasDenganJumlahBanyak(senjataTembakTerpilih.getIdAmunisiUtamaYangBisaDigunakan());
                    if(tempDaftarAmunisi != null){
                        for (Map.Entry<Integer, ArrayList<Barang>> temp : tempDaftarAmunisi.entrySet()) {
                            if(!temp.getValue().isEmpty()){
                                ArrayList<BarangSenjata> tempSenjata = PengelolaanBarang.convertBarangKeSenjata(temp.getValue());
                                if(tempSenjata != null){
                                    daftarAmunisi.addAll(tempSenjata);
                                    indeksAmunisiTerpilih = temp.getKey();
                                }
                            }
                        }
                    }
                }
                if(daftarAmunisi.isEmpty()){
                    System.out.println();
                    System.out.println("[ Persediaan Amunisi kosong. ]");
                }else{
                    /* proses isi peluru */
                    senjataTembakTerpilih.isiAmunisi(daftarAmunisi);

                    /* hapus daftarAmunisi yang ada di penyimpanan Player sesuai kebutuhan amunisi yang diperoleh */
                    this.oAdegan.oPlayer.hapusBarangDariDaftarBarangTerbatas(indeksAmunisiTerpilih, PengelolaanBarang.convertSenjataKeBarang(senjataTembakTerpilih.getDaftarAmunisiYangBerhasilDiambilIsiAmunisi()));
                    if(!senjataTembakTerpilih.getDaftarAmunisiSedangDigunakanYangDikeluarkan().isEmpty()){
                        ArrayList<Barang> amunisiTerambil = new ArrayList<>();
                        amunisiTerambil.addAll(senjataTembakTerpilih.getDaftarAmunisiSedangDigunakanYangDikeluarkan());
                        this.oAdegan.oPlayer.getPengelolaanBarang().tambahBarang(amunisiTerambil);
                    }
                    System.out.println();
                    System.out.println("[ Isi amunisi berhasil dilakukan ]");
                    this.statusIsiAmunisiBerhasil = true;
                }
            }
        }else{
            this.statusIsiAmunisiBerhasil = false;
        }
    }

    public boolean isStatusIsiAmunisiBerhasil() {
        return statusIsiAmunisiBerhasil;
    }
}
