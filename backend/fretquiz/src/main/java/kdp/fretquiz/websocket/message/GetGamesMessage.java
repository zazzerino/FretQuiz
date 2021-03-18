package kdp.fretquiz.websocket.message;

public record GetGamesMessage(Type type) implements Message {

    public GetGamesMessage() {
        this(Type.GET_GAMES);
    }
}
