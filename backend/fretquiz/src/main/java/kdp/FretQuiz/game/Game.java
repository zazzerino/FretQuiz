package kdp.FretQuiz.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.FretQuiz.Util;
import kdp.FretQuiz.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    /**
     * Maps a user's id to the user.
     */
    private final @NotNull Map<String, User> users = new HashMap<>();

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

    public Game addPlayer(User user) {
        user.joinGame(this.id);
        users.put(user.getId(), user);

        return this;
    }

    public Game removePlayer(String userId) {
        users.get(userId).leaveGame(this.id);
        users.remove(userId);

        if (isOver()) {
            state = State.GAME_OVER;
        }

        return this;
    }

    /**
     * Sets the game host to `playerId`.
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
        return users.size() == 0;
    }

    /**
     * The round that is currently being played.
     * It returns an Optional because if the game hasn't started, there is no round yet.
     * @return Optional.empty() if the game hasn't started, otherwise the round being played
     */
    @JsonProperty("currentRound")
    public Optional<Round> currentRound() {
        if (rounds.isEmpty()) {
            return Optional.empty();
        }

        // return the last element of the rounds list
        final var index = rounds.size() - 1;
        return Optional.of(rounds.get(index));
    }

    /**
     * Starts a new round.
     */
    public Game nextRound() {
        final var round = new Round(opts, users);
        rounds.add(round);

        state = State.PLAYING;
        return this;
    }

    /**
     * Called when a user makes a guess.
     * @return whether the guess was correct
     */
    public Guess guess(Guess.ClientGuess clientGuess) {
        final var playerId = clientGuess.playerId();
        final var clickedFret = clientGuess.clickedFret();

        final var round = currentRound().orElseThrow(NoSuchElementException::new);
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

    public String getHostId() {
        return hostId;
    }
}
