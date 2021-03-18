package kdp.fretquiz.game;

import kdp.fretquiz.theory.FretCoord;
import kdp.fretquiz.theory.Note;

import java.util.Map;

public record Guess(String playerId,
                    Note noteToGuess,
                    FretCoord clickedFret,
                    boolean isCorrect) {
}
