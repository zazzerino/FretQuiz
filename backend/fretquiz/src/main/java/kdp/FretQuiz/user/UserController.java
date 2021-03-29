package kdp.FretQuiz.user;

import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import kdp.FretQuiz.game.GameController;
import kdp.FretQuiz.websocket.Request;
import kdp.FretQuiz.websocket.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static kdp.FretQuiz.App.gameDao;
import static kdp.FretQuiz.App.userDao;

public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static void loginAnonymousUser(WsContext context) {
        final var sessionId = context.getSessionId();
        final var user = new User(sessionId);

        log.info("saving user: " + user);
        userDao.save(user);
        setUserIdAttribute(context, user);

        context.send(new Response.LoggedIn(user));
    }

    public static void login(WsMessageContext context) {
        final var message = context.message(Request.Login.class);
        final var name = message.name();

        final var user = getUserFromContext(context)
                .setName(name);

        log.info("saving user: " + user);
        userDao.save(user);
        setUserIdAttribute(context, user);

        context.send(new Response.LoggedIn(user));
    }

    public static void logout(WsMessageContext context) {
        final var user = getUserFromContext(context)
                .setName(User.DEFAULT_NAME);

        userDao.save(user);
        setUserIdAttribute(context, user);

        context.send(new Response.LoggedOut());
    }

    public static void cleanupUser(WsContext context) {
        final var userId = getUserIdAttribute(context);
        final var user = userDao.getUserById(userId);
        final var gameIds = user.getGameIds();

        for (final var gameId : gameIds) {
            final var game = gameDao.getGameById(gameId);
            game.removePlayer(userId);

            if (game.isOver()) {
                log.info("deleting game: " + gameId);
                gameDao.delete(gameId);
                GameController.broadcastGameIds();
            } else {
                gameDao.save(game);
                GameController.sendUpdatedGameToPlayers(gameId);
            }
        }

        user.leaveAllGames();
        userDao.save(user);

        log.info("removing user: " + userId);
        userDao.delete(userId);
    }

    /**
     * Store the user's id as a session attribute.
     */
    public static void setUserIdAttribute(WsContext context, User user) {
        context.attribute("userId", user.id);
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
        return userDao.getUserById(userId);
    }
}
