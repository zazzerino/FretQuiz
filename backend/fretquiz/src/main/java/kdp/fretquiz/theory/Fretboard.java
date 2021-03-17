package kdp.fretquiz.theory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record Fretboard(int startFret,
                        int endFret,
                        Tuning tuning,
                        Map<FretCoord, Note> notes) {

    public static Fretboard DEFAULT = Fretboard.create(0, 4, Tuning.STANDARD_GUITAR);

    public static Fretboard create(int startFret, int endFret, Tuning tuning) {
        var notes = calculateNotes(startFret, endFret, tuning);

        return new Fretboard(startFret, endFret, tuning, notes);
    }

    public static Map<FretCoord, Note> calculateNotes(int startFret, int endFret, Tuning tuning) {
        Map<FretCoord, Note> notes = new HashMap<>();
        var stringCount = tuning.notes().size();

        for (var string = 0; string < stringCount; string++) {
            for (var fret = endFret; fret >= startFret; fret--) {
                var coord = new FretCoord(string + 1, fret);
                var openStringNote = Note.fromString(tuning.get(string));
                var note = openStringNote.transpose(fret);

                notes.put(coord, note);
            }
        }

        return notes;
    }

    public Optional<Note> findNote(FretCoord coord) {
        return Optional.ofNullable(notes.get(coord));
    }

    public Optional<FretCoord> findFret(Note note) {
        return notes.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isEnharmonicWith(note))
                .findFirst()
                .map(Map.Entry::getKey);
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "startFret", startFret,
                "endFret", endFret,
                "tuning", tuning,
                "notes", notes
        );
    }
}
