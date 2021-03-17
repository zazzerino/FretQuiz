package kdp.fretquiz.websocket;

import io.javalin.websocket.WsCloseContext;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.user.User;
import kdp.fretquiz.user.UserController;
import kdp.fretquiz.websocket.message.DefaultMessage;
import kdp.fretquiz.websocket.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static kdp.fretquiz.App.userDao;

public class WebSocket {
    private static final Logger log = LoggerFactory.getLogger(WebSocket.class);

    public static void onConnect(WsContext context) {
        var sessionId = context.getSessionId();
        log.info("ws connection: " + sessionId);

        var user = new User(sessionId);
        log.info("saving user: " + user);
        userDao.save(user);

        context.attribute("userId", user.id());
        context.attribute("userName", user.name());
        context.send(Response.loginOk(user));
    }

    public static void onMessage(WsMessageContext context) {
        Message message = context.message(DefaultMessage.class);

        switch (message.getType()) {
            case LOGIN -> UserController.handleLogin(context);
            case LOGOUT -> {}
        }
    }

    public static void onClose(WsCloseContext context) {

    }
}
