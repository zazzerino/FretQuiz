package kdp.fretquiz.websocket.message;

public record CreateGameMessage(Type type) implements Message {

    public CreateGameMessage() {
        this(Type.CREATE_GAME);
    }
}
