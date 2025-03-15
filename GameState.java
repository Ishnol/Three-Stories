import java.util.*;

public class GameState {
    private int sanityLevel; // Tracks Queen Elara's mental state
    private List<String> inventory; // Stores collected items
    private Set<String> ritualsCompleted; // Tracks performed rituals
    private Map<String, Integer> relationships; // Tracks relationship levels with allies (e.g., Helio, Mylo)
    private boolean crownEquipped; // Tracks if the Crown of Foresight is equipped
    private boolean vaelcarnAwakened; // Tracks if Vaelcarn has been fully awakened

    public GameState() {
        sanityLevel = 100; // Starts at full sanity
        inventory = new ArrayList<>();
        ritualsCompleted = new HashSet<>();
        relationships = new HashMap<>();
        relationships.put("Helio", 50); // Neutral starting relationship
        relationships.put("Mylo", 50);
        crownEquipped = false;
        vaelcarnAwakened = false;
    }

    // Sanity Level Methods
    public int getSanityLevel() {
        return sanityLevel;
    }

    public void modifySanity(int amount) {
        sanityLevel += amount;
        if (sanityLevel > 100) sanityLevel = 100; // Cap sanity at 100
        if (sanityLevel < 0) sanityLevel = 0; // Minimum sanity is 0
        System.out.println("Sanity Level: " + sanityLevel);
    }

    // Inventory Methods
    public void addItemToInventory(String item) {
        if (!inventory.contains(item)) {
            inventory.add(item);
            System.out.println(item + " has been added to your inventory.");
        } else {
            System.out.println(item + " is already in your inventory.");
        }
    }

    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    public List<String> getInventory() {
        return inventory;
    }

    // Ritual Methods
    public void completeRitual(String ritual) {
        ritualsCompleted.add(ritual);
        System.out.println("You have completed the " + ritual + " ritual.");
    }

    public boolean isRitualCompleted(String ritual) {
        return ritualsCompleted.contains(ritual);
    }

    public Set<String> getCompletedRituals() {
        return ritualsCompleted;
    }

    // Relationship Methods
    public int getRelationshipLevel(String character) {
        return relationships.getOrDefault(character, 0);
    }

    public void modifyRelationship(String character, int amount) {
        int newLevel = relationships.getOrDefault(character, 0) + amount;
        relationships.put(character, Math.max(0, Math.min(newLevel, 100))); // Clamps the value between 0 and 100
        System.out.println(character + "'s relationship level is now: " + relationships.get(character));
    }

    // Crown of Foresight Methods
    public boolean isCrownEquipped() {
        return crownEquipped;
    }

    public void equipCrown() {
        crownEquipped = true;
        System.out.println("You have equipped the Crown of Foresight.");
    }

    public void unequipCrown() {
        crownEquipped = false;
        System.out.println("You have removed the Crown of Foresight.");
    }

    // Vaelcarn Methods
    public boolean isVaelcarnAwakened() {
        return vaelcarnAwakened;
    }

    public void awakenVaelcarn() {
        vaelcarnAwakened = true;
        System.out.println("The ancient entity Vaelcarn has fully awakened!");
    }
}

