package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * <b>Command</b> is an abstract representation of a request to the game state.
 * 
 * @author Sean Wammer
 */
public class Command {

	/* an alias to the game state that it will make a call on */
	GameModel gameModel;
	CommandType commandType;
	String[] args;
	
	/**
	 * Constructs a new Command that
	 * @param gameState
	 */
	public Command(GameModel gameModel, CommandType commandType, String[] args) {
		this.gameModel = gameModel;
		this.commandType = commandType;
		this.args = new String[args.length];
		System.arraycopy(args, 0, this.args, 0, args.length);
	}
	
	/**
	 * Runs this command. Returns output that should be printed.
	 * 
	 * @return output of this to be printed
	 */
	public String execute() {
	    /*
         * if (commandType == CommandType.ADD_HEALTH) {
         *     addHealth(args[0]);
         * } else if ...
         */
	    throw new NotImplementedException();
	}
}
