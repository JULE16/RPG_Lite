import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hunter extends Hero {
    protected int arrows;
    protected int arrowsMax;

    public Hunter() {
        this.lvl = 1;
        this.hp = 50;
        this.hpMax = this.hp;
        this.armor = 5;
        this.defense = 2;
        this.atk = 10;
        this.healMultiplier = 1;
        this.weapon = new Weapon("Beginner's bow", 1);
        this.inventory = new ArrayList<Consumable>();
        this.inventory.add(new Food());
        this.arrows = 5;
        this.arrowsMax = this.arrows;
    }

    public Hunter(int hp, int armor, int defense, int atk, Weapon weapon, ArrayList<Consumable> inventory, int arrows) {
        super(hp, armor, defense, atk, weapon, inventory);
        this.arrows = arrows;
    }

    @Override
    public void doAttack() {
        if(this.arrows>0) {
            super.doAttack();
            this.arrows -= 1;
        }
        else {
            System.out.println("Vous n'avez plus de flèches !");
            this.choice();
        }
    }

    @Override
    public void levelUp() {
        this.arrowsMax += 1;
        this.arrows = this.arrowsMax;
        super.levelUp();
    }

    @Override
    public void boost() {
        super.boost();
        Scanner scanner = new Scanner(System.in);
        int nb = 0;
        System.out.println("Choisissez un bonus parmis");
        System.out.println("1 : boost de dégat | 2 : boost de defense | 3: boost de heal | 4 : nourriture en plus | 5 : fleches en plus");
        while(true) {
            try {
                nb = scanner.nextInt();
            }catch(InputMismatchException e) {scanner.nextLine();}
            if(nb > 0 && nb < 6) {
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
            case 5 :
                this.arrowsMax += 2;
                break;
        }
    }

    public int getArrows() {
        return this.arrows;
    }

    public int getArrowsMax() {
        return this.arrowsMax;
    }
}
