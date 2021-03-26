package kdp.FretQuiz.user;

import io.javalin.websocket.WsMessageContext;
import kdp.FretQuiz.websocket.Request;
import kdp.FretQuiz.websocket.Response;
import kdp.FretQuiz.websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static kdp.FretQuiz.App.userDao;

public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static void login(WsMessageContext context) {
        final var message = context.message(Request.Login.class);
        final var name = message.name();

        final var user = WebSocket.getUserFromContext(context)
                .withName(name);

        userDao.save(user);
        WebSocket.setUserAttributes(context, user);

        context.send(new Response.LoggedIn(user));
    }

    public static void logout(WsMessageContext context) {
        final var message = context.message(Request.Logout.class);

        final var user = WebSocket.getUserFromContext(context)
                .withName(User.DEFAULT_NAME);

        userDao.save(user);
        WebSocket.setUserAttributes(context, user);

        context.send(new Response.LoggedOut());
    }
}
