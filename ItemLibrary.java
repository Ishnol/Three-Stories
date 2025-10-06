import java.util.*;

public class ItemLibrary {
    public static final Item CROWN_OF_FORESIGHT = new Item(
        "Crown of Foresight",
        "A gold circlet that reveals fragments of the future.",
        ItemType.KEY,
        0,
        false,
        Arrays.asList("foresight", "sanityDrain")
    );

    public static final Item RITUAL_COMPONENTS = new Item(
        "Ritual Components",
        "Sacred powders and relics needed for elemental rites.",
        ItemType.QUEST,
        0,
        true,
        Arrays.asList("ritualSuccess", "elementalBinding")
    );

    public static final Item DESERTGLASS_SHARD = new Item(
        "Desertglass Shard",
        "A crystal shard that whispers secrets of the dunes.",
        ItemType.BOOST,
        0,
        false,
        Arrays.asList("perceptionBoost", "sanityDrain")
    );

    public static final Item ECHO_VIAL = new Item(
        "Echo Vial",
        "Stores and replays voices to deceive or inspire.",
        ItemType.KEY,
        0,
        false,
        Arrays.asList("unlockSoundWard", "deceiveEnemy", "triggerOracleMemory")
    );

    // Optional: Add a method to get all items
    public static List<Item> getAllItems() {
        return Arrays.asList(CROWN_OF_FORESIGHT, RITUAL_COMPONENTS, DESERTGLASS_SHARD, ECHO_VIAL);
    }
}

