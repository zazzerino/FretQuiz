package kdp.fretquiz.game;

import kdp.fretquiz.Util;
import kdp.fretquiz.theory.FretCoord;
import kdp.fretquiz.theory.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Game handleGuess(String playerId, FretCoord clickedFret) {
        var guess = new Guess(playerId, noteToGuess, clickedFret, opts.fretboard());
        var guesses = new ArrayList<>(this.guesses);
        guesses.add(guess);

        var newNoteToGuess = Note.random();

        return Util.copy(this, Map.of(
                "newNoteToGuess", newNoteToGuess,
                "guesses", guesses
        ));
    }
}
