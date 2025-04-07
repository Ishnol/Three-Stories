public class Item {
    private String name; // Item name
    private String description; // Description of the item
    private ItemType type; // Type of item (healing, quest, etc.)
    private int powerBoost; // Any boost it provides (e.g., mana, attack, etc.)

    public Item(String name, String description, ItemType type, int powerBoost) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.powerBoost = powerBoost;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public int getPowerBoost() {
        return powerBoost;
    }

    // Display item details
    public void displayItemInfo() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Type: " + type);
        if (powerBoost > 0) {
            System.out.println("Power Boost: " + powerBoost);
        }
        if (type == ItemType.KEY) {
            System.out.println("This is a key item for unlocking story progression.");
        }
    }

    // Use the item on a target character
    public void use(Character target) {
        if (type == ItemType.HEALING || powerBoost > 0) {
            target.heal(powerBoost);
            System.out.println(target.getName() + " recovers " + powerBoost + " health!");
        } else if (type == ItemType.BOOST) {
            System.out.println("Boost item used, providing temporary enhancements!");
            // You can extend this to apply buffs later
        } else {
            System.out.println("This item cannot be used directly.");
        }
    }
}
