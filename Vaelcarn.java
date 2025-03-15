public class Vaelcarn extends Character {
    private String originStory; // The backstory of Vaelcarn
    private int corruptionLevel; // Tracks the entity's growing power
    private boolean isAwakened; // Indicates if Vaelcarn has fully awakened

    public Vaelcarn(String name, int health, int mana, int sanity, int attackPower, int defense, String originStory) {
        super(name, health, mana, sanity, attackPower, defense);
        this.originStory = originStory;
        this.corruptionLevel = 0; // Starts as dormant
        this.isAwakened = false; // Vaelcarn is not awakened at the start
    }

    // Getters and Setters
    public String getOriginStory() {
        return originStory;
    }

    public int getCorruptionLevel() {
        return corruptionLevel;
    }

    public boolean isAwakened() {
        return isAwakened;
    }

    // Vaelcarn's actions
    public void corrupt() {
        corruptionLevel += 10;
        System.out.println(name + " grows stronger. Corruption level: " + corruptionLevel);
        if (corruptionLevel >= 100 && !isAwakened) {
            awaken();
        }
    }

    public void awaken() {
        isAwakened = true;
        System.out.println(name + " has fully awakened! The sands tremble with its power.");
    }

    @Override
    public int attack() {
        System.out.println(name + " unleashes a devastating sandstorm attack!");
        return attackPower + (int)(Math.random() * 20); // Deals randomized high damage
    }

    public void tauntPlayer(int sanityLevel) {
        if (sanityLevel < 50) {
            System.out.println(name + " whispers: 'Your resolve falters. The sands will consume you.'");
        } else {
            System.out.println(name + " growls: 'You cannot escape your fate, Queen Elara.'");
        }
    }

    public void displayPresence() {
        System.out.println("A dark and oppressive force fills the air. Vaelcarn is near...");
    }
}
