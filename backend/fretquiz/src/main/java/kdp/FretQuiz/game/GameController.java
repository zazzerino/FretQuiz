package kdp.FretQuiz.game;

import io.javalin.websocket.WsMessageContext;
import kdp.FretQuiz.websocket.Request;
import kdp.FretQuiz.websocket.Response;
import kdp.FretQuiz.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

import static kdp.FretQuiz.App.gameDao;

public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    /**
     * Sends a user a list of game ids.
     */
    public static void getGameIds(WsMessageContext context) {
        var ids = gameDao.getGameIds();
        var response = new Response.GetGameIds(ids);

        context.send(response);
    }

    /**
     * Creates a new game and sends the game info back to the client.
     * Then it sends the updated game id list to each connected client.
     */
    public static void createGame(WsMessageContext context) {
        var user = WebSocket.getUserFromContext(context);
        var player = new Player(user.id());

        var game = new Game()
                .addPlayer(player)
                .assignHost(player.id());

        var response = new Response.GameCreated(game);

        log.info("creating game: " + game);
        gameDao.save(game);

        context.attribute("gameId", game.id);
        context.send(response);

        // let the users know there's a new game
        broadcastGameIds();
    }

    /**
     * Sends an up to date array of game ids to every connected client.
     */
    public static void broadcastGameIds() {
        var ids = gameDao.getGameIds();
        var response = new Response.GetGameIds(ids);

        WebSocket.broadcast(response);
    }

    /**
     * Connect's a user to a game and then sends the user the game's info.
     */
    public static void joinGame(WsMessageContext context) {
        var message = context.message(Request.JoinGameMessage.class);

        var userId = message.playerId();
        var gameId = message.gameId();

        var player = new Player(userId);
        var game = gameDao.getGameById(gameId)
                .orElseThrow(NoSuchElementException::new)
                .addPlayer(player);

        log.info("saving player " + userId + " to game " + gameId);
        gameDao.save(game);

        var response = new Response.GameJoined(game);

        context.attribute("gameId", game.id);
        context.send(response);
    }

    public static void handleGuess(WsMessageContext context) {
        var message = context.message(Request.PlayerGuessed.class);

        var newGuess = message.newGuess();
        var gameId = newGuess.gameId();

        var game = gameDao.getGameById(gameId)
                .orElseThrow(NoSuchElementException::new);

        var isCorrect = game.guess(newGuess);
        gameDao.save(game);

        var response = new Response.GuessResult(isCorrect, game);
        context.send(response);
    }
}
