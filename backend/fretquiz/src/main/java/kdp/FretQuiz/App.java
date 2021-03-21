package kdp.FretQuiz;

import io.javalin.Javalin;
import kdp.FretQuiz.game.GameDao;
import kdp.FretQuiz.user.UserDao;
import kdp.FretQuiz.websocket.WebSocket;

public class App {
    private static final int PORT = 8080;
    private static final String STATIC_FILE_PATH = "/public";

    public static UserDao userDao = new UserDao();
    public static GameDao gameDao = new GameDao();

    public static void main( String[] args ) {
        var app = Javalin.create(config -> {
            config.addStaticFiles(STATIC_FILE_PATH);
        });

        app.ws("/ws", ws -> {
            ws.onConnect(WebSocket::onConnect);
            ws.onMessage(WebSocket::onMessage);
            ws.onClose(WebSocket::onClose);
        });

        app.start(PORT);
    }
}
