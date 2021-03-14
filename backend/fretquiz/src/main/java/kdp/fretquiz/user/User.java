package kdp.fretquiz.user;

import java.util.Map;
import java.util.UUID;

public record User(String id,
                   String name) {

    public User(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public Map<String, String> toMap() {
        return Map.of(
                "id", id,
                "name", name
        );
    }
}
