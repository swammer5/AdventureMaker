package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b>GameState</b> represents the current game data. GameState can be mutated
 * to reflect changes in the game. GameState will keep track of all relevant
 * details about the game.
 * 
 * @author Sean Wammer
 */
public class GameState {

    /*
     * Representation invariant:
     * ========================
     * 
     * -fields all non-null
     * -currRoom is always lowercase (true case is stored in Room itself)
     * -rooms.contains(nameToRoom.get(currRoom)) == true
     */
    
    private Graph<Room> rooms;
    private String currRoom;
    private Player player;
    private Map<String, Room> nameToRoom;

    /**
     * Constructs a new GameState with the given data. GameState behavior not
     * specified if any of the given parameters are mutated. Internal state is
     * exposed.
     * 
     * In general, parser utility classes should load this data and construct a
     * new GameState.
     * 
     * @param rooms a graph of all the rooms in this game with edges between
     *        adjacent rooms
     * @param player the player status
     * @param currentPlayerRoom the short name of the room that the player is
     *        currently in
     * @param nameToRoom a map of all the short names of rooms to the room they
     *        reference. The Rooms in nameToRoom must be reference equal to the
     *        rooms in param 'rooms' such that modifying one modifies the other
     */
    public GameState(Graph<Room> rooms, Player player,
            String currentPlayerRoom, Map<String, Room> nameToRoom) {
        this.rooms = rooms;
        this.player = player;
        this.currRoom = currentPlayerRoom.toLowerCase().trim();
        this.nameToRoom = nameToRoom;
    }

    // TODO: add all CommandType requirements. Player commands are handled
    // because we can return the player directly. As for room mutations, we
    // should ask the game state for the room that matches the given short name.
    // Then the command mutates that directly too.
    // Then we just need to support 'go'. Note, 'look' is just return
    // room.longDesc()
    // so we don't have to ask gamestate to do this for us.
    // We only ask gameState to deliver room and player to us, and we ask for
    // 'go'

    /**
     * Returns all the short names of the rooms the the player can travel to
     * from their current room. Returns an empty set if there are no paths
     * leading from this room.
     */
    public List<String> adjacentRooms() {
        List<String> availableRooms = new ArrayList<>();

        // retrieve all the short names of all the adjacent rooms
        for (Room room : rooms.adjacent(nameToRoom.get(currRoom))) {
            availableRooms.add(room.getShortName());
        }
        
        return availableRooms;
    }

    /**
     * Returns true if and only if we were able to move the player to the room
     * with the specified short name. If the there is no such room with the
     * given short name adjacent to the players current room, we return false
     * and the player is not moved.
     * 
     * This is not case sensitive.
     * 
     * @param shortName the short name of the room to move the player to
     * @return true iff the player is successfully moved to the room with the
     *         given short name
     */
    public boolean go(String shortName) {
        // sanity check to make sure we are all on the same page for caps (main
        // will probably do this anyway but we don't rely on that)
        shortName = shortName.toLowerCase().trim();
        if (!adjacentRooms().contains(shortName)) {
            // the given room doesn't exist or is not adjacent to current room
            return false;
        } else {
            // the requested room is adjacent so we move player there
            currRoom = shortName;
            return true;
        }
    }

    // /**
    // * Returns true if and only if there is a room with the given short name
    // in
    // * this. Moves the player to the room with the given short name.
    // *
    // * @param shortName the short name of the room to move the player too
    // * @return true iff there is a room with the given shortName
    // */
    // public boolean teleport(String shortName) {
    // shortName = shortName.toLowerCase().trim();
    // if (nameToRoom.containsKey(shortName)) {
    // currRoom = shortName;
    // return true;
    // } else {
    // return false;
    // }
    // }

    /**
     * Returns the room with the given short name or null if there is no room
     * with this short name.
     * 
     * The room can be modified to update the game state. The internal
     * representation is not at risk because rooms can only be mutated through
     * the mutator methods, and these operations are safe. Additionally, game
     * state still holds a reference to the room so the room won't be lost if
     * anyone sets their reference to null.
     * 
     * @param shortName the short name of the desired room
     * @return the room with the given short name or null if there is no room
     *         with the given short name
     */
    public Room getRoom(String shortName) {
        // sanity check to make sure we are all on the same page for caps (main
        // will probably do this anyway but we don't rely on that)
        shortName = shortName.toLowerCase().trim();
        return nameToRoom.get(shortName);
    }

    /**
     * Returns the room the player is currently in.
     * 
     * @return the room the player is currently in.
     */
    public Room getCurrentRoom() {
        return nameToRoom.get(currRoom.toLowerCase());
    }

    /**
     * Returns the player of this game.
     * 
     * @return the player of this game
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Executes the command on the player's current room. Returns the output
     * that the commands produce to be printed by main or the empty string if
     * there is no output. Returns null if the command was not recognized in
     * this room.
     * 
     * @param input the command to attempt to run
     * @return the output that these commands produce to be printed by main or
     *         an empty String if there is no output, or null if the command is
     *         not recognized in this Room.
     */
    public String execute(String input) {
        return nameToRoom.get(currRoom).execute(input);
    }
}
