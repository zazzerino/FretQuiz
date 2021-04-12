package kdp.fretquiz.game;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A game data access object.
 */
public class GameDao {

    /**
     * Stores a map of game-ids to games.
     */
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public Collection<Game> getAllGames() {
        return games.values();
    }

    public Game getGameById(String gameId) {
        return Objects.requireNonNull(games.get(gameId));
    }

    public List<String> getGameIds() {
        return getAllGames().stream()
                .map(game -> game.id)
                .toList();
    }

    public List<Game.Info> getGameInfos() {
        return getAllGames().stream()
                .map(Game::info)
                .sorted(Comparator.comparing(Game.Info::createdAt).reversed()) // sort by newest first
                .toList();
    }

    public List<String> getPlayerIds(String gameId) {
        return getGameById(gameId).playerIds();
    }

    public void save(Game game) {
        games.put(game.id, game);
    }

    public void delete(String id) {
        games.remove(id);
    }
}
