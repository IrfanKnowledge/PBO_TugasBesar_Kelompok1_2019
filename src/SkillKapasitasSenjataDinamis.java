public class SkillKapasitasSenjataDinamis extends Skill{
    int batasMaxSenjataDinamis;

    SkillKapasitasSenjataDinamis(Player oPlayer, int idSkill, String nama, int idLevelDibutuhkan, int nilaiLevelDibutuhkan, String deskripsi, int batasMaxSenjataDinamis){
        super(oPlayer, idSkill, nama, idLevelDibutuhkan, nilaiLevelDibutuhkan, deskripsi);
        this.batasMaxSenjataDinamis = batasMaxSenjataDinamis;
    }

    @Override
    public void unlockSkill(Level level, int point) {
        if(this.idLevelDibutuhkan == level.idLevel && this.nilaiLevelDibutuhkan == level.nilaiLevel && this.pointDibutuhkan == point){
            //proses return blueprint kepada player yang meng-unlock skill ini
            //oPlayer.ubahBatasMaxSenjataDinamis(batasMaxSenjataDinamis);
            this.statusUnlock = true;
        }else{
            System.out.printf("Maaf, nilai level %s anda atau point masih kurang\n", level.nama);
        }
    }

    @Override
    protected boolean getStatusUnlock() {
        return super.getStatusUnlock();
    }
}
