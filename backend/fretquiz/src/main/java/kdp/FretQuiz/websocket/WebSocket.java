package kdp.FretQuiz.websocket;

import io.javalin.websocket.WsCloseContext;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import kdp.FretQuiz.game.GameController;
import kdp.FretQuiz.user.User;
import kdp.FretQuiz.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import static kdp.FretQuiz.App.gameDao;
import static kdp.FretQuiz.App.userDao;

public class WebSocket {

    private static final Logger log = LoggerFactory.getLogger(WebSocket.class);

    /**
     * Maps the user's id to their context.
     */
    private static final Map<String, WsContext> contexts = new HashMap<>();

    /**
     * This method is run when a user first connects to the site.
     * It logs them in as an anonymous user and sends them a list of game ids.
     */
    public static void onConnect(WsContext context) {
        final var sessionId = context.getSessionId();
        log.info("ws connection: " + sessionId);

        // create user
        final var user = new User(sessionId);
        contexts.put(user.id(), context);

        log.info("saving user: " + user);
        userDao.save(user);
        setUserIdAttribute(context, user);
        context.send(new Response.LoggedIn(user));

        // send a list of game ids to the user
        final var gameIds = gameDao.getGameIds();
        context.send(new Response.GameIds(gameIds));
    }

    /**
     * This method routes an incoming message to the proper handler.
     */
    public static void onMessage(WsMessageContext context) {
        final var request = context.message(Request.Default.class);
        log.info("message received: " + request.type());

        switch (request.type()) {
            case LOGIN -> UserController.login(context);
            case LOGOUT -> UserController.logout(context);
            case GET_GAME_IDS -> GameController.getGameIds(context);
            case CREATE_GAME -> GameController.createGame(context);
            case JOIN_GAME -> GameController.joinGame(context);
            case START_GAME -> GameController.startGame(context);
            case PLAYER_GUESS -> GameController.handleGuess(context);
            case NEXT_ROUND -> GameController.startNextRound(context);
        }
    }

    public static void onClose(WsCloseContext context) {
        final var userId = getUserIdAttribute(context);
        contexts.remove(userId);
        GameController.cleanupGames();
    }

    /**
     * Send a Response to each connected user.
     */
    public static void broadcast(Response response) {
        contexts.values()
                .forEach(context -> context.send(response));
    }

    /**
     * Send a response to each session in sessionIds.
     */
    public static void sendToSessions(Set<String> sessionIds, Response response) {
        contexts.values()
                .stream()
                .filter(context -> sessionIds.contains(context.getSessionId()))
                .forEach(context -> context.send(response));
    }

    /**
     * Store the user's id as a session attribute.
     */
    public static void setUserIdAttribute(WsContext context, User user) {
        context.attribute("userId", user.id());
    }

    public static String getUserIdAttribute(WsContext context) {
        return Objects.requireNonNull(context.attribute("userId"));
    }

    /**
     * Gets a user's info from the context.
     * Assumes that the "userId" attribute was set during onConnect().
     */
    public static User getUserFromContext(WsContext context) {
        final var userId = getUserIdAttribute(context);

        return userDao
                .getUserById(userId)
                .orElseThrow(NoSuchElementException::new);
    }
}
