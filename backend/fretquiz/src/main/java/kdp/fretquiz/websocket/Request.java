package kdp.fretquiz.websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kdp.fretquiz.game.Guess;
import kdp.fretquiz.theory.Accidental;

/**
 * A Request is a message originating from the client (the browser).
 * Each Request has a Request.Type and, optionally, some associated data.
 */
public interface Request
{
    Type type();

    enum Type
    {
        LOGIN,
        LOGOUT,
        GET_GAME_IDS,
        CREATE_GAME,
        JOIN_GAME,
        START_GAME,
        PLAYER_GUESS,
        NEXT_ROUND,
        TOGGLE_STRING,
        TOGGLE_ACCIDENTAL,
        SET_ROUND_COUNT,
        START_COUNTDOWN,
        START_ROUND_COUNTDOWN
    }

    /**
     * Request.Default is an record that only contains the incoming Request's type.
     * Each incoming request is cast to Request.Default first to determine the Request type.
     * Then it is then cast to the proper Request type so it can be used further.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    record Default(Type type) implements Request
    {
    }

    record Login(Type type, String name) implements Request
    {
        public Login(String name)
        {
            this(Type.LOGIN, name);
        }
    }

    record Logout(Type type) implements Request
    {
        public Logout()
        {
            this(Type.LOGOUT);
        }
    }

    record GetGameIds(Type type) implements Request
    {
        public GetGameIds()
        {
            this(Type.GET_GAME_IDS);
        }
    }

    record CreateGame(Type type) implements Request
    {
        public CreateGame()
        {
            this(Type.CREATE_GAME);
        }
    }

    record JoinGame(Type type,
                    String gameId,
                    String userId) implements Request
    {
        public JoinGame(String gameId, String userId)
        {
            this(Type.JOIN_GAME, gameId, userId);
        }
    }

    record StartGame(Type type, String gameId)
    {
        public StartGame(String gameId)
        {
            this(Type.START_GAME, gameId);
        }
    }

    record PlayerGuess(Type type, Guess.ClientGuess clientGuess) implements Request
    {
        public PlayerGuess(Guess.ClientGuess clientGuess)
        {
            this(Type.PLAYER_GUESS, clientGuess);
        }
    }

    record NextRound(Type type, String gameId, String userId) implements Request
    {
        public NextRound(String gameId, String userId)
        {
            this(Type.NEXT_ROUND, gameId, userId);
        }
    }

    record ToggleString(Type type, String gameId, int string) implements Request
    {
        public ToggleString(String gameId, int string)
        {
            this(Type.TOGGLE_STRING, gameId, string);
        }
    }

    record ToggleAccidental(Type type, String gameId, Accidental accidental) implements Request
    {
        public ToggleAccidental(String gameId, Accidental accidental)
        {
            this(Type.TOGGLE_ACCIDENTAL, gameId, accidental);
        }
    }

    record SetRoundCount(Type type, String gameId, int roundCount) implements Request
    {
        public SetRoundCount(String gameId, int roundCount)
        {
            this(Type.SET_ROUND_COUNT, gameId, roundCount);
        }
    }

    record StartCountdown(Type type, String gameId) implements Request
    {
        public StartCountdown(String gameId)
        {
            this(Type.START_COUNTDOWN, gameId);
        }
    }

    record StartRoundCountdown(Type type, String gameId) implements Request
    {
        public StartRoundCountdown(String gameId)
        {
            this(Type.START_ROUND_COUNTDOWN, gameId);
        }
    }
}
