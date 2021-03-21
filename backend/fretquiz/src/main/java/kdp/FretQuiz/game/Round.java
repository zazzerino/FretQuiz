package kdp.FretQuiz.game;

import kdp.FretQuiz.Util;
import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record Round(Note noteToGuess,
                    Fretboard fretboard,
                    @NotNull Map<String, Player> players,
                    @NotNull List<Guess> guesses) {

    public Round(Note noteToGuess, Fretboard fretboard, @NotNull Map<String, Player> players) {
        this(noteToGuess, fretboard, players, new ArrayList<>());
    }

    public record GuessResult(boolean isCorrect, Round round) {};

    public GuessResult guess(Guess.NewGuess newGuess) {
        var playerId = newGuess.playerId();
        var clickedFret = newGuess.clickedFret();

        var guess = new Guess(playerId, noteToGuess, clickedFret, fretboard);
        var guesses = Util.copyList(this.guesses);
        guesses.add(guess);

        var isCorrect = guess.isCorrect();
        var round = Util.copyRecord(this, Map.of("guesses", guesses));

        return new GuessResult(isCorrect, round);
    }

    public boolean isOver() {
        // the round is over if every player has guessed
        return players.size() == guesses.size();
    }
}
