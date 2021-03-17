package kdp.fretquiz.apigame;

import java.util.Map;

public record Player(String userId,
                     String gameId) {

    public Map<String, Object> toMap() {
        return Map.of("id", userId);
    }
}
