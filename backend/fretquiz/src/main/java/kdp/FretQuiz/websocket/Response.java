package kdp.FretQuiz.websocket;

import kdp.FretQuiz.game.Game;
import kdp.FretQuiz.user.User;

import java.util.Map;

/**
 * A Response is a message sent from the server to the client.
 * Each Response has a Response.Type and, optionally, some associated data.
 */
public interface Response {
    Type type();

    enum Type {
        LOGIN_OK,
        LOGOUT_OK,
        GAME_IDS,
        GAME_CREATED,
        GAME_JOINED,
        GAME_STARTED,
        GUESS_RESULT,
        WELCOME,
        GAME_UPDATED;
    }

    record LoginOk(Type type, User user) implements Response {
        public LoginOk(User user) {
            this(Type.LOGIN_OK, user);
        }
    }

    record LogoutOk(Type type) implements Response {
        public LogoutOk() {
            this(Type.LOGOUT_OK);
        }
    }

    record GameCreated(Type type, Map<String, Object> game) implements Response {
        public GameCreated(Game game) {
            this(Type.GAME_CREATED, game.toMap());
        }
    }

    record GameIds(Type type, String[] gameIds) implements Response {
        public GameIds(String[] gameIds) {
            this(Type.GAME_IDS, gameIds);
        }
    }

    record GameJoined(Type type, Map<String, Object> game) implements Response {
        public GameJoined(Game game) {
            this(Type.GAME_JOINED, game.toMap());
        }
    }

    record GameStarted(Type type, Map<String, Object> game) implements Response {
        public GameStarted(Game game) {
            this(Type.GAME_STARTED, game.toMap());
        }
    }

    record GuessResult(Type type, boolean isCorrect, Map<String, Object> game) implements Response {
        public GuessResult(boolean isCorrect, Game game) {
            this(Type.GUESS_RESULT, isCorrect, game.toMap());
        }
    }

    record PlayerJoined(Type type, String message) implements Response {
        public PlayerJoined(String message) {
            this(Type.WELCOME, message);
        }
    }

    record GameUpdated(Type type, Map<String, Object> game) implements Response {
        public GameUpdated(Game game) {
            this(Type.GAME_UPDATED, game.toMap());
        }
    }
}
