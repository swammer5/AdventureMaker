package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Offers an interface with the game state.
 * 
 * @author Sean Wammer
 */
public class GameModel {
	
	private GameState gameState;
	
	/**
	 * Constructs a new GameModel
	 */
	public GameModel() {
		// TODO: instantiate fields
		
		// when we want to load from a file, we won't make a new GameState,
		// we will call GameParser.loadGameState(filepath) to return a populated
		// GameState
		GameState gameState = new GameState();
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
	
	public boolean go(String roomName) {
		throw new NotImplementedException();
	}
	
	public void look() {
		throw new NotImplementedException();
	}
}