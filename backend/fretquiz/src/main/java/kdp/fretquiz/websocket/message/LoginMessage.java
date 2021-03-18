package kdp.fretquiz.websocket.message;

public record LoginMessage(Type type,
                           String name) implements Message {

    public LoginMessage(String name) {
        this(Type.LOGIN, name);
    }

    public LoginMessage(Type type, String name) {
        if (type != Type.LOGIN) {
            throw new IllegalArgumentException();
        }

        this.type = type;
        this.name = name;
    }
}
