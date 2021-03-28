package kdp.FretQuiz.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kdp.FretQuiz.theory.Fretboard;

/**
 * The game options. Can be changed by the client before each Round.
 */
public record Opts(int roundLength,
                   @JsonIgnore
                   Fretboard fretboard) {

    public static final int DEFAULT_ROUND_LENGTH = 20;

    public static final Opts DEFAULT = new Opts(DEFAULT_ROUND_LENGTH, Fretboard.DEFAULT);
}
