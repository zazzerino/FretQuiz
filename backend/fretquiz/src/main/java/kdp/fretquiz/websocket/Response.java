package kdp.fretquiz.websocket;

import kdp.fretquiz.user.User;

import java.util.Map;

public class Response {

    enum Type {
        BROADCAST("BROADCAST"),
        LOGIN_OK("LOGIN_OK"),
        LOGOUT_OK("LOGOUT_OK");

        Type(String type) {}
    }

    public static Map<String, Object> broadcast(String message) {
        return Map.of(
                "type", Type.BROADCAST,
                "message", message
        );
    }

    public static Map<String, Object> loginOk(User user) {
        return Map.of(
                "type", Type.LOGIN_OK,
                "user", user.toMap()
        );
    }

    public static Map<String, Object> logoutOk(User user) {
        return Map.of(
                "type", Type.LOGOUT_OK,
                "user", user.toMap()
        );
    }
}
