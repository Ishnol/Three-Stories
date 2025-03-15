public class Item {
    private String name; // Item name
    private String description; // Description of the item
    private boolean isKeyItem; // Whether it's crucial for story progression
    private int powerBoost; // Any boost it provides (e.g., mana, attack, etc.)

    public Item(String name, String description, boolean isKeyItem, int powerBoost) {
        this.name = name;
        this.description = description;
        this.isKeyItem = isKeyItem;
        this.powerBoost = powerBoost;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isKeyItem() {
        return isKeyItem;
    }

    public int getPowerBoost() {
        return powerBoost;
    }

    // Display item details
    public void displayItemInfo() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        if (isKeyItem) {
            System.out.println("This is a key item for story progression.");
        }
        if (powerBoost != 0) {
            System.out.println("Power Boost: " + powerBoost);
        }
    }
}
