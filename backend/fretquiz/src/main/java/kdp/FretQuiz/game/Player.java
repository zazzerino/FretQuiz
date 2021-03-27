package kdp.FretQuiz.game;

import java.util.ArrayList;
import java.util.List;

public record Player(String id, List<String> games) {

    public Player(String id) {
        this(id, new ArrayList<>());
    }

    public Player joinGame(String gameId) {
        this.games.add(gameId);
        return this;
    }

    public Player leaveGame(String gameId) {
        this.games.remove(gameId);
        return this;
    }

    public Player leaveAllGames() {
        for (var gameId : this.games) {
            leaveGame(gameId);
        }

        return this;
    }
}
