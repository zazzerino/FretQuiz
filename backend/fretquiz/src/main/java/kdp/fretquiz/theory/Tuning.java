package kdp.fretquiz.theory;

import java.util.List;

public record Tuning(List<String> notes) {

    public static final Tuning STANDARD_GUITAR = new Tuning(List.of("E", "B", "G", "D", "A", "E"));
}
