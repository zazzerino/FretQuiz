package kdp.fretquiz.websocket;

import kdp.fretquiz.game.Game;
import kdp.fretquiz.user.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Response {

    enum Type {
        BROADCAST("BROADCAST"),
        LOGIN_OK("LOGIN_OK"),
        LOGOUT_OK("LOGOUT_OK"),
        GET_GAME_IDS("GET_GAME_IDS"),
        GAME_CREATED("GAME_CREATED"),
        GAME_JOINED("GAME_JOINED"),
        GUESS_RESPONSE("GUESS_RESPONSE");

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

    public static Map<String, Object> getGameIds(List<String> gameIds) {
        return Map.of(
                "type", Type.GET_GAME_IDS,
                "gameIds", gameIds
        );
    }

    public static Map<String, Object> guessResult(Game.GuessResult result) {
        return Map.of(
                "type", Type.GUESS_RESPONSE,
                "guessResult", result
        );
    }

    public static Map<String, Object> gameJoined(Game game) {
        return Map.of(
                "type", Type.GAME_JOINED,
                "game", game
        );
    }
}
