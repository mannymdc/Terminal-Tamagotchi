import java.util.Scanner;

public class Shop {
    private static final Scanner scanner = new Scanner(System.in);

    public static void openShop(Pet pet) {
        System.out.println("\n🛍️ Welcome to the Pet Shop!");
        System.out.println("You have " + pet.getCoins() + " coins.");
        System.out.println("What would you like to buy?");
        System.out.println("1. 🍖 FOOD (10 coins)");
        System.out.println("2. 🧸 TOY (10 coins)");
        System.out.println("3. 🧼 SOAP (8 coins)");
        System.out.println("4. 💊 MEDICINE (15 coins)");
        System.out.println("5. ❌ Exit shop");
        System.out.print("Enter choice: ");

        String input = scanner.nextLine().trim();

        ItemType selectedItem = null;
        int cost = 0;

        switch (input) {
            case "1":
                selectedItem = ItemType.FOOD;
                cost = 10;
                break;
            case "2":
                selectedItem = ItemType.TOY;
                cost = 10;
                break;
            case "3":
                selectedItem = ItemType.SOAP;
                cost = 8;
                break;
            case "4":
                selectedItem = ItemType.MEDICINE;
                cost = 15;
                break;
            case "5":
                System.out.println("👋 Come back soon!");
                return;
            default:
                System.out.println("❌ Invalid choice.");
                return;
        }

        if (pet.getCoins() >= cost) {
            pet.spendCoins(cost);
            pet.getInventory().addItem(selectedItem);
            System.out.println("🛒 You bought a " + selectedItem.getEmoji() + " " + selectedItem + "!");
        } else {
            System.out.println("❌ Not enough coins.");
        }
    }
}
