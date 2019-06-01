import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PilihanLihatBarangSenjata extends Pilihan {
    private Adegan oAdegan;
    boolean validasiKembali2;

    PilihanLihatBarangSenjata(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {

        /* untuk kembali ke menu melihat isi kantong */
        boolean validasiKembali1 = false;
        while (!validasiKembali1){

            HashMap<String, ArrayList<Integer>> temp = this.oAdegan.oPlayer.pilihKategoriIdIndeksBarang("Senjata");
            if(temp != null){

                /* untuk kembali ke menu melihat daftar senjata */
                this.validasiKembali2 = false;
                while (!this.validasiKembali2){

                    /* ambil key dan value dari hasil memilih senjata */
                    for (Map.Entry<String, ArrayList<Integer>> oBarang : temp.entrySet()) {
                        Barang oBarangPilihan = this.oAdegan.oPlayer.pilihBarangSatu(oBarang.getKey(), oBarang.getValue().get(0), oBarang.getValue().get(1));

                        /* khusus jika memilih senjata lempar */
                        ArrayList<Barang> oSenjataLempar =  this.oAdegan.oPlayer.pilihBarangSemuanya(oBarang.getKey(), oBarang.getValue().get(0));

                        System.out.println();
                        System.out.println("Aksi : Melihat deskripsi senjata");

                        System.out.println();
                        if(oBarangPilihan.getJenis().equals("Senjata Pukul")){
                            System.out.printf("%-25s : %s\n", "nama", oBarangPilihan.nama);
                            System.out.printf("%-25s : %s\n", "Deskripsi", oBarangPilihan.deskripsi);
                            System.out.printf("%-25s : %s\n", "Harga beli", oBarangPilihan.getHargaBeli());
                            System.out.printf("%-25s : %s\n", "Harga jual", oBarangPilihan.getHargaJual());
                            System.out.printf("%-25s : %d\n", "Kekuatan", oBarangPilihan.getKekuatan());
                            System.out.printf("%-25s : %d\n", "Ketahanan", oBarangPilihan.getKetahanan());
                            System.out.printf("%-25s : %s\n", "Kemampuan diperbaiki", oBarangPilihan.jumlahKemampuanDiperbaiki());
                        }else if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
                            System.out.printf("%-12s : %s\n", "nama", oBarangPilihan.getNama());
                            System.out.printf("%-12s: %s\n", "Deskripsi", oBarangPilihan.getDeskripsi());
                            System.out.printf("%-12s : %s\n", "Harga beli", oBarangPilihan.getHargaBeli());
                            System.out.printf("%-12s : %s\n", "Harga jual", oBarangPilihan.getHargaJual());
                            System.out.printf("%-12s : %d\n", "Kekuatan", oBarangPilihan.getKekuatan());
                            System.out.printf("%-12s : %d / %d\n", "Isi peluru", oBarangPilihan.getJumlahPeluru(), oBarangPilihan.getBatasMaxAmunisi());
                        }
                        System.out.println();

                        System.out.printf("%2d. Gunakan Senjata\n", 1);

                        if(oBarangPilihan.getJenis().equals("Senjata Pukul")){
                            System.out.printf("%2d. %-20s\n", 2, "Perbaiki Barang");
                            System.out.printf("%2d. %-20s | (senjata akan dihapus, tidak dapat dikembalikan)\n", 3, "Buang Senjata");
                        }else if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
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
                                this.validasiKembali2 = true;
                                break;
                            case 1:
                                /* Proses gunakan senjata */
                                this.gunakanSenjata(oBarangPilihan, oSenjataLempar);
                                break;
                            case 2:
                                /* opsi perbaiki senjata jika senjata adalah jenis Senjata Pukul */
                                if(oBarangPilihan.getJenis().equals("Senjata Pukul")){
                                    this.perbaikiSenjata(oBarangPilihan);

                                /* opsi isi peluru jika senjata adalah jenis Senjata Tembak */
                                }else if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
                                    this.isiPeluru(oBarangPilihan);

                                /* Opsi membuang senjata jika senjata adalah jenis Senjata Lempar (selain Senjata Pukul dan Senjata Tembak) */
                                }else{
                                    this.buangSenjata(oBarangPilihan, oBarang.getKey(), oBarang.getValue(), oSenjataLempar);
                                }
                                break;
                            case 3:
                                /* Opsi membuang senjata jika Senjata jenis Senjata Pukul */
                                if(oBarangPilihan.getJenis().equals("Senjata Pukul")){
                                    this.buangSenjata(oBarangPilihan, oBarang.getKey(), oBarang.getValue(), oSenjataLempar);

                                /* Opsi ganti peluru jika Senjata jenis Senjata Tembak*/
                                }else if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
                                    this.gantiPeluru(oBarangPilihan);

                                }else{
                                    System.out.println();
                                    System.out.println("[ Pilihan tidak tersedia. ]");
                                }
                                break;
                            case 4:
                                /* Opsi membuang senjata jika Senjata adalah jenis Senjata Tembak */
                                if(oBarangPilihan.getJenis().equals("Senjata Tembak")){
                                    this.buangSenjata(oBarangPilihan, oBarang.getKey(), oBarang.getValue(), oSenjataLempar);

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
                    }

                }
            }else {
                validasiKembali1 = true;
            }
        }
    }

    private void gunakanSenjata(Barang oBarangPilihan, ArrayList<Barang> oSenjataLempar){
        if(oBarangPilihan.getJenis().equals("Senjata Pukul") || oBarangPilihan.getJenis().equals("Senjata Tembak")){
            this.oAdegan.oPlayer.senjata = oBarangPilihan;
            System.out.println();
            System.out.printf("[ %s berhasil digunakan ]\n", oBarangPilihan.getNama());
        }else{
            this.oAdegan.oPlayer.senjataLempar = oSenjataLempar;
            System.out.println();
            System.out.printf("[ %s berhasil digunakan ]\n", oSenjataLempar.get(0).getNama());
        }
    }

    private void perbaikiSenjata(Barang oBarangPilihan){
        for (Map.Entry<String, Integer> kebutuhanKomponen : oBarangPilihan.getKomponenUntukPerbaikan().entrySet()) {

            /* cek status kemampuan diperbaiki  */
            if(!oBarangPilihan.isStatusKemampuanDiperbaiki()){
                System.out.println();
                System.out.printf("[ %s sudah tidak bisa diperbaiki atau ketahanan masih %s ]\n", oBarangPilihan.getNama(), "100%");
            }else{
                /* ambil kebutuhan komponen Crafting untuk perbaikan */
                Barang komponenCrafting = this.oAdegan.oPlayer.pilihBarangSatu(kebutuhanKomponen.getKey(), kebutuhanKomponen.getValue(), 0);

                if(komponenCrafting != null){
                    /* proses memperbaiki barang */
                    oBarangPilihan.perbaikiBarang(komponenCrafting);

                    /* hapus satu komponen crafting tersebut di penyimpanan */
                    this.oAdegan.oPlayer.hapusBarangSatu(kebutuhanKomponen.getKey(), kebutuhanKomponen.getValue());
                }else{
                    System.out.println();
                    System.out.println("[ Persediaan Komponen Crafting untuk perbaikan senjata, kosong. ]");
                }
            }

        }
    }

    private void isiPeluru(Barang oBarangPilihan){
        /* Ambil id amunisi utama senjata tembak ini */
        for (Map.Entry<String, Integer> kebutuhanAmunisi : oBarangPilihan.getIdAmunisiUtama().entrySet()) {

            /* cek kondisi jumlah peluru senjata, masih penuh atau tidak  */
            if(oBarangPilihan.getJumlahKebutuhanIsiPeluru() == 0){
                System.out.println();
                System.out.printf("[ %s memiliki jumlah peluru yang masih penuh ]\n", oBarangPilihan.getNama());
            }else{
                ArrayList<Barang> daftarAmunisi = this.oAdegan.oPlayer.pilihBarangSemuanya(kebutuhanAmunisi.getKey(), kebutuhanAmunisi.getValue());
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
