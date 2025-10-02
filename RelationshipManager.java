import java.util.*;

public class RelationshipManager {
    private Map<String, Integer> relationships;

    public RelationshipManager() {
        this.relationships = new HashMap<>();
    }

    // Update relationship level
    public void updateRelationship(String allyName, int change) {
        int newLevel = relationships.getOrDefault(allyName, 50) + change; // Neutral baseline
        newLevel = Math.max(0, Math.min(100, newLevel)); // Clamp between 0 and 100
        relationships.put(allyName, newLevel);
        System.out.println(allyName + "'s relationship level is now: " + newLevel);
    }

    // Get current relationship level
    public int getRelationshipLevel(String allyName) {
        return relationships.getOrDefault(allyName, 50);
    }

    // Check trust threshold
    public boolean isTrustedBy(String allyName) {
        return getRelationshipLevel(allyName) >= 75;
    }

    // Display all relationships
    public void displayRelationships() {
        System.out.println("Relationships:");
        for (Map.Entry<String, Integer> entry : relationships.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Optional: trigger emotional events
    public void checkEmotionalTriggers(String allyName) {
        int level = getRelationshipLevel(allyName);
        if (level >= 90) {
            System.out.println(allyName + " is deeply loyal. They reveal a hidden truth.");
        } else if (level <= 20) {
            System.out.println(allyName + " is distant. They may abandon you in crisis.");
        }
    }
}
