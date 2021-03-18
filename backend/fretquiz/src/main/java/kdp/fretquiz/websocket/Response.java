package kdp.fretquiz.websocket;

import kdp.fretquiz.game.Game;
import kdp.fretquiz.user.User;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Response {

    enum Type {
        BROADCAST("BROADCAST"),
        LOGIN_OK("LOGIN_OK"),
        LOGOUT_OK("LOGOUT_OK"),
        ALL_GAMES("ALL_GAMES"),
        GAME_CREATED("GAME_CREATED");

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
                "user", user
        );
    }

    public static Map<String, Object> logoutOk(User user) {
        return Map.of(
                "type", Type.LOGOUT_OK,
                "user", user
        );
    }

    public static Map<String, Object> gameCreated(Game game) {
        return Map.of(
                "type", Type.GAME_CREATED,
                "game", game
        );
    }

    public static Map<String, Object> allGames(Collection<Game> games) {
        return Map.of(
                "type", Type.ALL_GAMES,
                "games", games
        );
    }
}
