package kdp.fretquiz.websocket.message;

public interface Message {
    Type type();

    enum Type {
        LOGIN("LOGIN"),
        LOGOUT("LOGOUT"),
        GET_ALL_GAMES("GET_ALL_GAMES"),
        CREATE_GAME("CREATE_GAME");

        Type(String type) {}
    }
}
