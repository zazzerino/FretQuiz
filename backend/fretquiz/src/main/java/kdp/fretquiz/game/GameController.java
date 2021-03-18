package kdp.fretquiz.game;

import io.javalin.websocket.WsContext;
import kdp.fretquiz.websocket.Response;
import kdp.fretquiz.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static kdp.fretquiz.App.gameDao;

public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    public static void getAll(WsContext context) {
        var games = gameDao.getAll();
        var response = Response.allGames(games);

        context.send(response);
    }

    public static void createGame(WsContext context) {
        var user = WebSocket.getUserFromContext(context);
        var player = new Player(user.id());
        var game = new Game().addPlayer(player);
        var response = Response.gameCreated(game);

        log.info("creating game: " + game);
        gameDao.save(game);

        context.attribute("gameId", game.id());
        context.send(response);
    }
}
