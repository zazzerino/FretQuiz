package kdp.fretquiz.game;

import kdp.fretquiz.theory.Note;

public record Guess(Note noteToGuess,
                    FretCoord clickedFret,
                    boolean wasCorrect) {
}
