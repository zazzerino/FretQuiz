package kdp.FretQuiz.game;

import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;

public record GameOpts(int roundLength,
                       int secondsRemaining,
                       Fretboard fretboard) {

    public static final int DEFAULT_ROUND_LENGTH = 30;

    public static GameOpts DEFAULT = new GameOpts(
            DEFAULT_ROUND_LENGTH,
            DEFAULT_ROUND_LENGTH,
            Fretboard.DEFAULT
    );

    public Note randomNote() {
        var fretCount = fretboard.endFret() - fretboard.startFret();
        var tuning = fretboard.tuning();

        var lowNote = Note.fromString(tuning.get(tuning.size() - 1));
        var highNote = Note.fromString(tuning.get(0)).transpose(fretCount);

        return Note.randomBetween(lowNote, highNote);
    }
}
