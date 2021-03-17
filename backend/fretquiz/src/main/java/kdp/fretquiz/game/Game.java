package kdp.fretquiz.game;

import kdp.fretquiz.Util;
import kdp.fretquiz.theory.FretCoord;
import kdp.fretquiz.theory.Fretboard;
import kdp.fretquiz.theory.Note;

import java.util.*;
import java.util.stream.Collectors;

public record Game(String id,
                   GameOpts opts,
                   Map<String, Player> players,
                   Map<String, Note> notesToGuess,
                   List<Guess> guesses) {

    public static Game create(Player ...players) {
        var game = new Game(
                Util.randomId(),
                GameOpts.DEFAULT,
                new HashMap<>(),
                new HashMap<>(),
                new ArrayList<>()
        );

        for (var player : players) {
            game = game.giveNoteTo(player.id());
        }

        return game;
    }

    public static boolean correctGuess(Note noteToGuess,
                                       FretCoord clickedFret,
                                       Fretboard fretboard) {
        var guessedNote = fretboard.findNote(clickedFret)
                .orElseThrow(NoSuchElementException::new);

        return guessedNote.isEnharmonicWith(noteToGuess);
    }

    public Game giveNoteTo(String playerId) {
        var note = Note.random();

        var notesToGuess = new HashMap<>(notesToGuess());
        notesToGuess.put(playerId, note);

        return new Game(id, opts, players, notesToGuess, guesses);
    }

    public Game addPlayer(Player player) {
        var players = new HashMap<>(this.players);
        players.put(player.id(), player);

        return new Game(id, opts, players, notesToGuess, guesses);
    }

    public Game handleGuess(String playerId, FretCoord clickedFret) {
        var noteToGuess = notesToGuess.get(playerId);
        var isCorrect = correctGuess(noteToGuess, clickedFret, opts.fretboard());
        var guess = new Guess(playerId, noteToGuess, clickedFret, isCorrect);

        var guesses = new ArrayList<>(this.guesses);
        guesses.add(guess);

        return new Game(id, opts, players, notesToGuess, guesses)
                .giveNoteTo(playerId);
    }

    public Map<String, Object> toMap() {
        var players = this.players.values()
                .stream()
                .map(Player::toMap)
                .collect(Collectors.toList());

        var notesToGuess = this.notesToGuess.values()
                .stream()
                .map(Note::name)
                .collect(Collectors.toList());

        var guesses = this.guesses
                .stream()
                .map(Guess::toMap)
                .collect(Collectors.toList());

        return Map.of(
                "id", id,
                "opts", opts,
                "players", players,
                "notesToGuess", notesToGuess,
                "guesses", guesses
        );
    }
}
