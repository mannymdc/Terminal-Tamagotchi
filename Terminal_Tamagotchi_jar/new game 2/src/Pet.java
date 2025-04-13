import java.util.Map;
import java.util.Random;

public class Pet {
    private String stage;
    private int turnCounter;
    private String name;
    private int hunger;
    private int happiness;
    private int cleanliness;
    private int energy;
    private int coins;

    private final Species species;
    private final Personality personality;
    private final Inventory inventory = new Inventory();

    // Constructor for new pet
    public Pet(String name) {
        this.stage = "Egg";
        this.turnCounter = 0;
        this.name = name;
        this.hunger = 50;
        this.happiness = 50;
        this.cleanliness = 50;
        this.energy = 50;
        this.coins = 0;
        this.species = Species.values()[new Random().nextInt(Species.values().length)];
        this.personality = Personality.values()[new Random().nextInt(Personality.values().length)];

        Utils.printMessage("🧬 Species: " + species + " " + species.getEmoji(), 25);
        Utils.printMessage("🧠 Personality: " + personality + " - " + personality.getDescription(), 25);
    }

    // Constructor for loading saved pet
    public Pet(String name, String stage, int hunger, int happiness, int cleanliness, int energy, int coins,
               Species species, Personality personality, Map<ItemType, Integer> inventoryData) {
        this.name = name;
        this.stage = stage;
        this.turnCounter = 0;
        this.hunger = hunger;
        this.happiness = happiness;
        this.cleanliness = cleanliness;
        this.energy = energy;
        this.coins = coins;
        this.species = species;
        this.personality = personality;

        for (ItemType item : inventoryData.keySet()) {
            inventory.getItemMap().put(item, inventoryData.get(item));
        }

        Utils.printMessage("🔁 Loaded pet: " + name + " the " + species.getEmoji() + "!", 25);
    }

    public void feed() {
        int foodValue = (personality == Personality.PICKY) ? 10 : 20;
        hunger = Math.min(hunger + foodValue, 100);
        energy = Math.min(energy + 10, 100);
        System.out.println(name + " enjoyed the meal!");
    }

    public void play() {
        if (personality == Personality.LAZY) {
            happiness = Math.min(happiness + 5, 100); // small boost for lazy
            energy = Math.max(energy - 5, 0);
            hunger = Math.max(hunger - 5, 0);
            cleanliness = Math.max(cleanliness - 5, 0);
            System.out.println(name + " reluctantly played... a little 😒");
            return;
        }

        happiness = Math.min(happiness + 20, 100);
        if (personality == Personality.CHEERFUL) {
            happiness = Math.min(happiness + 10, 100); // bonus
        }

        energy = Math.max(energy - 15, 0);
        hunger = Math.max(hunger - 10, 0);
        cleanliness = Math.max(cleanliness - 10, 0);
        System.out.println(name + " had so much fun playing!");
    }

    public void clean() {
        cleanliness = 100;
        System.out.println(name + " is squeaky clean!");
    }

    public void rest() {
        energy = Math.min(energy + 30, 100);
        hunger = Math.max(hunger - 15, 0);

        if (personality == Personality.LAZY) {
            happiness = Math.min(happiness + 10, 100);
            System.out.println(name + " is extra happy after napping 😌");
        } else {
            System.out.println(name + " is well rested.");
        }
    }

    public void tick() {
        hunger = Math.max(hunger - 5, 0);
        happiness = Math.max(happiness - 5, 0);
        cleanliness = Math.max(cleanliness - 3, 0);
        energy = Math.max(energy - 5, 0);

        if (hunger >= 25 && happiness >= 25 && cleanliness >= 25 && energy >= 25) {
            addCoins(20);
        }

        if (hunger == 0 || happiness == 0 || cleanliness == 0 || energy == 0) {
            System.out.println("😢 " + name + " is feeling really sad and neglected...");
        }

        checkEvolution();
    }

    public void checkEvolution() {
        turnCounter++;

        if (turnCounter == 3) {
            stage = "Baby";
            Utils.printMessage("🥚 Your egg has hatched into a Baby!", 25);
        } else if (turnCounter == 8) {
            stage = "Child";
            Utils.printMessage("🧒 " + name + " has grown into a Child!", 25);
        } else if (turnCounter == 15) {
            stage = "Teen";
            Utils.printMessage("🧑 " + name + " is now a rebellious Teen!", 25);
        } else if (turnCounter == 25) {
            stage = "Adult";
            Utils.printMessage("🧔 " + name + " has matured into an Adult!", 25);
        }
    }

    public void decreaseEnergy(int amount) {
        energy = Math.max(energy - amount, 0);
    }

    public void decreaseHappiness(int amount) {
        happiness = Math.max(happiness - amount, 0);
    }

    public void decreaseCleanliness(int amount) {
        cleanliness = Math.max(cleanliness - amount, 0);
    }

    public void increaseHunger(int amount) {
        hunger = Math.min(hunger + amount, 100);
    }

    public void increaseHappiness(int amount) {
        happiness = Math.min(happiness + amount, 100);
    }

    public void increaseCleanliness(int amount) {
        cleanliness = Math.min(cleanliness + amount, 100);
    }

    public void restoreFromSickness() {
        energy = Math.min(energy + 30, 100);
        happiness = Math.min(happiness + 20, 100);
        cleanliness = Math.min(cleanliness + 30, 100);
        System.out.println(name + " has recovered with some medicine!");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
        System.out.println("💰 You earned " + amount + " coins! Total: " + coins);
    }

    public void spendCoins(int amount) {
        coins -= amount;
    }

    private String getMoodFace() {
        if (hunger == 0 || happiness == 0 || cleanliness == 0 || energy == 0) return "😢 (Very Sad)";
        if (hunger <= 20) return "😩 (Hungry)";
        if (happiness <= 20) return "😭 (Sad)";
        if (cleanliness <= 30) return "😷 (Dirty)";
        if (energy <= 20) return "😴 (Tired)";
        if (happiness >= 80) return "😄 (Happy)";
        return "🙂 (Okay)";
    }

    private String getAsciiArt() {
        switch (stage) {
            case "Egg":
                return "   _____ \n  /     \\\n |  0 0  |\n  \\_____/";
            case "Baby":
                return "   (｡•ᴗ•｡)   \n  /       \\ \n  \\_____/";
            case "Child":
                return " (•ᴗ•)   \n /|_|\\  \n  / \\   ";
            case "Teen":
                return " (⌐■_■)  \n /| |\\  \n  / \\   ";
            case "Adult":
                return " (•_•)   \n /|\\|\\ \n  / \\  ";
            default:
                return " (x_x)   ";
        }
    }

    public void showStatus() {
        System.out.println("\n📊 Status of " + name + ":");
        System.out.println("Stage:       " + stage);
        System.out.println("Mood:        " + getMoodFace());
        System.out.println("Species:     " + species + " " + species.getEmoji());
        System.out.println("Personality: " + personality + " - " + personality.getDescription());
        System.out.println("Hunger:      " + hunger + "%");
        System.out.println("Happiness:   " + happiness + "%");
        System.out.println("Cleanliness: " + cleanliness + "%");
        System.out.println("Energy:      " + energy + "%");
        System.out.println("Coins:       " + coins);
        System.out.println("\n" + getAsciiArt());
    }

    public boolean isAlive() {
        return true;
    }

    public String getName() {
        return name;
    }

    // Save system getters
    public String getStage() { return stage; }
    public int getHunger() { return hunger; }
    public int getHappiness() { return happiness; }
    public int getCleanliness() { return cleanliness; }
    public int getEnergy() { return energy; }
    public Species getSpecies() { return species; }
    public Personality getPersonality() { return personality; }
}