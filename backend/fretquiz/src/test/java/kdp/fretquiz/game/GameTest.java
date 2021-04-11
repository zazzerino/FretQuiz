package kdp.fretquiz.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private final Player player1 = new Player("id1", "Alice");
    private final Player player2 = new Player("id2", "Bob");

    @Test
    void addPlayer() {
        var game = new Game();
        assertEquals(0, game.playerCount());

        game.addPlayer(player1);
        assertEquals(1, game.playerCount());
    }

    @Test
    void removePlayer() {
        var game = new Game()
                .addPlayer(player1);

        assertEquals(1, game.playerCount());
        game.removePlayer(player1.id());
        assertEquals(0, game.playerCount());

        game.addPlayer(player1);
        game.addPlayer(player2);
        assertEquals(2, game.playerCount());

        game.removePlayer(player1.id());
        assertEquals(1, game.playerCount());

        game.removePlayer(player2.id());
        assertEquals(0, game.playerCount());
    }

    @Test
    void assignHost() {
    }

    @Test
    void start() {
    }

    @Test
    void info() {
    }

    @Test
    void isOver() {
    }

    @Test
    void currentRound() {
    }

    @Test
    void nextRound() {
    }

    @Test
    void guess() {
    }

    @Test
    void playerGuesses() {
    }

    @Test
    void playerScore() {
    }

    @Test
    void roundsPlayed() {
    }

    @Test
    void roundsLeft() {
    }

    @Test
    void toggleString() {
    }

    @Test
    void toggleAccidental() {
    }

    @Test
    void setPlayerName() {
    }
}