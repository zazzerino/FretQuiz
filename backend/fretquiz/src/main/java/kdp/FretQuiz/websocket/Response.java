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
        LOGGED_IN,
        LOGGED_OUT,
        GAME_IDS,
        GAME_CREATED,
        GAME_JOINED,
        GAME_STARTED,
        GUESS_RESULT,
        PLAYER_JOINED,
        GAME_UPDATED;
    }

    record LoggedIn(Type type, User user) implements Response {
        public LoggedIn(User user) {
            this(Type.LOGGED_IN, user);
        }
    }

    record LoggedOut(Type type) implements Response {
        public LoggedOut() {
            this(Type.LOGGED_OUT);
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
            this(Type.PLAYER_JOINED, message);
        }
    }

    record GameUpdated(Type type, Map<String, Object> game) implements Response {
        public GameUpdated(Game game) {
            this(Type.GAME_UPDATED, game.toMap());
        }
    }
}
