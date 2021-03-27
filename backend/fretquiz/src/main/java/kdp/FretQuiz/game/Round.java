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

    private int secondsLeft;

    public final Note noteToGuess;
    public final Opts opts;

    private final @NotNull Map<String, Player> players;
    private final @NotNull List<Guess> guesses = new ArrayList<>();

    public Round(Opts opts, @NotNull Map<String, Player> players) {
        this.opts = opts;
        this.secondsLeft = opts.roundLength();
        this.noteToGuess = opts.fretboard().randomNote();
        this.players = players;
    }

    /**
     * Decrements secondsLeft.
     */
    public Round tick() {
        this.secondsLeft -= 1;
        return this;
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
    public Guess guess(String playerId, Fretboard.Coord clickedFret) {
        final var guess = new Guess(playerId, noteToGuess, clickedFret, opts.fretboard());
        guesses.add(guess);

        return guess;
    }

    public Map<String, Object> toMap() {
        final var guesses = this.guesses
                .stream()
                .map(Guess::toMap)
                .toList();

        return Map.of(
                "noteToGuess", noteToGuess,
                "secondsLeft", secondsLeft,
                "guesses", guesses
        );
    }
}
