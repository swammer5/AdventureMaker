package test;

import static org.junit.Assert.*;
import model.Command;
import model.CommandType;
import model.GameModel;
import model.Player;

import org.junit.Test;
import org.junit.BeforeClass;

public class CommandTest {

    private static GameModel model;

    @BeforeClass
    public static void setup() {
        // this should be mocked but it's ok. It'll just depend on GameModel.
        model = GameModel.newGame();
    }

    @Test
    public void testPrint() {
        String expected = "Print works!";

        String[] args = { expected };
        Command com = new Command(model, CommandType.PRINT, args);

        String actual = com.execute();

        assertEquals("PRINT should return the given String", expected, actual);
    }

    @Test
    public void testAddHealth() {
        // need to check that gameModel says player's health is changed by the
        // expected ammount.

        String[] args = { "5" };
        Command com = new Command(model, CommandType.ADD_HEALTH, args);

        Player player = model.getGameState().getPlayer();
        int prevHealth = player.curHealth();
        int prevMaxHealth = player.maxHealth();

        // test output
        String actual = com.execute();
        String expected = "";
        assertEquals("ADD_HEALTH should return empty string", expected, actual);

        // first sanity check that player and player2 both refer to the same
        // object. This isn't where the test should go, but we'll test this in
        // gameState too.
        Player player2 = model.getGameState().getPlayer();
        assertEquals("gameState should return reference to player", player,
                player2);
        
        // test effects of command
        int newHealth = player2.curHealth();
        int newMaxHealth = player2.maxHealth();
        int expected2 = prevHealth + 5;
        int actual2 = newHealth;
        assertEquals("health should be updated by ADD_HEALTH", expected2, actual2);
        assertEquals("max health should stay the same", prevMaxHealth, newMaxHealth);
    }
    // TODO: fill in more tests when Command and CommandType is fleshed out
}
