package kdp.fretquiz.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kdp.fretquiz.Util;

import java.util.ArrayList;
import java.util.List;

public final class User
{
    public static final String DEFAULT_NAME = "anon";
    public final String id;

    @JsonIgnore
    public final String sessionId;

    @JsonIgnore
    private final List<String> gameIds = new ArrayList<>();

    private String name;

    public User(String id,
                String sessionId,
                String name)
    {
        this.id = id;
        this.sessionId = sessionId;
        this.name = name;
    }

    public User(String name, String sessionId)
    {
        this(Util.randomId(), sessionId, name);
    }

    public User(String sessionId)
    {
        this(Util.randomId(), sessionId, DEFAULT_NAME);
    }

    public User joinGame(String gameId)
    {
        gameIds.add(gameId);
        return this;
    }

    public User leaveGame(String gameId)
    {
        gameIds.remove(gameId);
        return this;
    }

    public User leaveAllGames()
    {
        gameIds.clear();
        return this;
    }

    @JsonProperty("name")
    public String name()
    {
        return name;
    }

    public User setName(String name)
    {
        this.name = name;
        return this;
    }

    public List<String> gameIds()
    {
        return gameIds;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id='" + id + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", name='" + name + '\'' +
                ", gameIds=" + gameIds +
                '}';
    }
}
