package kdp.FretQuiz.game;

import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;

import java.util.NoSuchElementException;

/**
 * This class represents a user's guess during a round.
 */
public record Guess(String playerId,
                    Note noteToGuess,
                    Fretboard.Coord clickedFret,
                    Fretboard fretboard) {

    public boolean isCorrect() {
        var guessedNote = fretboard.findNote(clickedFret)
                .orElseThrow(NoSuchElementException::new);

        return guessedNote.isEnharmonicWith(noteToGuess);
    }

    /**
     * NewGuess represents the message that will be sent from the client.
     */
    public static record NewGuess(String gameId,
                                  String playerId,
                                  Fretboard.Coord clickedFret) {
    }
}
