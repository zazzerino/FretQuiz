package kdp.fretquiz.game;

import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.websocket.Response;
import kdp.fretquiz.websocket.WebSocket;
import kdp.fretquiz.websocket.message.GuessMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

import static kdp.fretquiz.App.gameDao;

public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    public static void getAll(WsMessageContext context) {
        var games = gameDao.getAll();
        var response = Response.getGames(games);

        context.send(response);
    }

    public static void createGame(WsMessageContext context) {
        var user = WebSocket.getUserFromContext(context);
        var player = new Player(user.id());

        var game = Game.create()
                .addPlayer(player)
                .assignHost(player.id());

        var response = Response.gameCreated(game);

        log.info("creating game: " + game);
        gameDao.save(game);

        context.attribute("gameId", game.id());
        context.send(response);
    }

    public static void handleGuess(WsMessageContext context) {
        var message = context.message(GuessMessage.class);
        var newGuess = message.guess();
        var playerId = newGuess.playerId();
        var gameId = newGuess.gameId();
        var clickedFret = newGuess.clickedFret();

        var game = gameDao.getGameById(gameId)
                .orElseThrow(NoSuchElementException::new);

        var guessResult = game.guess(playerId, clickedFret);
        game = guessResult.game();
        gameDao.save(game);

        var response = Response.guessResponse(guessResult);
        context.send(response);
    }
}
