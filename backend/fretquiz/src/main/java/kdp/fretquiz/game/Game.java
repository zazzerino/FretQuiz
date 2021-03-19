package kdp.fretquiz.game;

import kdp.fretquiz.Util;
import kdp.fretquiz.theory.FretCoord;
import kdp.fretquiz.theory.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public record Game(String id,
                   GameOpts opts,
                   Map<String, Player> players,
                   Note noteToGuess,
                   List<Guess> guesses) {

    public static Game create() {
        var id = Util.randomId();
        var opts = GameOpts.DEFAULT;
        var noteToGuess = Note.random();

        Map<String, Player> players = new HashMap<>();
        List<Guess> guesses = new ArrayList<>();

        return new Game(id, opts, players, noteToGuess, guesses);
    }

    public Game addPlayer(Player player) {
        var players = new HashMap<>(this.players);
        players.put(player.id(), player);

        return Util.copy(this, Map.of("players", players));
    }

    /**
     * Makes the player with `playerId` the game host.
     */
    public Game assignHost(String playerId) {
        var players = new HashMap<>(this.players);
        var player = players.get(playerId).makeHost();
        players.put(playerId, player);

        return Util.copy(this, Map.of("players", players));
    }

    public Player host() {
        return players.values()
                .stream()
                .filter(Player::isHost)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public record GuessResult(boolean isCorrect, Game game) {}

    public GuessResult guess(String playerId, FretCoord clickedFret) {
        var guess = new Guess(playerId, noteToGuess, clickedFret, opts.fretboard());
        var isCorrect = guess.isCorrect();

        var guesses = new ArrayList<>(this.guesses);
        guesses.add(guess);

        var game = Util.copy(this, Map.of(
                "newNoteToGuess", Note.random(),
                "guesses", guesses
        ));

        return new GuessResult(isCorrect, game);
    }
}
