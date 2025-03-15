import java.util.*;

public class GameManager {
    private GameState gameState; // Game state for tracking player progress
    private ChoiceSystem choiceSystem;
    private CutsceneManager cutsceneManager;
    private Player player; // Updated to use the Player class
    private Location currentLocation;
    private CommandSystem commandSystem;
    private Map<String, Location> locations;
    private RandomEventSystem randomEventSystem;
    private CombatSystem combatSystem;
    private List<Character> allies; // Allies like Helio and Mylo
    private Vaelcarn vaelcarn; // Ancient entity threatening the kingdom

    public GameManager() {
        gameState = new GameState();
        choiceSystem = new ChoiceSystem();
        cutsceneManager = new CutsceneManager();
        player = new Player("Elara", 100, 50, 100, 15, 5, "Fire");
        initializeLocations();
        initializeAllies();
        currentLocation = locations.get("Royal Palace");
        commandSystem = new CommandSystem(this);
        randomEventSystem = new RandomEventSystem(this);
        combatSystem = new CombatSystem(this, player);
        vaelcarn = new Vaelcarn("Vaelcarn", 200, 100, 0, 25, 10, 
            "An ancient force corrupted by forbidden magic, resting in the Eternal Dunes.");
    }

    private void initializeLocations() {
        locations = new HashMap<>();
        locations.put("Royal Palace", new Location("Royal Palace", "The heart of Aridia, where Queen Elara resides."));
        locations.put("Oasis of Isolde", new Location("Oasis of Isolde", "A lush oasis, a place of reflection and healing."));
        locations.put("Eternal Dunes", new Location("Eternal Dunes", "Endless sands, hiding ancient secrets."));
        locations.put("Ancient Ruins", new Location("Ancient Ruins", "A forgotten place with untold mysteries."));
    }

    private void initializeAllies() {
        allies = new ArrayList<>();
        allies.add(new Helio("Helio", 80, 30, 100, 10, 3, 7)); // Archery expert
        allies.add(new Mylo("Mylo", 70, 40, 100, 8, 2, "Artifacts")); // Knowledgeable scout
    }

    public void startGame() {
        cutsceneManager.playIntroCutscene();
        gameState.displayGameState(); // Display initial game state
        runGameLoop();
    }

    private void runGameLoop() {
        System.out.println("Welcome to the game, " + player.getName() + "!");
        System.out.println("You are currently at " + currentLocation.getName());
        currentLocation.displayLocationDetails();
        commandSystem.listenForCommand();
    }

    public void moveToLocation(String locationName) {
        if (locations.containsKey(locationName)) {
            currentLocation = locations.get(locationName);
            currentLocation.discover(); // Mark location as discovered
            System.out.println("You have traveled to " + currentLocation.getName() + ". " + currentLocation.getDescription());
            gameState.modifySanity(5); // Gain sanity for exploring new places
            randomEventSystem.triggerEvent();
        } else {
            System.out.println("That location does not exist.");
        }
        commandSystem.listenForCommand();
    }

    public void startCombat(Enemy enemy) {
        combatSystem.startCombat(enemy);
    }

    public void callAllyAction(String allyName) {
        for (Character ally : allies) {
            if (ally.getName().equalsIgnoreCase(allyName)) {
                if (ally instanceof Helio) {
                    ((Helio) ally).performArrowStrike();
                    gameState.modifyRelationship("Helio", 5); // Improve relationship for using Helio's skill
                } else if (ally instanceof Mylo) {
                    ((Mylo) ally).revealSecret();
                    gameState.modifyRelationship("Mylo", 5); // Improve relationship for consulting Mylo
                }
                return;
            }
        }
        System.out.println("No ally by that name is available.");
    }

    public void confrontVaelcarn() {
        vaelcarn.displayPresence();
        if (gameState.getSanityLevel() < 30) {
            System.out.println("Your low sanity weakens your resolve. Vaelcarn's taunts shake you to your core.");
        } else {
            System.out.println("Vaelcarn speaks: 'Queen Elara, you cannot undo the sands of time. Prepare yourself.'");
        }
        combatSystem.startCombat(vaelcarn);
    }

    public void performRitual(String ritualType) {
        if (gameState.isRitualCompleted(ritualType)) {
            System.out.println("You have already performed the " + ritualType + " ritual.");
        } else if (gameState.hasItem("Ritual Component")) {
            System.out.println("You perform the " + ritualType + " ritual, channeling the energies of " + ritualType + ".");
            gameState.completeRitual(ritualType);
            gameState.modifySanity(10); // Gain sanity for successfully performing the ritual
            vaelcarn.corrupt(); // Strengthens Vaelcarn
        } else {
            System.out.println("You lack the necessary components to perform this ritual.");
        }
    }

    public void displayGameState() {
        gameState.displayGameState(); // Utility to show the current state of the game
    }

    public Vaelcarn getVaelcarn() {
        return vaelcarn;
    }
}
