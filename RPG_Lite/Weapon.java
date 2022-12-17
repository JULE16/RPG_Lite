public class Weapon extends Item {
    private int dmg;

    public Weapon(String name, int dmg) {
        super(name);
        this.dmg = dmg;
    }

    public String toString() {
        return this.name +" dmg : "+String.valueOf(dmg);
    }
    
    public int getDmg() {
        return this.dmg;
    }
}
