import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Warrior extends Hero{
    public Warrior() {
        this.lvl = 1;
        this.hp = 50;
        this.hpMax = this.hp;
        this.armor = 5;
        this.defense = 2;
        this.atk = 10;
        this.healMultiplier = 1;
        this.weapon = new Weapon("Beginner's Sword", 1);
        this.inventory = new ArrayList<Consumable>();
        this.inventory.add(new Food());
    }
    
    public Warrior(int hp, int armor, int defense, int atk, Weapon weapon, ArrayList<Consumable> inventory) {
        super(hp, armor, defense, atk, weapon, inventory);
    }

    @Override
    public void boost() {
        super.boost();
        Scanner scanner = new Scanner(System.in);
        int nb = 0;
        System.out.println("Choisissez un bonus parmis");
        System.out.println("1 : boost de dégat | 2 : boost de defense | 3: boost de heal | 4 : nourriture en plus");
        while(true) {
            try {
                nb = scanner.nextInt();
            }catch(InputMismatchException e) {scanner.nextLine();}
            if(nb > 0 && nb < 5) {
                break;
            }
            else {
                System.out.println("Entrez un chiffre valide");
            }
        }
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
                this.inventory.add(new Food());
                break;
        }
    }
}
