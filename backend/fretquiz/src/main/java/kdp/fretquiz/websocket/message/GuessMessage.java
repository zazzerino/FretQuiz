package kdp.fretquiz.websocket.message;

import kdp.fretquiz.game.NewGuess;

public record GuessMessage(Message.Type type,
                           NewGuess guess) {

    public GuessMessage(NewGuess guess) {
        this(Message.Type.GUESS, guess);
    }
}
