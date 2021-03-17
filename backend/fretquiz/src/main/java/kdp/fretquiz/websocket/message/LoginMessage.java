package kdp.fretquiz.websocket.message;

public class LoginMessage implements Message {
    private final Type type = Type.LOGIN;
    private String name;

    public LoginMessage() {}

    public LoginMessage(String name) {
        this.name = name;
    }

    @Override
    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
