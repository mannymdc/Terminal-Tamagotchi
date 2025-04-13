import java.io.*;
import java.util.*;

public class SaveManager {
    private static final String FILE_NAME = "pet_save.txt";

    public static void savePet(Pet pet) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(pet.getName());
            writer.println(pet.getStage());
            writer.println(pet.getHunger());
            writer.println(pet.getHappiness());
            writer.println(pet.getCleanliness());
            writer.println(pet.getEnergy());
            writer.println(pet.getCoins());
            writer.println(pet.getSpecies().name());
            writer.println(pet.getPersonality().name());

            Map<ItemType, Integer> items = pet.getInventory().getItemMap();
            for (ItemType item : ItemType.values()) {
                writer.println(item.name() + "=" + items.getOrDefault(item, 0));
            }

            System.out.println("💾 Game saved!");
        } catch (IOException e) {
            System.out.println("❌ Failed to save pet.");
        }
    }

    public static Pet loadPet() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return null;

        try (Scanner reader = new Scanner(file)) {
            String name = reader.nextLine();
            String stage = reader.nextLine();
            int hunger = Integer.parseInt(reader.nextLine());
            int happiness = Integer.parseInt(reader.nextLine());
            int cleanliness = Integer.parseInt(reader.nextLine());
            int energy = Integer.parseInt(reader.nextLine());
            int coins = Integer.parseInt(reader.nextLine());
            Species species = Species.valueOf(reader.nextLine());
            Personality personality = Personality.valueOf(reader.nextLine());

            Map<ItemType, Integer> itemMap = new HashMap<>();
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split("=");
                itemMap.put(ItemType.valueOf(parts[0]), Integer.parseInt(parts[1]));
            }

            Pet pet = new Pet(name, stage, hunger, happiness, cleanliness, energy, coins, species, personality, itemMap);
            System.out.println("✅ Game loaded!");
            return pet;
        } catch (Exception e) {
            System.out.println("❌ Failed to load pet. Starting new game.");
            return null;
        }
    }
}
