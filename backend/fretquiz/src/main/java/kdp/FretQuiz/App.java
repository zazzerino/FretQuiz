package kdp.FretQuiz;

import io.javalin.Javalin;
import kdp.FretQuiz.game.GameDao;
import kdp.FretQuiz.user.UserDao;
import kdp.FretQuiz.websocket.WebSocket;

/**
 * The main application class.
 * App sets up the global objects (userDao, gameDao)
 * and routes incoming websocket messages to the correct handler.
 */
public class App {
    private static final int PORT = 8080;
    private static final String STATIC_FILE_PATH = "/public";
    private static final String WEBSOCKET_PATH = "/ws";

    /**
     * The userDao stores info about users.
     */
    public static UserDao userDao = new UserDao();
    /**
     * The gameDao stores info about games.
     */
    public static GameDao gameDao = new GameDao();

    public static void main( String[] args ) {
        var app = Javalin.create(config -> {
            config.addStaticFiles(STATIC_FILE_PATH);
        });

        app.ws(WEBSOCKET_PATH, ws -> {
            ws.onConnect(WebSocket::onConnect);
            ws.onMessage(WebSocket::onMessage);
            ws.onClose(WebSocket::onClose);
        });

        app.start(PORT);
    }
}
