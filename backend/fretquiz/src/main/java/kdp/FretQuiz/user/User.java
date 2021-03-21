package kdp.FretQuiz.user;

import kdp.FretQuiz.Util;

import java.util.Map;

public record User(String id,
                   String name,
                   String sessionId) {

    public static final String DEFAULT_NAME = "anon";

    public User(String sessionId) {
        this(Util.randomId(), DEFAULT_NAME, sessionId);
    }

    public User(String name, String sessionId) {
        this(Util.randomId(), name, sessionId);
    }

    public User withName(String name) {
        return Util.copyRecord(this, Map.of("name", name));
    }
}