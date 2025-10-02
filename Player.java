import java.util.*;

public class Player extends Character {
    // === Core Attributes ===
    private String elementalAffinity;
    private int sanity;
    private InventoryManager inventoryManager;
    private Map<String, Integer> relationships;
    private Set<String> conditions = new HashSet<>();

    // === Constructor ===
    public Player(String name, int health, int mana, int sanity, int attackPower, int defense, String elementalAffinity) {
        super(name, health, mana, sanity, attackPower, defense);
        this.elementalAffinity = elementalAffinity;
        this.sanity = sanity;
        this.inventoryManager = new InventoryManager();
        this.relationships = new HashMap<>();
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

    public boolean isSane() {
        return sanity >= 30;
    }

    public void checkSanityEffects() {
        if (sanity < 30) {
            System.out.println("Your mind trembles. Visions blur and commands may misfire.");
        } else if
