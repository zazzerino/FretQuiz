package kdp.FretQuiz.game;

import io.javalin.websocket.WsContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A game data access object.
 */
public class GameDao {

    /**
     * Stores a map of game-ids to games.
     */
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    /**
     * Stores a map of game-ids to websocket contexts.
     * Each context belongs to a user who is playing the specified game.
     */
    private final Map<String, List<WsContext>> contexts = new ConcurrentHashMap<>();

    public Collection<Game> getAll() {
        return games.values();
    }

    public Game getById(String gameId) {
        return Objects.requireNonNull(games.get(gameId));
    }

    public List<String> getGameIds() {
        return getAll()
                .stream()
                .map(game -> game.id)
                .toList();
    }

    public List<String> getUserIds(String gameId) {
        return getById(gameId).getUserIds();
    }

    public void save(Game game) {
        games.put(game.id, game);
    }

    public void delete(String id) {
        games.remove(id);
    }
}
