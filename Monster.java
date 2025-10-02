public class Monster extends Character {
    private String habitat;
    private String description;
    private boolean isHostile;

    public Monster(String name, int health, int mana, int sanity, int attackPower, int defense,
                   String habitat, String description, boolean isHostile) {
        super(name, health, mana, sanity, attackPower, defense);
        this.habitat = habitat;
        this.description = description;
        this.isHostile = isHostile;
    }

    // === Getters ===
    public String getHabitat() {
        return habitat;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHostile() {
        return isHostile;
    }

    // === Encounter Logic ===
    public void encounter() {
        System.out.println("You encounter " + name + " in the " + habitat + ".");
        System.out.println(description);
        if (isHostile) {
            System.out.println(name + " snarls and prepares to attack!");
        } else {
            System.out.println(name + " watches you silently, uncertain.");
        }
    }

    // === Combat Override ===
    @Override
    public int attack() {
        int damage = attackPower + (int)(Math.random() * 10);
        System.out.println(name + " attacks fiercely for " + damage + " damage!");
        return damage;
    }

    // === Display Monster Stats ===
    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Habitat: " + habitat);
        System.out.println("Hostile: " + (isHostile ? "Yes" : "No"));
        System.out.println("Description: " + description);
    }
}
