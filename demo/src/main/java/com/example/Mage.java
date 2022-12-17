package com.example;

import java.util.ArrayList;

public class Mage extends SpellCaster {
    
    public Mage() {
        this.lvl = 1;
        this.hp = 50;
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

    public Mage(int hp, int armor, int defense, int atk, Weapon weapon, ArrayList<Consumable> inventory, int mana) {
        super(hp, armor, defense, atk, weapon, inventory, mana);
    }

    @Override
    public void doAttack() {
        if(this.mana > this.cost) {
            super.doAttack();
            this.mana -= this.cost;
        }
        else {
            System.out.println("Vous n'avez pas assez de mana !");
            this.choice();
        }
    }
}
