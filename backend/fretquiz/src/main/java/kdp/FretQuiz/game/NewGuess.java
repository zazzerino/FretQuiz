package kdp.FretQuiz.game;

import kdp.FretQuiz.theory.Fretboard;

public record NewGuess(String gameId,
                       String playerId,
                       Fretboard.Coord clickedFret) {
}
