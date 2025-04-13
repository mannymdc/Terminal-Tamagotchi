public enum Personality {
    CHEERFUL,
    LAZY,
    MISCHIEVOUS,
    PICKY;

    public String getDescription() {
        switch (this) {
            case CHEERFUL:
                return "Gets extra happiness from play!";
            case LAZY:
                return "Loses energy slower, hates play.";
            case MISCHIEVOUS:
                return "Loves chaos, more random events.";
            case PICKY:
                return "Gets less from food, hates getting dirty.";
            default:
                return "";
        }
    }
}
