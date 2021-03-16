package kdp.fretquiz.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String userId;
    private final List<Guess> guesses = new ArrayList<>();

    public Player(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
