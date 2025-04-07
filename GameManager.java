import java.util.*;

public class GameManager {
    private GameState gameState; // Tracks overall game state
    private ChoiceSystem choiceSystem;
    private CutsceneManager cutsceneManager;
    private Player player; // Main character (Queen Elara)
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
        Location potentialLocation = locations.get(locationName);

        // Accessibility check
        if (!potentialLocation.isAccessible()) {
            System.out.println("You can't go there yet. Something blocks your way.");
            return; // Exit early if the location is not accessible
        }

        // Proceed to move if accessible
        currentLocation = potentialLocation;
        currentLocation.discover(); // Mark location as discovered
        currentLocation.displayLocationDetails(); // Show location details
        gameState.modifySanity(5); // Add a sanity boost for exploration
        randomEventSystem.triggerEvent(); // Trigger potential events
    } else {
        System.out.println("That location does not exist.");
    }
    commandSystem.listenForCommand(); // Wait for the next player command
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
        System.out.println("The air grows heavy as you approach the Eternal Dunes.");
        System.out.println("A shadow looms on the horizon. The sands themselves seem to writhe with malice.");
        
        // Vaelcarn makes its presence known
        vaelcarn.displayPresence();
        if (gameState.getSanityLevel() < 30) {
            System.out.println("Vaelcarn whispers: 'Your mind is weak, Queen Elara. Surrender to the sands.'");
        } else {
            System.out.println("Vaelcarn roars: 'You cannot escape your fate, mortal. Face your doom.'");
        }

        // Initial confrontation dialogue
        System.out.println("What will you do?");
        System.out.println("- Use Crown of Foresight");
        System.out.println("- Invoke Elemental Power");
        System.out.println("- Attack with allies");
        System.out.println("- Defend and observe");

        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine().toLowerCase();

        switch (action) {
            case "use crown of foresight":
                if (gameState.isCrownEquipped()) {
                    System.out.println("You channel the Crown's power, gaining insight into Vaelcarn's weakness.");
                    System.out.println("Vaelcarn falters briefly, the sands stilling for but a moment.");
                    gameState.modifySanity(10); // Gain sanity for using the Crown strategically
                } else {
                    System.out.println("The Crown of Foresight is not equipped!");
                }
                break;
            case "invoke elemental power":
                System.out.println("You summon the power of " + player.getElementalAffinity() + ", striking at Vaelcarn!");
                vaelcarn.takeDamage(20); // Deals damage to Vaelcarn
                break;
            case "attack with allies":
                System.out.println("You call upon your allies for aid.");
                callAllyAction("Helio");
                callAllyAction("Mylo");
                vaelcarn.takeDamage(30); // Allies contribute to the battle
                break;
            case "defend and observe":
                System.out.println("You brace yourself, observing Vaelcarn's movements.");
                System.out.println("The sands swirl violently, but you sense an opening...");
                gameState.modifySanity(5); // Gain a small sanity boost for remaining calm
                break;
            default:
                System.out.println("Invalid action. Vaelcarn strikes while you hesitate!");
                player.takeDamage(15); // Player takes damage for an invalid action
                break;
        }

        // Check Vaelcarn's health and escalate the battle
        if (vaelcarn.getHealth() > 0) {
            System.out.println("Vaelcarn roars in defiance, growing stronger!");
            vaelcarn.corrupt(); // Increase corruption to raise stakes
            combatSystem.startCombat(vaelcarn); // Proceed to full combat
        } else {
            System.out.println("Vaelcarn collapses, its shadow fading into the sands.");
            System.out.println("The Eternal Dunes fall silent, and the Kingdom of Aridia is saved.");
        }
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
