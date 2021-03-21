package kdp.FretQuiz.game;

import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;

public class Opts {
    private int roundLength;
    private int secondsRemaining;
    private Fretboard fretboard;

    public static final int DEFAULT_ROUND_LENGTH = 30;

    public static final Opts DEFAULT = new Opts(
            DEFAULT_ROUND_LENGTH,
            DEFAULT_ROUND_LENGTH,
            Fretboard.DEFAULT
    );

    public Opts(int roundLength, int secondsRemaining, Fretboard fretboard) {
        this.roundLength = roundLength;
        this.secondsRemaining = secondsRemaining;
        this.fretboard = fretboard;
    }


    public Note randomNote() {
        var fretCount = fretboard.endFret() - fretboard.startFret();
        var tuning = fretboard.tuning();

        var lowNote = Note.fromString(tuning.get(tuning.size() - 1));
        var highNote = Note.fromString(tuning.get(0)).transpose(fretCount);

        return Note.randomBetween(lowNote, highNote);
    }
}
