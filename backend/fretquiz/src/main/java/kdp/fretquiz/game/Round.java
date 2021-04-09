package kdp.fretquiz.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.fretquiz.theory.Fretboard;
import kdp.fretquiz.theory.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Every round, a new note will be displayed on the staff.
 * The players have `secondsLeft` to guess the note's location on the fretboard.
 * This class keeps a record of every player's Guess.
 */
public class Round {

    public final Note noteToGuess;
    private Opts opts;
    private int secondsElapsed;
    private final List<Guess> guesses = new ArrayList<>();

    @JsonIgnore
    public final List<String> playerIds;

    public Round(Opts opts, List<String> playerIds) {
        this.opts = opts;
        this.secondsElapsed = 0;
        this.noteToGuess = opts.randomNote();
        this.playerIds = playerIds;
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
        return playerIds.size() == guesses.size();
    }

    /**
     * Handles a new guess. Updates the guess list and returns whether the guess was correct.
     * @return true if the player correctly guessed the displayed note, false otherwise
     */
    public Guess guess(String playerId, Fretboard.Coord clickedFret) {
        final var guess = new Guess(playerId, noteToGuess, clickedFret, opts.fretboard());
        guesses.add(guess);

        return guess;
    }

    public int secondsElapsed() {
        return secondsElapsed;
    }

    public List<Guess> getGuesses() {
        return guesses;
    }

    public Note noteToGuess() {
        return noteToGuess;
    }

    public Opts opts() {
        return opts;
    }

    public Round setOpts(Opts opts) {
        this.opts = opts;
        return this;
    }
}
