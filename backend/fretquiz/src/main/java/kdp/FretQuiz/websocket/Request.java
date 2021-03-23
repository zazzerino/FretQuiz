package kdp.FretQuiz.websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kdp.FretQuiz.game.Guess;

/**
 * A Request is a message originating from the client.
 * Each Request has a Request.Type and, optionally, some associated data.
 */
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

    record CreateGame(Type type) implements Request {
        public CreateGame() {
            this(Type.CREATE_GAME);
        }
    }

    record JoinGameMessage(Type type,
                           String userId,
                           String gameId) implements Request {
        public JoinGameMessage(String userId, String gameId) {
            this(Type.JOIN_GAME, userId, gameId);
        }
    }

    record PlayerGuessed(Type type, Guess.NewGuess newGuess) implements Request {
        public PlayerGuessed(Guess.NewGuess newGuess) {
            this(Type.GUESS, newGuess);
        }
    }
}
