package kdp.fretquiz.game;

import kdp.fretquiz.theory.Note;
import kdp.fretquiz.theory.Theory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Game {
    private List<Player> players;
    private int roundLength;
    private int secondsRemaining;
    private final Map<String, Note> notesToGuess = new ConcurrentHashMap<>();

    public void start() {

    }

    public Note newNoteToGuess(String playerId) {
        var note = Theory.randomNote();
        notesToGuess.put(playerId, note);

        return note;
    }

    public void handleGuess(String playerId, FretCoord clickedFret) {

    }
}
