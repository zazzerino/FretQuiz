package kdp.fretquiz.websocket;

import io.javalin.websocket.WsCloseContext;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.game.GameController;
import kdp.fretquiz.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static kdp.fretquiz.App.gameDao;

public class WebSocket {

    private static final Logger log = LoggerFactory.getLogger(WebSocket.class);

    /**
     * The connected user sessions.
     */
    private static final List<WsContext> contexts = new ArrayList<>();

    /**
     * Send a response to each session in sessionIds.
     */
    public static void sendToSessionIds(List<String> sessionIds, Response response) {
        contexts.stream()
                .filter(context -> sessionIds.contains(context.getSessionId()))
                .forEach(context -> context.send(response));
    }

    /**
     * Send a Response to each connected user.
     */
    public static void broadcast(Response response) {
        contexts.forEach(context -> context.send(response));
    }

    /**
     * This method is run when a user first connects to the site.
     * It logs them in as an anonymous user and sends them a list of game ids.
     */
    public static void onConnect(WsContext context) {
        contexts.add(context);
        UserController.loginAnonymousUser(context);

        final var gameIds = gameDao.getGameIds();
        context.send(new Response.GameIds(gameIds));

        final var gameInfos = gameDao.getGameInfos();
        context.send(new Response.GameInfos(gameInfos));
    }

    public static void onClose(WsCloseContext context) {
        UserController.cleanupUser(context);

        log.info("removing context with session id: " + context.getSessionId());
        contexts.remove(context);
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
            case TOGGLE_STRING -> GameController.toggleString(context);
            case TOGGLE_ACCIDENTAL -> GameController.toggleAccidental(context);
            case SET_ROUND_COUNT -> GameController.setRoundCount(context);
        }
    }
}
