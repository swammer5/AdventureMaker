package model;

public enum CommandType {

    ////////////////////////////
    // general command format //
    ////////////////////////////
    
    /*
     * General flat file command format:
     *  <command type>, <appropriate # of command arguments>
     * 
     * command type is the CommandType of this command
     * appropriate # of arguments is all the arguments that this command takes
     */
    
    ///////////
    // basic //
    ///////////
    
    LOOK, GO, PRINT, //TELEPORT,
    
    ////////////
    // player //
    ////////////
    
    ADD_HEALTH, GIVE_ITEM, TAKE_ITEM,
    
    ///////////////////
    // room commands //
    ///////////////////
    
    // this room
    SET_NAME,
    SET_SHORT_DESC, SET_DESC,
    ADD_ITEM, REMOVE_ITEM,
    ADD_SCRIPT, REMOVE_SCRIPT,
    
    // specified room
    SET_NAME_OF,
    SET_SHORT_DESC_OF, SET_DESC_OF,
    ADD_ITEM_TO, REMOVE_ITEM_FROM,
    ADD_SCRIPT_TO, REMOVE_SCRIPT_FROM;
    
    /**
     * Returns the appropriate CommandType enum from the given String type or null if there is no match.
     * 
     * @param type the string representing a desired CommandType
     * @return the CommandType matching the given type
     */
    public static CommandType get(String type) {
        type = type.toUpperCase().trim();
        if (type.equals("LOOK")) {
            return LOOK;
        } else if (type.equals("GO")) {
            return GO;
        } else if (type.equals("PRINT")) {
            return PRINT;
        } else if (type.equals("ADD_HEALTH")) {
            return ADD_HEALTH;
        } else if (type.equals("GIVE_ITEM")) {
            return GIVE_ITEM;
        } else if (type.equals("TAKE_ITEM")) {
            return TAKE_ITEM;
        } else if (type.equals("SET_NAME")) {
            return SET_NAME;
        } else if (type.equals("SET_DESC")) {
            return SET_DESC;
        } else if (type.equals("ADD_ITEM")) {
            return ADD_ITEM;
        } else if (type.equals("REMOVE_ITEM")) {
            return REMOVE_ITEM;
        } else if (type.equals("ADD_SCRIPT")) {
            return ADD_SCRIPT;
        } else if (type.equals("REMOVE_SCRIPT")) {
            return REMOVE_SCRIPT;
        } else if (type.equals("SET_NAME_OF")) {
            return SET_NAME_OF;
        } else if (type.equals("SET_SHORT_DESC_OF")) {
            return SET_SHORT_DESC_OF;
        } else if (type.equals("SET_DESC_OF")) {
            return SET_DESC_OF;
        } else if (type.equals("ADD_ITEM_TO")) {
            return ADD_ITEM_TO;
        } else if (type.equals("REMOVE_ITEM_FROM")) {
            return REMOVE_ITEM_FROM;
        } else if (type.equals("ADD_SCRIPT_TO")) {
            return ADD_SCRIPT_TO;
        } else if (type.equals("REMOVE_SCRIPT_FROM")) {
            return REMOVE_SCRIPT_FROM;
        } else {
            return null;
        }
    }
}
