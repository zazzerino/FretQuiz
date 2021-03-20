package kdp.fretquiz.game;

import kdp.fretquiz.theory.Fretboard;
import kdp.fretquiz.theory.Note;

import java.util.NoSuchElementException;

public record Guess(String playerId,
                    Note noteToGuess,
                    Fretboard.Coord clickedFret,
                    Fretboard fretboard) {

    public boolean isCorrect() {
        var guessedNote = fretboard.findNote(clickedFret)
                .orElseThrow(NoSuchElementException::new);

        return guessedNote.isEnharmonicWith(noteToGuess);
    }
}
