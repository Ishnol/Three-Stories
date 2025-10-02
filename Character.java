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

// Queen Elara Subclass
public class QueenElara extends Character {
    private String elementalAffinity;
    private boolean crownOfForesight;

    public QueenElara(String name, int health, int mana, int sanity, int attackPower, int defense, String elementalAffinity) {
        super(name, health, mana, sanity, attackPower, defense);
        this.elementalAffinity = elementalAffinity;
        this.crownOfForesight = false;
    }

    public void equipCrown() {
        this.crownOfForesight = true;
        System.out.println(name + " equips the Crown of Foresight!");
    }

    public void unequipCrown() {
        this.crownOfForesight = false;
        System.out.println(name + " removes the Crown of Foresight.");
    }

    public boolean hasCrownEquipped() {
        return crownOfForesight;
    }

    public String getElementalAffinity() {
        return elementalAffinity;
    }

    public void useElementalPower(String element) {
        if (mana > 10) {
            mana -= 10;
            System.out.println(name + " unleashes the power of " + element + "!");
        } else {
            System.out.println("Not enough mana to use elemental power.");
        }
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Elemental Affinity: " + elementalAffinity);
        System.out.println("Crown of Foresight: " + (crownOfForesight ? "Equipped" : "Not Equipped"));
    }
}




// Helio Subclass
public class Helio extends Character {
    private int archerySkill;

    public Helio(String name, int health, int mana, int sanity, int attackPower, int defense, int archerySkill) {
        super(name, health, mana, sanity, attackPower, defense);
        this.archerySkill = archerySkill;
    }

    public int performArrowStrike() {
        int damage = attackPower + archerySkill;
        System.out.println(name + " performs an arrow strike for " + damage + " damage!");
        this.onCooldown = true;
        return damage;
    }

    public int getArcherySkill() {
        return archerySkill;
    }

    public void setArcherySkill(int archerySkill) {
        this.archerySkill = archerySkill;
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Archery Skill: " + archerySkill);
    }
}

// Mylo Subclass
public class Mylo extends Character {
    private String knowledgeSpecialty;

    public Mylo(String name, int health, int mana, int sanity, int attackPower, int defense, String knowledgeSpecialty) {
        super(name, health, mana, sanity, attackPower, defense);
        this.knowledgeSpecialty = knowledgeSpecialty;
    }

    public void revealSecret() {
        System.out.println(name + " uses their knowledge of " + knowledgeSpecialty + " to uncover hidden truths!");
        this.onCooldown = true;
    }

    public String getKnowledgeSpecialty() {
        return knowledgeSpecialty;
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Knowledge Specialty: " + knowledgeSpecialty);
    }
}
