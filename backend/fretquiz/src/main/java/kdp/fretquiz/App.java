package kdp.fretquiz;

import io.javalin.Javalin;
import kdp.fretquiz.game.GameDao;
import kdp.fretquiz.user.UserDao;
import kdp.fretquiz.websocket.WebSocket;

/**
 * The main application class.
 * App sets up the global objects (userDao, gameDao)
 * and routes incoming websocket messages to the correct handler.
 * The userDao keeps track of users, and the gameDao keeps track of games.
 */
public class App {

    private static final int PORT = 8080;
    private static final String WEBSOCKET_PATH = "/ws";

    public static UserDao userDao = new UserDao();
    public static GameDao gameDao = new GameDao();

    public static void main( String[] args ) {
        final var app = Javalin.create();

        app.ws(WEBSOCKET_PATH, ws -> {
            ws.onConnect(WebSocket::onConnect);
            ws.onMessage(WebSocket::onMessage);
            ws.onClose(WebSocket::onClose);
        });

        app.start(PORT);
    }
}
