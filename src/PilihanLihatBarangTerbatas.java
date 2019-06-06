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
            boolean validasiKembaliKeDaftarBarangTerbatas = false;
            while(!validasiKembaliKeDaftarBarangTerbatas){
                ArrayList<Barang> daftarBarangTerpilih = this.oAdegan.oPlayer.pilihBarangDariDaftarBarangTerbatas(this.dekripsi);
                if(daftarBarangTerpilih == null){
                    validasiKembaliKeDaftarBarangTerbatas = true;
                    validasiKembaliKeLihatIsiKantong = true;
                }else if(daftarBarangTerpilih.isEmpty()){
                    validasiKembaliKeDaftarBarangTerbatas = true;
                    validasiKembaliKeLihatIsiKantong = true;
                }else{
                    Barang barangTerpilih = daftarBarangTerpilih.get(0);
                    System.out.println();
                    System.out.println("Aksi : " + this.dekripsi);
                    System.out.println();
                    System.out.printf("%-25s : %s\n", "nama", barangTerpilih.nama);
                    System.out.printf("%-25s : %s\n", "Deskripsi", barangTerpilih.deskripsi);
                    System.out.printf("%-25s : %s\n", "Harga beli", barangTerpilih.getHargaBeli());
                    System.out.printf("%-25s : %s\n", "Harga jual", barangTerpilih.getHargaJual());

                    if(barangTerpilih instanceof BarangSenjataTembak){
                        PilihanLihatDeskripsiSenjataTembak oPilihanLihatDeskripsiSenjataTembak = new PilihanLihatDeskripsiSenjataTembak(this.dekripsi + "(senjata tembak)", this.oAdegan, ((BarangSenjataTembak) barangTerpilih));
                        oPilihanLihatDeskripsiSenjataTembak.aksi();
                    }else if(barangTerpilih instanceof BarangSenjataJarakDekat){
                        PilihanLihatDeskripsiSenjataJarakDekat oPilihanLihatDeskripsiSenjataJarakDekat = new PilihanLihatDeskripsiSenjataJarakDekat(this.dekripsi + "(senjata jarak dekat)", this.oAdegan, (BarangSenjataJarakDekat) barangTerpilih);
                        oPilihanLihatDeskripsiSenjataJarakDekat.aksi();
                    }else if(barangTerpilih instanceof BarangSenjata){
                        PilihanLihatDeskripsiSenjata oPilihanLihatDeskripsiSenjata = new PilihanLihatDeskripsiSenjata(this.dekripsi + "(senjata lempar / amunisi)", this.oAdegan, daftarBarangTerpilih);
                        oPilihanLihatDeskripsiSenjata.aksi();
                    }else if(barangTerpilih instanceof BarangPenggunaanPadaDiri){
                        PilihanLihatDeskripsiBarangPenggunaanPadaDiri oPilihanLihatDeskripsiBarangPenggunaanPadaDiri = new PilihanLihatDeskripsiBarangPenggunaanPadaDiri(this.dekripsi + "(penggunaan pada diri)", this.oAdegan, (BarangPenggunaanPadaDiri) barangTerpilih);
                        oPilihanLihatDeskripsiBarangPenggunaanPadaDiri.aksi();
                    }

                    switch(oScan.nextInt()){
                        case 0:
                            validasiKembaliKeDaftarBarangTerbatas = true;
                            break;
                        case 1:

                        case 2:
                            /* opsi perbaiki senjata jika barang => Class BarangSenjataJarakDekat */
                            if(barangTerpilih instanceof BarangSenjataJarakDekat){
                                this.perbaikiSenjata(((BarangSenjataJarakDekat) barangTerpilih));

                                /* opsi isi peluru jika barang adalah Class BarangSenjatTembak */
                            }else if(barangTerpilih instanceof BarangSenjataTembak){
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
