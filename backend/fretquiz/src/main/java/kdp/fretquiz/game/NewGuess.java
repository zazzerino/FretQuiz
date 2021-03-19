package kdp.fretquiz.game;

import kdp.fretquiz.theory.FretCoord;

public record NewGuess(String gameId,
                       String playerId,
                       FretCoord clickedFret) {
}
