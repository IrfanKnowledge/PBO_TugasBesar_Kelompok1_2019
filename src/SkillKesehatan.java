public class SkillKesehatan extends Skill{
    int batasMaxKesehatan;

    SkillKesehatan(Player oPlayer, int idSkill, String nama, int idLevelDibutuhkan, int nilaiLevelDibutuhkan, String deskripsi, int batasMaxKesehatan){
        super(oPlayer, idSkill, nama, idLevelDibutuhkan, nilaiLevelDibutuhkan, deskripsi);
        this.batasMaxKesehatan = batasMaxKesehatan;
    }

    @Override
    public void unlockSkill(Level level, int point) {
        if(this.idLevelDibutuhkan == level.idLevel && this.nilaiLevelDibutuhkan == level.nilaiLevel && this.pointDibutuhkan == point){
            //proses return blueprint kepada player yang meng-unlock skill ini
            //this.oPlayer.ubahBatasMaxKesehatan(this.batasMaxKesehatan);
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
