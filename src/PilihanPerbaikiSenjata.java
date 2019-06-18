public class PilihanPerbaikiSenjata extends Pilihan {
    private Adegan oAdegan;
    BarangSenjataJarakDekat senjataJarakDekatTerpilih;
    PilihanPerbaikiSenjata(String dekripsi, Adegan oAdegan, BarangSenjataJarakDekat senjataJarakDekatTerpilih) {
        super(dekripsi);
        this.oAdegan = oAdegan;
        this.senjataJarakDekatTerpilih = senjataJarakDekatTerpilih;
    }

    @Override
    public void aksi() {
        if(!senjataJarakDekatTerpilih.isStatusKemampuanDiperbaiki()){
            System.out.println();
            System.out.println("[ Senjata sudah tidak bisa diperbaiki ]");
        }else if(senjataJarakDekatTerpilih.isStatusKetahananMasihMaksimal()){
            System.out.println();
            System.out.println(String.format("[ %s ", senjataJarakDekatTerpilih.nama) + "masih memiliki ketahanan 100% ]");
        }else{
            /* ambil kebutuhan komponen Crafting untuk perbaikan */
            Barang komponenCrafting = this.oAdegan.oPlayer.getPengelolaanBarang().pilihBarangDariDaftarBarangTidakTerbatas(senjataJarakDekatTerpilih.getKategoriBarangUntukPerbaikan(), senjataJarakDekatTerpilih.getIdBarangUntukPerbaikan());
            if(komponenCrafting != null){
                /* proses memperbaiki barang */
                senjataJarakDekatTerpilih.perbaikiBarang(komponenCrafting);
                if(!senjataJarakDekatTerpilih.isStatusPerbaikiBarangBerhasil()){
                    System.out.println();
                    System.out.println("[ Perbaiki senjata gagal (id barang komponen tidak sesuai) ]");
                }else{
                    System.out.println();
                    System.out.println("[ Perbaiki senjata berhasil ]");
                }
                /* hapus satu komponen crafting tersebut di penyimpanan */
                this.oAdegan.oPlayer.getPengelolaanBarang().hapusBarangDariPenyimpananTidakTerbatas(komponenCrafting);
            }else{
                System.out.println();
                System.out.println("[ Persediaan Komponen Crafting untuk perbaikan senjata, kosong. ]");
            }
        }
    }
}
