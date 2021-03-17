package kdp.fretquiz;

import io.javalin.Javalin;
import kdp.fretquiz.game.GameController;
import kdp.fretquiz.game.GameDao;
import kdp.fretquiz.theory.Note;
import kdp.fretquiz.user.UserController;
import kdp.fretquiz.user.UserDao;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    private static final int PORT = 8080;
    private static final String STATIC_FILE_PATH = "/public";

    public static UserDao userDao = new UserDao();
    public static GameDao gameDao = new GameDao();

    public static void main( String[] args ) {
        var app = Javalin.create(config -> {
            config.addStaticFiles(STATIC_FILE_PATH);
        });

        app.routes(() -> {
            path("api", () -> {
                path("user", () -> {
                    get("all", UserController.getAll);
                    post("login", UserController.handleLogin);
                });
                path("game", () -> {
                    get("all", GameController.getAll);
                    post("create", GameController.create);
                });
            });
        });

        app.ws("/ws", ws -> {
//            pat
        });

        app.start(PORT);
    }
}
