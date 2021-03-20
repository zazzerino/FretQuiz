package kdp.fretquiz.game;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GameDao {
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public Optional<Game> getGameById(String id) {
        return Optional.ofNullable(games.get(id));
    }

    public Collection<Game> getAll() {
        return games.values();
    }

    public String[] getAllIds() {
        return games.values()
                .stream()
                .map(Game::id)
                .toArray(String[]::new);
    }

    public void save(Game game) {
        games.put(game.id(), game);
    }

    public void delete(String id) {
        games.remove(id);
    }
}
