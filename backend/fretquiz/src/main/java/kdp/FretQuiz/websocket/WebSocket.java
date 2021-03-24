package kdp.FretQuiz.websocket;

import io.javalin.websocket.WsCloseContext;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import kdp.FretQuiz.game.GameController;
import kdp.FretQuiz.user.User;
import kdp.FretQuiz.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import static kdp.FretQuiz.App.gameDao;
import static kdp.FretQuiz.App.userDao;

public class WebSocket {

    private static final Logger log = LoggerFactory.getLogger(WebSocket.class);

    /**
     * Holds the context for each connected user.
     */
    private static final List<WsContext> contexts = new ArrayList<>();

    /**
     * This method is run when a user first connects to the site.
     * It logs them in as an anonymous user and sends them a list of game ids.
     */
    public static void onConnect(WsContext context) {
        contexts.add(context);

        final var sessionId = context.getSessionId();
        log.info("ws connection: " + sessionId);

        final var user = new User(sessionId);
        log.info("saving user: " + user);
        userDao.save(user);
        setUserAttributes(context, user);

        context.send(new Response.LoginOk(user));

        // send the user a list of game ids
        final var gameIds = gameDao.getGameIds();
        context.send(new Response.GetGameIds(gameIds));
    }

    /**
     * This method routes an incoming message to the proper handler.
     */
    public static void onMessage(WsMessageContext context) {
        final Request request = context.message(Request.Default.class);

        log.info("message received: " + request.type());
        switch (request.type()) {
            case LOGIN -> UserController.login(context);
            case LOGOUT -> {}
            case GET_GAME_IDS -> GameController.getGameIds(context);
            case CREATE_GAME -> GameController.createGame(context);
            case JOIN_GAME -> GameController.joinGame(context);
//            case START_GAME -> Game
            case PLAYER_GUESSED -> GameController.handleGuess(context);
        }
    }

    public static void onClose(WsCloseContext context) {
        contexts.remove(context);
    }

    /**
     * Send a Response to each connected user.
     */
    public static void broadcast(Response response) {
        contexts.forEach(context -> {
            context.send(response);
        });
    }

    /**
     * Send a response to each session in sessionIds.
     */
    public static void sendToSessions(Set<String> sessionIds, Response response) {
        contexts.stream()
                .filter(ctx -> sessionIds.contains(ctx.getSessionId()))
                .forEach(ctx -> ctx.send(response));
    }

    /**
     * Store the user's info as context attributes.
     */
    public static void setUserAttributes(WsContext context, User user) {
        context.attribute("playerId", user.id());
        context.attribute("userName", user.name());
    }

    /**
     * Gets a user's info from the context.
     * Assumes that there is a "playerId" attribute already set (which should be done on connect).
     */
    public static User getUserFromContext(WsContext context) {
        final var userId = Objects.requireNonNull(context.attribute("playerId")).toString();

        return userDao.getUserById(userId)
                .orElseThrow(NoSuchElementException::new);
    }
}
