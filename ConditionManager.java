import java.util.*;

public class ConditionManager {
    private Set<String> conditions;

    public ConditionManager() {
        this.conditions = new HashSet<>();
    }

    // Add a condition
    public void addCondition(String condition) {
        conditions.add(condition.toLowerCase());
        System.out.println("Condition added: " + condition);
    }

    // Remove a condition
    public void removeCondition(String condition) {
        conditions.remove(condition.toLowerCase());
        System.out.println("Condition removed: " + condition);
    }

    // Check if a condition is active
    public boolean hasCondition(String condition) {
        return conditions.contains(condition.toLowerCase());
    }

    // Display all active conditions
    public void displayConditions() {
        System.out.println("Active Conditions:");
        if (conditions.isEmpty()) {
            System.out.println("- None");
        } else {
            for (String condition : conditions) {
                System.out.println("- " + condition);
            }
        }
    }

    // Optional: trigger effects based on condition
    public void checkConditionEffects() {
        if (hasCondition("cursed")) {
            System.out.println("Dark energy surrounds you. Rituals may fail.");
        }
        if (hasCondition("blessed")) {
            System.out.println("You feel protected. Sanity loss is reduced.");
        }
        if (hasCondition("exhausted")) {
            System.out.println("Your body aches. Combat effectiveness is lowered.");
        }
        if (hasCondition("inspired")) {
            System.out.println("Your spirit soars. Allies respond with renewed vigor.");
        }
    }
}
