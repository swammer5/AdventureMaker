package model;

import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Room {

	String name;
	String shortDesc;
	String longDesc;
	Map<String, Script> acceptedCommands;
	
	public Room() {
		throw new NotImplementedException();
	}
	
	public boolean execute(String input) {
		throw new NotImplementedException();
	}
	
	/**
	 * Returns true if the script was added successfully
	 * 
	 * @return
	 */
	public void addScript() {
		
	}
}
