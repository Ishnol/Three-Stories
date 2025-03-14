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
    private Player player;
    private Location currentLocation;
    private CommandSystem commandSystem;
    private Map<String, Location> locations;
    private RandomEventSystem randomEventSystem;
    private CombatSystem combatSystem;

    public GameManager() {
        gameState = new GameState();
        choiceSystem = new ChoiceSystem();
        cutsceneManager = new CutsceneManager();
        player = new Player("Elara");
        initializeLocations();
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
        if (locations.containsKey(locationName)) {
            currentLocation = locations.get(locationName);
            System.out.println("You have traveled to " + currentLocation.getName() + ". " + currentLocation.getDescription());
            randomEventSystem.triggerEvent();
        } else {
            System.out.println("That location does not exist.");
        }
        commandSystem.listenForCommand();
    }

    public void startCombat(Enemy enemy) {
        combatSystem.startCombat(enemy);
    }
}

class CombatSystem {
    private GameManager gameManager;
    private Player player;
    private Scanner scanner;

    public CombatSystem(GameManager gameManager, Player player) {
        this.gameManager = gameManager;
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    public void startCombat(Enemy enemy) {
        System.out.println("A " + enemy.getName() + " appears! Prepare for battle.");
        
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println("Choose an action: attack, defend, flee");
            String action = scanner.nextLine().toLowerCase();
            
            switch (action) {
                case "attack":
                    int playerDamage = player.attack();
                    enemy.takeDamage(playerDamage);
                    System.out.println("You hit the " + enemy.getName() + " for " + playerDamage + " damage!");
                    break;
                case "defend":
                    System.out.println("You brace yourself for the enemy's attack.");
                    break;
                case "flee":
                    System.out.println("You successfully escape the battle!");
                    return;
                default:
                    System.out.println("Invalid action. Try again.");
                    continue;
            }
            
            if (enemy.getHealth() > 0) {
                int enemyDamage = enemy.attack();
                player.takeDamage(enemyDamage);
                System.out.println("The " + enemy.getName() + " strikes you for " + enemyDamage + " damage!");
            }
        }
        
        if (player.getHealth() <= 0) {
            System.out.println("You have been defeated...");
            System.exit(0);
        } else {
            System.out.println("You defeated the " + enemy.getName() + "!");
        }
    }
}

class Enemy extends Entity {
    public Enemy(String name, int health) {
        super(name, health);
    }

    public int attack() {
        return new Random().nextInt(10) + 1; // Random damage 1-10
    }
}

class RandomEventSystem {
    private Random random;
    private GameManager gameManager;

    public RandomEventSystem(GameManager gameManager) {
        this.gameManager = gameManager;
        random = new Random();
    }

    public void triggerEvent() {
        int eventRoll = random.nextInt(100);

        if (eventRoll < 30) {
            System.out.println("A mysterious traveler appears and offers you advice.");
        } else if (eventRoll < 60) {
            System.out.println("You stumble upon an ancient artifact buried in the sand.");
        } else if (eventRoll < 90) {
            System.out.println("A sudden sandstorm forces you to take cover, delaying your journey.");
        } else {
            Enemy enemy = new Enemy("Desert Wraith", 30);
            gameManager.startCombat(enemy);
        }
    }
}

class Player extends Entity {
    public Player(String name) {
        super(name, 100);
    }

    public int attack() {
        return new Random().nextInt(15) + 5; // Random damage 5-15
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

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }
}
