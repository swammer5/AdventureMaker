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
            ret += command.execute();
        }
        return ret;
    }
}
