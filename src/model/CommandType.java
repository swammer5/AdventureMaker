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
    
    //////////////////////
    // basic and player //
    //////////////////////
    
    LOOK, GO, PRINT, ADD_HEALTH, GIVE_ITEM, TAKE_ITEM,
    
    ///////////////////
    // room commands //
    ///////////////////
    
    // this room
    SET_NAME, SET_SHORT_DESC, SET_DESC, ADD_ITEM, REMOVE_ITEM, ADD_SCRIPT, REMOVE_SCRIPT,
    
    // specified room
    SET_NAME_OF, SET_SHORT_DESC_OF, SET_DESC_OF, ADD_ITEM_TO, REMOVE_ITEM_FROM, ADD_SCRIPT_TO, REMOVE_SCRIPT_FROM
}
