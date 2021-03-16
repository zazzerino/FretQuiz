package kdp.fretquiz.theory;

import kdp.fretquiz.Util;
import kdp.fretquiz.game.FretCoord;

import java.util.ArrayList;

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

    public static void fretboardNotes(Tuning tuning,
                                      int startFret,
                                      int endFret) {
        var notes = new ArrayList<>();

        for (var string = 0; string < tuning.notes().size(); string++) {
            for (var fret = startFret; fret <= startFret + endFret; fret++) {

            }
        }
    }

//    public static boolean correctGuess(Note noteToGuess,
//                                       FretCoord clickedFret,
//                                       Tuning tuning) {
//
//    }
}
