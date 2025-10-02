import java.util.*;

public class GameState {
    private int sanityLevel;
    private InventoryManager inventoryManager;
    private RelationshipManager relationshipManager;
    private ConditionManager conditionManager;

    private Set<String> ritualsCompleted;
    private boolean crownEquipped;
    private boolean vaelcarnAwakened;
    private Set<String> storyFlags;

    public GameState() {
        sanityLevel = 100;
        inventoryManager = new InventoryManager();
        relationshipManager = new RelationshipManager();
        conditionManager = new ConditionManager();

        ritualsCompleted = new HashSet<>();
        crownEquipped = false;
        vaelcarnAwakened = false;
        storyFlags = new HashSet<>();

        relationshipManager.updateRelationship("Helio", 0); // Neutral baseline
        relationshipManager.updateRelationship("Mylo", 0);
    }

    // === Sanity Methods ===
    public int getSanityLevel() {
        return sanityLevel;
    }

    public void modifySanity(int amount) {
        sanityLevel += amount;
        sanityLevel = Math.max(0, Math.min(100, sanityLevel));
        System.out.println("Your current sanity level is: " + sanityLevel);
        checkSanityEffects();
    }

    public void checkSanityEffects() {
        if (sanityLevel <= 25) {
            System.out.println("Whispers haunt your thoughts... Elara's mind begins to fray.");
        } else if (sanityLevel <= 50) {
            System.out.println("A sense of unease settles over you, weakening your resolve.");
        }
    }

    // === Ritual Methods ===
    public void completeRitual(String ritual) {
        if (ritualsCompleted.add(ritual)) {
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

    // === Story Flags ===
    public void setFlag(String flag) {
        storyFlags.add(flag);
        System.out.println("Flag set: " + flag);
    }

    public boolean hasFlag(String flag) {
        return storyFlags.contains(flag);
    }

    // === Crown Methods ===
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

    // === Vaelcarn Methods ===
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

    // === Accessors for Managers ===
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public RelationshipManager getRelationshipManager() {
        return relationshipManager;
    }

    public ConditionManager getConditionManager() {
        return conditionManager;
    }

    // === Display Method ===
    public void displayGameState() {
        System.out.println("===== Game State =====");
        System.out.println("Sanity Level: " + sanityLevel);
        System.out.println("Crown of Foresight Equipped: " + (crownEquipped ? "Yes" : "No"));
        System.out.println("Vaelcarn Awakened: " + (vaelcarnAwakened ? "Yes" : "No"));
        System.out.println("Completed Rituals: " + ritualsCompleted);
        System.out.println("Story Flags: " + storyFlags);
        inventoryManager.displayInventory();
        relationshipManager.displayRelationships();
        conditionManager.displayConditions();
    }
}
