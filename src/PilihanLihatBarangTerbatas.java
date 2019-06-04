import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class PilihanLihatBarangTerbatas extends Pilihan {
    private Adegan oAdegan;

    PilihanLihatBarangTerbatas(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {

        /* untuk kembali ke menu melihat isi kantong */
        boolean validasiKembaliKeLihatIsiKantong = false;
        while (!validasiKembaliKeLihatIsiKantong){
            boolean validasiKembaliKeDaftarBarangTertentu;
            ArrayList<Barang> daftarBarangTerpilih = this.oAdegan.oPlayer.pilihBarangDariDaftarBarangTerbatas(this.dekripsi);
            if(daftarBarangTerpilih != null){

                /* untuk kembali ke menu melihat daftar barang terbatas */
                validasiKembaliKeDaftarBarangTertentu = false;
                while (!validasiKembaliKeDaftarBarangTertentu){
                    Barang barangTerpilih = daftarBarangTerpilih.get(0);
                    System.out.println();
                    System.out.println("Aksi : " + this.dekripsi + "(rincian barang tertentu)");

                    System.out.println();
                    System.out.printf("%-25s : %s\n", "nama", barangTerpilih.nama);
                    System.out.printf("%-25s : %s\n", "Deskripsi", barangTerpilih.deskripsi);
                    System.out.printf("%-25s : %s\n", "Harga beli", barangTerpilih.getHargaBeli());
                    System.out.printf("%-25s : %s\n", "Harga jual", barangTerpilih.getHargaJual());
                    if(barangTerpilih instanceof BarangSenjata){
                        System.out.printf("%-25s : %d\n", "Kekuatan", ((BarangSenjata) barangTerpilih).getKekuatan());
                    }
                    if(barangTerpilih instanceof BarangSenjataJarakDekat){
                        System.out.printf("%-25s : %d\n", "Ketahanan", ((BarangSenjataJarakDekat) barangTerpilih).getKetahanan());
                        System.out.printf("%-25s : %d\n", "Kemampuan diperbaiki", ((BarangSenjataJarakDekat) barangTerpilih).getJumlahKemampuanDiperbaiki());
                    }
                    if(barangTerpilih instanceof BarangSenjataTembak){
                        System.out.printf("%-12s : %d/%d\n", "Isi peluru", ((BarangSenjataTembak) barangTerpilih).getJumlahAmunisiDigunakan(),
                                ((BarangSenjataTembak) barangTerpilih).getBatasMaxAmunisiDigunakan());
                    }
                    System.out.println();

                    System.out.printf("%2d. Gunakan Barang\n", 1);

                    if(barangTerpilih instanceof BarangSenjataJarakDekat){
                        System.out.printf("%2d. %-20s\n", 2, "Perbaiki Barang");
                        System.out.printf("%2d. %-20s | (senjata akan dihapus, tidak dapat dikembalikan)\n", 3, "Buang Senjata");
                    }else if(barangTerpilih instanceof BarangSenjataTembak){
                        System.out.printf("%2d. %-20s\n", 2, "Isi Peluru");
                        System.out.printf("%2d. %-20s\n", 3, "Ganti Peluru");
                        System.out.printf("%2d. %-20s | (senjata akan dihapus, tidak dapat dikembalikan)\n", 4, "Buang Senjata");
                    }else{
                        System.out.printf("%2d. %-20s | (senjata akan dihapus, tidak dapat dikembalikan)\n", 2, "Buang Senjata");
                    }

                    System.out.printf("%2d. Kembali\n", 0);
                    System.out.print("Masukkan Pilihan : ");
                    Scanner oScan = new Scanner(System.in);
                    switch(oScan.nextInt()){
                        case 0:
                            validasiKembaliKeDaftarBarangTertentu = true;
                            break;
                        case 1:
                            /* Proses gunakan senjata */
                            this.gunakanBarang(barangTerpilih, daftarBarangTerpilih);
                            break;
                        case 2:
                            /* opsi perbaiki senjata jika senjata adalah jenis Senjata Pukul */
                            if(barangTerpilih.getJenis().equals("Senjata Pukul")){
                                this.perbaikiSenjata(barangTerpilih);

                                /* opsi isi peluru jika senjata adalah jenis Senjata Tembak */
                            }else if(barangTerpilih.getJenis().equals("Senjata Tembak")){
                                this.isiPeluru(barangTerpilih);

                                /* Opsi membuang senjata jika senjata adalah jenis Senjata Lempar (selain Senjata Pukul dan Senjata Tembak) */
                            }else{
                                this.buangSenjata(barangTerpilih, oBarang.getKey(), oBarang.getValue(), oSenjataLempar);
                            }
                            break;
                        case 3:
                            /* Opsi membuang senjata jika Senjata jenis Senjata Pukul */
                            if(barangTerpilih.getJenis().equals("Senjata Pukul")){
                                this.buangSenjata(barangTerpilih, oBarang.getKey(), oBarang.getValue(), oSenjataLempar);

                                /* Opsi ganti peluru jika Senjata jenis Senjata Tembak*/
                            }else if(barangTerpilih.getJenis().equals("Senjata Tembak")){
                                this.gantiPeluru(barangTerpilih);

                            }else{
                                System.out.println();
                                System.out.println("[ Pilihan tidak tersedia. ]");
                            }
                            break;
                        case 4:
                            /* Opsi membuang senjata jika Senjata adalah jenis Senjata Tembak */
                            if(barangTerpilih.getJenis().equals("Senjata Tembak")){
                                this.buangSenjata(barangTerpilih, oBarang.getKey(), oBarang.getValue(), oSenjataLempar);

                            }else{
                                System.out.println();
                                System.out.println("[ Pilihan tidak tersedia. ]");
                            }
                            break;

                        default:
                            System.out.println();
                            System.out.println("[ Pilihan tidak tersedia. ]");
                            break;
                    }
                    /* ambil key dan value dari hasil memilih senjata */
                    for (BarangSenjata oBarang : daftarBarangTerpilih) {
                    }

                }
            }else {
                validasiKembaliKeLihatIsiKantong = true;
            }
        }
    }

    private void gunakanBarang(Barang barangPilihan, ArrayList<Barang> daftarBarangPilihan){
        if(barangPilihan instanceof BarangSenjataJarakDekat || barangPilihan instanceof BarangSenjataTembak){
            this.oAdegan.oPlayer.simpanKembaliSenjataYangDigunakan();
            this.oAdegan.oPlayer.gunakanSenjataDariPenyimpanan(((BarangSenjata) barangPilihan));
            System.out.println();
            System.out.printf("[ %s berhasil digunakan ]\n", barangPilihan.nama);
        }else if(barangPilihan instanceof BarangSenjata){
            this.oAdegan.oPlayer.simpanKembaliSenjataYangDigunakan();
            this.oAdegan.oPlayer.gunakanSenjataDariPenyimpanan(this.oAdegan.oPlayer.getPengelolaanBarang().convertBarangKeSenjata(daftarBarangPilihan));
            System.out.println();
            System.out.printf("[ %s berhasil digunakan ]\n", daftarBarangPilihan.get(0).nama);
        }else{

        }
    }

    private void perbaikiSenjata(BarangSenjataJarakDekat oBarangPilihan){
        /* ambil kebutuhan komponen Crafting untuk perbaikan */
        Barang komponenCrafting = this.oAdegan.oPlayer.getPengelolaanBarang().pilihBarang(oBarangPilihan.getKategoriBarangUntukPerbaikan(), oBarangPilihan.getIdBarangUntukPerbaikan());

        if(komponenCrafting != null){
            /* proses memperbaiki barang */
            if(oBarangPilihan.perbaikiBarang(komponenCrafting)){
                /* hapus satu komponen crafting tersebut di penyimpanan */
                this.oAdegan.oPlayer.getPengelolaanBarang().hapusBarang(komponenCrafting);
            }
        }else{
            System.out.println();
            System.out.println("[ Persediaan Komponen Crafting untuk perbaikan senjata, kosong. ]");
        }
    }

    private void isiPeluru(BarangSenjata oBarangPilihan){
        /* casting tipe data (class) */
        BarangSenjataTembak oSenjataTembak = (BarangSenjataTembak) oBarangPilihan;

        /* cek kondisi jumlah peluru senjata, masih penuh atau tidak  */
        if(oSenjataTembak.getJumlahKebutuhanIsiAmunisi() == 0){
            System.out.println();
            System.out.printf("[ %s memiliki jumlah peluru yang masih penuh ]\n", oBarangPilihan.nama));
        }else{
            ArrayList<Barang> daftarAmunisi = this.oAdegan.oPlayer.getPengelolaanBarang().pilihBarangBanyak(oSenjataTembak.getKategoriAmunisiUtama(), oSenjataTembak.getIdAmunisiUtama());
            if(daftarAmunisi != null){
                /* ambil jumlah kebutuhan isi peluru*/
                int jumlahKebutuhanPeluru = oBarangPilihan.getJumlahKebutuhanIsiPeluru();

                /* proses isi peluru */
                oBarangPilihan.isiPeluru(daftarAmunisi);

                /* hapus daftarAmunisi yang ada di penyimpanan Player sesuai kebutuhan amunisi yang diperoleh */
                this.oAdegan.oPlayer.hapusBarangJumlahTertentu(kebutuhanAmunisi.getKey(), kebutuhanAmunisi.getValue(), jumlahKebutuhanPeluru);
            }else{
                System.out.println();
                System.out.println("[ Persediaan Amunisi kosong. ]");
            }
        }
        for (Map.Entry<String, Integer> kebutuhanAmunisi : oBarangPilihan.getIdAmunisiUtama().entrySet()) {

        }
    }

    private void gantiPeluru(Barang oBarangPilihan){
        for (Map.Entry<String, ArrayList<Integer>> oDaftarIdAmunisi : oBarangPilihan.getDaftarIdAmunisi().entrySet()) {
            System.out.println();
            for(int i=0; i<oDaftarIdAmunisi.getValue().size(); i++){
                System.out.println();
                System.out.printf("%2d. %s\n", i+1, oDaftarIdAmunisi.getValue().get(i));
            }
            System.out.printf("%2d. Batal ganti peluru\n", 0);
            System.out.printf("Masukkan pilihan : \n");
            Scanner oScan = new Scanner(System.in);
            int pilihanGantiAmunisi = oScan.nextInt();

            /* jika pilihan == 0 maka jangan lakukan apapun */
            if(pilihanGantiAmunisi == 0){

            }else if(pilihanGantiAmunisi < 0 || pilihanGantiAmunisi > oDaftarIdAmunisi.getValue().size()){
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia ]");
            }else{
                oBarangPilihan.gantiPeluru(pilihanGantiAmunisi-1);

                System.out.println();
                System.out.println("[ Peluru utama senjata telah diganti ]");
            }
        }

        /* jika kosong maka */
        if(oBarangPilihan.getDaftarIdAmunisi().isEmpty()){
            System.out.println();
            System.out.println("[ Daftar amunisi kosong ]");
        }
    }

    private void buangSenjata(Barang oBarangPilihan, String kategori , ArrayList<Integer> oBarang, ArrayList<Barang> oSenjataLempar){
        if(oBarangPilihan.getJenis().equals("Senjata Pukul") || oBarangPilihan.getJenis().equals("Senjata Tembak")){
            this.oAdegan.oPlayer.hapusBarangSatuIndeksTertentu(kategori, oBarang.get(0), oBarang.get(1));
            System.out.println();
            System.out.println("[ Senjata telah dihapus. ]");

            this.validasiKembali2 = true;
        }else{
            System.out.println();
            System.out.printf("%2d. Batal membuang senjata\n", 0);
            System.out.printf("Masukkan jumlah senjata yang akan dibuang (dihapus) : ");
            Scanner oScan = new Scanner(System.in);
            int jumlahHapus = oScan.nextInt();

            /* jika 0 maka tidak melakukan apapun */
            if(jumlahHapus == 0){

            }else if(jumlahHapus < 0 || jumlahHapus > oSenjataLempar.size()){
                System.out.println();
                System.out.println("[ jumlah tidak valid ]");
            }else{
                this.oAdegan.oPlayer.hapusBarangJumlahTertentu(oBarangPilihan.getKategoriPenyimpanan(), oBarangPilihan.getIdBarang(), jumlahHapus);
                System.out.println();
                System.out.println("[ Senjata telah dihapus/dikurangi. ]");
                if(jumlahHapus == oSenjataLempar.size()){
                    this.validasiKembali2 = true;
                }
            }
        }
    }
}
