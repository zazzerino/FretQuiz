package kdp.fretquiz.theory;

public enum Octave {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);

    private static final Octave[] vals = values();

    public final int val;

    Octave(int val) {
        this.val = val;
    }

    public Octave next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }
}
