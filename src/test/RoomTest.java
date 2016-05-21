package test;

import model.Command;
import model.CommandType;
import model.GameModel;
import model.Room;
import model.Script;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;

public class RoomTest {

    private static Script axeScript;
    
    @BeforeClass
    public static void setup() {
        GameModel model = null;
        // GameModel model = GameModel.newGame()
        
        /*
         * "ADD_SCRIPT", "get axe",
         *         "REMOVE_ITEM", "axe"
         *         "GIVE_ITEM", "axe"
         *         "REMOVE_SCRIPT", "get axe"
         */
        
        axeScript = new Script();
        
        String[] args = {"axe"};
        axeScript.add(new Command(model, CommandType.REMOVE_ITEM, args));
        args[0] = "axe";
        axeScript.add(new Command(model, CommandType.GIVE_ITEM, args));
        args[0] = "get axe";
        axeScript.add(new Command(model, CommandType.REMOVE_SCRIPT, args));
        args[0] = "You got an axe!";
        axeScript.add(new Command(model, CommandType.PRINT, args));
    }
    
    @Test
    public void testAddScript() {
        Room room = new Room("Backyard", "Modest Backyard", "A small backyard",
                "It's wonderful here. There is a tire swing and a chest you can open!");

        // making sure script is instantiated
        assertTrue(axeScript != null);

        room.addScript("Get Axe", axeScript);
        
        // also making sure the trim and case are handled
        assertTrue(room.acceptsInput(" get aXe  "));
    }

    @Test
    public void testRemoveScript() {
        Room room = new Room("Backyard", "Modest Backyard", "A small backyard",
                "It's wonderful here. There is a tire swing and a chest you can open!");

        room.addScript("Get Axe", axeScript);
        assertTrue(room.acceptsInput("get axe"));
        room.removeScript("get AXE");
        assertTrue(!room.acceptsInput("Get Axe"));
    }

    @Test
    public void testAcceptsInput() {
        Room room = new Room("Backyard", "Modest Backyard", "A small backyard",
                "It's wonderful here. There is a tire swing and a chest you can open!");

        // bad inputs, expect false
        assertTrue(!room.acceptsInput("not accepted input"));
        assertTrue(!room.acceptsInput(""));
        
        // add an accepted input and see if the status flips appropriately
        assertTrue(!room.acceptsInput("get axe"));
        room.addScript("Get Axe", axeScript);
        assertTrue(room.acceptsInput("get axe"));
        room.removeScript("get AXE");
        assertTrue(!room.acceptsInput("Get Axe"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testExecuteBadCommand() {
        Room room = new Room("Backyard", "Modest Backyard", "A small backyard",
                "It's wonderful here. There is a tire swing and a chest you can open!");
        room.addScript("get axe", axeScript);
        
        String expected = null;
        String actual = room.execute("bad input ha ha!");
        assertEquals("unrecognized (not accepted) input should return null", expected, actual);
        
        actual = room.execute(" baD inPUt ha ha!    ");
        assertEquals("unrecognized (not accepted) input should return null", expected, actual);
        
        // should throw expected exception here
        actual = room.execute(null);
    }

    @Test
    public void testExecute() {
        Room room = new Room("Backyard", "Modest Backyard", "A small backyard",
                "It's wonderful here. There is a tire swing and a chest you can open!");
        room.addScript("get axe", axeScript);
        
        String expected = "You got an axe!";
        String actual = room.execute("get axe");
        
        assertEquals("Script associated with command should be executed and return text should be returned", expected, actual);
    }
}
