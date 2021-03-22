package kdp.FretQuiz.game;

import kdp.FretQuiz.Util;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Game {
    public final String id;
    private final Opts opts;

    private final @NotNull Map<String, Player> players;
    private String hostId;

    private final @NotNull List<Round> rounds;
    private boolean hasStarted;

    public Game() {
        id = Util.randomId();
        opts = Opts.DEFAULT;
        players = new HashMap<>();
        rounds = new ArrayList<>();
        hasStarted = false;
    }

    public Game addPlayer(Player player) {
        players.put(player.id(), player);
        return this;
    }

    public Game assignHost(String playerId) {
        hostId = playerId;
        return this;
    }

    public Game start() {
        hasStarted = true;
        return this;
    }

    public boolean isOver() {
        return players.size() == 0;
    }

    public Optional<Round> currentRound() {
        if (rounds.isEmpty()) {
            return Optional.empty();
        }

        var index = rounds.size() - 1;
        return Optional.of(rounds.get(index));
    }

//    public Game nextRound() {
//        var note = opts.fretboard().randomNote();
//        var round = new Round(note, opts.getFretboard(), players);
//
//        rounds.add(round);
//        return this;
//    }

    public boolean getHasStarted() {
        return hasStarted;
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "id", id,
                "players", players.keySet(),
                "currentRound", currentRound(),
                "hostId", hostId,
                "hasStarted", hasStarted
        );
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
