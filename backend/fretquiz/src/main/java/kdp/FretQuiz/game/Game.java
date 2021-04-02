package kdp.FretQuiz.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.FretQuiz.Util;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A Game consists of a series of rounds.
 * Every round a note will be displayed on a staff and every user guesses the note's position on the fretboard.
 * The game ends when all the rounds have been played, or all the users have left.
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
        PLAYING, // users are guessing
        ROUND_OVER, // all users have guessed
        GAME_OVER // all rounds have been played, or all users have left
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

        if (userIds.isEmpty()) {
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
        state = State.PLAYING;
        nextRound();
        return this;
    }

    /**
     * The game ends when all the users leave or all rounds have been played.
     */
    @JsonProperty("isOver")
    public boolean isOver() {
        return state == State.GAME_OVER;
    }

    @JsonProperty("currentRound")
    public Round currentRound() {
        if (rounds.isEmpty()) {
            return null;
        }

        final var index = rounds.size() - 1;
        return rounds.get(index);
    }

    /**
     * Starts a new round.
     */
    public Game nextRound() {
        state = State.PLAYING;
        final var round = new Round(opts, userIds);
        rounds.add(round);
        return this;
    }

    /**
     * Called when a user makes a guess. Mutates the game it's called on.
     * @return the Guess result
     */
    public Guess updateWithGuess(Guess.ClientGuess clientGuess) {
        final var userId = clientGuess.userId();
        final var clickedFret = clientGuess.clickedFret();

        final var round = currentRound();

        if (round == null) {
            throw new NoSuchElementException("must start a round before guessing");
        }

        final var guess = round.guess(userId, clickedFret);

        if (round.isOver()) {
            state = State.ROUND_OVER;
        }

        if (roundsPlayed() == opts.roundCount()) {
            state = State.GAME_OVER;
        }

        return guess;
    }

    /**
     * @return The guesses made by the user with `userId`.
     */
    public List<Guess> userGuesses(String userId) {
        final List<Guess> guesses = new ArrayList<>();

        for (final var round : rounds) {
            for (final var guess : round.getGuesses()) {
                if (guess.userId().equals(userId)) {
                    guesses.add(guess);
                }
            }
        }

        return guesses;
    }

    /**
     * A user gets a point for guessing correctly and loses a point for guessing incorrectly.
     */
    public int score(String userId) {
        var score = 0;

        for (final var guess : userGuesses(userId)) {
            if (guess.isCorrect()) {
                score += 1;
            } else {
                score -= 1;
            }
        }

        return score;
    }

    record UserScore(String userId, int score) {}

    @JsonProperty("userScores")
    public List<UserScore> userScores() {
        final List<UserScore> scores = new ArrayList<>();

        for (final var userId : userIds) {
            scores.add(new UserScore(userId, score(userId)));
        }

        return scores;
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

    @JsonProperty("roundsPlayed")
    public int roundsPlayed() {
        return rounds.size();
    }

    @JsonProperty("roundsLeft")
    public int roundsLeft() {
        return opts.roundCount() -  rounds.size();
    }
}
