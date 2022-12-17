import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static ArrayList<Hero> team = new ArrayList<Hero>();
    static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    static ArrayList<Combatant> combatants;
    static int length = 0;

    public static void main(String[] args) {
        int fights = 5;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choissez le nombre de Heros dans votre equipe");
        while(true) {
            try{
                length = scanner.nextInt();
            }catch(InputMismatchException e) {scanner.nextLine();}
            if(length > 0) {
                break;
            }
            else {
                System.out.println("Entrez un chiffre superieur à 0");
            }
        }
        setHeroes();
        startFights(fights);
    }
    

    public static void setHeroes() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        for(int i=0;i<length;i++) {
            System.out.println("Choisissez la classe de votre hero :");
            System.out.println("1 : Warrior | 2 : Hunter | 3 : Mage | 4 : Healer");
            while(true) {
                try {
                    choice = scanner.nextInt();
                }catch(InputMismatchException e) {scanner.nextLine();}
                if(choice > 0 && choice <=4) {
                    break;
                }
                else {
                    System.out.println("Entrez un chiffre valide");
                }
            }
            switch(choice) {
                case 1 :
                    team.add(new Warrior());
                    break;
                case 2 :
                    team.add(new Hunter());
                    break;
                case 3 :
                    team.add(new Mage());
                    break;
                case 4 :
                    team.add(new Healer());
                    break;
            }
        }
    }

    public static ArrayList<Enemy> setEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for(int i=0;i<length;i++) {
            enemies.add(new Enemy());
        }
        return enemies;
    }

    public static void startFights(int fights) {
        int counter = 0;
        do {
            if(counter<fights-1) {
                enemies = setEnemies();
                for(int i=0; i<counter; i++) {
                    levelUp(2);
                }
                System.out.println("Combat n°"+String.valueOf(counter+1));
                fight();
                for(int i=0; i<team.size(); i++) {
                    Hero hero = team.get(i);
                    hero.boost();
                }
                levelUp(1);
            }
            if(counter == fights-1) {
                enemies.add(new Enemy(500,250,20,40));
                System.out.println("Boss fight");
                fight();
            }
            counter += 1;
        } while(team.size() > 0 && counter <fights);
        if(team.size() > 0) {
            System.out.println("Vous avez remporté la partie");
        }
    }

    public static void fight() {
        combatants = new ArrayList<Combatant>();
        combatants.addAll(team);
        combatants.addAll(enemies);
        Collections.shuffle(combatants);
        int counter = 0;
        do {
            Combatant currentCombatant;
            printBoard();
            if(counter < combatants.size()) {
                currentCombatant = combatants.get(counter);
                System.out.println(currentCombatant.getClass());
                currentCombatant.choice();
                counter += 1;
            }
            if(counter == combatants.size()) {
                counter = 0;
            }
        } while(team.size() > 0 && enemies.size() > 0);
        if(team.size() > 0) {
            System.out.println("VICTOIRE !!!!!!!");
        }
        if(enemies.size() > 0) {
            System.out.println("Vous avez perdu !!!");
        }
    }

    public static void levelUp(int nb) {
        if(nb ==1) {
            for(int i=0; i<team.size(); i++) {
                Hero hero = team.get(i);
                hero.levelUp();
            }
        }
        if(nb == 2) {
            for(int i=0; i<enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                enemy.levelUp();
            }
        }
    }
    
    public static void printBoard() {
        System.out.println();
        System.out.println(printTeam());
        System.out.println();
        System.out.println(printEnemies());
        System.out.println();
    }

    public static String printTeam() {
        String str = "";
        for(int i=0; i<team.size(); i++) {
            Hero hero = team.get(i);
            String hp = String.valueOf(hero.getHp());
            String armor = String.valueOf(hero.getArmor());
            str += "| "+hero.getClass()+" hp : "+hp+" armor : "+armor+" |";
        }
        return str;
    }

    public static String printEnemies() {
        String str = "";
        for(int i=0; i<enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            String hp = String.valueOf(enemy.getHp());
            String armor = String.valueOf(enemy.getArmor());
            str +="| "+enemy.getClass()+" hp : "+hp+" armor : "+armor+" |";
        }
        return str;
    }
    
    public static int randInt(int min, int max) {
        Random random = new Random();
        int randInt = random.nextInt((max-min)+1)+min;
        return randInt;
    }
}