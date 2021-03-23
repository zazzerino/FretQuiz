package kdp.FretQuiz.game;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A game database object.
 */
public class GameDao {
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public Optional<Game> getGameById(String id) {
        return Optional.ofNullable(games.get(id));
    }

    public String[] getGameIds() {
        return games.values()
                .stream()
                .map(game -> game.id)
                .toArray(String[]::new);
    }

    public void save(Game game) {
        games.put(game.id, game);
    }

    public void delete(String id) {
        games.remove(id);
    }
}
