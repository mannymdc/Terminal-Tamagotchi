import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Utils.showTitle();

        System.out.println("🐾 What would you like to do?");
        System.out.println("1. Start New Game");
        System.out.println("2. Load Saved Game");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine().trim();

        Pet pet;
        if (choice.equals("2")) {
            pet = SaveManager.loadPet();
            if (pet == null) {
                System.out.println("❌ No saved game found. Starting a new one!");
                System.out.print("Name your new pet: ");
                String name = scanner.nextLine().trim();
                pet = new Pet(name);
            }
        } else {
            System.out.print("Name your new pet: ");
            String name = scanner.nextLine().trim();
            pet = new Pet(name);
        }

        Utils.printMessage("\n🎉 You've adopted " + pet.getName() + "!");
        System.out.println("Take care of your pet!");

        while (pet.isAlive()) {
            pet.showStatus();

            System.out.println("\nChoose an action:");
            System.out.println("1. Feed");
            System.out.println("2. Play");
            System.out.println("3. Clean");
            System.out.println("4. Rest");
            System.out.println("5. Do nothing");
            System.out.println("6. Use Inventory");
            System.out.println("7. Visit Shop");
            System.out.println("8. How to Play");
            System.out.println("9. Save Game");

            System.out.print("Your choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    pet.feed();
                    Utils.pressEnter(scanner);
                    break;
                case "2":
                    pet.play();
                    Utils.pressEnter(scanner);
                    break;
                case "3":
                    pet.clean();
                    Utils.pressEnter(scanner);
                    break;
                case "4":
                    pet.rest();
                    Utils.pressEnter(scanner);
                    break;
                case "5":
                    System.out.println(pet.getName() + " stares at you in confusion...");
                    Utils.pressEnter(scanner);
                    break;
                case "6":
                    pet.getInventory().showInventory();
                    System.out.print("Enter item to use (FOOD, TOY, SOAP, MEDICINE): ");
                    String itemChoice = scanner.nextLine().trim().toUpperCase();
                    try {
                        ItemType item = ItemType.valueOf(itemChoice);
                        pet.getInventory().useItem(item, pet);
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Invalid item type.");
                    }
                    Utils.pressEnter(scanner);
                    break;
                case "7":
                    Shop.openShop(pet);
                    Utils.pressEnter(scanner);
                    break;
                case "8":
                    Utils.showHelp();
                    Utils.pressEnter(scanner);
                    break;
                case "9":
                    SaveManager.savePet(pet);
                    System.out.println("💾 Game saved manually!");
                    Utils.pressEnter(scanner);
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
                    Utils.pressEnter(scanner);
                    continue;
            }

            // Time passes
            pet.tick();

            // Random event may happen
            RandomEvent.checkForEvent(pet);

            // Delay and divider
            Utils.wait(1000);
            Utils.printDivider();
        }

        SaveManager.savePet(pet); // Auto-save on exit
        Utils.printMessage("\n📦 Progress saved. Thanks for playing!", 40);
        scanner.close();
    }
}
