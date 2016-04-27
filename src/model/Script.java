package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * <b>Script</b> represents a executable list of commands. Scripts run commands
 * on the game state to modify it and can return output.
 * 
 * @author swammer
 */
public class Script {

    public Script() {
        throw new NotImplementedException();
    }

    /**
     * Executes the list of Command in this Script recursively in order. Returns
     * any text to be printed or null if there is no output.
     * 
     * @return the output that these commands produce to be printed by main or
     *         an empty String if there is no output.
     */
    public String execute() {
        String ret = "";
        throw new NotImplementedException();
    }
}
