package kdp.fretquiz.websocket.message;

public class CreateGameMessage implements Message {
    private final Type type = Type.CREATE_GAME;

    public CreateGameMessage() {}

    @Override
    public Type getType() {
        return type;
    }
}
