package kdp.FretQuiz.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kdp.FretQuiz.Util;

import java.util.ArrayList;
import java.util.List;

public final class User {

    public final String id;
    private String name;

    @JsonIgnore
    public final String sessionId;

    @JsonIgnore
    private final List<String> gameIds = new ArrayList<>();

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

    public String getId() {
        return id;
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
