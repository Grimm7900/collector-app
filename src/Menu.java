import java.util.Scanner;

public class Menu {

    public static boolean running = true;

    public static void runLoop() {
        Scanner scanner = new Scanner(System.in);

        // Create the player once so their party and PC stay saved
        Trainer Player = new Trainer("Timmy");

        // Have the player choose their starter Pokemon
        System.out.println("Welcome, please choose a starter \n");
        System.out.println("1. Charmander");
        System.out.println("2. Squirtle");
        System.out.println("3. Bulbasaur");

        String input = "";

        boolean starterLoop = true;

        while (starterLoop) {
            input = scanner.nextLine().toLowerCase();

            if (input.equals("1")) {
                Player.addCreature(Creature.starterCreature(1));
                System.out.println("\nYou chose Charmander!");
                starterLoop = false;

            } else if (input.equals("2")) {
                Player.addCreature(Creature.starterCreature(2));
                System.out.println("\nYou chose Squirtle!");
                starterLoop = false;

            } else if (input.equals("3")) {
                Player.addCreature(Creature.starterCreature(3));
                System.out.println("\nYou chose Bulbasaur!");
                starterLoop = false;

            } else {
                System.out.println("Invalid choice. Please choose 1, 2, or 3.");
            }
        }

        // Main game loop
        while (running) {

            // Generate a new random Pokemon encounter
            Creature encounter = Creature.randomCreature();

            System.out.println("\nA wild creature appeared!");
            System.out.println(encounter);

            // Ask the player if they want to catch the Pokemon
            boolean catchLoop = true;

            while (catchLoop) {

                System.out.println("\nDo you want to catch it? (Y/N)");
                input = scanner.nextLine().toLowerCase();

                if (input.equals("y")) {

                    if (Player.addCreature(encounter)) {
                        System.out.println("\nYou caught " + encounter.getName() + "!");
                    }

                    catchLoop = false;

                } else if (input.equals("n")) {

                    System.out.println("\nYou ran away!");
                    catchLoop = false;

                } else {

                    System.out.println("\nInvalid response.");
                }
            }

            // Give the player the option to view their party or PC
            boolean showTeamLoop = true;

            while (showTeamLoop) {

                System.out.println("\nWould you like to see your Party or PC?");
                System.out.println("1. Party");
                System.out.println("2. PC");
                System.out.println("3. Neither");

                input = scanner.nextLine();

                if (input.equals("1")) {

                    System.out.println("\nHere is your party:");
                    Player.printParty();
                    showTeamLoop = false;

                } else if (input.equals("2")) {

                    System.out.println("\nHere is your PC:");
                    Player.printPc();

                    // PC menu for searching and moving Pokemon
                    boolean pcMenuLoop = true;

                    while (pcMenuLoop) {

                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1. Search for a Pokemon by name");
                        System.out.println("2. Search for a Pokemon by type");
                        System.out.println("3. Move Pokemon to Party");
                        System.out.println("4. Exit PC");

                        input = scanner.nextLine().toLowerCase();

                        if (input.equals("1")) {

                            System.out.println("\nEnter the Pokemon name:");
                            String target = scanner.nextLine();

                            Creature found = Player.findPcByName(target);

                            if (found != null) {
                                System.out.println("\nPokemon found!");
                                System.out.println(found);
                            } else {
                                System.out.println("\nPokemon not found.");
                            }

                        } else if (input.equals("2")) {

                            System.out.println("\nEnter the Pokemon type:");
                            String target = scanner.nextLine();

                            Player.findPcByType(target);

                        } else if (input.equals("3")) {

                            if (Player.getPcSize() == 0) {
                                System.out.println("\nYour PC is empty!");
                            } else {

                                Player.printPc();

                                System.out.println("\nEnter the PC slot of the Pokemon you want:");
                                int pcSlot;

                                try {
                                    pcSlot = Integer.parseInt(scanner.nextLine());

                                    System.out.println("\nEnter the party slot you want to put it in:");
                                    int partySlot = Integer.parseInt(scanner.nextLine());

                                    Player.swapWithPC(partySlot - 1, pcSlot - 1);

                                } catch (NumberFormatException e) {
                                    System.out.println("\nPlease enter a number.");
                                }
                            }

                        } else if (input.equals("4")) {

                            System.out.println("\nLeaving PC.");
                            pcMenuLoop = false;

                        } else {

                            System.out.println("\nInvalid choice.");
                        }
                    }

                    showTeamLoop = false;

                } else if (input.equals("3")) {

                    System.out.println("\nOkay");
                    showTeamLoop = false;

                } else {

                    System.out.println("\nInvalid response.");
                }
            }

            // Ask the player if they want to continue finding Pokemon
            boolean endLoop = true;

            while (endLoop) {

                System.out.println("\nEnd run?");
                input = scanner.nextLine().toLowerCase();

                if (input.equals("y")) {

                    System.out.println("Okay, ending run");
                    running = false;
                    endLoop = false;

                } else if (input.equals("n")) {

                    System.out.println("Okay, finding another encounter");
                    endLoop = false;

                } else {

                    System.out.println("Invalid response.");
                }
            }
        }

        scanner.close();
    }
}