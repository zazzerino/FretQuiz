package kdp.fretquiz.game;

import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.websocket.Response;
import kdp.fretquiz.websocket.WebSocket;
import kdp.fretquiz.websocket.message.GuessMessage;
import kdp.fretquiz.websocket.message.JoinGameMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static kdp.fretquiz.App.gameDao;

public class GameController {
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    public static void getAllIds(WsMessageContext context) {
        var ids = gameDao.getAll()
                .stream()
                .map(Game::id)
                .collect(Collectors.toList());

        var response = Response.getGameIds(ids);

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

    public static void joinGame(WsMessageContext context) {
        var message = context.message(JoinGameMessage.class);
        var userId = message.userId();
        var gameId = message.gameId();

        var player = new Player(userId);
        var game = gameDao.getGameById(gameId)
                .orElseThrow(NoSuchElementException::new)
                .addPlayer(player);

        var response = Response.gameJoined(game);

        log.info("saving player " + userId + " to game " + gameId);
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

        var guessResult = gameDao.getGameById(gameId)
                .orElseThrow(NoSuchElementException::new)
                .guess(playerId, clickedFret);

        var game = guessResult.game();
        gameDao.save(game);

        var response = Response.guessResult(guessResult);
        context.send(response);
    }
}
