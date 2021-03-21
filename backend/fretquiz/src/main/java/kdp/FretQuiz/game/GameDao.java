package kdp.FretQuiz.game;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GameDao {
    private final Map<String, GameRec> games = new ConcurrentHashMap<>();

    public Optional<GameRec> getGameById(String id) {
        return Optional.ofNullable(games.get(id));
    }

    public Collection<GameRec> getAll() {
        return games.values();
    }

    public String[] getIds() {
        return games.values()
                .stream()
                .map(GameRec::id)
                .toArray(String[]::new);
    }

    public void save(GameRec game) {
        games.put(game.id(), game);
    }

    public void delete(String id) {
        games.remove(id);
    }
}
