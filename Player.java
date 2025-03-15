import java.util.*;

public class Player extends Character {
    private String elementalAffinity; // Player's primary elemental power
    private int sanity; // Sanity level affects decisions and outcomes
    private List<Item> inventory; // Inventory for key items
    private Map<String, Integer> relationships; // Relationship levels with allies

    public Player(String name, int health, int mana, int sanity, int attackPower, int defense, String elementalAffinity) {
        super(name, health, mana, sanity, attackPower, defense);
        this.elementalAffinity = elementalAffinity;
        this.sanity = sanity;
        this.inventory = new ArrayList<>();
        this.relationships = new HashMap<>();
    }

    // Getters and Setters
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
        if (sanity > 100) sanity = 100; // Cap sanity at 100
        if (sanity < 0) sanity = 0; // Minimum sanity is 0
        System.out.println("Sanity Level: " + sanity);
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item) {
        if (!inventory.contains(item)) {
            inventory.add(item);
            System.out.println(item.getName() + " has been added to your inventory.");
        } else {
            System.out.println(item.getName() + " is already in your inventory.");
        }
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void useItem(String itemName) {
        if (hasItem(itemName)) {
            System.out.println("You use the " + itemName + ".");
            // Add functionality for specific items here
        } else {
            System.out.println("You do not have " + itemName + " in your inventory.");
        }
    }

    public void updateRelationship(String allyName, int change) {
        int newLevel = relationships.getOrDefault(allyName, 50) + change; // Default relationship level is neutral (50)
        relationships.put(allyName, Math.max(0, Math.min(100, newLevel))); // Clamp between 0 and 100
        System.out.println(allyName + "'s relationship level is now: " + relationships.get(allyName));
    }

    public int getRelationshipLevel(String allyName) {
        return relationships.getOrDefault(allyName, 50); // Default is neutral
    }

    public void displayStats() {
        super.displayStats();
        System.out.println("Elemental Affinity: " + elementalAffinity);
        System.out.println("Sanity Level: " + sanity);
        System.out.println("Inventory: ");
        for (Item item : inventory) {
            System.out.println("- " + item.getName());
        }
        System.out.println("Relationships: ");
        for (Map.Entry<String, Integer> entry : relationships.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
