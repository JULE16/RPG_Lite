package com.example;

public class Food extends Consumable {
    private int hp;

    public Food() {
        this.name = "lembas";
        this.hp = 20;
    }
    
    public Food(String name, int hp) {
        super(name);
        this.hp = hp;
    }

    public int getHp() {
        return this.hp;
    }
    
}
