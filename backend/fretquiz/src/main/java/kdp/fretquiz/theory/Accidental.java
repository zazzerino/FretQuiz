package kdp.fretquiz.theory;

import kdp.fretquiz.Util;

import java.util.Arrays;
import java.util.List;

public enum Accidental {
//    DOUBLE_FLAT("bb"),
    FLAT("b"),
    NONE(""),
    SHARP("#");
//    DOUBLE_SHARP("##");

    private final static Accidental[] vals = values();

    private final String value;

    Accidental(String value) {
        this.value = value;
    }

    public int offset() {
        var offsets = Util.zip(
                Arrays.asList(Accidental.values()),
                List.of(-1, 0, 1)
//                List.of(-2, -1, 0, 1, 2)
        );

        return offsets.get(this);
    }

    public Accidental next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

//    public Accidental next() {
//        if (this == DOUBLE_SHARP) {
//            return NONE;
//        } else {
//            return vals[(this.ordinal() + 1) % vals.length];
//        }
//    }
}
