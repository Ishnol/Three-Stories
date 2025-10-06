public class QueenElara extends Character {
    private String elementalAffinity;
    private boolean crownOfForesight;

    public QueenElara(String name, int health, int mana, int sanity, int attackPower, int defense, String elementalAffinity) {
        super(name, health, mana, sanity, attackPower, defense);
        this.elementalAffinity = elementalAffinity;
        this.crownOfForesight = false;
    }

    public void equipCrown() {
        this.crownOfForesight = true;
        System.out.println(name + " equips the Crown of Foresight!");
    }

    public void unequipCrown() {
        this.crownOfForesight = false;
        System.out.println(name + " removes the Crown of Foresight.");
    }

    public boolean hasCrownEquipped() {
        return crownOfForesight;
    }

    public String getElementalAffinity() {
        return elementalAffinity;
    }

    public void useElementalPower(String element) {
        if (mana > 10) {
            mana -= 10;
            System.out.println(name + " unleashes the power of " + element + "!");
        } else {
            System.out.println("Not enough mana to use elemental power.");
        }
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Elemental Affinity: " + elementalAffinity);
        System.out.println("Crown of Foresight: " + (crownOfForesight ? "Equipped" : "Not Equipped"));
    }
}


