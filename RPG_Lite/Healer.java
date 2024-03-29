import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Healer extends SpellCaster {
    
    public Healer() {
        this.lvl = 1;
        this. hp = 50;
        this.hpMax = this.hp;
        this.armor = 5;
        this.defense = 2;
        this.atk = 10;
        this.healMultiplier = 1;
        this.weapon = new Weapon("Beginner's Staff", 1);
        this.inventory = new ArrayList<Consumable>();
        this.inventory.add(new Potion());
        this.mana = 50;
        this.manaMax = this.mana;
        this.cost = 10;
    }
    
    public Healer(int hp, int armor, int defense, int atk, Weapon weapon, ArrayList<Consumable> inventory, int mana) {
        super(hp, armor, defense, atk, weapon, inventory, mana);
    }

    @Override
    public void doAttack() {
        if(this.mana > this.cost) {
            Scanner scanner = new Scanner(System.in);
            int nb =0;
            for(int i=0; i<Game.team.size(); i++) {
                Hero hero = Game.team.get(i);
                System.out.println(i+1+" : "+hero.getClass()+" : "+" hp : "+hero.hp+" armor : "+hero.armor);
            }
            System.out.println("Selectionez une cible");
            while(true) {
                try {
                    nb = scanner.nextInt();
                }catch(InputMismatchException e) {scanner.nextLine();}
                if(nb >0 && nb <= Game.team.size()) {
                    break;
                }
                else {
                    System.out.println("Entrez un chiffre valide");
                }
            }
            Hero target = Game.team.get(nb-1);
            target.hp += this.getAtk()*this.weapon.getDmg();
            if (target.hp > target.hpMax) {
                target.hp =target.hpMax;
            }
            this.mana -= this.cost;
        }
        else {
            System.out.println("Vous n'avez pas assez de mana !");
            this.choice();
        }
    }
}
