import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarangSenjataTembak extends BarangSenjata{

    /* private karena hanya untuk set, get tertentu, dan untuk proses internal */
    private BarangSenjata amunisiUtama;

    /* private karena hanya digunakan untuk tambah dan get (+ telah dimodifikasi) dan proses internal */
    private HashMap<Integer, BarangSenjata> daftarAmunisiCocok = new HashMap<>();

    /* private karena hanya digunakan untuk tambah dan get (+ telah dimodifikasi)dan proses internal*/
    private ArrayList<BarangSenjata> daftarAmunisi = new ArrayList<>();

    /* private karena memiliki batas minimal nilai */
    private int batasMaxAmunisi;

    //constructor tanpa daftarEfek
    BarangSenjataTembak(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                        boolean statusBeli, boolean statusJual, int hargaBeli, int hargaJual,
                        int kekuatan, int batasMaxAmunisi){
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusBeli, statusJual, hargaBeli, hargaJual, kekuatan);

        this.setBatasMaxAmunisi(batasMaxAmunisi);
    }

    public void setAmunisiUtamaSenjata(BarangSenjata oBarang){
        this.amunisiUtama = oBarang;

        if(!this.daftarAmunisiCocok.containsKey(oBarang.idBarang)){
            this.tambahAmunisiCocok(oBarang);
        }
    }

    public String getKategoriAmunisiUtama() {
        return this.amunisiUtama.kategoriBarang;
    }

    public int getIdAmunisiUtama(){
        return this.amunisiUtama.idBarang;
    }

    public String namaAmunisiUtama(){
        return this.amunisiUtama.nama;
    }

    public void tambahAmunisiCocok(BarangSenjata oBarang) {
        this.daftarAmunisiCocok.put(oBarang.idBarang, oBarang);
    }

    public void tambahAmunisiCocok(HashMap<Integer, BarangSenjata> daftarAmunisiCocok){
        this.daftarAmunisiCocok.putAll(daftarAmunisiCocok);
    }

    public HashMap<Integer, BarangSenjata> getDaftarAmunisiCocok() {
        /* object HashMap dibedakan agar tidak dapat memanipulasi daftarAmunisiCocok diluar Class ini
        *  selain hanya bisa menggunakan method khusus untuk menambahkan object pada daftarAmunisiCocok */
        HashMap<Integer, BarangSenjata> temp = new HashMap<>();
        temp.putAll(this.daftarAmunisiCocok);
        return temp;
    }

    public void tambahAmunisi(BarangSenjata oAmunisi) {
        this.daftarAmunisi.add(oAmunisi);
    }

    public void tambahAmunisi(ArrayList<BarangSenjata> oDaftarAmunisi){
        if(getJumlahKebutuhanIsiAmunisi() == 0){
            System.out.println();
            System.out.println(String.format("[ Jumlah amunisi %s masih penuh ]\n", this.nama));
        }else{
            if(oDaftarAmunisi.isEmpty()){
                System.out.println();
                System.out.println("[ komponen amunisi yang akan digunakan kosong ]");
            }else{
                for(int i=oDaftarAmunisi.size()-1; i >= 0; i--){

                    if(oDaftarAmunisi.get(i).idBarang != this.amunisiUtama.idBarang){
                        System.out.println();
                        System.out.println("[ Bukan amunisi yang sedang digunakan ]");
                        System.out.println(String.format("[ Dibutuhkan %s (%s)]", this.amunisiUtama.nama, this.amunisiUtama.kategoriBarang));

                        /* keluar dari for */
                        break;
                    }

                    this.tambahAmunisi(oDaftarAmunisi.get(i));
                    oDaftarAmunisi.remove(i);

                    if(getJumlahKebutuhanIsiAmunisi() == 0){
                        /* keluar dari for */
                        break;
                    }
                }
            }
        }
        this.daftarAmunisi.addAll(oDaftarAmunisi);
    }

    public int getJumlahAmunisi(){
        return this.daftarAmunisi.size();
    }

    public int getJumlahKebutuhanIsiAmunisi(){
        return this.batasMaxAmunisi - this.daftarAmunisi.size();
    }

    public void setBatasMaxAmunisi(int batasMaxAmunisi) {
        if(batasMaxAmunisi <= 0){
            this.batasMaxAmunisi = 1;
        }else{
            this.batasMaxAmunisi = batasMaxAmunisi;
        }
    }

    public int getBatasMaxAmunisi(){
        return batasMaxAmunisi;
    }

    private BarangSenjataTembak prosesCloning(BarangSenjataTembak oBarang){
        BarangSenjataTembak barangCloning = new BarangSenjataTembak(oBarang.idBarang, oBarang.nama, oBarang.kategoriBarang ,oBarang.deskripsi,
                oBarang.statusJual, oBarang.statusBeli, oBarang.getHargaJual(), oBarang.getHargaBeli(),
                oBarang.getKekuatan(), oBarang.batasMaxAmunisi);

        setAmunisiUtamaSenjata(oBarang.amunisiUtama);
        this.tambahAmunisiCocok(oBarang.daftarAmunisiCocok);
        this.tambahAmunisi(oBarang.daftarAmunisi);

        return barangCloning;
    }

    public BarangSenjataTembak cloning() {
        return prosesCloning(this);
    }

    public BarangSenjata gunakanBarangSenjata() {
        if(this.daftarAmunisi.size() < 1){
            //proses gagal atau dianggap tidak bisa menembak karena habis amunisi
            return null;
        }else{
            //memindahkan amunisi ke variable sementara
            BarangSenjata oAmunisi = this.daftarAmunisi.get(this.daftarAmunisi.size()-1);

            //amunisi dalam senjata berkurang 1
            this.daftarAmunisi.remove(this.daftarAmunisi.size()-1);

            /* nilai kekuatan peluru dipengaruhi nilai kekuatan benda yang menembakkannya (misal pistol, busur dll)
            *  ketika ditembakkan */
            oAmunisi.setKekuatan(oAmunisi.getKekuatan() + this.getKekuatan());

            //proses menembak berhasil
            return oAmunisi;
        }
    }

    public void gantiAmunisiUtama(int inputPilihan){
        if(this.daftarAmunisiCocok.isEmpty()){
            System.out.println("[ daftarAmunisiCocok masih kosong, belum didefinisikan atau ditambahkan ]");
            System.out.println();
        }else{
            int i=0;
            for (Map.Entry<Integer, BarangSenjata> oDaftarAmunisiUtama : this.daftarAmunisiCocok.entrySet()) {
                if(i == inputPilihan-1){
                    this.setAmunisiUtamaSenjata(oDaftarAmunisiUtama.getValue());

                    /* keluar dari for */
                    break;
                }
                i++;
            }
        }
    }

    public ArrayList<BarangSenjata> tukarDaftarAmunisi(ArrayList<BarangSenjata> oDaftarAmunisi){
        ArrayList<BarangSenjata> temp = new ArrayList<>();
        temp.addAll(this.daftarAmunisi);
        this.daftarAmunisi.clear();
        this.daftarAmunisi.addAll(oDaftarAmunisi);

        return temp;
    }
}
