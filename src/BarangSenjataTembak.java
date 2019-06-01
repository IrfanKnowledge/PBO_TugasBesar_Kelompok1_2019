import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarangSenjataTembak extends BarangSenjata{

    /* private karena hanya untuk set, get tertentu, dan untuk proses internal */
    private BarangSenjata amunisiUtama;

    /* private karena hanya digunakan untuk tambah dan get (+ telah dimodifikasi) dan proses internal */
    private HashMap<Integer, BarangSenjata> daftarAmunisiCocok = new HashMap<>();

    /* private karena hanya digunakan untuk tambah dan get (+ telah dimodifikasi)dan proses internal*/
    private ArrayList<BarangSenjata> daftarAmunisiSedangDigunakan = new ArrayList<>();

    /* private karena memiliki batas minimal nilai */
    private int batasMaxAmunisiDigunakan;

    BarangSenjataTembak(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                        boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                        int kekuatan, int batasMaxAmunisiDigunakan){
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusJual, statusBeli, hargaJual, hargaBeli, kekuatan);

        this.setBatasMaxAmunisiDigunakan(batasMaxAmunisiDigunakan);
    }

    public void setAmunisiUtama(BarangSenjata oBarang){
        this.amunisiUtama = oBarang.cloning();

        if(!this.daftarAmunisiCocok.containsKey(oBarang.idBarang)){
            this.tambahAmunisiCocok(oBarang);
        }
    }

    public int getIdAmunisiUtama(){
        return this.amunisiUtama.idBarang;
    }

    public String namaAmunisiUtama(){
        return this.amunisiUtama.nama;
    }

    public String getKategoriAmunisiUtama() {
        return this.amunisiUtama.kategoriBarang;
    }

    public void tambahAmunisiCocok(BarangSenjata oBarang) {
        this.daftarAmunisiCocok.put(oBarang.idBarang, oBarang.cloning());
    }

    private HashMap<Integer, BarangSenjata> cloningDaftarAmunisiCocok(HashMap<Integer, BarangSenjata> daftarAmunisiCocok){
        HashMap<Integer, BarangSenjata> temp = new HashMap<>();
        for (Map.Entry<Integer, BarangSenjata> isi : daftarAmunisiCocok.entrySet()) {
            temp.put(isi.getKey(), isi.getValue().cloning());
        }
        return temp;
    }

    /* HashMap input dari parameter berikut tidak dihapus sebab
    *  tujuan atribut daftarAmunisiCocok pada class ini hanya untuk mengetahui
    *  kebutuhan jenis amunisi yang cocok untuk digunakan senjata ini */
    public void tambahAmunisiCocok(HashMap<Integer, BarangSenjata> daftarAmunisiCocok){
        this.daftarAmunisiCocok.putAll(this.cloningDaftarAmunisiCocok(daftarAmunisiCocok));
    }

    public HashMap<Integer, BarangSenjata> getDaftarAmunisiCocok() {
        /* object HashMap dibedakan agar tidak dapat memanipulasi daftarAmunisiCocok diluar Class ini,
        *  selain hanya bisa menggunakan method khusus untuk menambahkan object pada daftarAmunisiCocok,
        *  kemudian isi HashMap tersebut dibuat object berbeda agar tidak dapat dimanipulasi diluar Class ini */
        return this.cloningDaftarAmunisiCocok(this.daftarAmunisiCocok);
    }

    public boolean validasiAmunisiSedangDigunakan(BarangSenjata oAmunisi){
        if(getJumlahKebutuhanIsiAmunisi() == 0){
            System.out.println();
            System.out.println(String.format("[ Jumlah amunisi %s masih penuh ]\n", this.nama));

            return false;
        }else if(oAmunisi.idBarang != this.amunisiUtama.idBarang){
            System.out.println();
            System.out.println("[ Jenis amunisi yang digunakan tidak cocok ]");
            System.out.println(String.format("[ Dibutuhkan %s (%s)]", this.amunisiUtama.nama, this.amunisiUtama.kategoriBarang));

            return false;
        }
        return true;
    }

    public void tambahAmunisi(BarangSenjata oAmunisi) {
        this.daftarAmunisiSedangDigunakan.add(oAmunisi.cloning());
    }

    public void tambahAmunisi(BarangSenjata oAmunisi, int jumlahInstance) {
        if(jumlahInstance > this.getJumlahKebutuhanIsiAmunisi()){
            jumlahInstance = this.getJumlahKebutuhanIsiAmunisi();
        }
        this.daftarAmunisiSedangDigunakan.addAll(Cloning.cloning(oAmunisi, jumlahInstance));
    }

    public void tambahAmunisi(ArrayList<BarangSenjata> oDaftarAmunisi){
        if(oDaftarAmunisi.isEmpty()){
            System.out.println();
            System.out.println("[ Daftar amunisi yang akan digunakan kosong ]");
        }else{
            int pengulanganIsiAmunisi;
            if(this.getJumlahKebutuhanIsiAmunisi() - oDaftarAmunisi.size() <= 0) {
                pengulanganIsiAmunisi = this.getJumlahKebutuhanIsiAmunisi();
            }else{
                pengulanganIsiAmunisi = oDaftarAmunisi.size();
            }
            /* ArrayList menerapkan Linked List sehingga dapat FIFO (first in first out) */
            for(int i=0; i<pengulanganIsiAmunisi; i+=0){

                /* selalu di cek karena dalam list inputan bisa saja terdapat amunisi yang berbeda Id */
                if(oDaftarAmunisi.get(i).idBarang != this.amunisiUtama.idBarang){
                    System.out.println();
                    System.out.println(String.format("[ Telah bertambah sebanyak %d ]"));
                    System.out.println("[ Terdapat jenis amunisi yang digunakan tidak cocok ]");
                    System.out.println(String.format("[ Dibutuhkan %s (%s)]", this.amunisiUtama.nama, this.amunisiUtama.kategoriBarang));

                    /* keluar dari for */
                    break;
                }
                this.daftarAmunisiSedangDigunakan.add(oDaftarAmunisi.get(i));
                oDaftarAmunisi.remove(i);
            }
        }
    }

    public int getJumlahAmunisiDigunakan(){
        return this.daftarAmunisiSedangDigunakan.size();
    }

    public void setBatasMaxAmunisiDigunakan(int batasMaxAmunisiDigunakan) {
        if(batasMaxAmunisiDigunakan <= 0){
            this.batasMaxAmunisiDigunakan = 1;
        }else{
            this.batasMaxAmunisiDigunakan = batasMaxAmunisiDigunakan;
        }
    }

    public int getJumlahKebutuhanIsiAmunisi(){
        return this.batasMaxAmunisiDigunakan - this.daftarAmunisiSedangDigunakan.size();
    }

    public int getBatasMaxAmunisiDigunakan(){
        return batasMaxAmunisiDigunakan;
    }

    public BarangSenjataTembak cloning() {
        BarangSenjataTembak barangCloning = new BarangSenjataTembak(this.idBarang, this.nama, this.kategoriBarang ,this.deskripsi,
                this.statusJual, this.statusBeli, this.getHargaJual(), this.getHargaBeli(),
                this.getKekuatan(), this.batasMaxAmunisiDigunakan);

        barangCloning.setAmunisiUtama(this.amunisiUtama);
        barangCloning.tambahAmunisiCocok(this.daftarAmunisiCocok);
        barangCloning.tambahAmunisi(this.daftarAmunisiSedangDigunakan.get(0), this.getJumlahAmunisiDigunakan());

        return barangCloning;
    }

    public BarangSenjata gunakanBarangSenjata() {
        if(this.daftarAmunisiSedangDigunakan.size() < 1){
            //proses gagal atau dianggap tidak bisa menembak karena habis amunisi
            return null;
        }else{
            //memindahkan amunisi ke variable sementara
            BarangSenjata oAmunisi = this.daftarAmunisiSedangDigunakan.get(0);

            //amunisi dalam senjata berkurang 1
            this.daftarAmunisiSedangDigunakan.remove(0);

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
            if(inputPilihan < this.daftarAmunisiCocok.size() || inputPilihan > this.daftarAmunisiCocok.size()){
                System.out.println("[ pilihan tidak tersedia ]");
                System.out.println();
            }else{
                int i=0;
                for (Map.Entry<Integer, BarangSenjata> oDaftarAmunisiCocok : this.daftarAmunisiCocok.entrySet()) {
                    if(i == inputPilihan-1){
                        this.amunisiUtama = oDaftarAmunisiCocok.getValue();

                        /* keluar dari for */
                        break;
                    }
                    i++;
                }
            }
        }
    }

    public ArrayList<BarangSenjata> tukarDaftarAmunisi(ArrayList<BarangSenjata> oDaftarAmunisiSedangDigunakan){
        ArrayList<BarangSenjata> temp = new ArrayList<>();
        temp.addAll(this.daftarAmunisiSedangDigunakan);
        this.daftarAmunisiSedangDigunakan.clear();
        this.tambahAmunisi(oDaftarAmunisiSedangDigunakan);

        return temp;
    }
}
