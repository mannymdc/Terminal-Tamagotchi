import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<ItemType, Integer> items = new HashMap<>();

    public void addItem(ItemType type) {
        items.put(type, items.getOrDefault(type, 0) + 1);
        System.out.println("🧺 You received: " + type.getEmoji() + " " + type + "!");
    }

    public boolean useItem(ItemType type, Pet pet) {
        if (!items.containsKey(type) || items.get(type) <= 0) {
            System.out.println("❌ You don’t have any " + type + " left.");
            return false;
        }

        switch (type) {
            case FOOD:
                pet.increaseHunger(40);
                break;
            case TOY:
                pet.increaseHappiness(40);
                break;
            case SOAP:
                pet.increaseCleanliness(100);
                break;
            case MEDICINE:
                pet.restoreFromSickness();
                break;
        }

        items.put(type, items.get(type) - 1);
        System.out.println("✅ Used: " + type.getEmoji() + " " + type);
        return true;
    }

    public void showInventory() {
        System.out.println("\n📦 Inventory:");
        for (ItemType type : ItemType.values()) {
            int count = items.getOrDefault(type, 0);
            System.out.println("- " + type.getEmoji() + " " + type + " x" + count + " (" + type.getDescription() + ")");
        }
    }

    public Map<ItemType, Integer> getItemMap() {
        return items;
    }

}
