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
