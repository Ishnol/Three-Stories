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
    private Player player;
    private Location currentLocation;

    public GameManager() {
        gameState = new GameState();
        choiceSystem = new ChoiceSystem();
        cutsceneManager = new CutsceneManager();
        player = new Player("Elara");
        currentLocation = new Location("Royal Palace");
    }

    public void startGame() {
        cutsceneManager.playIntroCutscene();
        gameState.initialize();
        runGameLoop();
    }

    private void runGameLoop() {
        System.out.println("Welcome to the game, " + player.getName() + "!");
        System.out.println("You are currently at " + currentLocation.getName());
    }
}

class GameState {
    public void initialize() {
        System.out.println("Game state initialized. Player begins their journey.");
    }
}

class ChoiceSystem {
    public void makeChoice(String choice) {
        System.out.println("Player made choice: " + choice);
    }
}

class CutsceneManager {
    public void playIntroCutscene() {
        System.out.println("A long time ago, in the kingdom of Aridia...");
    }
}

class Entity {
    protected String name;
    protected int health;

    public Entity(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }
}

class Player extends Entity {
    public Player(String name) {
        super(name, 100);
    }
}

class Location {
    private String name;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Item {
    private String itemName;

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
