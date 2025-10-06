// Base Character Class
public class Character {
    protected String name;
    protected int health;
    protected int mana; 
    protected int sanity; 
    protected int attackPower;
    protected int defense;
    protected boolean onCooldown = false;

    public Character(String name, int health, int mana, int sanity, int attackPower, int defense) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.sanity = sanity;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public int getSanity() {
        return sanity;
    }
    public void setSanity(int sanity) {
        this.sanity = sanity;
    }
    public void takeDamage(int damage) {
        int actualDamage = damage - defense;
        if (actualDamage < 0) actualDamage = 0;
        health -= actualDamage;
    }
    public int attack() {
        return attackPower + (int)(Math.random() * 5);
    }
    public void heal(int amount) {
        health += amount;
    }
    public boolean isOnCooldown() {
    return onCooldown;
}

public void setCooldown(boolean status) {
    this.onCooldown = status;
}
   
    public void displayStats() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Mana: " + mana);
        System.out.println("Sanity: " + sanity);
    }
}


}
