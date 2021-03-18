package kdp.fretquiz.game;

import kdp.fretquiz.Util;
import kdp.fretquiz.theory.FretCoord;
import kdp.fretquiz.theory.Fretboard;
import kdp.fretquiz.theory.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public record Game(String id,
                   GameOpts opts,
                   Map<String, Player> players,
                   Note noteToGuess,
                   List<Guess> guesses) {

    public Game() {
        this(
                Util.randomId(),
                GameOpts.DEFAULT,
                new HashMap<>(),
                Note.random(),
                new ArrayList<>()
        );
    }

    public static boolean correctGuess(Note noteToGuess,
                                       FretCoord clickedFret,
                                       Fretboard fretboard) {
        var guessedNote = fretboard.findNote(clickedFret)
                .orElseThrow(NoSuchElementException::new);

        return guessedNote.isEnharmonicWith(noteToGuess);
    }

    public Game addPlayer(Player player) {
        var players = new HashMap<>(this.players);
        players.put(player.id(), player);

        return new Game(id, opts, players, noteToGuess, guesses);
    }

    public Game handleGuess(String playerId, FretCoord clickedFret) {
        var isCorrect = correctGuess(noteToGuess, clickedFret, opts.fretboard());
        var guess = new Guess(playerId, noteToGuess, clickedFret, isCorrect);

        var guesses = new ArrayList<>(this.guesses);
        guesses.add(guess);

        var note = Note.random();

        return new Game(id, opts, players, note, guesses);
    }

    public Map<String, Object> toMap() {
        var playerMaps = this.players.values()
                .stream()
                .map(Player::toMap)
                .collect(Collectors.toList());

        var guessMaps = this.guesses
                .stream()
                .map(Guess::toMap)
                .collect(Collectors.toList());

        return Map.of(
                "id", id,
                "opts", opts.toMap(),
                "players", playerMaps,
                "noteToGuess", noteToGuess.toMap(),
                "guesses", guessMaps
        );
    }
}
