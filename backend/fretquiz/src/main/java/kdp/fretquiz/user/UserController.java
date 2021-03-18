package kdp.fretquiz.user;

import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.websocket.Response;
import kdp.fretquiz.websocket.WebSocket;
import kdp.fretquiz.websocket.message.LoginMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static kdp.fretquiz.App.userDao;

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static void handleLogin(WsMessageContext context) {
        var message = context.message(LoginMessage.class);
        var name = message.getName();

        var user = WebSocket.getUserFromContext(context)
                .withName(name);

        log.info("saving user: " + user);
        userDao.save(user);

        WebSocket.setUserAttributes(context, user);
        context.send(Response.loginOk(user));
    }
}
