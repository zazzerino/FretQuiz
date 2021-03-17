package kdp.fretquiz.game;

import kdp.fretquiz.theory.Fretboard;

import java.util.Map;

public record GameOpts(int roundLength,
                       int secondsRemaining,
                       Fretboard fretboard) {

    public static final int DEFAULT_ROUND_LENGTH = 30;

    public static GameOpts DEFAULT = new GameOpts(
            DEFAULT_ROUND_LENGTH,
            DEFAULT_ROUND_LENGTH,
            Fretboard.DEFAULT
    );

    public Map<String, Object> toMap() {
        return Map.of(
                "roundLength", roundLength,
                "secondsRemaining", secondsRemaining,
                "fretboard", fretboard
        );
    }
}
