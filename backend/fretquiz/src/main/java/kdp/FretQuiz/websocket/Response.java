package kdp.FretQuiz.websocket;

import kdp.FretQuiz.game.GameRec;
import kdp.FretQuiz.user.User;

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

    static LoginOk loginOk(User user) {
        return new LoginOk(user);
    }

    record LogoutOk(Type type) implements Response {
        public LogoutOk() {
            this(Type.LOGOUT_OK);
        }
    }

    static LogoutOk logoutOk() {
        return new LogoutOk();
    }

    record GameCreated(Type type, GameRec game) implements Response {
        public GameCreated(GameRec game) {
            this(Type.GAME_CREATED, game);
        }
    }

    static GameCreated gameCreated(GameRec game) {
        return new GameCreated(game);
    }

    record GetGameIds(Type type, String[] gameIds) implements Response {
        public GetGameIds(String[] gameIds) {
            this(Type.GET_GAME_IDS, gameIds);
        }
    }

    static GetGameIds getGameIds(String[] gameIds) {
        return new GetGameIds(gameIds);
    }

//    record GuessResult(Type type, Game.GuessResult result) implements Response {
//        public GuessResult(Game.GuessResult result) {
//            this(Type.GUESS_RESULT, result);
//        }
//    }
//
//    static GuessResult guessResult(Game.GuessResult result) {
//        return new GuessResult(result);
//    }

    record GameJoined(Type type, GameRec game) implements Response {
        public GameJoined(GameRec game) {
            this(Type.GAME_JOINED, game);
        }
    }

    static GameJoined gameJoined(GameRec game) {
        return new GameJoined(game);
    }
}
