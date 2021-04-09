package kdp.fretquiz.game;

public record Player(String id, String name) {

    public Player withName(String name) {
        return new Player(id, name);
    }
}
