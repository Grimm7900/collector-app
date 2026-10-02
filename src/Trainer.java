import java.util.ArrayList;

public class Trainer {

    private String name;
    private int activeCreature;

    // The Trainer manages the party and PC
    private ArrayList<Creature> creatureList;
    private ArrayList<Creature> pcList;

    public Trainer(String name) {
        this.name = name;
        creatureList = new ArrayList<Creature>();
        pcList = new ArrayList<Creature>();
    }

    // Adds a Pokemon to the party, or sends it to the PC if the party is full
    public boolean addCreature(Creature creature) {

        if (creatureList.size() >= 6) {

            System.out.println("\n" + "Your party is full!");
            System.out.println(creature.getName() + " was sent to the PC.");

            pcList.add(creature);

        } else {

            creatureList.add(creature);
        }

        return true;
    }

    // Moves a Pokemon from the party into the PC
    public boolean moveToPC(int partyIndex) {

        if (partyIndex < 0 || partyIndex >= creatureList.size()) {
            System.out.println("That party slot is empty or does not exist.");
            return false;
        }

        Creature creature = creatureList.get(partyIndex);

        pcList.add(creature);
        creatureList.remove(partyIndex);

        System.out.println(creature.getName() + " was moved to the PC.");

        return true;
    }

    // Moves a Pokemon from the PC into the party
    public boolean moveToParty(int pcIndex) {

        if (pcIndex < 0 || pcIndex >= pcList.size()) {
            System.out.println("That PC slot does not exist.");
            return false;
        }

        if (creatureList.size() >= 6) {
            System.out.println("Your party is full!");
            return false;
        }

        Creature creature = pcList.get(pcIndex);

        creatureList.add(creature);
        pcList.remove(pcIndex);

        System.out.println(creature.getName() + " was moved to your party.");

        return true;
    }

    // Removes a Pokemon from the party
    public boolean removeCreature(int partyIndex) {

        if (partyIndex < 0 || partyIndex >= creatureList.size()) {
            System.out.println("That party slot does not exist.");
            return false;
        }

        Creature removed = creatureList.remove(partyIndex);

        System.out.println(removed.getName() + " was removed from your party.");

        return true;
    }

    // Used by the menu to check if the PC has any Pokemon
    public int getPcSize() {
        return pcList.size();
    }

    // Moves a PC Pokemon into a specific party slot.
    // If that slot is occupied, the two Pokemon are swapped.
    public boolean swapWithPC(int partyIndex, int pcIndex) {

        if (partyIndex < 0 || partyIndex >= 6) {
            System.out.println("That party slot does not exist.");
            return false;
        }

        if (pcIndex < 0 || pcIndex >= pcList.size()) {
            System.out.println("That PC slot does not exist.");
            return false;
        }

        Creature pcCreature = pcList.get(pcIndex);

        // If the party slot is empty, just move the Pokemon there
        if (partyIndex >= creatureList.size()) {

            creatureList.add(pcCreature);
            pcList.remove(pcIndex);

            System.out.println(pcCreature.getName() + " was moved to your party.");

        } else {

            // If the slot is occupied, send that Pokemon to the PC
            Creature partyCreature = creatureList.get(partyIndex);

            pcList.add(partyCreature);

            // Replace the party Pokemon with the selected PC Pokemon
            creatureList.set(partyIndex, pcCreature);

            pcList.remove(pcIndex);

            System.out.println(
                    pcCreature.getName() +
                            " was moved to party slot " +
                            (partyIndex + 1) + "."
            );

            System.out.println(
                    partyCreature.getName() +
                            " was sent to the PC."
            );
        }

        return true;
    }

    // Displays all Pokemon currently stored in the PC
    public void printPc() {

        if (pcList.isEmpty()) {
            System.out.println("Your PC is empty!");
            return;
        }

        System.out.println("\n" + "========== PC ==========");

        for (int i = 0; i < pcList.size(); i++) {

            System.out.println(
                    "\n" + "PC Slot " + (i + 1) + ": " + pcList.get(i)
            );
        }
    }

    // Displays all six party slots, including empty slots
    public void printParty() {

        for (int i = 0; i < 6; i++) {

            System.out.println("\n" + "Slot " + (i + 1));

            if (i < creatureList.size()) {
                System.out.println(creatureList.get(i));

            } else {
                System.out.println(Creature.emptyCreature());
            }
        }
    }

    // Searches the PC for a Pokemon by name
    public Creature findPcByName(String target) {

        for (Creature c : pcList) {

            if (c.getName().equalsIgnoreCase(target)) {
                return c;
            }
        }

        return null;
    }

    // Searches the PC for a Pokemon by type
    public void findPcByType(String target) {
        boolean found = false;

        for (Creature c : pcList) {
            if (c.getType().equalsIgnoreCase(target)) {
                System.out.println("\nPokemon found!");
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo Pokemon of that type were found.");
        }
    }
}