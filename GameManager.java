public class GameManager {
    private GameState gameState;
    private Player player;
    private CommandSystem commandSystem;
    private Map<String, Location> locations;
    private Location currentLocation;
    private List<Character> allies;
    private Vaelcarn vaelcarn;
    private RandomEventSystem randomEventSystem;
    private CombatSystem combatSystem;
    private CutsceneManager cutsceneManager;
    private ChoiceSystem choiceSystem;

    public GameManager() {
        gameState = new GameState();
        player = new Player("Elara", 100, 50, 100, 15, 5, "Fire");
        commandSystem = new CommandSystem(player, gameState);
        cutsceneManager = new CutsceneManager();
        choiceSystem = new ChoiceSystem();
        randomEventSystem = new RandomEventSystem(this);
        combatSystem = new CombatSystem(this, player);
        vaelcarn = new Vaelcarn("Vaelcarn", 200, 100, 0, 25, 10, "An ancient force corrupted by forbidden magic.");
        initializeLocations();
        initializeAllies();
        currentLocation = locations.get("Royal Palace");
    }

    private void initializeLocations() {
        locations = new HashMap<>();
        locations.put("Royal Palace", new Location("Royal Palace", "The heart of Aridia."));
        locations.put("Oasis of Isolde", new Location("Oasis of Isolde", "A lush oasis of healing."));
        locations.put("Eternal Dunes", new Location("Eternal Dunes", "Endless sands hiding secrets."));
        locations.put("Ancient Ruins", new Location("Ancient Ruins", "A forgotten place of mystery."));
    }

    private void initializeAllies() {
        allies = new ArrayList<>();
        allies.add(new Helio("Helio", 80, 30, 100, 10, 3, 7));
        allies.add(new Mylo("Mylo", 70, 40, 100, 8, 2, "Artifacts"));
    }

    public void startGame() {
        cutsceneManager.playIntroCutscene();
        gameState.displayGameState();
        runGameLoop();
    }

    private void runGameLoop() {
        System.out.println("Welcome, " + player.getName() + "!");
        System.out.println("Current location: " + currentLocation.getName());
        currentLocation.displayLocationDetails();
        commandSystem.listenForCommand();
    }

  public void moveToLocation(String locationName) {
    if (!locations.containsKey(locationName)) {
        System.out.println("That location does not exist.");
        return;
    }

    Location target = locations.get(locationName);
    if (!target.isAccessible()) {
        System.out.println("You can't go there yet.");
        return;
    }

    currentLocation = target;
    currentLocation.discover();
    currentLocation.displayLocationDetails();
    gameState.modifySanity(5);

    // Trigger monster encounter
    List<Monster> monsters = currentLocation.getMonsters();
    if (monsters != null && !monsters.isEmpty()) {
        Monster encounter = monsters.get(new Random().nextInt(monsters.size()));
        encounter.encounter();
        if (encounter.isHostile()) {
            combatSystem.startCombat(encounter);
        }
    }

    // Continue game loop
    randomEventSystem.triggerEvent();
    commandSystem.listenForCommand();
}


        Location target = locations.get(locationName);
        if (!target.isAccessible()) {
            System.out.println("You can't go there yet.");
            return;
        }

        currentLocation = target;
        currentLocation.discover();
        currentLocation.displayLocationDetails();
        gameState.modifySanity(5);
        randomEventSystem.triggerEvent();
        commandSystem.listenForCommand();
    }

    public int callAllyAction(String allyName) {
        for (Character ally : allies) {
            if (ally.getName().equalsIgnoreCase(allyName)) {
                if (ally.isOnCooldown()) {
                    System.out.println(ally.getName() + " is recovering.");
                    return 0;
                }

                if (ally instanceof Helio) {
                    ((Helio) ally).performArrowStrike();
                    gameState.getRelationshipManager().updateRelationship("Helio", 5);
                    return 20;
                } else if (ally instanceof Mylo) {
                    ((Mylo) ally).revealSecret();
                    gameState.getRelationshipManager().updateRelationship("Mylo", 5);
                    return 10;
                }
            }
        }
        System.out.println("No ally by that name is available.");
        return 0;
    }

    public void performRitual(String ritualType) {
        if (gameState.getConditionManager().hasCondition("cursed")) {
            System.out.println("You are cursed. The ritual fails.");
            return;
        }

        if (gameState.isRitualCompleted(ritualType)) {
            System.out.println("You’ve already performed the " + ritualType + " ritual.");
        } else if (gameState.getInventoryManager().hasItem("Ritual Component")) {
            System.out.println("You perform the " + ritualType + " ritual.");
            gameState.completeRitual(ritualType);
            gameState.modifySanity(10);
            vaelcarn.corrupt();
        } else {
            System.out.println("You lack the components to perform this ritual.");
        }
    }

    public void confrontVaelcarn() {
        System.out.println("The air grows heavy as you approach the Eternal Dunes...");
        vaelcarn.displayPresence();

        if (gameState.getSanityLevel() < 30) {
            System.out.println("Vaelcarn whispers: 'Your mind is weak, Queen Elara.'");
        } else {
            System.out.println("Vaelcarn roars: 'Face your doom.'");
        }

        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine().toLowerCase();

        switch (action) {
            case "use crown of foresight":
                if (gameState.isCrownEquipped()) {
                    System.out.println("You channel the Crown’s power.");
                    gameState.modifySanity(10);
                } else {
                    System.out.println("The Crown is not equipped.");
                }
                break;
            case "invoke elemental power":
                System.out.println("You summon " + player.getElementalAffinity() + "!");
                vaelcarn.takeDamage(20);
                break;
            case "attack with allies":
                int damage = callAllyAction("Helio") + callAllyAction("Mylo");
                vaelcarn.takeDamage(damage);
                break;
            case "defend and observe":
                System.out.println("You brace yourself and observe...");
                gameState.modifySanity(5);
                break;
            default:
                System.out.println("Invalid action. The sands lash out!");
                player.takeDamage(15);
                break;
        }

        if (vaelcarn.getHealth() > 0) {
            System.out.println("Vaelcarn grows stronger!");
            vaelcarn.corrupt();
            combatSystem.startCombat(vaelcarn);
        } else {
            System.out.println("Vaelcarn collapses. The Kingdom of Aridia is saved.");
        }
    }

    public void displayGameState() {
        gameState.displayGameState();
    }

    public Vaelcarn getVaelcarn() {
        return vaelcarn;
    }
}
