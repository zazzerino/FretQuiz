package kdp.fretquiz;

import io.javalin.Javalin;
import kdp.fretquiz.game.GameController;
import kdp.fretquiz.game.GameDao;
import kdp.fretquiz.user.UserDao;
import kdp.fretquiz.websocket.WebSocket;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The main application class.
 * App sets up the global objects (userDao, gameDao)
 * and routes incoming websocket messages to the correct handler.
 * The userDao keeps track of users, and the gameDao keeps track of games.
 */
public class App {

    public static final String WEBSOCKET_PATH = "/ws";

    public static final UserDao userDao = new UserDao();
    public static final GameDao gameDao = new GameDao();

    public static void main(String[] args) {
        // the port number will be passed as a cli argument
        Integer port = null;

        try {
            port = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: must provide a port number as a cli argument");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: port number not parsable as an int");
            System.exit(1);
        }

        final var app = Javalin.create();

        // setup the websocket handlers
        app.ws(WEBSOCKET_PATH, ws -> {
            ws.onConnect(WebSocket::onConnect);
            ws.onMessage(WebSocket::onMessage);
            ws.onClose(WebSocket::onClose);
        });

        app.start(port);

        // cleanup finished games every four minutes
        final var gameCleanupService = Executors.newScheduledThreadPool(1);
        gameCleanupService.scheduleAtFixedRate(
                () -> GameController.cleanupGames(4), 4, 4, TimeUnit.MINUTES
        );

        // broadcast game infos every thirty seconds
        final var gameBroadcastService = Executors.newScheduledThreadPool(1);
        gameBroadcastService.scheduleAtFixedRate(
                GameController::broadcastGameInfos, 30, 30, TimeUnit.SECONDS
        );
    }
}
