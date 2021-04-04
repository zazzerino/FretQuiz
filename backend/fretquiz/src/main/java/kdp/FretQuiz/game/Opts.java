package kdp.FretQuiz.game;

import kdp.FretQuiz.Util;
import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * The game options. Can be changed by the client before each game.
 */
public record Opts(Fretboard fretboard,
                   int roundCount,
                   List<Integer> stringsToUse) {

    public static int DEFAULT_ROUND_COUNT = 4;

    public Opts() {
        this(
                Fretboard.DEFAULT,
                DEFAULT_ROUND_COUNT,
                Util.range(1, Fretboard.DEFAULT.stringCount() + 1)
        );
    }

    public Opts withRoundCount(int roundCount) {
        return new Opts(fretboard, roundCount, stringsToUse);
    }

    public Note randomNote() {
        final var notes = new ArrayList<Note>();

        for (final var string : stringsToUse) {
            notes.addAll(fretboard.notesOnString(string));
        }

        return Util.randomElement(notes);
    }

    public Opts withToggledString(int string) {
        final var stringsToUse = Util.toggle(this.stringsToUse, string);
        return new Opts(fretboard, roundCount, stringsToUse);
    }
}
