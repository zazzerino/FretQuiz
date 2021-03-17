package kdp.fretquiz.apigame;

import kdp.fretquiz.Util;
import kdp.fretquiz.theory.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    public final String id = Util.randomId();
    private int roundLength = 30;
    private int secondsRemaining = 30;

    private final Map<String, Player> players = new HashMap<>();
    private final Map<String, Note> notesToGuess = new HashMap<>();
    private final List<Guess> guesses = new ArrayList<>();

    private final Tuning tuning = Tuning.STANDARD_GUITAR;
    private final int startFret = 0;
    private final int endFret = 4;
    private final Map<Note, FretCoord> notes = Fretboard.notes(tuning, startFret, endFret);

    public static Game createWith(Player ...players) {
        var game = new Game();

        for (var player : players) {
            game.addPlayer(player);
            game.giveNewNote(player.userId());
        }

        return game;
    }

    public String getId() {
        return this.id;
    }

    public static boolean correctGuess(Note noteToGuess,
                                       FretCoord clickedFret,
                                       Tuning tuning,
                                       Map<Note, FretCoord> fretboardNotes) {
        var guessedNote = Fretboard.findNoteAt(clickedFret, tuning, fretboardNotes);
        return guessedNote.isEnharmonicWith(noteToGuess);
    }

    public Note giveNewNote(String playerId) {
        var note = Note.random();
        notesToGuess.put(playerId, note);

        return note;
    }

    public boolean handleGuess(String playerId, FretCoord clickedFret) {
        var noteToGuess = notesToGuess.get(playerId);
        var isCorrect = correctGuess(noteToGuess, clickedFret, tuning, notes);
        var guess = new Guess(playerId, noteToGuess, clickedFret, isCorrect);

        guesses.add(guess);
        giveNewNote(playerId);

        return isCorrect;
    }

    public void addPlayer(Player player) {
        players.put(player.userId(), player);
    }

    public Map<String, Object> toMap() {
        var playerMaps = players.values().stream()
                .map(Player::toMap)
                .collect(Collectors.toList());

        Map<String, String> noteNames = new HashMap<>();

        for (var entry : notesToGuess.entrySet()) {
            noteNames.put(entry.getKey(), entry.getValue().name());
        }

        List<Map<String, Object>> guessMaps = guesses.stream()
                .map(Guess::toMap)
                .collect(Collectors.toList());

        return Map.of(
                "roundLength", roundLength,
                "secondsRemaining", secondsRemaining,
                "players", playerMaps,
                "notesToGuess", noteNames,
                "guesses", guessMaps
        );
    }
}
