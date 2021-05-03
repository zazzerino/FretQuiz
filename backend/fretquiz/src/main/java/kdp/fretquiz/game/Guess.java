package kdp.fretquiz.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.fretquiz.theory.Fretboard;
import kdp.fretquiz.theory.Note;

import java.util.NoSuchElementException;

/**
 * This class represents a player's guess during a round.
 */
public record Guess(String playerId,
                    Note noteToGuess,
                    Fretboard.Coord clickedFret,
                    @JsonIgnore Fretboard fretboard)
{
    /**
     * Did the player click on the fret where the `noteToGuess` is played?
     */
    @JsonProperty("isCorrect")
    public boolean isCorrect()
    {
        final var guessedNote = fretboard
                .findNoteAt(clickedFret)
                .orElseThrow(NoSuchElementException::new);

        return guessedNote.isEnharmonicWith(noteToGuess);
    }

    @JsonProperty("correctFret")
    public Fretboard.Coord correctFret()
    {
        return fretboard.findCoord(noteToGuess)
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * ClientGuess represents the text that will be sent from the client when a guess is made.
     */
    public static record ClientGuess(String gameId,
                                     String playerId,
                                     Fretboard.Coord clickedFret)
    {
    }
}
