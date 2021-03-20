package kdp.fretquiz.websocket.message;

public record GetGameIdsMessage(Type type) implements Message {

    public GetGameIdsMessage() {
        this(Type.GET_GAME_IDS);
    }
}
