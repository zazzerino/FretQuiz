package kdp.fretquiz.game;

import kdp.fretquiz.Util;

import java.util.Map;

public record Player(String id,
                     String userId) {

    public Player(String userId) {
        this(Util.randomId(), userId);
    }
}
