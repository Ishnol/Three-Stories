import java.util.*;

public class GameState {
    private int sanityLevel; // Tracks Queen Elara's mental state
    private List<Item> inventory; // Stores collected items as Item objects
    private Set<String> ritualsCompleted; // Tracks performed rituals
    private Map<String, Integer> relationships; // Tracks relationship levels with allies (e.g., Helio, Mylo)
    private boolean crownEquipped; // Tracks if the Crown of Foresight is equipped
    private boolean vaelcarnAwakened; // Tracks if Vaelcarn has been fully awakened
    private Set<String> storyFlags; // e.g., "foundOasisMap", "spokeToOracle"


    public GameState() {
        sanityLevel = 100; // Starts at full sanity
        inventory = new ArrayList<>();
        ritualsCompleted = new HashSet<>();
        relationships = new HashMap<>();
        relationships.put("Helio", 50); // Neutral starting relationship
        relationships.put("Mylo", 50);
        crownEquipped = false;
        vaelcarnAwakened = false;
        storyFlags = new HashSet<>();

    }

    // Sanity Level Methods
    public int getSanityLevel() {
        return sanityLevel;
    }

    public void modifySanity(int amount) {
        sanityLevel += amount;
        if (sanityLevel > 100) sanityLevel = 100; // Cap sanity at 100
        if (sanityLevel < 0) sanityLevel = 0; // Minimum sanity is 0
        System.out.println("Your current sanity level is: " + sanityLevel);
        checkSanityEffects();

    }

    public void setFlag(String flag) {
    storyFlags.add(flag);
}

public boolean hasFlag(String flag) {
    return storyFlags.contains(flag);
}

    public void setFlag(String flag) {
    storyFlags.add(flag);
    System.out.println("Flag set: " + flag);
}

  
    public void checkSanityEffects() {
    if (sanityLevel <= 25) {
        System.out.println("Whispers haunt your thoughts... Elara's mind begins to fray.");
    } else if (sanityLevel <= 50) {
        System.out.println("A sense of unease settles over you, weakening your resolve.");
    }
}

    
    // Inventory Methods
    public void addItemToInventory(Item item) {
        if (!inventory.contains(item)) {
            inventory.add(item);
            System.out.println(item.getName() + " has been added to your inventory.");
        } else {
            System.out.println(item.getName() + " is already in your inventory.");
        }
    }

  public boolean hasItem(String itemName) {
    return inventory.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
}

    public void useItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println("You use the " + itemName + ": " + item.getDescription());
                if (item.isConsumable()) {
                    inventory.remove(item); // Remove item if it's consumable
                    System.out.println(itemName + " has been consumed and removed from your inventory.");
                }
                return;
            }
        }
        System.out.println("You do not have " + itemName + " in your inventory.");
    }

    public List<Item> getInventory() {
        return inventory;
    }

    // Ritual Methods
    public void completeRitual(String ritual) {
        if (!ritualsCompleted.contains(ritual)) {
            ritualsCompleted.add(ritual);
            System.out.println("You have successfully completed the " + ritual + " ritual.");
        } else {
            System.out.println("The " + ritual + " ritual has already been performed.");
        }
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

   public String getRelationshipStatus(String character) {
    int level = relationships.getOrDefault(character, 0);
    if (level >= 90) return "Loyal Companion";
    else if (level >= 70) return "Trusted Ally";
    else if (level >= 50) return "Neutral";
    else if (level >= 30) return "Uneasy Ally";
    else return "Distant";
}


    public void modifyRelationship(String character, int amount) {
        int newLevel = relationships.getOrDefault(character, 50) + amount;
        relationships.put(character, Math.max(0, Math.min(newLevel, 100))); // Clamps between 0 and 100
        System.out.println(character + "'s relationship level is now: " + relationships.get(character));
    }

    // Crown of Foresight Methods
    public boolean isCrownEquipped() {
        return crownEquipped;
    }

    public void equipCrown() {
        if (!crownEquipped) {
            crownEquipped = true;
            System.out.println("You have equipped the Crown of Foresight.");
        } else {
            System.out.println("The Crown of Foresight is already equipped.");
        }
    }

    public void unequipCrown() {
        if (crownEquipped) {
            crownEquipped = false;
            System.out.println("You have removed the Crown of Foresight.");
        } else {
            System.out.println("The Crown of Foresight is not currently equipped.");
        }
    }

    // Vaelcarn Methods
    public boolean isVaelcarnAwakened() {
        return vaelcarnAwakened;
    }

    public void awakenVaelcarn() {
        if (!vaelcarnAwakened) {
            vaelcarnAwakened = true;
            System.out.println("The ancient entity Vaelcarn has fully awakened! The sands tremble with its power...");
        } else {
            System.out.println("Vaelcarn is already awakened.");
        }
    }

    // Utility Method for Displaying State
    public void displayGameState() {
        System.out.println("===== Game State =====");
        System.out.println("Sanity Level: " + sanityLevel);
        System.out.println("Crown of Foresight Equipped: " + (crownEquipped ? "Yes" : "No"));
        System.out.println("Vaelcarn Awakened: " + (vaelcarnAwakened ? "Yes" : "No"));
        System.out.println("Completed Rituals: " + ritualsCompleted);
        System.out.println("Relationships: " + relationships);
        System.out.println("Story Flags: " + storyFlags);
        System.out.println("Inventory: ");
        for (Item item : inventory) {
            System.out.println("- " + item.getName());
        }
    }
}
