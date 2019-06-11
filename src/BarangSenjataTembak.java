import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarangSenjataTembak extends BarangSenjata{

    /* private karena hanya untuk set, get tertentu, dan untuk proses internal */
    private BarangSenjata amunisiUtamaYangBisaDigunakan;

    /* private karena hanya digunakan untuk tambah dan get (+ telah dimodifikasi) dan proses internal */
    private HashMap<Integer, BarangSenjata> daftarAmunisiYangBisaDigunakan = new HashMap<>();

    /* private karena hanya digunakan untuk tambah dan get (+ telah dimodifikasi)dan proses internal*/
    private ArrayList<BarangSenjata> daftarAmunisiSedangDigunakan = new ArrayList<>();

    /* daftarSementara mengenai object barang yang berhasil mengisi amunisi */
    private ArrayList<BarangSenjata> daftarAmunisiYangBerhasilDiambilIsiAmunisi = new ArrayList<>();
    /* daftarSementara mengenai object barang yang dikeluarkan saat ganti amunisi */
    private ArrayList<BarangSenjata> daftarAmunisiSedangDigunakanYangDikeluarkan = new ArrayList<>();

    private boolean statusAmunisiBerhasilTerambil = false;
    private boolean statusAmunisiBerhasilDiganti = false;

    /* private karena memiliki batas minimal nilai */
    private int batasMaxAmunisiDigunakan;

    BarangSenjataTembak(int idBarang, String nama, String kategoriPenyimpanan, String deskripsi,
                        boolean statusDapatDigunakanAdeganTertentu,
                        boolean statusJual, boolean statusBeli, int hargaJual, int hargaBeli,
                        int kekuatan, int batasMaxAmunisiDigunakan){
        super(idBarang, nama, kategoriPenyimpanan, deskripsi, statusDapatDigunakanAdeganTertentu, true, statusJual, statusBeli, hargaJual, hargaBeli, kekuatan);

        this.setBatasMaxAmunisiDigunakan(batasMaxAmunisiDigunakan);
    }

    //===================================================================================================
    /* get kekuatan yang dimodifikasi */
    //===================================================================================================
    @Override
    public int getKekuatan() {
        if(!daftarAmunisiSedangDigunakan.isEmpty()){
            return (super.getKekuatan() + daftarAmunisiSedangDigunakan.get(0).getKekuatan());
        }
        return super.getKekuatan();
    }

    //===================================================================================================
    /* status */
    //===================================================================================================
    public boolean isStatusAmunisiBerhasilTerambil() {
        return statusAmunisiBerhasilTerambil;
    }

    public boolean isStatusAmunisiBerhasilDiganti() {
        return statusAmunisiBerhasilDiganti;
    }

    //===================================================================================================
    /* set, get, tambah amunisi yang bisa digunakan senjata ini */
    //===================================================================================================

    public void setAmunisiUtamaYangBisaDigunakan(BarangSenjata amunisi){
        /* object dibuat berbeda karena atribut ini hanya untuk
        *  mendapat informasi jenis daftar amunisi yang sedang digunakan saja */
        this.amunisiUtamaYangBisaDigunakan = amunisi.cloning();

        /* jika tidak terdapat amunisi ini pada daftarAmunisiYangBisaDigunakan maka tambahkan */
        if(!this.daftarAmunisiYangBisaDigunakan.containsKey(amunisi.idBarang)){
            this.tambahAmunisiYangDiperlukan(amunisi);
        }
    }

    public int getIdAmunisiUtamaYangBisaDigunakan(){
        if(this.amunisiUtamaYangBisaDigunakan == null){
            return -1;
        }
        return this.amunisiUtamaYangBisaDigunakan.idBarang;
    }

    public String getNamaAmunisiUtamaYangDiperlukan(){
        if(this.amunisiUtamaYangBisaDigunakan == null){
            return null;
        }
        return this.amunisiUtamaYangBisaDigunakan.nama;
    }

    public String getKategoriAmunisiUtamaYangDiperlukan() {
        if(this.amunisiUtamaYangBisaDigunakan == null){
            return null;
        }
        return this.amunisiUtamaYangBisaDigunakan.kategoriBarang;
    }

    public String getDeskripsiAmunisiUtamaYangDiperlukan() {
        if(this.amunisiUtamaYangBisaDigunakan == null){
            return null;
        }
        return this.amunisiUtamaYangBisaDigunakan.deskripsi;
    }

    public void tambahAmunisiYangDiperlukan(BarangSenjata oBarang) {
        /* object dibuat berbeda karena atribut ini hanya untuk
         *  mendapat informasi jenis daftar amunisi yang bisa digunakan saja */
        this.daftarAmunisiYangBisaDigunakan.put(oBarang.idBarang, oBarang.cloning());
    }

    private HashMap<Integer, BarangSenjata> cloningDaftarAmunisiCocok(HashMap<Integer, BarangSenjata> daftarAmunisiDiperlukan){
        HashMap<Integer, BarangSenjata> temp = new HashMap<>();
        for (Map.Entry<Integer, BarangSenjata> isi : daftarAmunisiDiperlukan.entrySet()) {
            temp.put(isi.getKey(), isi.getValue().cloning());
        }
        return temp;
    }

    /* isi object hashmap dibuat berebeda,
    *  sebab tujuan atribut daftarAmunisiYangBisaDigunakan pada class ini hanya untuk mengetahui
    *  kebutuhan jenis amunisi yang cocok/bisaDigunakan/sesuai untuk senjata ini */
    public void tambahAmunisiYangDiperlukan(HashMap<Integer, BarangSenjata> daftarAmunisiDiperlukan){
        if(this.amunisiUtamaYangBisaDigunakan == null){
            for (Map.Entry<Integer, BarangSenjata> amunisiUtama : daftarAmunisiDiperlukan.entrySet()) {
                this.amunisiUtamaYangBisaDigunakan = amunisiUtama.getValue();
                break;
            }
        }
        this.daftarAmunisiYangBisaDigunakan.putAll(this.cloningDaftarAmunisiCocok(daftarAmunisiDiperlukan));
    }

    public HashMap<Integer, BarangSenjata> getDaftarAmunisiYangBisaDigunakan() {
        /* isi object hashmap dibuat berebeda,
         *  sebab tujuan atribut daftarAmunisiYangBisaDigunakan pada class ini hanya untuk mengetahui
         *  kebutuhan jenis amunisi yang cocok/bisaDigunakan/sesuai untuk senjata ini */
        return this.cloningDaftarAmunisiCocok(this.daftarAmunisiYangBisaDigunakan);
    }

    //===================================================================================================
    /* get daftar amunisi untuk menampung sementara peluru yang berhasil masuk dan dikeluarkan */
    //===================================================================================================
    public ArrayList<BarangSenjata> getDaftarAmunisiYangBerhasilDiambilIsiAmunisi() {
        return daftarAmunisiYangBerhasilDiambilIsiAmunisi;
    }

    public ArrayList<BarangSenjata> getDaftarAmunisiSedangDigunakanYangDikeluarkan() {
        return daftarAmunisiSedangDigunakanYangDikeluarkan;
    }

    //===================================================================================================
    /* set dan get daftar amunisi yang sedang digunakan */
    //===================================================================================================
    public void setBatasMaxAmunisiDigunakan(int batasMaxAmunisiDigunakan) {
        if(batasMaxAmunisiDigunakan <= 0){
            this.batasMaxAmunisiDigunakan = 1;
        }else{
            this.batasMaxAmunisiDigunakan = batasMaxAmunisiDigunakan;
        }
    }

    public int getBatasMaxAmunisiDigunakan(){
        return batasMaxAmunisiDigunakan;
    }

    public int getJumlahAmunisiSedangDigunakan(){
        return this.daftarAmunisiSedangDigunakan.size();
    }

    public int getJumlahKebutuhanIsiAmunisi(){
        return this.batasMaxAmunisiDigunakan - this.daftarAmunisiSedangDigunakan.size();
    }

    //===================================================================================================
    /* Isi amunisi berdasarkan daftarAmunisiYangBisaDigunakan yang telah ditentukan */
    //===================================================================================================

    private void prosesIsiAmunisi(ArrayList<BarangSenjata> daftarAmunisiInput){
        this.daftarAmunisiYangBerhasilDiambilIsiAmunisi.clear();
        if(daftarAmunisiInput != null) {
            if (!daftarAmunisiInput.isEmpty()) {
                if (this.amunisiUtamaYangBisaDigunakan != null) {
                    if (this.getJumlahKebutuhanIsiAmunisi() > 0) {
                        int idIndeksAwal = daftarAmunisiInput.get(0).idBarang;
                        for (BarangSenjata amunisiInput : daftarAmunisiInput) {
                            if (amunisiInput.idBarang != this.amunisiUtamaYangBisaDigunakan.idBarang) {
                                break;
                            }
                            if (amunisiInput.idBarang != idIndeksAwal) {
                                break;
                            }
                            this.daftarAmunisiSedangDigunakan.add(amunisiInput);
                            this.daftarAmunisiYangBerhasilDiambilIsiAmunisi.add(amunisiInput);
                            if (this.getJumlahKebutuhanIsiAmunisi() <= 0) {
                                break;
                            }
                        }
                    }
                }

            }
        }
        if(this.daftarAmunisiYangBerhasilDiambilIsiAmunisi.isEmpty()){
            this.statusAmunisiBerhasilTerambil = false;
        }else{
            this.statusAmunisiBerhasilTerambil = true;
        }
    }

    public void isiAmunisi(BarangSenjata amunisiInput) {
        if(amunisiInput == null){
            this.statusAmunisiBerhasilTerambil = false;
        }else{
            ArrayList<BarangSenjata> tempDaftarAmunisiInput = new ArrayList<>();
            tempDaftarAmunisiInput.add(amunisiInput.cloning());
            this.prosesIsiAmunisi(tempDaftarAmunisiInput);
        }
    }

    public void isiAmunisi(BarangSenjata amunisiInput, int jumlahInstance) {
        if(amunisiInput == null){
            this.statusAmunisiBerhasilTerambil = false;
        }else{
            if(this.getJumlahKebutuhanIsiAmunisi() <= 0){
                this.statusAmunisiBerhasilTerambil = false;
            }else if(jumlahInstance > this.getJumlahKebutuhanIsiAmunisi()){
                jumlahInstance = this.getJumlahKebutuhanIsiAmunisi();

                ArrayList<BarangSenjata> tempDaftarAmunisiInput = new ArrayList<>();
                tempDaftarAmunisiInput.addAll(Cloning.cloning(amunisiInput, jumlahInstance));
                this.prosesIsiAmunisi(tempDaftarAmunisiInput);
            }
        }
    }

    public void isiAmunisi(ArrayList<BarangSenjata> daftarAmunisiInput){
        this.prosesIsiAmunisi(daftarAmunisiInput);
    }

    //===================================================================================================
    /* gunakan dan ganti amunisi  */
    //===================================================================================================
    public BarangSenjata gunakanSenjata() {
        if(this.daftarAmunisiSedangDigunakan == null){
            return null;
        }
        if(this.daftarAmunisiSedangDigunakan.isEmpty()){
            //proses gagal atau dianggap tidak bisa menembak karena habis amunisi
            return null;
        }
        BarangSenjata amunisiDitembakkan = this.daftarAmunisiSedangDigunakan.get(0);

        /* nilai kekuatan peluru dipengaruhi nilai kekuatan benda yang menembakkannya
         *  (misal pistol, busur dll) ketika ditembakkan atau firepower */
        amunisiDitembakkan.setKekuatan(amunisiDitembakkan.getKekuatan() + this.getKekuatan());

        //amunisi dalam senjata berkurang 1
        this.daftarAmunisiSedangDigunakan.remove(0);

        //proses menembak berhasil
        return amunisiDitembakkan;
    }

    public void gantiAmunisiYangSedangDigunakan(ArrayList<BarangSenjata> daftarAmunisiInput){
        this.daftarAmunisiSedangDigunakanYangDikeluarkan.clear();

        if(daftarAmunisiInput == null){
            this.statusAmunisiBerhasilDiganti = false;
        }else if(daftarAmunisiInput.isEmpty()){
            this.statusAmunisiBerhasilDiganti = false;
        }else if(this.daftarAmunisiYangBisaDigunakan.isEmpty()){
            this.statusAmunisiBerhasilDiganti = false;
        }else if(!this.daftarAmunisiYangBisaDigunakan.containsKey(daftarAmunisiInput.get(0).idBarang)){
            this.statusAmunisiBerhasilDiganti = false;
        }else{
            if(!this.daftarAmunisiSedangDigunakan.isEmpty()){
                this.daftarAmunisiSedangDigunakanYangDikeluarkan.addAll(this.daftarAmunisiSedangDigunakan);
            }
            this.daftarAmunisiSedangDigunakan.clear();
            this.prosesIsiAmunisi(daftarAmunisiInput);
            this.statusAmunisiBerhasilDiganti = true;
        }
    }

    //===================================================================================================
    /* setiap Class Barang dan turunan memiliki kemampuan cloning */
    //===================================================================================================
    public BarangSenjataTembak cloning() {
        BarangSenjataTembak barangCloning = new BarangSenjataTembak(this.idBarang, this.nama, this.kategoriBarang ,this.deskripsi, this.statusDapatDigunakanAdeganTertentu,
                this.statusJual, this.statusBeli, this.getHargaJual(), this.getHargaBeli(),
                this.getKekuatan(), this.batasMaxAmunisiDigunakan);

        if(this.amunisiUtamaYangBisaDigunakan != null){
            barangCloning.setAmunisiUtamaYangBisaDigunakan(this.amunisiUtamaYangBisaDigunakan);
        }
        barangCloning.tambahAmunisiYangDiperlukan(this.daftarAmunisiYangBisaDigunakan);
        if(!this.daftarAmunisiSedangDigunakan.isEmpty()){
            barangCloning.isiAmunisi(this.daftarAmunisiSedangDigunakan.get(0), this.getJumlahAmunisiSedangDigunakan());
        }

        return barangCloning;
    }

    @Override
    public void print() {
        super.print();
        System.out.printf("%-25s : %d/%d\n", "Isi Amunisi", this.getJumlahAmunisiSedangDigunakan(),
                this.getBatasMaxAmunisiDigunakan());
    }

    //        if(this.daftarAmunisiYangBisaDigunakan.isEmpty()){
//            System.out.println("[ daftarAmunisiYangBisaDigunakan masih kosong, belum didefinisikan atau ditambahkan ]");
//            System.out.println();
//        }else{
//        }
//            if(inputPilihan < this.daftarAmunisiYangBisaDigunakan.size() || inputPilihan > this.daftarAmunisiYangBisaDigunakan.size()){
//                System.out.println("[ pilihan tidak tersedia ]");
//                System.out.println();
//            }else{
//            }

//    public ArrayList<BarangSenjata> tukarDaftarAmunisi(ArrayList<BarangSenjata> oDaftarAmunisiSedangDigunakan){
//        ArrayList<BarangSenjata> temp = new ArrayList<>();
//        temp.addAll(this.daftarAmunisiSedangDigunakan);
//        this.daftarAmunisiSedangDigunakan.clear();
//        if(this.validasiAmunisiUntukMengisiAmunisi(oDaftarAmunisiSedangDigunakan)){
//            this.isiAmunisi(oDaftarAmunisiSedangDigunakan);
//        }
//        return temp;
//    }
}

//    public void isiAmunisi(ArrayList<BarangSenjata> daftarAmunisiInput){
//        int pengulanganIsiAmunisi;
//        if(this.getJumlahKebutuhanIsiAmunisi() - daftarAmunisiInput.size() <= 0) {
//            pengulanganIsiAmunisi = this.getJumlahKebutuhanIsiAmunisi();
//        }else{
//            pengulanganIsiAmunisi = daftarAmunisiInput.size();
//        }
//        /* ArrayList menerapkan Linked List sehingga dapat FIFO (first in first out) */
//        for(int i=0; i<pengulanganIsiAmunisi; i+=0){
//
//            /* selalu di cek karena dalam list inputan bisa saja terdapat amunisi yang berbeda Id */
//            if(daftarAmunisiInput.get(i).idBarang != this.amunisiUtamaYangBisaDigunakan.idBarang){
//                System.out.println();
//                System.out.println(String.format("[ Telah bertambah sebanyak %d ]"));
//                System.out.println("[ Terdapat jenis amunisi yang digunakan tidak cocok ]");
//                System.out.println(String.format("[ Dibutuhkan %s (%s)]", this.amunisiUtamaYangBisaDigunakan.nama, this.amunisiUtamaYangBisaDigunakan.kategoriBarang));
//
//                /* keluar dari for */
//                break;
//            }
//            this.daftarAmunisiSedangDigunakan.add(daftarAmunisiInput.get(i));
//            daftarAmunisiInput.remove(i);
//        }
//    }

//    public boolean validasiAmunisiUntukMengisiAmunisi(ArrayList<BarangSenjata> daftarAmunisi){
//        if(daftarAmunisi == null){
//            return false;
//        }
//        if(daftarAmunisi.isEmpty()){
////            System.out.println();
////            System.out.println("[ Daftar amunisi yang akan digunakan kosong ]");
//            return false;
//        }else if(getJumlahKebutuhanIsiAmunisi() == 0){
////            System.out.println();
////            System.out.println(String.format("[ Jumlah amunisi %s masih penuh ]\n", this.nama));
//            return false;
//        }else if(daftarAmunisi.get(0).idBarang != this.amunisiUtamaYangBisaDigunakan.idBarang){
//            System.out.println();
//            System.out.println("[ Jenis amunisi yang digunakan tidak cocok ]");
//            System.out.println(String.format("[ Dibutuhkan %s (%s)]", this.amunisiUtamaYangBisaDigunakan.nama, this.amunisiUtamaYangBisaDigunakan.kategoriBarang));
//            return false;
//        }else{
//            boolean statusAmunisi = true;
//            /* ArrayList menerapkan Linked List sehingga dapat FIFO (first in first out) */
//            for(int i=0; i<daftarAmunisi.size(); i+=0){
//                /* selalu di cek karena dalam list inputan bisa saja terdapat amunisi yang berbeda Id */
//                if(daftarAmunisi.get(i).idBarang != this.amunisiUtamaYangBisaDigunakan.idBarang){
//                    System.out.println();
//                    System.out.println("[ Terdapat jenis amunisi yang digunakan tidak cocok ]");
//                    System.out.println(String.format("[ Dibutuhkan %s (%s)]", this.amunisiUtamaYangBisaDigunakan.nama, this.amunisiUtamaYangBisaDigunakan.kategoriBarang));
//
//                    statusAmunisi = false;
//                    /* keluar dari for */
//                    break;
//                }
//            }
//            return statusAmunisi;
//        }
//    }
