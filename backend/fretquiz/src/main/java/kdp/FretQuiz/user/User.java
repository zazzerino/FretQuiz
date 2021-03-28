package kdp.FretQuiz.user;

import kdp.FretQuiz.Util;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class User {

    public final String id;
    private String name;

    private final @NotNull List<String> gameIds;

    public static final String DEFAULT_NAME = "anon";

    public User(String id,
                String name,
                List<String> gameIds) {
        this.id = id;
        this.name = name;
        this.gameIds = gameIds;
    }

    public User() {
        this(Util.randomId(), DEFAULT_NAME, new ArrayList<>());
    }

    public User(String name) {
        this(Util.randomId(), name, new ArrayList<>());
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
        gameIds.forEach(this::leaveGame);
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<String> gameIds() {
        return gameIds;
    }
}
