package kdp.fretquiz.apigame;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

import static kdp.fretquiz.App.gameDao;

public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    public static Handler getAll = context -> {
        var games = gameDao.getAll()
                .stream()
                .collect(Collectors.toMap(Game::getId, Game::toMap));

        var response = Map.of("games", games);

        context.json(response);
    };

    public static Handler create = context -> {
        String userId = context.sessionAttribute("userId");

        if (userId == null) {
            throw new RuntimeException("Must be logged in to create a new game.");
        }

        var game = new Game();
        game.addPlayer(new Player(userId, game.id));
        game.giveNewNote(userId);

        log.info("creating game: " + game);
        gameDao.save(game);

        var response = Map.of("game", game.toMap());
        context.sessionAttribute("gameId", game.id);
        context.json(response);
    };
}
