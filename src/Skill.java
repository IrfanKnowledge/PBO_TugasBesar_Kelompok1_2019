public abstract class Skill {
    protected Player oPlayer;
    protected final int idSkill;
    protected String nama;
    protected int idLevelDibutuhkan;
    protected int nilaiLevelDibutuhkan;
    protected int pointDibutuhkan = 1;
    protected String deskripsi;
    protected boolean statusUnlock = false;

    Skill(Player oPlayer, int idSkill, String nama, int idLevelDibutuhkan, int nilaiLevelDibutuhkan, String deskripsi){
        this.oPlayer = oPlayer;
        this.idSkill = idSkill;
        this.nama = nama;
        this.idLevelDibutuhkan = idLevelDibutuhkan;
        this.nilaiLevelDibutuhkan = nilaiLevelDibutuhkan;
        this.deskripsi = deskripsi;
    }

    protected void unlockSkill(Level level, int point){}

    //proses unlocSkill khusus hanya untuk Crafting
    public Barang unlockSkillBarangBluePrint(Level level, int point){
        return null;
    }

    protected boolean getStatusUnlock(){
        return this.statusUnlock;
    }
}
