package www.smktelkommlg.sch.id.dotaapi;

public class HeroModel {
    private final String heroName;
    private final String heroAttr;
    private final String heroAttack;
    private final String heroImg;

    public String getHeroName() {
        return heroName;
    }

    public String getHeroAttr() {
        return heroAttr;
    }

    public String getHeroAttack() {
        return heroAttack;
    }

    public String getHeroImg() {
        return heroImg;
    }

    public HeroModel(String heroName, String heroAttr, String heroAttack, String heroImg) {
        this.heroName = heroName;
        this.heroAttr = heroAttr;
        this.heroAttack = heroAttack;
        this.heroImg = heroImg;
    }
}
