package kdp.FretQuiz.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;

import java.util.NoSuchElementException;

/**
 * This class represents a user's guess during a round.
 */
public record Guess(String userId,
                    Note noteToGuess,
                    Fretboard.Coord clickedFret,
                    @JsonIgnore Fretboard fretboard) {

    /**
     * Did the user click on the fret where the `noteToGuess` is played?
     */
    @JsonProperty("isCorrect")
    public boolean isCorrect() {
        final var guessedNote = fretboard
                .findNoteAt(clickedFret)
                .orElseThrow(NoSuchElementException::new);

        return guessedNote.isEnharmonicWith(noteToGuess);
    }

    @JsonProperty("correctFret")
    public Fretboard.Coord correctFret() {
        return fretboard.findCoord(noteToGuess)
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * ClientGuess represents the message that will be sent from the client when a guess is made.
     */
    public static record ClientGuess(String gameId,
                                     String playerId,
                                     Fretboard.Coord clickedFret) {
    }
}
