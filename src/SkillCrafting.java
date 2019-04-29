public class SkillCrafting extends Skill {
    private Barang blueprint;

    SkillCrafting(Player oPlayer, int idSkill, String nama, int idLevelDibutuhkan, int nilaiLevelDibutuhkan, String deskripsi, Barang blueprint){
        super(oPlayer, idSkill, nama, idLevelDibutuhkan, nilaiLevelDibutuhkan, deskripsi);
        this.blueprint = blueprint;
    }

    @Override
    public Barang unlockSkillBarangBluePrint(Level level, int point) {
        if(this.idLevelDibutuhkan == level.idLevel && this.nilaiLevelDibutuhkan == level.nilaiLevel && this.pointDibutuhkan == point){
            //proses return blueprint kepada player yang meng-unlock skill ini
            this.statusUnlock = true;
        }else{
            System.out.printf("Maaf, nilai level %s anda atau point masih kurang\n", level.nama);
        }
        return this.blueprint;
    }

    @Override
    protected boolean getStatusUnlock() {
        return super.getStatusUnlock();
    }
}
