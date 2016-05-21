package test;

import static org.junit.Assert.*;
import model.Command;
import model.CommandType;
import model.GameModel;

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
        fail("Not yet implement");
    }

    // TODO: fill in more tests when Command and CommandType is fleshed out
}
