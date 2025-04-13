public enum ItemType {
    FOOD("🍖", "Heals hunger more"),
    TOY("🧸", "Boosts happiness more"),
    SOAP("🧼", "Fully restores cleanliness"),
    MEDICINE("💊", "Cures sickness");

    private final String emoji;
    private final String description;

    ItemType(String emoji, String description) {
        this.emoji = emoji;
        this.description = description;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getDescription() {
        return description;
    }
}
