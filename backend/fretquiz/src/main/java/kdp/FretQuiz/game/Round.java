package kdp.FretQuiz.game;

import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Every round, a new note will be displayed on the staff.
 * The users have `secondsLeft` to guess the note's location on the fretboard.
 * This class keeps a record of every user's Guess.
 */
public class Round {

    public final Note noteToGuess;
    public final Opts opts;
    private int secondsLeft;

    private final @NotNull Map<String, Player> players;
    private final @NotNull List<Guess> guesses = new ArrayList<>();

    public Round(Opts opts, Map<String, Player> players) {
        this.opts = opts;
        this.secondsLeft = opts.roundLength();
        this.noteToGuess = opts.fretboard().randomNote();
        this.players = players;
    }

    /**
     * Decrements secondsLeft.
     * @return the number of seconds left in the round
     */
    public int tick() {
        return --secondsLeft;
    }

    /**
     * The round is over once every player has guessed.
     */
    public boolean isOver() {
        return players.size() == guesses.size();
    }

    /**
     * Handles a new guess. Updates the guess list and returns whether the guess was correct.
     * @return true if the user correctly guessed the displayed note, false otherwise
     */
    public boolean guess(String playerId, Fretboard.Coord clickedFret) {
        final var guess = new Guess(playerId, noteToGuess, clickedFret, opts.fretboard());
        guesses.add(guess);

        return guess.isCorrect();
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "noteToGuess", noteToGuess,
                "secondsLeft", secondsLeft,
                "guesses", guesses
        );
    }
}
