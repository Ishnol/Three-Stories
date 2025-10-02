import java.util.*;

public class InventoryManager {
    private List<Item> inventory;

    public InventoryManager() {
        this.inventory = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (!inventory.contains(item)) {
            inventory.add(item);
            System.out.println(item.getName() + " has been added to your inventory.");
        } else {
            System.out.println(item.getName() + " is already in your inventory.");
        }
    }
public void removeItem(String itemName) {
    inventory.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
}

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void useItem(String itemName, Player player) {
        if (hasItem(itemName)) {
            System.out.println("You use the " + itemName + ".");
            switch (itemName.toLowerCase()) {
                case "crown of foresight":
                    player.modifySanity(-10);
                    System.out.println("Visions flood your mind. Sanity decreases.");
                     removeItem(itemName); // if it's consumable
                     break;
                case "veil of isolde":
                    System.out.println("A calm veil surrounds you. Mental influence blocked.");
                    break;
                default:
                    System.out.println("The item has no immediate effect.");
            }
        } else {
            System.out.println("You do not have " + itemName + " in your inventory.");
        }
    }

    public void displayInventory() {
        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println("- " + item.getName());
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
