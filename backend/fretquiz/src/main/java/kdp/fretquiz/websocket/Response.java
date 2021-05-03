package kdp.fretquiz.websocket;

import kdp.fretquiz.chat.ChatMessage;
import kdp.fretquiz.game.Game;
import kdp.fretquiz.game.Guess;
import kdp.fretquiz.user.User;

import java.util.List;

/**
 * A Response is a text sent from the server to the client.
 * Each Response has a Response.Type and, optionally, some associated data.
 */
public interface Response
{
    Type type();

    enum Type
    {
        FLASH,
        LOGGED_IN,
        LOGGED_OUT,
        GAME_IDS,
        GAME_INFOS,
        GAME_CREATED,
        GAME_JOINED,
        GAME_STARTED,
        GUESS_RESULT,
        PLAYER_JOINED,
        GAME_UPDATED,
        ROUND_STARTED,
        GAME_OVER,
        GAME_COUNTDOWN,
        UPDATE_CHAT
    }

    record Flash(Type type, String message) implements Response
    {
        public Flash(String message)
        {
            this(Type.FLASH, message);
        }
    }

    record LoggedIn(Type type, User user) implements Response
    {
        public LoggedIn(User user)
        {
            this(Type.LOGGED_IN, user);
        }
    }

    record LoggedOut(Type type) implements Response
    {
        public LoggedOut()
        {
            this(Type.LOGGED_OUT);
        }
    }

    record GameCreated(Type type, Game game) implements Response
    {
        public GameCreated(Game game)
        {
            this(Type.GAME_CREATED, game);
        }
    }

    record GameIds(Type type, List<String> gameIds) implements Response
    {
        public GameIds(List<String> gameIds)
        {
            this(Type.GAME_IDS, gameIds);
        }
    }

    record GameInfos(Type type, List<Game.Info> gameInfos) implements Response
    {
        public GameInfos(List<Game.Info> gameInfos)
        {
            this(Type.GAME_INFOS, gameInfos);
        }
    }

    record GameJoined(Type type, Game game) implements Response
    {
        public GameJoined(Game game)
        {
            this(Type.GAME_JOINED, game);
        }
    }

    record GameStarted(Type type, Game game) implements Response
    {
        public GameStarted(Game game)
        {
            this(Type.GAME_STARTED, game);
        }
    }

    record GuessResult(Type type, Guess guess, Game game) implements Response
    {
        public GuessResult(Guess guess, Game game)
        {
            this(Type.GUESS_RESULT, guess, game);
        }
    }

    record PlayerJoined(Type type, String message) implements Response
    {
        public PlayerJoined(String message)
        {
            this(Type.PLAYER_JOINED, message);
        }
    }

    record GameUpdated(Type type, Game game) implements Response
    {
        public GameUpdated(Game game)
        {
            this(Type.GAME_UPDATED, game);
        }
    }

    record RoundStarted(Type type, Game game) implements Response
    {
        public RoundStarted(Game game)
        {
            this(Type.ROUND_STARTED, game);
        }
    }

    record GameOver(Type type, Game game) implements Response
    {
        public GameOver(Game game)
        {
            this(Type.GAME_OVER, game);
        }
    }

    record GameCountdown(Type type, int secondsLeft) implements Response
    {
        public GameCountdown(int secondsLeft)
        {
            this(Type.GAME_COUNTDOWN, secondsLeft);
        }
    }

    record UpdateChat(Type type, List<ChatMessage> messages) implements Response
    {
        public UpdateChat(List<ChatMessage> messages)
        {
            this(Type.UPDATE_CHAT, messages);
        }
    }
}
