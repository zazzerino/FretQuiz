package kdp.fretquiz.apigame;

import kdp.fretquiz.theory.FretCoord;
import kdp.fretquiz.theory.Note;

import java.util.Map;

public record Guess(String playerId,
                    Note noteToGuess,
                    FretCoord clickedFret,
                    boolean isCorrect) {

    public Map<String, Object> toMap() {
        return Map.of(
                "noteToGuess", noteToGuess.name(),
                "clickedFret", clickedFret,
                "isCorrect", isCorrect
        );
    }
}
