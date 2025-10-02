import java.util.*;

public class CommandSystem {
    private Player player;
    private GameState gameState;

    public CommandSystem(Player player, GameState gameState) {
        this.player = player;
        this.gameState = gameState;
    }

    public void executeCommand(String command) {
        switch (command.toLowerCase()) {
            case "perform ritual":
                if (player.canPerformRitual()) {
                    gameState.completeRitual("Elemental Rite");
                    player.modifySanity(-15);
                    System.out.println("The ritual surges through the desert. You feel its power—and its cost.");
                } else {
                    System.out.println("You are not in a stable state to perform a ritual.");
                }
                break;

            case "use relic":
                player.getInventoryManager().useItem("Desertglass Shard", player);
                break;

            case "summon elemental":
                if (player.isElementCompatible("fire")) {
                    System.out.println("You summon a blazing elemental to aid you.");
                    player.modifySanity(-10);
                } else {
                    System.out.println("Your elemental affinity is misaligned. The summoning fails.");
                }
                break;

            case "share vision":
                if (player.getRelationshipManager().isTrustedBy("Helio")) {
                    System.out.println("You share a vision with Helio. He offers insight into the Entity’s origin.");
                    gameState.setFlag("sharedVisionWithHelio");
                } else {
                    System.out.println("Helio is not ready to receive your vision.");
                }
                break;

            case "equip crown":
                gameState.equipCrown();
                break;

            case "awaken vaelcarn":
                gameState.awakenVaelcarn();
                break;

            case "check status":
                player.displayStats();
                gameState.displayGameState();
                break;

            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}

