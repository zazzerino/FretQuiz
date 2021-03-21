package kdp.FretQuiz.game;

import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;

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
