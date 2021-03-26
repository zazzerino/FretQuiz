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

    /**
     * Did the user click on the fret where the `noteToGuess` is played?
     */
    public boolean isCorrect() {
        final var guessedNote = fretboard.findNoteAt(clickedFret)
                .orElseThrow(NoSuchElementException::new);

        return guessedNote.isEnharmonicWith(noteToGuess);
    }

    /**
     * ClientGuess represents the message that will be sent from the client when a guess is made.
     */
    public static record ClientGuess(String gameId,
                                     String playerId,
                                     Fretboard.Coord clickedFret) {
    }
}
