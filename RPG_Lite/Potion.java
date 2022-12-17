public class Potion extends Consumable {
    private int mana;

    public Potion() {
        this.name = "potion";
        this.mana = 25;
    }
    
    public Potion(String name, int mana) {
        super(name);
        this.mana = mana;
    }

    public int getMana() {
        return this.mana;
    }
}
