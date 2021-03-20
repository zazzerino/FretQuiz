package kdp.fretquiz.websocket.message;

public record JoinGameMessage(Type type,
                              String userId,
                              String gameId) implements Message {

    public JoinGameMessage(String userId, String gameId) {
        this(Type.JOIN_GAME, userId, gameId);
    }
}
