package kdp.fretquiz.theory;

import java.util.HashMap;
import java.util.Map;

public class Fretboard {

    public static Map<Note, FretCoord> notes(Tuning tuning,
                                             int startFret,
                                             int endFret) {
        Map<Note, FretCoord> notes = new HashMap<>();
        var stringCount = tuning.notes().size();

        for (var string = 0; string < stringCount; string++) {
            for (var fret = endFret; fret >= startFret; fret--) {
                var openString = Note.fromString(tuning.get(string));
                var note = openString.transpose(fret);
                var coord = new FretCoord(string + 1, fret);

                notes.put(note, coord);
            }
        }

        return notes;
    }

    public static Note findNoteAt(FretCoord coord,
                                  Tuning tuning,
                                  Map<Note, FretCoord> fretboardNotes) {
        var openString = Note.fromString(tuning.get(coord.string() - 1));
        var coordNote = openString.transpose(coord.fret());

        return fretboardNotes.keySet().stream()
                .filter(note -> note.isEnharmonicWith(coordNote))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static FretCoord findFret(Note note, Map<Note, FretCoord> fretboardNotes) {
        return fretboardNotes.get(note);
    }
}
