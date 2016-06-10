package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import model.Command;
import model.CommandType;
import model.GameModel;
import model.Player;
import model.Room;

import org.junit.Test;
import org.junit.BeforeClass;

public class CommandTest {

    private static GameModel model;

    @BeforeClass
    public static void setup() throws IOException {
        // this should be mocked but it's ok. It'll just depend on GameModel.
        model = GameModel.loadGame(1);
        //model = GameModel.newGame();
    }

    @Test
    public void testAddHealth() {
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
        assertEquals("health should be updated by ADD_HEALTH", expected2,
                actual2);
        assertEquals("max health should stay the same", prevMaxHealth,
                newMaxHealth);
    }

    @Test
    public void testAddItem() {
        String item = "Copper Axe of Unit Testing";
        String[] args = { item };
        Command com = new Command(model, CommandType.ADD_ITEM, args);

        Room room = model.getGameState().getCurrentRoom();
        List<String> items = room.getItems();
        assertTrue(!items.contains(item));

        // test output
        String actual = com.execute();
        String expected = "";
        assertEquals("ADD_ITEM should return empty string", expected, actual);

        // first sanity check that room and room2 both refer to the same
        // object. This isn't where the test should go, but we'll test this in
        // gameState too.
        Room room2 = model.getGameState().getCurrentRoom();
        assertEquals("gameState should return reference to Room", room, room2);

        // test effects of command
        assertEquals("ADD_ITEM should add item to the current room", true,
                room2.getItems().contains(item));
    }

    @Test
    public void testAddItemTo() {
        fail("Test not implemented!");
    }

    @Test
    public void testAddScript() {
        // just check if the room accepts the script now, and execute it to make
        // sure it prints hello
        fail("Test not implemented!");
    }

    @Test
    public void testAddScriptTo() {
        fail("Test not implemented!");
    }

    @Test
    public void testGiveItem() {
        fail("Test not implemented!");
    }

    @Test
    public void testTakeItem() {
        fail("Test not implemented!");
    }

    @Test
    public void testGo() {
        fail("Test not implemented!");
    }

    @Test
    public void testLook() {
        fail("Test not implemented!");
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
    public void testRemoveItem() {
        fail("Test not implemented!");
    }

    @Test
    public void testRemoveItemFrom() {
        fail("Test not implemented!");
    }

    @Test
    public void testRemoveScript() {
        fail("Test not implemented!");
    }

    @Test
    public void testRemoveScriptFrom() {
        fail("Test not implemented!");
    }

    @Test
    public void testSetShortDesc() {
        fail("Test not implemented!");
    }

    @Test
    public void testSetShortDescOf() {
        fail("Test not implemented!");
    }

    @Test
    public void testSetDesc() {
        fail("Test not implemented!");
    }

    @Test
    public void testSetDescOf() {
        fail("Test not implemented!");
    }

    @Test
    public void testSetName() {
        fail("Test not implemented!");
    }

    @Test
    public void testSetNameOf() {
        fail("Test not implemented!");
    }
}
