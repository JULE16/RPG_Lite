package com.example;

import java.util.ArrayList;
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
            for(int i=0; i<Game.team.size(); i++) {
                System.out.println(i+1+" : "+Game.team.get(i).toString());
            }
            System.out.println("Selectionez une cible");
            int nb = scanner.nextInt();
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
