package kdp.fretquiz.game;

import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.user.User;
import kdp.fretquiz.user.UserController;
import kdp.fretquiz.websocket.Request;
import kdp.fretquiz.websocket.Response;
import kdp.fretquiz.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;

import static kdp.fretquiz.App.gameDao;
import static kdp.fretquiz.App.userDao;

public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    private static void connectUserToGame(String gameId, User user) {
        final var game = gameDao.getGameById(gameId);
        final var player = new Player(user.id, user.name());

        game.addPlayer(player);
        gameDao.save(game);

        user.joinGame(gameId);
        userDao.save(user);
    }

    /**
     * Send a message to all players of a game.
     */
    public static void notifyPlayers(String gameId, Response response) {
        final var userIds = gameDao.getPlayerIds(gameId);
        final var sessionIds = userDao.getSessionIds(userIds);
        WebSocket.sendToSessionIds(sessionIds, response);
    }

    /**
     * Send the new state of a game to all its players.
     */
    public static void sendUpdatedGameToPlayers(String gameId) {
        final var game = gameDao.getGameById(gameId);
        notifyPlayers(game.id, new Response.GameUpdated(game));
    }

    public static void getGameIds(WsMessageContext context) {
        final var gameIds = gameDao.getGameIds();
        context.send(new Response.GameIds(gameIds));
    }

    /**
     * Creates a new game and sends the game info back to the client.
     * Then it sends the updated game id list to each connected client.
     */
    public static void createGame(WsMessageContext context) {
        final var user = UserController.getUserFromContext(context);

        final var game = new Game()
                .assignHost(user.id);

        log.info("creating game: " + game);
        gameDao.save(game);

        connectUserToGame(game.id, user);
        context.send(new Response.GameCreated(game));

        // let users know there's a new game
        broadcastGameInfos();
    }

    public static void broadcastGameInfos() {
        final var gameInfos = gameDao.getGameInfos();
        WebSocket.broadcast(new Response.GameInfos(gameInfos));
    }

    /**
     * Connect's a user to a game and then sends the user the game's info.
     */
    public static void joinGame(WsMessageContext context) {
        final var message = context.message(Request.JoinGame.class);

        final var userId = message.userId();
        final var gameId = message.gameId();

        final var user = userDao.getUserById(userId);
        final var game = gameDao.getGameById(gameId);

        // if the user has already joined the game, do nothing
        if (game.playerIds().contains(userId)) {
            context.send(new Response.Flash("user has already joined game"));
            return;
        }

        log.info("adding user " + userId + " to game " + gameId);
        connectUserToGame(game.id, user);

        context.send(new Response.GameJoined(game));

        notifyPlayers(game.id, new Response.PlayerJoined(userId + " has joined the game"));
        sendUpdatedGameToPlayers(game.id);
        broadcastGameInfos();
    }

    public static void startGame(WsMessageContext context) {
        final var message = context.message(Request.StartGame.class);

        final var gameId = message.gameId();
        final var game = gameDao
                .getGameById(gameId)
                .start();

        gameDao.save(game);

        notifyPlayers(game.id, new Response.GameStarted(game));
    }

    public static void startGameCountdown(WsMessageContext context) {
        final var message = context.message(Request.StartCountdown.class);
        final var gameId = message.gameId();

        final var timer = new Timer();
        final var task = new CountdownTask(
                3,
                (n) -> notifyPlayers(gameId, new Response.GameCountdown(n)),
                () -> startGame(context)
        );

        timer.schedule(task, 0, 1000);
    }

    public static void handleGuess(WsMessageContext context) {
        final var message = context.message(Request.PlayerGuess.class);

        final var clientGuess = message.clientGuess();
        final var gameId = clientGuess.gameId();

        final var game = gameDao.getGameById(gameId);
        final var guess = game.guess(clientGuess);

        gameDao.save(game);

        context.send(new Response.GuessResult(guess, game));
        sendUpdatedGameToPlayers(game.id);

        if (game.isOver()) {
            notifyPlayers(game.id, new Response.GameOver(game));
        }
    }

    public static void startNextRound(WsMessageContext context) {
        final var message = context.message(Request.NextRound.class);

        final var gameId = message.gameId();
        final var userId = message.userId();

        final var game = gameDao.getGameById(gameId);

        final boolean userIsHost = game.hostId().equals(userId);
        final boolean roundIsOver = game.currentRound().isOver();

        if (userIsHost && roundIsOver) {
            game.nextRound();
            gameDao.save(game);
            notifyPlayers(game.id, new Response.RoundStarted(game));
        }
    }

    public static void toggleString(WsMessageContext context) {
        final var message = context.message(Request.ToggleString.class);

        final var gameId = message.gameId();
        final var stringNum = message.string();

        final var game = gameDao
                .getGameById(gameId)
                .toggleString(stringNum);

        gameDao.save(game);
        sendUpdatedGameToPlayers(game.id);
    }

    public static void toggleAccidental(WsMessageContext context) {
        final var message = context.message(Request.ToggleAccidental.class);

        final var gameId = message.gameId();
        final var accidental = message.accidental();

        final var game = gameDao
                .getGameById(gameId)
                .toggleAccidental(accidental);

        gameDao.save(game);
        sendUpdatedGameToPlayers(game.id);
    }

    public static void setRoundCount(WsMessageContext context) {
        final var message = context.message(Request.SetRoundCount.class);

        final var gameId = message.gameId();
        final var roundCount = message.roundCount();

        final var game = gameDao
                .getGameById(gameId)
                .setRoundCount(roundCount);

        gameDao.save(game);
        sendUpdatedGameToPlayers(game.id);
    }

    /**
     * Removes finished games.
     * @param minutes if the game is older than the given number of minutes it will be removed
     */
    public static void cleanupGames(int minutes) {
        log.info("cleaning up games...");
        final var games = gameDao.getAllGames();

        for (final var game : games) {
            if (game.isOver() || game.isOlderThan(minutes)) {
                log.info("removing game: " + game.id);
                gameDao.delete(game.id);
            }
        }

        broadcastGameInfos();
    }
}
