import java.util.Random;

public class Creature {

    // Information stored for each Pokemon
    private String name;
    private String type;
    private int level;
    private int hp;
    private int xp;
    private boolean isShiny = false;

    public Creature(String name, String type, int level, int hp, int xp, boolean isShiny) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hp = hp;
        this.xp = xp;
        this.isShiny = isShiny;
    }

    // Creates one of the three starter Pokemon
    public static Creature starterCreature(int choice) {

        if (choice == 1) {
            return new Creature("Charmander", "Fire", 5, 35, 0, false);

        } else if (choice == 2) {
            return new Creature("Squirtle", "Water", 5, 35, 0, false);

        } else {
            return new Creature("Bulbasaur", "Grass", 5, 35, 0, false);
        }
    }

    // Creates a random wild Pokemon with a random level and shiny chance
    public static Creature randomCreature() {

        Random randCreature = new Random();
        Random randShiny = new Random();

        int randomSpawnChance = randCreature.nextInt(1, 17);
        int randomShinyChance = randShiny.nextInt(1, 101);

        int levelOne = 0;
        int hpOne = 0;
        boolean isShinyOne = false;

        int levelTwo = 0;
        int hpTwo = 0;
        boolean isShinyTwo = false;

        int levelThree = 0;
        int hpThree = 0;
        boolean isShinyThree = false;

        // Gives the Pokemon a 1% chance to be shiny
        if (randomShinyChance == 100) {
            isShinyOne = true;
            isShinyTwo = true;
            isShinyThree = true;
        }

        // Decide which Pokemon and level range will spawn
        if (randomSpawnChance <= 8) {

            Random randLevelOne = new Random();
            levelOne = randLevelOne.nextInt(1, 6);
            hpOne = 25 + (2 * levelOne);

        } else if (randomSpawnChance < 12) {

            Random randLevelTwo = new Random();
            levelTwo = randLevelTwo.nextInt(4, 9);
            hpTwo = 23 + (2 * levelTwo);

        } else {

            Random randLevelThree = new Random();
            levelThree = randLevelThree.nextInt(7, 11);
            hpThree = 23 + (2 * levelThree);
        }

        // Return the Pokemon based on the random spawn chance
        if (randomSpawnChance <= 8) {

            return new Creature(
                    "Rattata", "Normal", levelOne, hpOne, 0, isShinyOne
            );

        } else if (randomSpawnChance < 12) {

            return new Creature(
                    "Shroomish", "Grass", levelTwo, hpTwo, 0, isShinyTwo
            );

        } else {

            return new Creature(
                    "Mudkip", "Water", levelThree, hpThree, 0, isShinyThree
            );
        }
    }

    // Attacks another creature based on the attacker's level
    public void attack(Creature target) {

        int damage = this.level * 2;

        target.setHp(target.getHp() - damage);

        System.out.println(this.name + " hit " + target.getName());
    }

    // Getters allow other classes to access private information
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHp() {
        return this.hp;
    }

    public int getXp() {
        return this.xp;
    }

    public boolean getIsShiny() {
        return this.isShiny;
    }

    // Prevent HP from going below zero
    public void setHp(int hp) {

        if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    public void levelUp() {
        this.level = level + 1;
    }

    // Prevent the creature from being set to an invalid level
    public void setLevel(int level) {

        if (level <= 0) {
            this.level = 1;
            this.hp = 23 + 2;

        } else {
            this.level = level;
            this.hp = 23 + (2 * level);
        }
    }

    // Add XP only when the amount is positive
    public void addXP(int exp) {

        if (exp > 0) {
            this.xp = this.xp + exp;
        }
    }

    public void damageCreature(int damage) {
        setHp(this.hp - damage);
    }

    // Controls how a Creature is displayed when printed
    @Override
    public String toString() {

        return "==================================" + "\n" +
                "Pokemon: " + name + "\n" +
                "Type: " + type + "\n" +
                "Level: " + level + "\n" +
                "HP: " + hp + "\n" +
                "XP: " + xp + "\n" +
                "Shiny: " + isShiny;
    }

    // Used when displaying an empty party slot
    public static String emptyCreature() {

        return """
            ==================================
            Pokemon: Empty
            Type: None
            Level: None
            HP: None
            XP: None
            Shiny: None""";
    }
}