package kdp.fretquiz.websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kdp.fretquiz.game.NewGuess;

public interface Request {
    Type type();

    enum Type {
        LOGIN,
        LOGOUT,
        GET_GAME_IDS,
        CREATE_GAME,
        JOIN_GAME,
        GUESS;
    }

    /**
     * Request.Default is an record that only contains the incoming Request's type.
     * Each incoming request is cast to Request.Default first to determine the Request type.
     * Then it is then cast to the proper Request type so it can be used further.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    record Default(Type type) implements Request {}

    record Login(Type type, String name) implements Request {
        public Login(String name) {
            this(Type.LOGIN, name);
        }
    }

//    record GetGameIds(Type type) implements Request {
//        public GetGameIds() {
//            this(Type.GET_GAME_IDS);
//        }
//    }

//    record CreateGameMessage(Type type) implements Request {
//        public CreateGameMessage() {
//            this(Type.CREATE_GAME);
//        }
//    }

    record GuessMessage(Type type, NewGuess guess) implements Request {
        public GuessMessage(NewGuess guess) {
            this(Type.GUESS, guess);
        }
    }

    record JoinGameMessage(Type type,
                           String userId,
                           String gameId) implements Request {
        public JoinGameMessage(String userId, String gameId) {
            this(Type.JOIN_GAME, userId, gameId);
        }
    }
}
