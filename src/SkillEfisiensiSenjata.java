public class SkillEfisiensiSenjata extends Skill{
    int efisiensiSenjata;

    SkillEfisiensiSenjata(Player oPlayer, int idSkill, String nama, int idLevelDibutuhkan, int nilaiLevelDibutuhkan, String deskripsi, int efisiensiSenjata){
        super(oPlayer, idSkill, nama, idLevelDibutuhkan, nilaiLevelDibutuhkan, deskripsi);
        this.efisiensiSenjata = efisiensiSenjata;
    }

    @Override
    protected void unlockSkill(Level level, int point) {
        if(this.idLevelDibutuhkan == level.idLevel && this.nilaiLevelDibutuhkan == level.nilaiLevel && this.pointDibutuhkan == point){
            //proses return blueprint kepada player yang meng-unlock skill ini
            oPlayer.ubahEfisiensiSenjata(efisiensiSenjata);
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
