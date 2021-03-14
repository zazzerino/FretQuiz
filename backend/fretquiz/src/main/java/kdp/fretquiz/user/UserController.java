package kdp.fretquiz.user;

import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static kdp.fretquiz.App.userDao;

public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    public static Handler getAll = context -> {
        var users = userDao.getAll();
        var response = Map.of("users", users);

        context.json(response);
    };

    public static Handler handleLogin = context -> {
        var name = context.formParam("name");
        var user = new User(name);

        log.info("logging in " + user);

//        context.cookie("username", user.name());
//        context.sessionAttribute("user", user.toMap());

        context.json(Map.of("user", user.toMap()));
    };
}
