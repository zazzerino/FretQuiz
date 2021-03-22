package kdp.FretQuiz.websocket;

import kdp.FretQuiz.game.Game;
import kdp.FretQuiz.user.User;

public interface Response {
    Type type();

    enum Type {
        LOGIN_OK,
        LOGOUT_OK,
        GAME_IDS,
        GAME_CREATED,
        GAME_JOINED,
        GUESS_RESPONSE;
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

    record GameCreated(Type type, Game game) implements Response {
        public GameCreated(Game game) {
            this(Type.GAME_CREATED, game);
        }
    }

    record GameIds(Type type, String[] gameIds) implements Response {
        public GameIds(String[] gameIds) {
            this(Type.GAME_IDS, gameIds);
        }
    }

//    record GuessResponse(Type type, Game.GuessResult result) implements Response {
//        public GuessResponse(Game.GuessResult result) {
//            this(Type.GUESS_RESPONSE, result);
//        }
//    }

    record GameJoined(Type type, Game game) implements Response {
        public GameJoined(Game game) {
            this(Type.GAME_JOINED, game);
        }
    }
}
