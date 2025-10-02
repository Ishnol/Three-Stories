public class Location {
    private String name;
    private String description;
    private boolean isDiscovered;
    private boolean accessible;

    // New fields
    private String elementalAffinity; // e.g., "Fire", "Water", "Air", "Earth"
    private int dangerLevel; // Scale from 0 (safe) to 10 (deadly)
    private boolean ritualCompatible; // Can rituals be performed here?
    private String ambientEffect; // e.g., "Sanity Drain", "Vision Boost", "Elemental Surge"

    public Location(String name, String description, String elementalAffinity, int dangerLevel,
                    boolean ritualCompatible, String ambientEffect) {
        this.name = name;
        this.description = description;
        this.elementalAffinity = elementalAffinity;
        this.dangerLevel = dangerLevel;
        this.ritualCompatible = ritualCompatible;
        this.ambientEffect = ambientEffect;
        this.isDiscovered = false;
        this.accessible = true;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isDiscovered() { return isDiscovered; }
    public boolean isAccessible() { return accessible; }
    public String getElementalAffinity() { return elementalAffinity; }
    public int getDangerLevel() { return dangerLevel; }
    public boolean isRitualCompatible() { return ritualCompatible; }
    public String getAmbientEffect() { return ambientEffect; }

    // Setters
    public void setAccessible(boolean accessible) { this.accessible = accessible; }
    public void setDangerLevel(int dangerLevel) { this.dangerLevel = dangerLevel; }

    // Discover method
    public void discover() {
        if (!isDiscovered) {
            isDiscovered = true;
            System.out.println("You have discovered: " + name);
        } else {
            System.out.println(name + " has already been discovered.");
        }
    }

    // Display method
    public void displayLocationDetails() {
        System.out.println("Location: " + name);
        System.out.println("Description: " + description);
        System.out.println("Elemental Affinity: " + elementalAffinity);
        System.out.println("Danger Level: " + dangerLevel + "/10");
        System.out.println("Ritual Compatible: " + (ritualCompatible ? "Yes" : "No"));
        System.out.println("Ambient Effect: " + ambientEffect);
        System.out.println("Status: " + (isDiscovered ? "Discovered" : "Undiscovered"));
        System.out.println("Accessibility: " + (accessible ? "Accessible" : "Blocked"));
    }
}
