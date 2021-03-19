package kdp.fretquiz.websocket.message;

public interface Message {
    Type type();

    enum Type {
        LOGIN("LOGIN"),
        LOGOUT("LOGOUT"),
        GET_GAMES("GET_GAMES"),
        CREATE_GAME("CREATE_GAME"),
        GUESS("GUESS");

        Type(String type) {}
    }
}
