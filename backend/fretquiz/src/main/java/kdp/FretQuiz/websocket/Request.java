package kdp.FretQuiz.websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kdp.FretQuiz.game.Guess;

/**
 * A Request is a message originating from the client (the browser).
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
        START_GAME,
        PLAYER_GUESS,
        NEXT_ROUND;
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

    record Logout(Type type) implements Request {
        public Logout() {
            this(Type.LOGOUT);
        }
    }

    record GetGameIds(Type type) implements Request {
        public GetGameIds() {
            this(Type.GET_GAME_IDS);
        }
    }

    record CreateGame(Type type) implements Request {
        public CreateGame() {
            this(Type.CREATE_GAME);
        }
    }

    record JoinGame(Type type,
                    String gameId,
                    String userId) implements Request {
        public JoinGame(String gameId, String userId) {
            this(Type.JOIN_GAME, gameId, userId);
        }
    }

    record StartGame(Type type, String gameId) {
        public StartGame(String gameId) {
            this(Type.START_GAME, gameId);
        }
    }

    record PlayerGuess(Type type, Guess.ClientGuess clientGuess) implements Request {
        public PlayerGuess(Guess.ClientGuess clientGuess) {
            this(Type.PLAYER_GUESS, clientGuess);
        }
    }

    record NextRound(Type type, String gameId, String userId) implements Request {
        public NextRound(String gameId, String userId) {
            this(Type.NEXT_ROUND, gameId, userId);
        }
    }
}
