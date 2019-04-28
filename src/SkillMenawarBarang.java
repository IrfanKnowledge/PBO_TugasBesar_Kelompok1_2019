public class SkillMenawarBarang extends Skill {
    int kemampuanMenawarBarang;

    SkillMenawarBarang(Player oPlayer, int idSkill, String nama, int idLevelDibutuhkan, int nilaiLevelDibutuhkan, String deskripsi, int kemampuanMenawarBarang){
        super(oPlayer, idSkill, nama, idLevelDibutuhkan, nilaiLevelDibutuhkan, deskripsi);
        this.kemampuanMenawarBarang = kemampuanMenawarBarang;
    }

    @Override
    protected void unlockSkill(Level level, int point) {
        if(this.idLevelDibutuhkan == level.idLevel && this.nilaiLevelDibutuhkan == level.nilaiLevel && this.pointDibutuhkan == point){
            //proses return blueprint kepada player yang meng-unlock skill ini
            oPlayer.ubahKemampuanMenawarBarang(kemampuanMenawarBarang);
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
