package kdp.FretQuiz.game;

import kdp.FretQuiz.Util;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Game {
    private final String id;
    private OptsRec opts;
    private final @NotNull Map<String, Player> players;
    private final @NotNull List<Round> rounds;
    private boolean hasStarted;

    public Game() {
        id = Util.randomId();
        opts = OptsRec.DEFAULT;
        players = new HashMap<>();
        rounds = new ArrayList<>();
        hasStarted = false;
    }

    public Game addPlayer(Player player) {
        players.put(player.id(), player);
        return this;
    }

    public Game assignHost(String playerId) {
        var player = players.get(playerId)
                .makeHost();

        players.put(playerId, player);
        return this;
    }

    public Player host() {
        return players.values()
                .stream()
                .filter(Player::isHost)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Game start() {
        hasStarted = true;
        return this;
    }

    public boolean isOver() {
        return opts.secondsRemaining() == 0;
    }

    /**
     * Decrement secondsRemaining.
     */
    public Game tick() {
        var secondsRemaining = opts.secondsRemaining() - 1;
        opts = Util.copyRecord(this.opts, Map.of("secondsRemaining", secondsRemaining));
        return this;
    }

    public Round currentRound() {
        var index = rounds.size() - 1;
        return rounds.get(index);
    }

    public Game nextRound() {
        var note = opts.randomNote();
        var round = new Round(note, opts.fretboard(), players);

        rounds.add(round);
        return this;
    }

//    public record GuessResult(boolean isCorrect, GameRec game) {}
//
//    public GameRec.GuessResult guess(String playerId, Fretboard.Coord clickedFret) {
//        var newGuess = new Guess.NewGuess(id, playerId, clickedFret);
//
//        var result = currentRound().guess(newGuess);
//        var isCorrect = result.isCorrect();
//
//        var rounds = Util.copyList(this.rounds);
//        var game = Util.copyRecord(this, Map.of("rounds", rounds))
//                .nextRound();
//
//        return new GameRec.GuessResult(isCorrect, game);
//    }
}
