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
        GET_GAME_IDS,
        GAME_CREATED,
        GAME_JOINED,
        GUESS_RESULT;
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

    record GetGameIds(Type type, String[] gameIds) implements Response {
        public GetGameIds(String[] gameIds) {
            this(Type.GET_GAME_IDS, gameIds);
        }
    }

    record GameJoined(Type type, Map<String, Object> game) implements Response {
        public GameJoined(Game game) {
            this(Type.GAME_JOINED, game.toMap());
        }
    }

    record GuessResult(Type type, boolean isCorrect, Game game) {
        public GuessResult(boolean isCorrect, Game game) {
            this(Type.GUESS_RESULT, isCorrect, game);
        }
    }
}
