public class Mylo extends Character {
    private String knowledgeSpecialty;

    public Mylo(String name, int health, int mana, int sanity, int attackPower, int defense, String knowledgeSpecialty) {
        super(name, health, mana, sanity, attackPower, defense);
        this.knowledgeSpecialty = knowledgeSpecialty;
    }

    public void revealSecret() {
        System.out.println(name + " uses their knowledge of " + knowledgeSpecialty + " to uncover hidden truths!");
        this.onCooldown = true;
    }

    public String getKnowledgeSpecialty() {
        return knowledgeSpecialty;
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Knowledge Specialty: " + knowledgeSpecialty);
    }
}

