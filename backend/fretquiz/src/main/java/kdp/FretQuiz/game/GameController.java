package kdp.FretQuiz.game;

import io.javalin.websocket.WsMessageContext;
import kdp.FretQuiz.user.UserController;
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
import static kdp.FretQuiz.App.userDao;

public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    /**
     * Maps a gameId to a set of userIds belonging to the game's players.
     */
    private static final @NotNull Map<String, Set<String>> gameSessions = new HashMap<>();

    /**
     * Returns a set of playerIds belonging to the players of the game with gameId.
     */
    private static Set<String> getPlayerIds(String gameId) {
        var playerIds = gameSessions.get(gameId);

        if (playerIds == null) {
            playerIds = new HashSet<>();
        }

        return playerIds;
    }

    private static void connectUserToGame(String gameId, String userId) {
        final var playerIds = getPlayerIds(gameId);
        playerIds.add(userId);
        gameSessions.put(gameId, playerIds);
    }

    /**
     * Send a message to all players of a game.
     */
    private static void notifyPlayers(String gameId, Response response) {
        final var sessionIds = getPlayerIds(gameId);
        WebSocket.sendToSessions(sessionIds, response);
    }

    /**
     * Send the new state of a game to all its players.
     */
    private static void sendUpdatedGameToPlayers(String gameId) {
        final var game = gameDao
                .getGameById(gameId)
                .orElseThrow(NoSuchElementException::new);

        notifyPlayers(game.id, new Response.GameUpdated(game));
    }

    public static void getGameIds(WsMessageContext context) {
        final var ids = gameDao.getGameIds();
        context.send(new Response.GameIds(ids));
    }

    /**
     * Creates a new game and sends the game info back to the client.
     * Then it sends the updated game id list to each connected client.
     */
    public static void createGame(WsMessageContext context) {
        final var user = UserController.getUserFromContext(context);

        final var game = new Game()
                .addPlayer(user)
                .assignHost(user.getId());

        log.info("creating game: " + game);
        gameDao.save(game);

        connectUserToGame(game.id, context.getSessionId());
        context.send(new Response.GameCreated(game));

        // let users know there's a new game
        broadcastGameIds();
    }

    /**
     * Sends an up to date array of game ids to every connected client.
     */
    public static void broadcastGameIds() {
        final var ids = gameDao.getGameIds();
        WebSocket.broadcast(new Response.GameIds(ids));
    }

    /**
     * Connect's a user to a game and then sends the user the game's info.
     */
    public static void joinGame(WsMessageContext context) {
        final var message = context.message(Request.JoinGame.class);

        final var userId = message.playerId();
        final var gameId = message.gameId();

        final var user = userDao.getUserById(userId)
                .orElseThrow(NoSuchElementException::new);

        log.info("adding user " + userId + " to game " + gameId);
        final var game = gameDao
                .getGameById(gameId)
                .orElseThrow(NoSuchElementException::new)
                .addPlayer(user);

        gameDao.save(game);

        connectUserToGame(game.id, context.getSessionId());
        context.send(new Response.GameJoined(game));

        notifyPlayers(game.id, new Response.PlayerJoined(userId + " has joined the game"));
        sendUpdatedGameToPlayers(game.id);
    }

    public static void startGame(WsMessageContext context) {
        final var message = context.message(Request.StartGame.class);

        final var gameId = message.gameId();
        final var game = gameDao
                .getGameById(gameId)
                .orElseThrow(NoSuchElementException::new)
                .start();

        gameDao.save(game);

        context.send(new Response.GameStarted(game));
        sendUpdatedGameToPlayers(game.id);
    }

    public static void handleGuess(WsMessageContext context) {
        final var message = context.message(Request.PlayerGuess.class);

        final var clientGuess = message.clientGuess();
        final var gameId = clientGuess.gameId();

        final var game = gameDao
                .getGameById(gameId)
                .orElseThrow(NoSuchElementException::new);

        final var guess = game.guess(clientGuess);

        gameDao.save(game);

        context.send(new Response.GuessResult(guess, game));
        sendUpdatedGameToPlayers(game.id);
    }

    public static void startNextRound(WsMessageContext context) {
        final var message = context.message(Request.NextRound.class);

        final var gameId = message.gameId();
        final var playerId = message.playerId();

        final var game = gameDao
                .getGameById(gameId)
                .orElseThrow(NoSuchElementException::new);

        final var userIsHost = game.getHostId().equals(playerId);
        final var currentRound = game.currentRound();
        final var roundIsOver = currentRound.isPresent() && currentRound.get().isOver();

        if (userIsHost && roundIsOver) {
            game.nextRound();
            gameDao.save(game);
            notifyPlayers(game.id, new Response.RoundStarted(game));
        }
    }

    /**
     * Removes finished games.
     */
    public static void cleanupGames() {
        gameDao.getAll()
                .removeIf(Game::isOver);
    }
}
