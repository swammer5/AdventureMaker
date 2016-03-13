package model;

/**
 * <b>Command</b> is an abstract representation of a request to the game state.
 * 
 * @author Sean Wammer
 */
public class Command {

	/* an alias to the game state that it will make a call on */
	GameState gameState;
	CommandType commandType;
	String[] args;
	
	/**
	 * Constructs a new Command that
	 * @param gameState
	 */
	public Command(GameState gameState, CommandType commandType, String[] args) {
		this.gameState = gameState;
		this.commandType = commandType;
		this.args = new String[args.length];
		System.arraycopy(args, 0, this.args, 0, args.length);
	}
	
}
