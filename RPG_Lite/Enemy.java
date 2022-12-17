public class Enemy extends Combatant {
    
    public Enemy() {
        this.lvl = 1;
        this.hp = 25;
        this.hpMax = this.hp;
        this.armor = 25;
        this.startArmor = this.armor;
        this.defense = 10;
        this.atk = 10;
    }

    public Enemy(int hp, int armor, int defense, int atk) {
        super(hp, armor, defense, atk);
    }

    @Override
    public void doAttack() {
        Hero target = Game.team.get(Game.randInt(0, Game.team.size()-1));
        target.armor -= this.getAtk();
        if(target.armor < 0) {
            target.hp += target.armor;
            target.armor = 0;
        }
        if(target.hp <= 0) {
            Game.team.remove(target);
            Game.combatants.remove(target);
        }
    }

    @Override
    public void choice() {
        System.out.println("C'est le tour de l'adversaire !");
        int nb = Game.randInt(0, 1);
        switch(nb) {
            case 0 :
                this.doAttack();
                System.out.println(this.getClass()+" attaque !");
                break;
            case 1 :
                this.doDeffend();
                System.out.println(this.getClass()+" déffend !");
                break;
        }
    }

    @Override
    public void levelUp() {
        this.hpMax += 25;
        this.startArmor += 25;
        this.atk += 5;
        super.levelUp();
    }

    public String toString() {
        return this.getClass().toString()+" : "+"hp="+this.hp+" armor="+this.armor+" atk="+this.atk;
    }

    public int getAtk() {
        return this.atk;
    }
    
}
