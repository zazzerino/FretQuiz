package kdp.fretquiz.user;

import io.javalin.websocket.WsMessageContext;
import kdp.fretquiz.websocket.Response;
import kdp.fretquiz.websocket.message.LoginMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Objects;

import static kdp.fretquiz.App.userDao;

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static void handleLogin(WsMessageContext context) {
        var message = context.message(LoginMessage.class);
        var name = message.getName();

        var userId = Objects.requireNonNull(context.attribute("userId")).toString();

        var user = userDao.getUserById(userId)
                .orElseThrow(NoSuchElementException::new);

        user = user.withName(name);
        log.info("saving user: " + user);
        userDao.save(user);

        context.send(Response.loginOk(user));
    }
}
