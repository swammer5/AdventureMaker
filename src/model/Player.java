package model;

/**
 * <b>Player</b> is a data encapsulation class that represents the player in the
 * game.
 * 
 * @author swammer
 */
public class Player {

	String name;
	// TODO: add other fields
	
	public Player(String name) {
		this.name = name;
		// TODO: instantiate other fields
	}
	
	/**
	 * Returns the name of this
	 * 
	 * @return the name of this
	 */
	public String name() {
		return name;
	}
}
