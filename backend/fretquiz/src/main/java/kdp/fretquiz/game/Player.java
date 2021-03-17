package kdp.fretquiz.game;

import kdp.fretquiz.Util;

import java.util.Map;

public record Player(String id,
                     String userId,
                     String gameId) {

    public Player(String userId, String gameId) {
        this(Util.randomId(), userId, gameId);
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "id", id,
                "userId", userId,
                "gameId", gameId
        );
    }
}
