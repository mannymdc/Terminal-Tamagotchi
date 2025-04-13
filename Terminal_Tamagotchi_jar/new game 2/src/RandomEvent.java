import java.util.Random;

public class RandomEvent {
    private static final Random rand = new Random();

    public static void checkForEvent(Pet pet) {
        if (!pet.isAlive()) return;

        int chance = rand.nextInt(100); // 0 to 99

        if (chance < 20) { // 20% chance of a random event
            int eventType = rand.nextInt(4); // 0 to 3

            switch (eventType) {
                case 0:
                    Utils.printMessage("🍪 " + pet.getName() + " found a snack on the floor!", 25);
                    pet.feed(); // acts like a free feed
                    break;

                case 1:
                    Utils.printMessage("🦋 " + pet.getName() + " got excited chasing a butterfly!", 25);
                    pet.play(); // acts like a free play
                    break;

                case 2:
                    Utils.printMessage("😷 Uh oh... " + pet.getName() + " is feeling sick...", 25);
                    pet.decreaseEnergy(20);
                    pet.decreaseHappiness(15);
                    pet.decreaseCleanliness(30);
                    break;

                case 3:
                    ItemType randomItem = ItemType.values()[rand.nextInt(ItemType.values().length)];
                    Utils.printMessage("🎁 " + pet.getName() + " found an item: " + randomItem.getEmoji() + " " + randomItem + "!", 25);
                    pet.getInventory().addItem(randomItem);
                    break;
            }
        }
    }
}
