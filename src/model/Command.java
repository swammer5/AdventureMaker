package model;

/**
 * <b>Command</b> is an abstract representation of a request to change the game
 * state or perform an action.
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
     * Runs this command. Returns output that should be printed. Returns null if
     * there was an error with this command.
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
            String shortName = args[0];
            boolean goSuccess = gameModel.go(shortName);
            if (!goSuccess) {
                return null;
            } else {
                return "";
            }
            // case TELEPORT:
            // move player regardless of location (does not return string)
            // String shortName = args[0];
            // gameModel.getGameState().teleport(shortName);
            // return "";
        case ADD_HEALTH:
            // add to player health (does not return string)
            try {
                int health = Integer.parseInt(args[0]);
                gameModel.getGameState().getPlayer().addHealth(health);
                return "";
            } catch (NumberFormatException e) {
                return null;
            }
        case GIVE_ITEM:
            // give item to player inventory (does not return string)
            String item = args[0];
            gameModel.getGameState().getPlayer().giveItem(item);
            return "";
        case TAKE_ITEM:
            // remove item from player inventory if they have it (does not
            // return string)
            item = args[0];
            // could raise error if item doesn't exist, but we don't
            gameModel.getGameState().getPlayer().removeItem(item);
            return "";
        case PRINT:
            // return text to print
            String printString = args[0];
            return printString;
        case SET_NAME:
            // set long name of the player's current room
            String newName = args[0];
            gameModel.getGameState().getCurrentRoom().setName(newName);
            return "";
        case SET_SHORT_DESC:
            // set short description of the player's current room
            String newShortDesc = args[0];
            gameModel.getGameState().getCurrentRoom().setName(newShortDesc);
            return "";
        case SET_DESC:
            // set long description of the player's current room
            String newLongDesc = args[0];
            gameModel.getGameState().getCurrentRoom().setName(newLongDesc);
            return "";
        case ADD_ITEM:
            // add item to the player's current room
            item = args[0];
            gameModel.getGameState().getCurrentRoom().addItem(item);
            return "";
        case REMOVE_ITEM:
            // remove one of the specified item from the player's current room
            item = args[0];
            // could raise error if item doesn't exist, but we don't
            gameModel.getGameState().getCurrentRoom().removeItem(item);
            return "";
        case ADD_SCRIPT:
            // add the specified script to the player's current room
            return addScript();
        case REMOVE_SCRIPT:
            // remove the script with the specified input from the player's current room
            return removeScript();
        case SET_NAME_OF:
            // set name of the specified room
            shortName = args[0];
            newName = args[1];
            Room targetRoom = gameModel.getGameState().getRoom(shortName);
            if (targetRoom == null) {
                // no room with this name
                return null;
            } else {
                targetRoom.setName(newName);
                return "";
            }
        case SET_SHORT_DESC_OF:
            // set short description of the specified room
            shortName = args[0];
            newShortDesc = args[1];
            targetRoom = gameModel.getGameState().getRoom(shortName);
            if (targetRoom == null) {
                // no room with this name
                return null;
            } else {
                targetRoom.setShortDesc(newShortDesc);
                return "";
            }
        case SET_DESC_OF:
            // set long description of the specified room
            shortName = args[0];
            newLongDesc = args[1];
            targetRoom = gameModel.getGameState().getRoom(shortName);
            if (targetRoom == null) {
                // no room with this name
                return null;
            } else {
                targetRoom.setLongDesc(newLongDesc);
                return "";
            }
        case ADD_ITEM_TO:
            // add item to the specified room
            shortName = args[0];
            item = args[1];
            targetRoom = gameModel.getGameState().getRoom(shortName);
            if (targetRoom == null) {
                // no room with this name
                return null;
            } else {
                targetRoom.addItem(item);
                return "";
            }
        case REMOVE_ITEM_FROM:
            // remove one of the specified item from the specified room
            shortName = args[0];
            item = args[1];
            targetRoom = gameModel.getGameState().getRoom(shortName);
            if (targetRoom == null) {
                // no room with this name
                return null;
            } else {
                // could raise error if item doesn't exist, but we don't
                targetRoom.removeItem(item);
                return "";
            }
        case ADD_SCRIPT_TO:
            // add the specified script to the specified room
            return addScriptTo();
        case REMOVE_SCRIPT_FROM:
            // remove the script with the specified input from the specified room
            return removeScriptFrom();
        default:
            // command not recognized
            return null;
        }
    }

    /**
     * Adds this Command's script to the player's current room.
     * 
     * @return null if there was an error or empty string ("") if script was
     *         added successfully
     */
    private String addScript() {
        String input = args[0].toLowerCase().trim();
        Script scriptToAdd = parseScript(1);
        
        if (scriptToAdd == null) {
            // error with script parsing
            return null;
        } else {
            Room targetRoom = gameModel.getGameState().getCurrentRoom();
            targetRoom.addScript(input, scriptToAdd);
            return "";
        }
    }

    /**
     * Remove this Command's script from the player's current room.
     * 
     * @return null if there was an error or empty string ("") if script was
     *         removed successfully
     */
    private String removeScript() {
        // input associated with the script to remove
        String input = args[0].toLowerCase().trim();
        
        Room targetRoom = gameModel.getGameState().getCurrentRoom();
        if (!targetRoom.acceptsInput(input)) {
            // input is not accepted by this room, nothing to remove
            return null;
        } else {
            targetRoom.removeScript(input);
            return "";
        }
    }

    /**
     * Adds this Command's script to the room specified in args[0].
     * 
     * @return null if there was an error or empty string ("") if script was
     *         added successfully to the specified room
     */
    private String addScriptTo() {
        String shortName = args[0];
        String input = args[1].toLowerCase().trim();
        Script scriptToAdd = parseScript(2);
        
        if (scriptToAdd == null) {
            // error with script parsing
            return null;
        } else {
            Room targetRoom = gameModel.getGameState().getRoom(shortName);
            if (targetRoom == null) {
                // no room with this name
                return null;
            } else {
                targetRoom.addScript(input, scriptToAdd);
                return "";
            }
        }
    }

    /**
     * Remove this Command's script from the room specified in args[0].
     * 
     * @return null if there was an error or empty string ("") if script was
     *         removed successfully from the specified room
     */
    private String removeScriptFrom() {
        String shortName = args[0];
        // input associated with the script to remove
        String input = args[1].toLowerCase().trim();
        
        Room targetRoom = gameModel.getGameState().getRoom(shortName);
        if (targetRoom == null) {
            // no room with this name
            return null;
        } else {
            if (!targetRoom.acceptsInput(input)) {
                // input is not accepted by this room, nothing to remove
                return null;
            } else {
                targetRoom.removeScript(input);
                return "";
            }
        }
    }

    /**
     * Parses args starting at scriptStart index and recursively builds a
     * Script. Returns the parsed Script or null if there was an error.
     * 
     * @param scriptStart the index at the start of the script String
     * @return
     */
    private Script parseScript(int scriptStart) {
        Script script = new Script();
        
        // while there are still commands, add to the script
        
        return script;
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
