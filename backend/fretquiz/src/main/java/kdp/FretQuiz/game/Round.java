package kdp.FretQuiz.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.FretQuiz.theory.Fretboard;
import kdp.FretQuiz.theory.Note;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Every round, a new note will be displayed on the staff.
 * The users have `secondsLeft` to guess the note's location on the fretboard.
 * This class keeps a record of every user's Guess.
 */
public class Round {

    private int secondsElapsed;

    public final Note noteToGuess;
    public final Opts opts;

    private final @NotNull List<String> userIds;
    private final @NotNull List<Guess> guesses = new ArrayList<>();

    public Round(Opts opts, List<String> userIds) {
        this.opts = opts;
        this.secondsElapsed = opts.roundLength();
        this.noteToGuess = opts.fretboard().randomNote();
        this.userIds = userIds;
    }

    /**
     * Increment secondsElapsed.
     */
    public Round tick() {
        this.secondsElapsed += 1;
        return this;
    }

    /**
     * The round is over once every player has guessed.
     */
    @JsonProperty("isOver")
    public boolean isOver() {
        return userIds.size() == guesses.size();
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

    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public List<Guess> getGuesses() {
        return guesses;
    }
}
