public class Location {
    private String name; // Location name
    private String description; // Description of the location
    private boolean isDiscovered; // Tracks if the location has been discovered
    private boolean accessible; // Tracks if the location is accessible

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.isDiscovered = false; // Locations start as undiscovered
        this.accessible = true; // By default, locations are accessible
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void discover() {
        if (!isDiscovered) {
            isDiscovered = true;
            System.out.println("You have discovered: " + name);
        } else {
            System.out.println(name + " has already been discovered.");
        }
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public void displayLocationDetails() {
        System.out.println("Location: " + name);
        System.out.println("Description: " + description);
        System.out.println("Status: " + (isDiscovered ? "Discovered" : "Undiscovered"));
        System.out.println("Accessibility: " + (accessible ? "Accessible" : "Blocked"));
    }
}
