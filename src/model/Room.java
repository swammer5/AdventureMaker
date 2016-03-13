package model;

import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * <b>Room</b> represents a a mutable room in a text adventure game. Rooms can
 * register user commands associated with Scripts that run Commands.
 * 
 * @author Sean Wammer
 */
public class Room {

	String name;
	String shortDesc;
	String longDesc;
	Map<String, Script> acceptedCommands;
	
	public Room() {
		throw new NotImplementedException();
	}
	
	/**
	 * Returns true if the input is a recognized command and the associated
	 * script was run successfully. Returns false otherwise.
	 * 
	 * @param input the command to attempt to run
	 * @return true iff the script associated with the given input is run
	 *              successfully
	 */
	public boolean execute(String input) {
		if (!hasCommand(input)) {
			return false;
		} else {
			// this is where we run the script and return whether the script
			// was successful or not.
			throw new NotImplementedException();
		}
	}
	
	/**
	 * Adds the given Script to this room associated with the given user input
	 * command. Overwrites any previous script that may have been associated
	 * with the given command. Given input is not case-sensitive. Room will
	 * not remember the case of the command.
	 * 
	 * Returns true if a script was overwritten when registering this new user
	 * input.
	 * 
	 * @param input the user command to register that triggers the given script
	 * @param script the script to add to this room
	 */
	public void addScript(String input, Script script) {
		input = fix(input);
	}
	
	/**
	 * Returns the Script associated with the given input for this room or null
	 * if there is no such Script.
	 * 
	 * @param input the user command to retrieve the Script of
	 * @return the Script associated with the given input or null if there is
	 * no script
	 */
	public Script getScript(String input) {
		return acceptedCommands.get(input);
	}
	
	/**
	 * Returns true if and only if this room has the given input registered to
	 * a script. Not case sensitive.
	 * 
	 * @param input the user command to check
	 * @return true iff this room has the given input registered to a script
	 */
	public boolean hasCommand(String input) {
		input = fix(input);
		return acceptedCommands.containsKey(input);
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
