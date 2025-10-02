public class Vaelcarn extends Character {
    private String originStory;       // Lore of Vaelcarn's creation
    private int corruptionLevel;      // Tracks growing power
    private boolean isAwakened;       // Indicates full awakening

    public Vaelcarn(String name, int health, int mana, int sanity, int attackPower, int defense, String originStory) {
        super(name, health, mana, sanity, attackPower, defense);
        this.originStory = originStory;
        this.corruptionLevel = 0;
        this.isAwakened = false;
    }

    // === Getters ===
    public String getOriginStory() {
        return originStory;
    }

    public int getCorruptionLevel() {
        return corruptionLevel;
    }

    public boolean isAwakened() {
        return isAwakened;
    }

    public boolean isFullyCorrupted() {
        return corruptionLevel >= 100;
    }

    // === Boss Behavior ===
    public void corrupt() {
        corruptionLevel += 10;
        attackPower += 5;
        sanity -= 5;
        System.out.println(name + " grows stronger. Corruption level: " + corruptionLevel);
        System.out.println("Attack Power increased to " + attackPower + ". Sanity now: " + sanity);

        if (corruptionLevel >= 100 && !isAwakened) {
            awaken();
        }
    }

    public void awaken() {
        isAwakened = true;
        System.out.println(name + " has fully awakened! The sands tremble with its power.");
    }

    public void unleashDesertWrath(Character target) {
        int damage = attackPower + corruptionLevel / 2;
        System.out.println(name + " unleashes Desert Wrath for " + damage + " damage!");
        target.takeDamage(damage);
    }

    public void tauntPlayer(int sanityLevel) {
        if (sanityLevel < 50) {
            System.out.println(name + " whispers: 'Your resolve falters. The sands will consume you.'");
        } else {
            System.out.println(name + " growls: 'You cannot escape your fate, Queen Elara.'");
        }
    }

    public void revealOrigin() {
        System.out.println("Vaelcarn's origin: " + originStory);
    }

    public void displayPresence() {
        System.out.println("A dark and oppressive force fills the air. Vaelcarn is near...");
    }

    @Override
    public int attack() {
        System.out.println(name + " unleashes a devastating sandstorm attack!");
        return attackPower + (int)(Math.random() * 20);
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Corruption Level: " + corruptionLevel);
        System.out.println("Awakened: " + (isAwakened ? "Yes" : "No"));
    }
}
