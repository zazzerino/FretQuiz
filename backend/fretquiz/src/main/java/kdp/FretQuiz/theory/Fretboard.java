package kdp.FretQuiz.theory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record Fretboard(Tuning tuning,
                        int startFret,
                        int endFret,
                        Map<Coord, Note> notes) {

    public static record Coord(int string, int fret) {}

    public static Fretboard DEFAULT = Fretboard.create(Tuning.STANDARD_GUITAR, 0, 4);

    public static Fretboard create(Tuning tuning, int startFret, int endFret) {
        var notes = calculateNotes(tuning, startFret, endFret);

        return new Fretboard(tuning, startFret, endFret, notes);
    }

    public static Map<Coord, Note> calculateNotes(Tuning tuning, int startFret, int endFret) {
        Map<Coord, Note> notes = new HashMap<>();
        var stringCount = tuning.notes().size();

        for (var string = 0; string < stringCount; string++) {
            for (var fret = endFret; fret >= startFret; fret--) {
                var coord = new Coord(string + 1, fret);
                var openStringNote = Note.fromString(tuning.get(string));

                // transpose the open string note up `fret` number of half-steps
                var note = openStringNote.transpose(fret);

                notes.put(coord, note);
            }
        }

        return notes;
    }

    public Optional<Note> findNote(Coord coord) {
        return Optional.ofNullable(notes.get(coord));
    }

    public Optional<Coord> findFret(Note note) {
        return notes.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isEnharmonicWith(note))
                .findFirst()
                .map(Map.Entry::getKey);
    }
}
