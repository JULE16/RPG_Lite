public abstract class Combatant {
    protected int lvl;
    protected int hp;
    protected int hpMax;
    protected int armor;
    protected int startArmor;
    protected int defense;
    protected int atk;

    public Combatant() {}

    public Combatant(int hp, int armor, int defense, int atk) {
        this.lvl = 1;
        this.hp = hp;
        this.hpMax = this.hp;
        this.armor = armor;
        this.startArmor = this.armor;
        this.defense = defense;
        this.atk = atk;
    }

    public void doAttack() {}

    public void doDeffend() {
        this.armor +=this.defense;
    }

    public void choice() {}

    public void levelUp() {
        this.lvl += 1;
        this.hp = this.hpMax;
        this.armor = this.startArmor;
    }
    
    public String toString() {
        return this.getClass().toString()+" : "+"hp="+this.hp+" hp max="+this.hpMax+" armor="+this.armor+" defense="+this.defense+" atk="+this.atk;
    }

    public int getLvl() {
        return this.lvl;
    }

    public int getHp() {
        return this.hp;
    }

    public int getHpMax() {
        return this.hpMax;
    }
    
    public int getArmor() {
        return this.armor;
    }

    public int getStartArmor() {
        return this.startArmor;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getAtk() {
        return this.atk;
    }

}