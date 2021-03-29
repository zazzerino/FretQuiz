package kdp.FretQuiz.user;

import kdp.FretQuiz.Util;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class User {

    public final String id;
    public final String sessionId;
    private String name;
    private final @NotNull List<String> gameIds = new ArrayList<>();

    public static final String DEFAULT_NAME = "anon";

    public User(String id,
                String sessionId,
                String name) {
        this.id = id;
        this.sessionId = sessionId;
        this.name = name;
    }

    public User(String name, String sessionId) {
        this(Util.randomId(), sessionId, name);
    }

    public User(String sessionId) {
        this(Util.randomId(), sessionId, DEFAULT_NAME);
    }

    public User joinGame(String gameId) {
        gameIds.add(gameId);
        return this;
    }

    public User leaveGame(String gameId) {
        gameIds.remove(gameId);
        return this;
    }

    public User leaveAllGames() {
        gameIds.clear();
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getGameIds() {
        return gameIds;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", name='" + name + '\'' +
                ", gameIds=" + gameIds +
                '}';
    }
}
