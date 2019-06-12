import java.util.ArrayList;

public abstract class Adegan {
    /* Game ini merupakan game Single Player sehingga setiap adegan memiliki Player yang sama */
    public static Player oPlayer;

    /* Pada setiap adegan terdapat hal berikut dan memiliki kemungkinan dapat melakukan aksi penggunaan barang */
    public final int idAdegan;
    public String posisiPlayer;
    public String namaRuangan;
    public String namaLuarRuangan;
    public String namaTempat;
    public String narasi;

    /* tanpa namaLuarRuangan */
    Adegan(int idAdegan, String posisiPlayer, String namaRuangan, String namaLuarRuangan, String namaTempat, String narasi){
        this.idAdegan = idAdegan;
        this.posisiPlayer = posisiPlayer;
        this.namaRuangan = namaRuangan;
        this.namaLuarRuangan = namaLuarRuangan;
        this.namaTempat = namaTempat;
        this.narasi = narasi;
    }

    //===================================================================================================
    /* Menu Utama Adegan */
    //===================================================================================================
    public void mainkan(){
        System.out.println();
        //System.out.printf("Waktu: %1.2f\n", this.oPlayer.getWaktu());
        System.out.println("Nama : " + this.oPlayer.nama);
        System.out.println("Senjata : " + this.oPlayer.getNamaSenjataDigunakan());
        System.out.println("Kesehatan : " + this.oPlayer.getKesehatan());
        System.out.println("Daftar Efek : " + this.oPlayer.getDaftarEfekDiri());
        System.out.println("Nama Tempat : " + this.namaTempat);
        System.out.println("Narasi : " + this.narasi);
    }
}