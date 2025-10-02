import java.util.*;

public class Player extends Character {
    // === Core Attributes ===
    private String elementalAffinity;
    private int sanity;

    // === Modular Managers ===
    private InventoryManager inventoryManager;
    private RelationshipManager relationshipManager;
    private ConditionManager conditionManager;

    // === Constructor ===
    public Player(String name, int health, int mana, int sanity, int attackPower, int defense, String elementalAffinity) {
        super(name, health, mana, sanity, attackPower, defense);
        this.elementalAffinity = elementalAffinity;
        this.sanity = sanity;
        this.inventoryManager = new InventoryManager();
        this.relationshipManager = new RelationshipManager();
        this.conditionManager = new ConditionManager();
    }

    // === Getters & Setters ===
    public String getElementalAffinity() {
        return elementalAffinity;
    }

    public void setElementalAffinity(String elementalAffinity) {
        this.elementalAffinity = elementalAffinity;
    }

    public int getSanity() {
        return sanity;
    }

    public void modifySanity(int amount) {
        sanity += amount;
        sanity = Math.max(0, Math.min(100, sanity));
        System.out.println("Sanity Level: " + sanity);
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public RelationshipManager getRelationshipManager() {
        return relationshipManager;
    }

    public ConditionManager getConditionManager() {
        return conditionManager;
    }

    // === Sanity & Conditions ===
    public boolean isSane() {
        return sanity >= 30;
    }

    public void checkSanityEffects() {
        if (sanity < 30) {
            System.out.println("Your mind trembles. Visions blur and commands may misfire.");
        } else if (sanity > 80) {
            System.out.println("Your foresight sharpens. You feel attuned to the desert's whispers.");
        }
    }

    public boolean canPerformRitual() {
        return isSane() && !conditionManager.hasCondition("cursed");
    }

    // === Combat & Synergy ===
    public boolean isElementCompatible(String element) {
        return this.elementalAffinity.equalsIgnoreCase(element);
    }

    public void triggerAllyCombo(String allyName) {
        if (relationshipManager.isTrustedBy(allyName)) {
            System.out.println("You and " + allyName + " perform a synchronized elemental strike!");
            // Add combo logic here
        } else {
            System.out.println(allyName + " hesitates. Trust must be earned.");
        }
    }

    // === Narrative Hooks ===
    public void checkNarrativeTriggers() {
        if (sanity <= 10) {
            System.out.println("A dark vision overtakes you. The Entity whispers your name...");
            // Trigger hallucination event
        }
        if (relationshipManager.getRelationshipLevel("Helio") >= 90 &&
            inventoryManager.hasItem("Echo Vial")) {
            System.out.println("Helio shares a forgotten mantra. A hidden path opens.");
            // Unlock secret location or ritual
        }
    }

    // === Display ===
    public void displayStats() {
        super.displayStats();
        System.out.println("Elemental Affinity: " + elementalAffinity);
        System.out.println("Sanity Level: " + sanity);
        inventoryManager.displayInventory();
        relationshipManager.displayRelationships();
        conditionManager.displayConditions();
    }
}
