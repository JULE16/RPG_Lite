package com.example;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Hero extends Combatant implements EventHandler<ActionEvent>{
    protected ArrayList<Consumable> inventory;
    protected Weapon weapon;
    protected double healMultiplier;

    public Hero() {}

    public Hero(int hp, int armor, int defense, int atk, Weapon weapon, ArrayList<Consumable> inventory) {
        super(hp, armor, defense, atk);
        this.lvl = 1;
        this.weapon = weapon;
        this.inventory = inventory;
        this.healMultiplier = 1;
        this.hpMax = this.hp;
        this.startArmor = this.armor;
    }

    @Override
    public void doAttack() {
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<Game.enemies.size(); i++) {
            System.out.println(i+1+" : "+Game.enemies.get(i).toString());
        }
        System.out.println("Selectionez une cible");
        int nb = scanner.nextInt();
        Enemy target = Game.enemies.get(nb-1);
        target.armor -= this.getAtk()*this.weapon.getDmg();
        if (target.armor <0) {
            target.hp +=target.armor;
            target.armor = 0;
        }
        if(target.hp <= 0) {
            Game.enemies.remove(target);
            Game.combatants.remove(target);
        }    
    }

    @Override
    public void choice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("C'est voter tour ! Choissez une action à faire.");
        System.out.println("1 : attack | 2 : deffend | 3 : use item");
        int nb = scanner.nextInt();
        switch(nb) {
            case 1 :
                this.doAttack();
                break;
            case 2 :
                this.doDeffend();
                break;
            case 3 : 
                if(this.inventory.size() > 0) {
                    System.out.println("Selectionnez un item parmis : ");
                    for(int i=0; i<this.inventory.size(); i++) {
                        System.out.println(i+1+" : "+this.inventory.get(i).getName());
                    }
                    int nb2 = scanner.nextInt();
                    Consumable consumable = this.inventory.get(nb2-1);
                    this.useConsumable(consumable);
                    this.inventory.remove(consumable);
                }
                else {
                    System.out.println("Votre inventaire est vide !");
                    this.choice(); 
                }
                break;
        }

    }
    
    public void useConsumable(Consumable consumable) {
        if(consumable instanceof Food) {
            Food food = (Food) consumable;
            this.hp += food.getHp()*this.healMultiplier;
            if(this.hp > this.hpMax) {
                this.hp = hpMax;
            }
            this.inventory.remove(food);
        }

        if(consumable instanceof Potion) {
            Potion potion = (Potion) consumable;
            if(this instanceof SpellCaster) {
                SpellCaster spellCaster = (SpellCaster) this;
                spellCaster.mana += potion.getMana()*this.healMultiplier;
                if(spellCaster.mana > spellCaster.manaMax) {
                    spellCaster.mana = spellCaster.manaMax;
                }
                this.inventory.remove(potion);
            }
        }
    }

    @Override
    public void levelUp() {
        this.hpMax += 25;
        this.startArmor += 10; 
        this.atk += 5;
        this.defense +=3;
        super.levelUp();
    }
    
    public void boost() {
        System.out.println(this.getClass());
    }

    @Override
    public String toString() {
        return this.getClass().toString()+" : "+"hp="+this.hp+" hp max="+this.hpMax+" armor="+this.armor+" defense="+this.defense+" atk="+this.atk+" weapon="+this.weapon+" inventory="+this.inventory+" heal_multiplier="+healMultiplier;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public ArrayList<Consumable> getInventory() {
        return this.inventory;
    }

    public double getHealMultiplier() {
        return this.healMultiplier;
    }

    @Override
    public void handle(ActionEvent arg0) {
        
        
    }
    
    
}
