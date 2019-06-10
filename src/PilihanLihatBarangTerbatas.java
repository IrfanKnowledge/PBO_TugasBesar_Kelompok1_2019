import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihanLihatBarangTerbatas extends Pilihan {
    public Adegan oAdegan;
    public boolean kembaliKeMenuSebelumnya;
    private ArrayList<PilihanLihatIsiKantong> daftarPilihan = new ArrayList<>();
    public int indeksBarangTerpilih;
    public ArrayList<Barang> daftarBarangTerpilih = new ArrayList<>();

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
                this.kembaliKeMenuSebelumnya = true;
            }else{
                daftarBarangTerpilih.clear();
                indeksBarangTerpilih = -1;
                for (Map.Entry<Integer, ArrayList<Barang>> tempBarangTerpilih: hashTempBarangTerpilih.entrySet()) {
                    daftarBarangTerpilih.addAll(tempBarangTerpilih.getValue());
                    indeksBarangTerpilih = tempBarangTerpilih.getKey();
                }
                Barang barangTerpilih = daftarBarangTerpilih.get(0);
                this.daftarPilihan.clear();
                if(barangTerpilih instanceof BarangSenjataTembak){
                    PilihanLihatDeskripsiSenjataTembak oPilihanLihatDeskripsiSenjataTembak = new PilihanLihatDeskripsiSenjataTembak(this.dekripsi + "(senjata tembak)", this.oAdegan, indeksBarangTerpilih, (BarangSenjataTembak) barangTerpilih);
                    this.daftarPilihan.add(oPilihanLihatDeskripsiSenjataTembak);
                }else if(barangTerpilih instanceof BarangSenjataJarakDekat){
                    PilihanLihatDeskripsiSenjataJarakDekat oPilihanLihatDeskripsiSenjataJarakDekat = new PilihanLihatDeskripsiSenjataJarakDekat(this.dekripsi + "(senjata jarak dekat)", this.oAdegan, indeksBarangTerpilih, (BarangSenjataJarakDekat) barangTerpilih);
                    this.daftarPilihan.add(oPilihanLihatDeskripsiSenjataJarakDekat);
                }else if(barangTerpilih instanceof BarangSenjata){
                    PilihanLihatDeskripsiSenjata oPilihanLihatDeskripsiSenjata = new PilihanLihatDeskripsiSenjata(this.dekripsi, this.oAdegan, indeksBarangTerpilih , PengelolaanBarang.convertBarangKeSenjata(daftarBarangTerpilih));
                    this.daftarPilihan.add(oPilihanLihatDeskripsiSenjata);
                }else if(barangTerpilih instanceof BarangPenggunaanPadaDiri){
                    PilihanLihatDeskripsiBarangPenggunaanPadaDiri oPilihanLihatDeskripsiBarangPenggunaanPadaDiri = new PilihanLihatDeskripsiBarangPenggunaanPadaDiri(this.dekripsi + "(penggunaan pada diri)", this.oAdegan, indeksBarangTerpilih, (BarangPenggunaanPadaDiri) barangTerpilih);
                    this.daftarPilihan.add(oPilihanLihatDeskripsiBarangPenggunaanPadaDiri);
                }
                while(!kembaliKeDaftarBarangTerbatas){
                    if(daftarBarangTerpilih.isEmpty()){
                        kembaliKeDaftarBarangTerbatas = true;
                    }else{
                        System.out.println();
                        System.out.println("Aksi : " + this.dekripsi + " (Rincian Barang)");
                        System.out.println();
                        System.out.printf("%-25s : %s\n", "nama", barangTerpilih.nama);
                        System.out.printf("%-25s : %s\n", "Deskripsi", barangTerpilih.deskripsi);
                        if(!barangTerpilih.statusBeli){
                            System.out.printf("%-25s : -\n", "Harga beli");
                        }else{
                            System.out.printf("%-25s : %s\n", "Harga beli", barangTerpilih.getHargaBeli());
                        }
                        if(!barangTerpilih.statusJual){
                            System.out.printf("%-25s : -\n", "Harga jual");
                        }else{
                            System.out.printf("%-25s : %s\n", "Harga jual", barangTerpilih.getHargaJual());
                        }
                        this.daftarPilihan.get(0).aksi();
                        if(this.daftarPilihan.get(0).kembaliKeMenuSebelumnya){
                            kembaliKeDaftarBarangTerbatas = true;
                        }
                    }
                }
            }
        }
    }

}
