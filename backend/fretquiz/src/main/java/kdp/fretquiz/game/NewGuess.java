package kdp.fretquiz.game;

import kdp.fretquiz.theory.Fretboard;

public record NewGuess(String gameId,
                       String playerId,
                       Fretboard.Coord clickedFret) {
}
