import java.util.ArrayList;
import java.util.Scanner;

public abstract class Pilihan {
    public String dekripsi;
    public boolean kembaliKeMenuSebelumnya;

    Pilihan(String dekripsi){
        this.dekripsi = dekripsi;
    }

    public void aksi(){}

    public String getDekripsi() {
        return dekripsi;
    }

    public void pemilihan(ArrayList<Pilihan> daftarPilihan){
        System.out.printf("%2d. Kembali\n", 0);
        System.out.print("Masukkan Pilihan : ");
        Scanner oScan = new Scanner(System.in);
        int input = oScan.nextInt();
        if(input < 0 || input > daftarPilihan.size()){
            System.out.println();
            System.out.println("[ Pilihan tidak tersedia. ]");
        }else if(input == 0){
            this.kembaliKeMenuSebelumnya = true;
        }else{
            daftarPilihan.get(input-1).aksi();
            this.kembaliKeMenuSebelumnya = daftarPilihan.get(input-1).kembaliKeMenuSebelumnya;
        }
    }

    public void printDaftarPilihan(ArrayList<Pilihan> daftarPilihan){
        int i = 0;
        for (Pilihan oPilihan : daftarPilihan) {
            System.out.printf("%2d. %-25s\n", i+1, oPilihan.dekripsi);
            i++;
        }
    }
}