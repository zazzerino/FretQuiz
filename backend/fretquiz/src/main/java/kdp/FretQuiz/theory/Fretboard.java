package kdp.FretQuiz.theory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class represents a slice of a fretboard from `startFret` to `endFret`
 * in the given `tuning`. The `notes` field maps each Fretboard.Coord to the
 * note found at that Coord.
 */
public record Fretboard(Tuning tuning,
                        int startFret,
                        int endFret,
                        Map<Coord, Note> notes) {

    /**
     * Fretboard.Coord represents a location on the fretboard (the string & fret).
     */
    public static record Coord(int string, int fret) {}

    /**
     * The 1st position of a 6-string guitar in standard tuning.
     */
    public static Fretboard DEFAULT = Fretboard.create(Tuning.STANDARD_GUITAR, 0, 4);

    /**
     * Creates a new Fretboard and calculates the notes on that can be found on that fretboard.
     * @param startFret the lowest fret (in pitch & number)
     * @param endFret the highest fret (in pitch & number)
     */
    public static Fretboard create(Tuning tuning, int startFret, int endFret) {
        final var notes = calculateNotes(tuning, startFret, endFret);
        return new Fretboard(tuning, startFret, endFret, notes);
    }

    /**
     * Returns a Map with the keys being each Fretboard.Coord and the values being the Notes played at that coord.
     */
    public static Map<Coord, Note> calculateNotes(Tuning tuning, int startFret, int endFret) {
        Map<Coord, Note> notes = new HashMap<>();
        final var stringCount = tuning.notes().size();

        for (var string = 0; string < stringCount; string++) {
            for (var fret = endFret; fret >= startFret; fret--) {
                final var coord = new Coord(string + 1, fret);
                final var openStringNote = Note.fromString(tuning.get(string));

                // transpose the open string note up `fret` number of half-steps
                final var note = openStringNote.transpose(fret);
                notes.put(coord, note);
            }
        }

        return notes;
    }

    /**
     * Find the Note at the given Fretboard.Coord (string & fret).
     */
    public Optional<Note> findNoteAt(Coord coord) {
        return Optional.ofNullable(notes.get(coord));
    }

    /**
     * Return the Fretboard.Coord where a given Note is played.
     */
    public Optional<Coord> findFret(Note note) {
        return notes.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isEnharmonicWith(note))
                .findFirst()
                .map(Map.Entry::getKey);
    }

    /**
     * Returns a random note on that can be played on the fretboard.
     */
    public Note randomNote() {
        final var fretCount = endFret - startFret;

        final var lowNote = Note.fromString(tuning.get(tuning.size() - 1));
        final var highNote = Note.fromString(tuning.get(0)).transpose(fretCount);

        return Note.randomBetween(lowNote, highNote);
    }
}
