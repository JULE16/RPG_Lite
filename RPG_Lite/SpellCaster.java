import java.util.ArrayList;
import java.util.Scanner;

public class SpellCaster extends Hero{
    protected int mana;
    protected int manaMax;
    protected int cost;

    public SpellCaster() {}

    public SpellCaster(int hp, int armor,int defense, int atk, Weapon weapon, ArrayList<Consumable> inventory, int mana) {
        super(hp, armor, defense, atk, weapon, inventory);
        this.lvl = 1;
        this.mana = mana;
        this.manaMax = this.mana;
    }

    @Override
    public void levelUp() {
        this.manaMax += 10;
        this.mana = this.manaMax;
        super.levelUp();
    }

    @Override
    public void boost() {
        super.boost();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez un bonus parmis");
        System.out.println("1 : boost de dégat | 2 : boost de defense | 3: boost de heal | 4 : potion en plus | 5 : cout de mana reduit");
        int nb = scanner.nextInt();
        switch(nb) {
            case 1 :
                this.atk += 5;
                break;
            case 2 :
                this.defense += 3;
                break;
            case 3 :
                this.healMultiplier = 1.5;
                break;
            case 4 :
                this.inventory.add(new Potion());
                break;
            case 5 :
                this.cost -= 2;
                break;
        }
    }

    @Override
    public String toString() {
        return this.getClass().toString()+" : "+"hp="+this.hp+" hp max="+this.hpMax+" armor="+this.armor+" defense="+this.defense+" atk="+this.atk+" mana="+this.mana+" mana max="+this.manaMax+" weapon="+this.weapon+" inventory="+this.inventory+" heal_multiplier="+healMultiplier;
    }
    
    public int getMana() {
        return this.mana;
    }

    public int getManaMax() {
        return this.manaMax;
    }

    public int getCost() {
        return this.cost;
    }
}
