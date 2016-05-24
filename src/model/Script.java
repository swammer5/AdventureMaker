package model;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * <b>Script</b> represents a executable list of commands. Scripts run commands
 * on the game state to modify it and can return output.
 * 
 * @author swammer
 */
public class Script {

    /*
     * General flat file script format:
     *  <accepted input>
     *      <command 1>
     *      <command 2>
     *      ...
     * 
     * General flat file command format:
     *  <command type>, <appropriate # of command arguments>
     * 
     * For example,
     *  "press button"
     *      "PRINT", "EIGHT"
     *      "ADD_HEALTH", "-8"
     *      "PRINT", "[You lost 8 health]"
     * 
     * 
     * Add script and remove script will be interesting because they introduce recursive scripts.
     * 
     * General form:
     * 'ADD_SCRIPT, <script to add>'
     * 
     * The script you can add follows the same format of any script
     *  'ADD_SCRIPT, <accepted input>,
     *      <any # of commands>'
     * 
     * This expands to
     *  'ADD_SCRIPT, <accepted input>, 
     *      <command type>, <appropriate # of command argument>'
     * for some number of command types.
     * 
     * Example script in some given room,
     *  "open chest"
     *      "REMOVE_SCRIPT", "open chest"
     *      "PRINT", "You open the chest. There is an axe inside."
     *      "ADD_SCRIPT", "get axe",
     *          "REMOVE_ITEM", "axe"
     *          "GIVE_ITEM", "axe"
     *          "REMOVE_SCRIPT", "get axe"
     *          "ADD_SCRIPT", "chop tree"
     *              "GIVE_ITEM", "wood"
     *              "PRINT", "you got wood!"
     *              "LOOK"
     *          "ADD_SCRIPT", "backyard", "chop tree"
     *              "GIVE_ITEM", "wood"
     *              "PRINT", "you got wood!"
     *              "LOOK"
     *      "ADD_ITEM", "axe"
     * 
     * 'ADD_SCRIPT_TO just has <short room name> at the beginning as the first argument.
     */
    
    private List<Command> commands;
    // TODO add other fields
    
    public Script() {
        this.commands = new ArrayList<>();
        // TODO add other fields
    }
    
    /**
     * Adds the given command to the end of this script.
     * 
     * @param command the Command to add to this script
     */
    public void add(Command command) {
        commands.add(command);
    }

    /**
     * Executes the list of Command in this Script recursively in order.
     * 
     * @return the output that these commands produce to be printed by main.
     */
    public String execute() {
        String ret = "";
        for (Command command : commands) {
            String result = command.execute();
            // TODO decide behavior for bad Command case (do we want good debugging, or robust games that don't crash?)
            // perhaps we can just have a debug flag in..GameModel?
            if (result == null) {
                // COMMAND ERROR!! RAISE EXCEPTION HERE? WE DON'T WANT TO ADD
                // 'null' TO THE RETURN STRING
            }

            ret += result;
        }
        return ret;
    }
}
