package kdp.fretquiz.websocket.message;

public interface Message {
    Type type();

    enum Type {
        LOGIN("LOGIN"),
        LOGOUT("LOGOUT"),
        GET_GAME_IDS("GET_GAME_IDS"),
        CREATE_GAME("CREATE_GAME"),
        JOIN_GAME("JOIN_GAME"),
        GUESS("GUESS");

        Type(String type) {}
    }
}
