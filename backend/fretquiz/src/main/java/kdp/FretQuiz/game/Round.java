package kdp.FretQuiz.game;

import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Round {
    public final Note noteToGuess;
    public final Opts opts;
    private int secondsLeft;

    private final Map<String, Player> players;
    private final @NotNull List<Guess> guesses = new ArrayList<>();

    public Round(Note noteToGuess, Opts opts, Map<String, Player> players) {
        this.noteToGuess = noteToGuess;
        this.opts = opts;
        this.secondsLeft = opts.roundLength();
        this.players = players;
    }

    /**
     * Decrements secondsRemaining.
     * @return the number of seconds left in the round
     */
    public int tick() {
        return --secondsLeft;
    }

    /**
     * The round is over if every player has guessed.
     */
    public boolean isOver() {
        return players.size() == guesses.size();
    }

    /**
     * Handles a new guess. Updates the guess list and returns whether the guess was correct.
     * @return true if the user correctly guessed the displayed note, false otherwise
     */
    public boolean guess(String playerId, Fretboard.Coord clickedFret) {
        var guess = new Guess(playerId, noteToGuess, clickedFret, opts.fretboard());
        guesses.add(guess);

        return guess.isCorrect();
    }
}
