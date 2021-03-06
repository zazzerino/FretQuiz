package kdp.fretquiz.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.fretquiz.Util;
import kdp.fretquiz.theory.Accidental;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * A Game consists of a series of rounds.
 * Every round a note will be displayed on a staff and every player guesses the note's position on the fretboard.
 * The game ends when all the rounds have been played, or all the players have left.
 */
public class Game
{
    public final String id;

    @JsonIgnore
    public final Instant createdAt;

    @JsonProperty("opts")
    private final Opts opts;

    /**
     * The rounds that have been played. The current round is the last element.
     */
    @JsonProperty("rounds")
    private final List<Round> rounds = new ArrayList<>();

    private State state;

    @JsonProperty("hostId")
    private String hostId;

    @JsonProperty("players")
    private List<Player> players = new ArrayList<>();

    public Game()
    {
        this.id = Util.randomId();
        this.createdAt = Instant.now();
        this.opts = new Opts();
        this.state = State.INIT;
    }

    public Game addPlayer(Player player)
    {
        players.add(player);
        return this;
    }

    public Game removePlayer(String playerId)
    {
        players.removeIf(player -> player.id().equals(playerId));

        if (players.isEmpty()) {
            state = State.GAME_OVER;
        }

        return this;
    }

    /**
     * Sets the game host to `id`.
     *
     * @return the updated Game
     */
    public Game assignHost(String playerId)
    {
        hostId = playerId;
        return this;
    }

    /**
     * Starts the game and starts a new round.
     *
     * @return the updated Game
     */
    public Game start()
    {
        state = State.PLAYING;
        nextRound();
        return this;
    }

    public Info info()
    {
        return new Info(id, createdAt.toString(), hostName(), playerCount(), state);
    }

    public int playerCount()
    {
        return players.size();
    }

    public String hostName()
    {
        return players.stream()
                .filter(player -> player.id().equals(hostId))
                .findFirst()
                .map(Player::name)
                .orElse(null);
    }

    /**
     * The game ends when all the players leave or all rounds have been played.
     */
    @JsonProperty("isOver")
    public boolean isOver()
    {
        return state == State.GAME_OVER;
    }

    @JsonProperty("currentRound")
    public Round currentRound()
    {
        if (rounds.isEmpty()) {
            return null;
        }

        final var index = rounds.size() - 1;
        return rounds.get(index);
    }

    public List<String> playerIds()
    {
        return players.stream()
                .map(Player::id)
                .toList();
    }

    /**
     * Starts a new round.
     */
    public Game nextRound()
    {
        state = State.PLAYING;
        final var round = new Round(opts, playerIds());
        rounds.add(round);
        return this;
    }

    /**
     * Called when a player makes a guess. Mutates the game it's called on.
     *
     * @return the Guess result
     */
    public Guess guess(Guess.ClientGuess clientGuess)
    {
        final var playerId = clientGuess.playerId();
        final var clickedFret = clientGuess.clickedFret();

        final var round = currentRound();

        if (round == null) {
            throw new NoSuchElementException("must start a round before guessing");
        }

        final var guess = round.guess(playerId, clickedFret);

        if (round.isOver()) {
            state = State.ROUND_OVER;

            // if all the rounds have been played, the game is over
            if (roundsPlayed() == opts.roundCount()) {
                state = State.GAME_OVER;
            }
        }

        return guess;
    }

    /**
     * @return The guesses made by the player with `id`.
     */
    public List<Guess> playerGuesses(String playerId)
    {
        final var guesses = new ArrayList<Guess>();

        for (final var round : rounds) {
            for (final var guess : round.getGuesses()) {
                if (guess.playerId().equals(playerId)) {
                    guesses.add(guess);
                }
            }
        }

        return guesses;
    }

    /**
     * A player gets a point for guessing correctly.
     */
    public int playerScore(String playerId)
    {
        var score = 0;

        for (final var guess : playerGuesses(playerId)) {
            if (guess.isCorrect()) {
                score += 1;
            }
        }

        return score;
    }

    @JsonProperty("scores")
    public List<PlayerScore> scores()
    {
        final var scores = new ArrayList<PlayerScore>();

        for (final var player : players) {
            final var playerScore = new PlayerScore(player, playerScore(player.id()));
            scores.add(playerScore);
        }

        return scores;
    }

    @JsonProperty("rounds")
    public List<Round> rounds()
    {
        return rounds;
    }

    public String hostId()
    {
        return hostId;
    }

    @JsonProperty("state")
    public State state()
    {
        return state;
    }

    @JsonProperty("roundsPlayed")
    public int roundsPlayed()
    {
        return rounds.size();
    }

    @JsonProperty("roundsLeft")
    public int roundsLeft()
    {
        return opts.roundCount() - rounds.size();
    }

    public Game toggleString(int string)
    {
        opts.toggleString(string);
        return this;
    }

    public Game toggleAccidental(Accidental accidental)
    {
        opts.toggleAccidental(accidental);
        return this;
    }

    public Game setRoundCount(int roundCount)
    {
        opts.setRoundCount(roundCount);
        return this;
    }

    public Game setPlayerName(String playerId, String playerName)
    {
        players = players.stream()
                .map(player -> playerId.equals(player.id())
                        ? player.withName(playerName)
                        : player)
                .collect(Collectors.toCollection(ArrayList::new));

        return this;
    }

    public boolean isOlderThan(long minutes)
    {
        final var minutesAgo = Instant
                .now()
                .minus(minutes, ChronoUnit.MINUTES);

        return createdAt.isBefore(minutesAgo);
    }

    enum State
    {
        INIT, // the game has been created but not started
        PLAYING, // players are guessing
        ROUND_OVER, // all players have guessed
        GAME_OVER // all rounds have been played, or all players have left
    }

    public record Info(String gameId,
                       String createdAt,
                       String hostName,
                       int playerCount,
                       State state)
    {
    }

    record PlayerScore(Player player, int score)
    {
    }
}
