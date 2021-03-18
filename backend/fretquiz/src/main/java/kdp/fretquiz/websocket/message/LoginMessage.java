package kdp.fretquiz.websocket.message;

public record LoginMessage(Type type,
                           String name) implements Message {

    public LoginMessage(String name) {
        this(Type.LOGIN, name);
    }
}
