package kdp.FretQuiz.game;

import kdp.FretQuiz.Util;
import kdp.FretQuiz.theory.Fretboard;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public record GameRec(String id,
                      OptsRec opts,
                      @NotNull Map<String, Player> players,
                      @NotNull List<Round> rounds,
                      boolean hasStarted) {

    public static GameRec create() {
        var id = Util.randomId();
        var opts = OptsRec.DEFAULT;

        Map<String, Player> players = new HashMap<>();
        List<Round> rounds = new ArrayList<>();

        return new GameRec(id, opts, players, rounds, false);
    }

    public GameRec addPlayer(Player player) {
        var players = Util.copyMap(this.players);
        players.put(player.id(), player);

        return Util.copyRecord(this, Map.of("players", players));
    }

    /**
     * Makes the player with `playerId` the game host.
     */
    public GameRec assignHost(String playerId) {
        var players = Util.copyMap(this.players);
        var player = players.get(playerId).makeHost();
        players.put(playerId, player);

        return Util.copyRecord(this, Map.of("players", players));
    }

    public Player host() {
        return players.values()
                .stream()
                .filter(Player::isHost)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public GameRec start() {
        return Util.copyRecord(this, Map.of("hasStarted", true));
    }

    public boolean isFinished() {
        return opts.secondsRemaining() == 0;
    }

    /**
     * Decrement secondsRemaining.
     */
    public GameRec tick() {
        var secondsRemaining = opts.secondsRemaining() - 1;
        var opts = Util.copyRecord(opts(), Map.of("secondsRemaining", secondsRemaining));

        return Util.copyRecord(this, Map.of("opts", opts));
    }

    public Round currentRound() {
        var index = rounds.size() - 1;
        return rounds.get(index);
    }

    public GameRec nextRound() {
        var note = opts.randomNote();
        var round = new Round(note, opts.fretboard(), players);
        var rounds = Util.copyList(this.rounds);

        return Util.copyRecord(this, Map.of("rounds", rounds));
    }

    public record GuessResult(boolean isCorrect, GameRec game) {}

    public GuessResult guess(String playerId, Fretboard.Coord clickedFret) {
        var newGuess = new Guess.NewGuess(id, playerId, clickedFret);

        var result = currentRound().guess(newGuess);
        var isCorrect = result.isCorrect();

        var rounds = Util.copyList(this.rounds);
        var game = Util.copyRecord(this, Map.of("rounds", rounds))
                .nextRound();

        return new GuessResult(isCorrect, game);
    }

//    public record GuessResult(boolean isCorrect, Game game) {}

//    public GuessResult guess(String playerId, Fretboard.Coord clickedFret) {
//        var guess = new Guess(playerId, noteToGuess, clickedFret, opts.fretboard());
//        var isCorrect = guess.isCorrect();
//
//        var guesses = new ArrayList<>(this.guesses);
//        guesses.add(guess);
//
//        var game = Util.copyRecord(this, Map.of(
//                "newNoteToGuess", opts.randomNote(),
//                "guesses", guesses
//        ));
//
//        return new GuessResult(isCorrect, game);
//    }
}
