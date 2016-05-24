package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * <b>Command</b> is an abstract representation of a request to the game state.
 * 
 * @author Sean Wammer
 */
public class Command {

    /** An alias to the game state that this may make calls on */
    GameModel gameModel;
    /** The action this command performs */
    CommandType commandType;
    /** Parameters to this command (can be empty if no args) */
    String[] args;

    /**
     * Constructs a new Command.
     * 
     * @param gameState
     */
    public Command(GameModel gameModel, CommandType commandType, String[] args) {
        this.gameModel = gameModel;
        this.commandType = commandType;
        this.args = new String[args.length];
        System.arraycopy(args, 0, this.args, 0, args.length);

        // check to make sure that the number of arguments matches the command
        // type, like print should have 1 arg, add health should have 1 arg and
        // look should have no args
        checkRep();
    }

    /**
     * Runs this command. Returns output that should be printed.
     * 
     * @return output of this to be printed
     */
    public String execute() {
        /*
         * Giant switch statement here We should also check for bad commands
         * here. It should probably throw an exception so that the developer
         * knows there is a bad command.
         * 
         * if (commandType == CommandType.ADD_HEALTH) { addHealth(args[0]); }
         * else if ...
         */
        switch (commandType) {
        case LOOK:
            // look in the room you are in
            return gameModel.look();
        case GO:
            // move the player to the given room if it is connected (does not
            // return string)
            
            break;
        //case TELEPORT:
            // move player regardless of location (does not return string)
            // shortName = args[0]
            // gameModel.getGameState().teleport(shortName)
            // return "";
        case ADD_HEALTH:
            // int to add to player health
            assert (args.length == 1);
            break;
        case GIVE_ITEM:
            // give item to player inventory
            assert (args.length == 1);
            break;
        case TAKE_ITEM:
            // remove item from player inventory
            assert (args.length == 1);
            break;
        case PRINT:
            // text to print
            assert (args.length == 1);
            break;
        case SET_NAME:
            // long name to set this room's name to
            assert (args.length == 1);
            break;
        case SET_SHORT_DESC:
            // short description to set this room's short description to
            assert (args.length == 1);
            break;
        case SET_DESC:
            // long description to set this room's long description to
            assert (args.length == 1);
            break;
        case ADD_ITEM:
            // item to add
            assert (args.length == 1);
            break;
        case REMOVE_ITEM:
            // item to remove
            assert (args.length == 1);
            break;
        case ADD_SCRIPT:
            assert (args.length > 2);
            break;
        case REMOVE_SCRIPT:
            assert (args.length > 2);
            break;
        case SET_NAME_OF:
            // short name of desired room, long name to set given room's name to
            assert (args.length == 2);
            break;
        case SET_SHORT_DESC_OF:
            // short name of desired room, short description to set given room's
            // short description to
            assert (args.length == 2);
            break;
        case SET_DESC_OF:
            // short name of desired room, long description to set given room's
            // long description to
            assert (args.length == 2);
            break;
        case ADD_ITEM_TO:
            // short name of desired room, item to add to given room
            assert (args.length == 2);
            break;
        case REMOVE_ITEM_FROM:
            // short name of desired room, item to remove from given room
            assert (args.length == 2);
            break;
        case ADD_SCRIPT_TO:
            assert (args.length > 3);
            break;
        case REMOVE_SCRIPT_FROM:
            assert (args.length > 3);
            break;
        }

        throw new NotImplementedException();
    }

    /**
     * Checks to make sure the representation invariant is not violated
     */
    private void checkRep() {
        // null checks
        assert (gameModel != null);
        assert (commandType != null);
        assert (args != null);

        // check arg lengths for all command types
        switch (commandType) {
        case LOOK:
            // no args, you just look in the room you are in
            assert (args.length == 0);
            break;
        case GO:
            // short name of the desired room to travel to
            assert (args.length == 1);
            break;
        case ADD_HEALTH:
            // int to add to player health
            assert (args.length == 1);
            break;
        case GIVE_ITEM:
            // give item to player inventory
            assert (args.length == 1);
            break;
        case TAKE_ITEM:
            // remove item from player inventory
            assert (args.length == 1);
            break;
        case PRINT:
            // text to print
            assert (args.length == 1);
            break;
        case SET_NAME:
            // long name to set this room's name to
            assert (args.length == 1);
            break;
        case SET_SHORT_DESC:
            // short description to set this room's short description to
            assert (args.length == 1);
            break;
        case SET_DESC:
            // long description to set this room's long description to
            assert (args.length == 1);
            break;
        case ADD_ITEM:
            // item to add
            assert (args.length == 1);
            break;
        case REMOVE_ITEM:
            // item to remove
            assert (args.length == 1);
            break;
        case ADD_SCRIPT:
            assert (args.length > 2);
            break;
        case REMOVE_SCRIPT:
            assert (args.length > 2);
            break;
        case SET_NAME_OF:
            // short name of desired room, long name to set given room's name to
            assert (args.length == 2);
            break;
        case SET_SHORT_DESC_OF:
            // short name of desired room, short description to set given room's
            // short description to
            assert (args.length == 2);
            break;
        case SET_DESC_OF:
            // short name of desired room, long description to set given room's
            // long description to
            assert (args.length == 2);
            break;
        case ADD_ITEM_TO:
            // short name of desired room, item to add to given room
            assert (args.length == 2);
            break;
        case REMOVE_ITEM_FROM:
            // short name of desired room, item to remove from given room
            assert (args.length == 2);
            break;
        case ADD_SCRIPT_TO:
            assert (args.length > 3);
            break;
        case REMOVE_SCRIPT_FROM:
            assert (args.length > 3);
            break;
        }
    }
}
