package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Offers an interface with the game state.
 * 
 * @author swammer
 */
public class GameModel {
	
	/**
	 * Constructs a new GameModel
	 */
	public GameModel() {
		// TODO: instantiate fields
	}
	
	/**
	 * Returns true if and only if the command was recognized by the room.
	 * Runs the script associated with the command in this room.
	 * 
	 * @param command the command to attempt to run in this room
	 * @return true iff the command was recognized and run
	 */
	public boolean execute(String command) {
		throw new NotImplementedException();
	}
}