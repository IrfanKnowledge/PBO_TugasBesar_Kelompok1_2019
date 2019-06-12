import java.util.Scanner;

public class PilihanSerang extends Pilihan {
    private AdeganBertarung oAdegan;

    PilihanSerang(String deskripsi, AdeganBertarung oAdegan){
        super(deskripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        this.kembaliKeMenuSebelumnya = false;
        while(!this.kembaliKeMenuSebelumnya){
            System.out.println();
            System.out.println("Aksi : " + this.dekripsi);
            this.oAdegan.printDaftarLawan();
            System.out.printf("%2d. Kembali\n", 0);
            Scanner oScan = new Scanner(System.in);
            System.out.print("Masukkan Pilihan : ");
            int input = oScan.nextInt();
            if(input < 0 || input > this.oAdegan.getJumlahLawan()){
                System.out.println();
                System.out.println("[ Pilihan tidak tersedia. ]");
            }else if(input == 0){
                this.kembaliKeMenuSebelumnya = true;
            }else{
                boolean statusDapatMenyerang = false;
                if(!this.oAdegan.oPlayer.isPlayerMenggunakanSenjata()){
                    System.out.println();
                    System.out.println("[ Tidak memilki senjata untuk menyerang ]");
                }else if(!this.oAdegan.oPlayer.isSenjataDigunakanMerupakanSenjataTembak()){
                    statusDapatMenyerang = true;
                }else if(this.oAdegan.oPlayer.isAmunisiYangDigunakanKosong()){
                    this.oAdegan.oPlayer.isiAmunisiSenjataYangDigunakan();
                    if(!this.oAdegan.oPlayer.isAmunisiYangDigunakanKosong()){
                        statusDapatMenyerang = true;
                    }
                }else{
                    statusDapatMenyerang = true;
                }
                if(statusDapatMenyerang){
                    this.oAdegan.serangLawan(input);
                    if(this.oAdegan.isLawanYangDiserangMati(input)){
                        this.oAdegan.hapusLawanYangMati(input);
                    }
                    this.oAdegan.lawanMenyerang();
                }
            }
            if(this.oAdegan.oPlayer.isSelesai()){
                this.kembaliKeMenuSebelumnya = true;
            }
        }
    }
}
