package kdp.FretQuiz.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.FretQuiz.Util;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A Game consists of a series of round.
 * Every round a note will be displayed on a staff and every user guesses the note's position on the fretboard.
 * The game ends when all the users leave.
 */
public class Game {

    public final String id;

    private Opts opts;
    private String hostId;
    private State state;

    private final @NotNull List<String> userIds = new ArrayList<>();

    /**
     * The rounds that have been played. The current round is the last element.
     */
    private final @NotNull List<Round> rounds = new ArrayList<>();

    enum State {
        INIT, // the game has been created but not started
        PLAYING, // players are guessing
        ROUND_OVER, // all players have guessed
        GAME_OVER // all players have left
    }

    public Game() {
        this.id = Util.randomId();
        this.opts = Opts.DEFAULT;
        this.state = State.INIT;
    }

    public Game addPlayer(String userId) {
        userIds.add(userId);
        return this;
    }

    public Game removePlayer(String userId) {
        userIds.remove(userId);

        if (isOver()) {
            state = State.GAME_OVER;
        }

        return this;
    }

    /**
     * Sets the game host to `userId`.
     * @return the updated Game
     */
    public Game assignHost(String userId) {
        hostId = userId;
        return this;
    }

    /**
     * Starts the game and starts a new round.
     * @return the updated Game
     */
    public Game start() {
        nextRound();
        state = State.PLAYING;
        return this;
    }

    /**
     * The game ends when all the players leave.
     */
    @JsonProperty("isOver")
    public boolean isOver() {
        return userIds.size() == 0;
    }

   @JsonProperty("currentRound")
    public Round currentRound() {
        if (rounds.isEmpty()) {
            return null;
        } else {
            final var index = rounds.size() - 1;
            return rounds.get(index);
        }
    }

    /**
     * Starts a new round.
     */
    public Game nextRound() {
        final var round = new Round(opts, userIds);
        rounds.add(round);
        state = State.PLAYING;
        return this;
    }

    /**
     * Called when a user makes a guess. Mutates the game it's called on.
     * @return the Guess result
     */
    public Guess updateWithGuess(Guess.ClientGuess clientGuess) {
        final var playerId = clientGuess.playerId();
        final var clickedFret = clientGuess.clickedFret();

        final var round = currentRound();

        if (round == null) {
            throw new NoSuchElementException("must start a round before guessing");
        }

        final var guess = round.guess(playerId, clickedFret);

        if (round.isOver()) {
            state = State.ROUND_OVER;
        }

        return guess;
    }

    public Opts getOpts() {
        return opts;
    }

    public Game setOpts(Opts opts) {
        this.opts = opts;
        return this;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public String getHostId() {
        return hostId;
    }

    public State getState() {
        return state;
    }
}
