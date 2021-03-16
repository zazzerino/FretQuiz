package kdp.fretquiz.theory;

import kdp.fretquiz.Util;

import java.util.ArrayList;
import java.util.List;

public class Theory {

    public static Note randomNote() {
        var whiteKey = Util.randomElement(WhiteKey.values());
        var accidental = Util.randomElement(Accidental.values());
        var octave = Util.randomElement(Octave.values());

        return new Note(whiteKey, accidental, octave);
    }

    /**
     * Moves the note up by the given number of half-steps.
     * @param halfSteps must be a positive number
     */
    public static Note transpose(Note note, int halfSteps) {
        while (halfSteps > 0) {
            note = note.next();
            halfSteps--;
        }

        return note;
    }

    public static List<Note> fretboardNotes(Tuning tuning,
                                            int startFret,
                                            int endFret) {
        List<Note> notes = new ArrayList<>();

        for (var string = 0; string < tuning.notes().size(); string++) {
            for (var fret = endFret; fret >= startFret; fret--) {
                var openString = Note.fromName(tuning.get(string));
                var note = transpose(openString, fret);
                notes.add(note);
            }
        }

        return notes;
    }

    public static Note findNoteAt(FretCoord coord, Tuning tuning, List<Note> notes) {
        var openString = Note.fromName(tuning.get(coord.string() - 1));
        var coordNote = transpose(openString, coord.fret());

        return notes.stream()
                .filter(note -> note.isEnharmonicWith(coordNote))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

//    public static boolean correctGuess(Note noteToGuess,
//                                       FretCoord clickedFret,
//                                       Tuning tuning) {
//
//    }
}
