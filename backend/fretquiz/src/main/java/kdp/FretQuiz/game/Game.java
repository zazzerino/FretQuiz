package kdp.FretQuiz.game;

import kdp.FretQuiz.Util;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
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
     * Maps a player's id to the Player record.
     */
    private final @NotNull Map<String, Player> players = new HashMap<>();

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
        id = Util.randomId();
        opts = Opts.DEFAULT;
        state = State.INIT;
    }

    public String id() {
        return id;
    }

    public Game addPlayer(Player player) {
        players.put(player.id(), player);
        return this;
    }

    public Game removePlayer(String playerId) {
        players.remove(playerId);
        return this;
    }

    /**
     * Sets the game host to `playerId`.
     * @return the updated Game
     */
    public Game assignHost(String playerId) {
        hostId = playerId;
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
     * The game ends when all the players leave.
     */
    public boolean isOver() {
        return players.size() == 0;
    }

    /**
     * The round that is currently being played.
     * It returns an Optional because if the game hasn't started, there is no round yet.
     * @return Optional.empty() if the game hasn't started, otherwise the round being played
     */
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
        final var round = new Round(opts, players);
        rounds.add(round);
        return this;
    }

    /**
     * Called when a user makes a guess.
     * @return whether the guess was correct
     */
    public boolean guess(Guess.ClientGuess clientGuess) {
        final var playerId = clientGuess.playerId();
        final var clickedFret = clientGuess.clickedFret();

        final var round = currentRound().orElseThrow(NoSuchElementException::new);
        final var isCorrect = round.guess(playerId, clickedFret);

        if (round.isOver()) {
            nextRound();
        }

        return isCorrect;
    }

    public Game setOpts(Opts opts) {
        this.opts = opts;
        return this;
    }

    /**
     * A map representing the Game. This method is for sending the game's info as json to the client.
     */
    public Map<String, Object> toMap() {
        final var playerIds = this.players.keySet();

        // return the current round if it exists, or an empty map if it doesn't
        final var currentRound = currentRound().isPresent() ?
                currentRound().get().toMap() :
                Collections.emptyMap();

        return Map.of(
                "id", id,
                "players", playerIds,
                "currentRound", currentRound,
                "hostId", hostId,
                "state", state
        );
    }
}
