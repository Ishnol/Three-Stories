import java.util.*;

public class Item {
    private String name;
    private String description;
    private ItemType type;
    private int powerBoost;
    private boolean consumable;
    private List<String> effects;

    public Item(String name, String description, ItemType type, int powerBoost, boolean consumable, List<String> effects) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.powerBoost = powerBoost;
        this.consumable = consumable;
        this.effects = effects != null ? effects : new ArrayList<>();
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

    public boolean isConsumable() {
        return consumable;
    }

    public List<String> getEffects() {
        return effects;
    }

    // Display item details
    public void displayItemInfo() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Type: " + type);
        if (powerBoost > 0) {
            System.out.println("Power Boost: " + powerBoost);
        }
        if (consumable) {
            System.out.println("This item will be consumed upon use.");
        }
        if (!effects.isEmpty()) {
            System.out.println("Effects:");
            for (String effect : effects) {
                System.out.println("- " + effect);
            }
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
            // Future: Apply buffs based on effects
        } else if (type == ItemType.KEY) {
            System.out.println("Key item activated. It may unlock a story event or puzzle.");
        } else {
            System.out.println("This item cannot be used directly.");
        }
    }
}

Item crownOfForesight = new Item(
    "Crown of Foresight",
    "A gold circlet that reveals fragments of the future.",
    ItemType.KEY,
    0,
    false,
    Arrays.asList("foresight", "sanityDrain")
);

Item ritualComponents = new Item(
    "Ritual Components",
    "Sacred powders and relics needed for elemental rites.",
    ItemType.QUEST,
    0,
    true,
    Arrays.asList("ritualSuccess", "elementalBinding")
);

Item desertglassShard = new Item(
    "Desertglass Shard",
    "A crystal shard that whispers secrets of the dunes.",
    ItemType.BOOST,
    0,
    false,
    Arrays.asList("perceptionBoost", "sanityDrain")
);

Item echoVial = new Item(
    "Echo Vial",
    "Stores and replays voices to deceive or inspire.",
    ItemType.KEY,
    0,
    false,
    Arrays.asList("unlockSoundWard", "deceiveEnemy", "triggerOracleMemory")
);
