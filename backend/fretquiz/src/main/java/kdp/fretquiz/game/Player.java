package kdp.fretquiz.game;

public record Player(String id,
                     boolean isHost) {

    public Player(String id) {
        this(id, false);
    }

    public Player makeHost() {
        return new Player(id, true);
    }

    public Player removeHost() {
        return new Player(id, false);
    }
}
