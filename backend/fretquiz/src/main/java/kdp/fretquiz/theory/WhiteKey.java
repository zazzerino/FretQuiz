package kdp.fretquiz.theory;

import kdp.fretquiz.Util;

import java.util.Arrays;
import java.util.List;

public enum WhiteKey {
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    A("A"),
    B("B");

    private final static WhiteKey[] vals = values();

    private final String val;

    WhiteKey(String val) {
        this.val = val;
    }

    public int halfStepsFromC() {
        var offsets = Util.zip(
                Arrays.asList(WhiteKey.values()),
                List.of(0, 2, 4, 5, 7, 9, 11)
        );

        return offsets.get(this);
    }

    public WhiteKey next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }
}
