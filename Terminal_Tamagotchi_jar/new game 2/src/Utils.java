// Utils.java
import java.util.Scanner;

public class Utils {

    // Delay in milliseconds (like Thread.sleep but safe)
    public static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("⚠️ Something interrupted the wait.");
        }
    }

    // Divider line for cleaner output
    public static void printDivider() {
        System.out.println("\n-----------------------------\n");
    }

    // Show title screen (optional flair)
    public static void showTitle() {
        System.out.println("╔═══════════════════════════╗");
        System.out.println("      🐾 Terminal Pet 🐾    ");
        System.out.println("╚═══════════════════════════╝");
    }

    // Basic message printing (no delay)
    public static void printMessage(String message) {
        System.out.println(message);
    }

    // Stylized message printing
    public static void printMessage(String message, int delayPerCharMs) {
        for (char c : message.toCharArray()) {
            System.out.print(c);
            wait(delayPerCharMs);
        }
        System.out.println();
    }

    public static void showHelp() {
        printDivider();
        System.out.println("📖 HOW TO PLAY - TERMINAL TAMAGOTCHI\n");

        System.out.println("💡 GOAL: Take care of your pet and keep it happy for as long as you can!");
        System.out.println("\n📊 STATS:");
        System.out.println(" - Hunger: Feed your pet to keep it full.");
        System.out.println(" - Happiness: Play or give it toys to cheer it up.");
        System.out.println(" - Cleanliness: Clean your pet so it stays healthy.");
        System.out.println(" - Energy: Let your pet rest or use medicine.");

        System.out.println("\n🎭 PERSONALITY & SPECIES:");
        System.out.println(" - Each pet is randomly assigned a species and personality.");
        System.out.println(" - Personalities affect how actions work! (Lazy pets dislike playing, etc.)");

        System.out.println("\n🎒 INVENTORY:");
        System.out.println(" - Items can be found randomly or bought in the shop.");
        System.out.println(" - Use FOOD, TOY, SOAP, or MEDICINE to help your pet.");

        System.out.println("\n🛍️ SHOP:");
        System.out.println(" - Earn coins by taking good care of your pet.");
        System.out.println(" - Spend coins to buy useful items to help your pet.");

        System.out.println("\n🧬 EVOLUTION:");
        System.out.println(" - Your pet evolves over time: Egg → Baby → Child → Teen → Adult");

        System.out.println("\n💀 DEATH?");
        System.out.println(" - Pets no longer die! If stats hit 0, your pet just gets very sad. You can recover.");

        System.out.println("\n✅ TIPS:");
        System.out.println(" - Use items to recover stats fast.");
        System.out.println(" - Lazy pets can still be happy with TOYS or naps!");
        System.out.println(" - Keep all stats above 25 to earn coins.");

        printDivider();
    }

    public static void pressEnter(Scanner scanner) {
        System.out.print("\n🔄 Press Enter to continue...");
        scanner.nextLine();
        printDivider();
    }



}
