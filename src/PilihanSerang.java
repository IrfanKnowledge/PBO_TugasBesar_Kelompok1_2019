import java.util.Scanner;

public class PilihanSerang extends Pilihan {
    private AdeganBertarung oAdeganBertarung;

    PilihanSerang(String deskripsi, AdeganBertarung oAdeganBertarung){
        super(deskripsi);
        this.oAdeganBertarung = oAdeganBertarung;
    }

    @Override
    public void aksi() {
        this.kembaliKeMenuSebelumnya = false;
        while(!this.kembaliKeMenuSebelumnya){
            System.out.println();
            System.out.println("Aksi : " + this.dekripsi);
            this.oAdeganBertarung.printDaftarLawan();
            System.out.printf("%2d. Kembali\n", 0);
            Scanner oScan = new Scanner(System.in);
            System.out.print("Masukkan Pilihan : ");
            int input = oScan.nextInt();
            if(input < 0 || input > this.oAdeganBertarung.getJumlahLawan()){
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia. ]");
            }else if(input == 0){
                this.kembaliKeMenuSebelumnya = true;
            }else{
                boolean statusDapatMenyerang = false;
                boolean statusSenjataBerjenisSenjataTembak = false;
                if(!this.oAdeganBertarung.oPlayer.isPlayerMenggunakanSenjata()){
                    System.out.println();
                    System.out.println("[ Tidak memilki senjata untuk menyerang ]");
                }else if(!this.oAdeganBertarung.oPlayer.isSenjataDigunakanMerupakanSenjataTembak()){
                    statusDapatMenyerang = true;
                }else if(this.oAdeganBertarung.oPlayer.isAmunisiYangDigunakanKosong()){
                    statusSenjataBerjenisSenjataTembak = true;
                    this.oAdeganBertarung.oPlayer.isiAmunisiSenjataYangDigunakan();
                    if(!this.oAdeganBertarung.oPlayer.isAmunisiYangDigunakanKosong()){
                        statusDapatMenyerang = true;
                    }
                }else{
                    statusSenjataBerjenisSenjataTembak = true;
                    statusDapatMenyerang = true;
                }
                if(statusDapatMenyerang || statusSenjataBerjenisSenjataTembak){
                    this.oAdeganBertarung.serangLawan(input);
                    if(this.oAdeganBertarung.isLawanYangDiserangMati(input)){
                        if(!this.oAdeganBertarung.isBarangDijatuhkanLawanKosong(input)){
                            System.out.println();
                            System.out.println("[ Lawan menjatuhkan barang ! ]");
                            this.oAdeganBertarung.tambahBarangPadaAdeganNormal(this.oAdeganBertarung.getBarangDijatuhkanLawan(input));
                        }
                        this.oAdeganBertarung.hapusLawanYangMati(input);
                    }
                    this.oAdeganBertarung.lawanMenyerang();
                }
            }
            if(this.oAdeganBertarung.getJumlahLawan() <= 0){
                this.kembaliKeMenuSebelumnya = true;
                this.oAdeganBertarung.adeganBertarungTelahBerakhir = true;
            }
            if(this.oAdeganBertarung.oPlayer.getKesehatan() <= 0){
                this.kembaliKeMenuSebelumnya = true;
                this.oAdeganBertarung.oPlayer.isSelesai = true;
            }
        }
    }
}
