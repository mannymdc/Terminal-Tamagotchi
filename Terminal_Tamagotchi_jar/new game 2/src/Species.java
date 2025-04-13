public enum Species {
    DOG("🐶"), CAT("🐱"), HAMSTER("🐹"), LIZARD("🦎");

    private final String emoji;

    Species(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
