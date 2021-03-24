package kdp.FretQuiz.game;

import io.javalin.websocket.WsMessageContext;
import kdp.FretQuiz.websocket.Request;
import kdp.FretQuiz.websocket.Response;
import kdp.FretQuiz.websocket.WebSocket;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import static kdp.FretQuiz.App.gameDao;

public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    /**
     * Maps a gameId to a set of sessionIds belonging to users playing the game.
     */
    private static final @NotNull Map<String, Set<String>> gameSessions = new HashMap<>();

    /**
     * Sends a user a list of game ids.
     */
    public static void getGameIds(WsMessageContext context) {
        final var ids = gameDao.getGameIds();
        context.send(new Response.GetGameIds(ids));
    }

    /**
     * Creates a new game and sends the game info back to the client.
     * Then it sends the updated game id list to each connected client.
     */
    public static void createGame(WsMessageContext context) {
        final var user = WebSocket.getUserFromContext(context);
        final var player = new Player(user.id()); // link the game player to the user id

        final var game = new Game()
                .addPlayer(player)
                .assignHost(player.id());

        log.info("creating game: " + game);
        gameDao.save(game);

        connectSessionToGame(game.id, context.getSessionId());

        final var response = new Response.GameCreated(game);
        context.send(response);

        // let users know there's a new game
        broadcastGameIds();
    }

    private static void connectSessionToGame(String gameId, String sessionId) {
        var sessionIds = gameSessions.get(gameId);

        if (sessionIds == null) {
            sessionIds = new HashSet<>();
        }

        sessionIds.add(sessionId);
        gameSessions.put(gameId, sessionIds);
    }

    private static Set<String> getSessionIds(String gameId) {
        return gameSessions.get(gameId);
    }

    private static void notifyPlayers(String gameId, Response response) {
        final var sessionIds = getSessionIds(gameId);
        WebSocket.sendToSessions(sessionIds, response);
    }

    /**
     * Sends an up to date array of game ids to every connected client.
     */
    public static void broadcastGameIds() {
        final var ids = gameDao.getGameIds();
        final var response = new Response.GetGameIds(ids);

        WebSocket.broadcast(response);
    }

    /**
     * Connect's a user to a game and then sends the user the game's info.
     */
    public static void joinGame(WsMessageContext context) {
        final var message = context.message(Request.JoinGameMessage.class);

        final var userId = message.playerId();
        final var gameId = message.gameId();

        log.info("adding player " + userId + " to game " + gameId);
        final var player = new Player(userId);
        final var game = gameDao.getGameById(gameId)
                .orElseThrow(NoSuchElementException::new)
                .addPlayer(player);

        gameDao.save(game);

        connectSessionToGame(game.id, context.getSessionId());

        final var response = new Response.GameJoined(game);
        context.send(response);

        notifyPlayers(game.id, new Response.Welcome(userId + " has joined the game"));
    }

    public static void startGame(WsMessageContext context) {
        final var request = context.message(Request.StartGame.class);

        final var gameId = request.gameId();
        final var game = gameDao.getGameById(gameId)
                .orElseThrow(NoSuchElementException::new);

        game.start();
        gameDao.save(game);

        final var response = new Response.GameStarted(game);
        context.send(response);
    }

    public static void handleGuess(WsMessageContext context) {
        final var request = context.message(Request.PlayerGuessed.class);

        final var newGuess = request.newGuess();
        final var gameId = newGuess.gameId();

        final var game = gameDao.getGameById(gameId)
                .orElseThrow(NoSuchElementException::new);

        final var isCorrect = game.guess(newGuess);

        gameDao.save(game);

        final var response = new Response.GuessResult(isCorrect, game);
        context.send(response);
    }
}
