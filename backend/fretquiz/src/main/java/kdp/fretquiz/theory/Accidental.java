package kdp.fretquiz.theory;

public enum Accidental {
    FLAT("b"),
    NONE(""),
    SHARP("#");

    private final static Accidental[] vals = values();

    private final String value;

    public static Accidental fromName(String name) {
        return switch (name) {
            case "b" -> FLAT;
            case "" -> NONE;
            case "#" -> SHARP;
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }

    Accidental(String value) {
        this.value = value;
    }

    public int halfStepOffset() {
        return switch (this) {
            case FLAT -> -1;
            case NONE -> 0;
            case SHARP -> 1;
        };
    }

    public Accidental next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }
}
