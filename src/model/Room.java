package model;

import java.util.List;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * <b>Room</b> represents a a mutable room in a text adventure game. Rooms can
 * register user commands associated with Scripts that run Commands.
 * 
 * @author Sean Wammer
 */
public class Room {

    /** Acts as the identifier for this room */
    private String shortName;

    private String name;
    private String shortDesc;
    private String longDesc;
    private List<String> items;
    private Map<String, Script> acceptedCommands;

    public Room() {
        throw new NotImplementedException();
    }

    /**
     * Returns the short name of this room. This acts as the room ID and must be
     * unique.
     * 
     * @return the short name of this room
     */
    public String getShortName() {
        return shortName;
    }
    
    // TODO add other getter methods
    
    // TODO add setter methods too
    
    // TODO reorder methods so they make sense

    /**
     * Runs the Script associated with the given command. Returns the output
     * that the commands produce to be printed by main, the empty string if
     * there is no output. Returns null if the command was not recognized in
     * this room.
     * 
     * @param input the command to attempt to run
     * @return the output that these commands produce to be printed by main or
     *         an empty String if there is no output, or null if the command is
     *         not recognized in this Room.
     */
    public String execute(String input) {
        input = fix(input);
        if (!hasCommand(input)) {
            return null;
        } else {
            return acceptedCommands.get(input).execute();
        }
    }

    /**
     * Adds the given Script to this room associated with the given user input
     * command. Overwrites any previous script that may have been associated
     * with the given command. Given input is not case-sensitive. Room will not
     * remember the case of the command.
     * 
     * @param input the user command to register that triggers the given script
     * @param script the script to add to this room
     */
    public void addScript(String input, Script script) {
        input = fix(input);
        // TODO implement
        throw new NotImplementedException();
    }

    /**
     * Removes the Script associated with the given user input from this room.
     * 
     * @param input the user command associated with the Script to remove
     */
    public void removeScript(String input) {
        acceptedCommands.remove(fix(input));
    }

    /**
     * Returns the Script associated with the given input for this room or null
     * if there is no such Script.
     * 
     * @param input the user command to retrieve the Script of
     * @return the Script associated with the given input or null if there is no
     *         script associated with this input.
     */
    public Script getScript(String input) {
        return acceptedCommands.get(input);
    }

    /**
     * Returns true if and only if this room has the given input registered to a
     * script. Not case sensitive.
     * 
     * @param input the user command to check
     * @return true iff this room has the given input registered to a script
     */
    public boolean hasCommand(String input) {
        return acceptedCommands.containsKey(fix(input));
    }

    /**
     * Standardizes the input so that we are more likely to recognize commands.
     * 
     * Implementation note: We currently flatten to lower case and trim
     * whitespace.
     * 
     * @param input the input String to standardize
     * @return the standardized input string
     */
    private String fix(String input) {
        return input.toLowerCase().trim();
    }
}
