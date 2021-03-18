package kdp.fretquiz.websocket.message;

public interface Message {
    Type getType();

    enum Type {
        LOGIN("LOGIN"),
        LOGOUT("LOGOUT"),
        CREATE_GAME("CREATE_GAME");

        Type(String type) {}
    }
}
