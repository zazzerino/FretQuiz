package kdp.fretquiz.websocket.message;

public interface Message {
    Type getType();

    enum Type {
        LOGIN("LOGIN"),
        LOGOUT("LOGOUT");

        Type(String type) {}
    }
}
