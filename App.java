import java.util.*;

public class App {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}

class GameManager {
    private GameState gameState;
    private ChoiceSystem choiceSystem;
    private CutsceneManager cutsceneManager;
    private QueenElara player; // Updated to use the QueenElara subclass
    private Location currentLocation;
    private CommandSystem commandSystem;
    private Map<String, Location> locations;
    private RandomEventSystem randomEventSystem;
    private CombatSystem combatSystem;
    private List<Character> allies; // For Helio and Mylo

    public GameManager() {
        gameState = new GameState();
        choiceSystem = new ChoiceSystem();
        cutsceneManager = new CutsceneManager();
        player = new QueenElara("Elara", 100, 50, 100, 15, 5, "Fire"); // Create Queen Elara
        initializeLocations();
        initializeAllies(); // Add allies
        currentLocation = locations.get("Royal Palace");
        commandSystem = new CommandSystem(this);
        randomEventSystem = new RandomEventSystem(this);
        combatSystem = new CombatSystem(this, player);
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
        allies.add(new Helio("Helio", 80, 30, 100, 10, 3, 7)); // Add Helio with archery skill
        allies.add(new Mylo("Mylo", 70, 40, 100, 8, 2, "Artifacts")); // Add Mylo with knowledge specialty
    }

    public void startGame() {
        cutsceneManager.playIntroCutscene();
        gameState.initialize();
        runGameLoop();
    }

    private void runGameLoop() {
        System.out.println("Welcome to the game, " + player.getName() + "!");
        System.out.println("You are currently at " + currentLocation.getName());
        commandSystem.listenForCommand();
    }

    


   public void moveToLocation(String locationName) {
    String normalized = locationName.toLowerCase();
    for (String key : locations.keySet()) {
        if (key.toLowerCase().equals(normalized)) {
            Location potentialLocation = locations.get(key);

            // Check if the location is accessible
            if (!potentialLocation.isAccessible()) {
                System.out.println("You can't go there yet. Something blocks your way.");
                commandSystem.listenForCommand();
                return;
            }

            // Proceed to move if accessible
            currentLocation = potentialLocation;
            System.out.println("You have traveled to " + currentLocation.getName() + ". " + currentLocation.getDescription());
            randomEventSystem.triggerEvent();
            commandSystem.listenForCommand();
            return;
        }
    }
    System.out.println("That location does not exist.");
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
                } else if (ally instanceof Mylo) {
                    ((Mylo) ally).revealSecret();
                }
                return;
            }
        }
        System.out.println("No ally by that name is available.");
    }
   }
}
