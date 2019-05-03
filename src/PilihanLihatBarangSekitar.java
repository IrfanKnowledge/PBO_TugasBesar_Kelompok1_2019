import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihanLihatBarangSekitar extends Pilihan {

    private Adegan oAdegan;

    PilihanLihatBarangSekitar(String dekripsi, Adegan oAdegan) {
        super(dekripsi);
        this.oAdegan = oAdegan;
    }

    @Override
    public void aksi() {
        HashMap<String, ArrayList<ArrayList<Barang>>> oKategoriBarang = this.oAdegan.lihatBarangSekitar();
        if(oKategoriBarang != null){

            /*  Berikut algoritma menyeleksi senjata yang bisa di ambil */
            HashMap<String, ArrayList<ArrayList<Barang>>> tempList3 = new HashMap<>();
            for (Map.Entry<String, ArrayList<ArrayList<Barang>>> isiList3 : oKategoriBarang.entrySet()) {

                /* Jika kategorinya bukan senjata, ambil semua */
                if(!isiList3.getValue().equals("Senjata")){
                    tempList3.put(isiList3.getKey(), isiList3.getValue());

                    /*  Hapus kategori ini */
                    oKategoriBarang.remove(isiList3.getKey());

                /* Jika kategorinya adalah senjata maka... kita seleksi */
                }else{

                    /* untuk menampung list 2 dimensi */
                    ArrayList<ArrayList<Barang>> tempList2 = new ArrayList<>();

                    /* Untuk acuan perbandingan dengan jumlah slot senjata kosong pada Player yang tersedia */
                    int jumlahAmbil = 0;

                    /* Jika jumlah slot senjata kosong pada player tidak 0 atau tidak penuh maka...*/
                    if(this.oAdegan.oPlayer.getJumlahSlotSenjataKosong() != 0){
                        /* Pengulangan mengeluarkan isiList2 yang berupa List 1 dimensi dimulai dari urutan terakhir pada isiList 2 dimensi */
                        for (int i=isiList3.getValue().size()-1; i>=0; i--)   {

                            /* Untuk menampung list 1 dimensi */
                            ArrayList<Barang> tempList1 = new ArrayList<>();

                            /* Pengulangan mengeluarkan isi List 1 dimensi dimulai dari urutan terakhir*/
                            for(int j=isiList3.getValue().get(i).size()-1; j>=0; j--){

                                tempList1.add(isiList3.getValue().get(i).get(j));
                                jumlahAmbil++;

                                /* hapus barang yang telah diambil */
                                isiList3.getValue().get(i).remove(j);

                                /* jika sudah memenuhi jumlah slot kosong yang tersedia keluar dari foreach ini */
                                if(jumlahAmbil == this.oAdegan.oPlayer.getJumlahSlotSenjataKosong()){
                                    break;
                                }
                            }
                            tempList2.add(tempList1);

                            /* Hapus isi urutuan List 2 dimensi yang telah kosong*/
                            if(isiList3.getValue().get(i).isEmpty()){
                                isiList3.getValue().remove(i);
                            }

                            /* jika sudah memenuhi jumlah slot kosong yang tersedia keluar dari foreach ini */
                            if(jumlahAmbil == this.oAdegan.oPlayer.getJumlahSlotSenjataKosong()){
                                break;
                            }
                        }

                        tempList3.put("Senjata", tempList2);
                    }
                }
            }

            this.oAdegan.oPlayer.tambahBarang(tempList3);
        }

    }
}
