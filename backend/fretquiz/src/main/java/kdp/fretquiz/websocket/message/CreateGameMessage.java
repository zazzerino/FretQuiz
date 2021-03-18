package kdp.fretquiz.websocket.message;

public record CreateGameMessage(Type type) implements Message {

    public CreateGameMessage() {
        this(Type.CREATE_GAME);
    }

    public CreateGameMessage(Type type) {
        if (type != Type.CREATE_GAME) {
            throw new IllegalArgumentException();
        }

        this.type = type;
    }
}
