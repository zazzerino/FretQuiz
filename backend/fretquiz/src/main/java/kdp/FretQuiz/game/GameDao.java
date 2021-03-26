package kdp.FretQuiz.game;

import io.javalin.websocket.WsContext;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Optional<Game> getGameById(String id) {
        return Optional.ofNullable(games.get(id));
    }

    public String[] getGameIds() {
        return getAll()
                .stream()
                .map(Game::id)
                .toArray(String[]::new);
    }

    public void save(Game game) {
        games.put(game.id, game);
    }

    public void delete(String id) {
        games.remove(id);
    }
}
