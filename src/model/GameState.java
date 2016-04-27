package model;

import java.util.HashSet;
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

    private Graph<Room> rooms;
    private Player player;
    private String currRoom;
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
        this.currRoom = currentPlayerRoom;
        this.nameToRoom = nameToRoom;
    }

    /**
     * Returns all the short names of the rooms the the player can travel to
     * from their current room. Returns an empty set if there are no paths
     * leading from this room.
     */
    public Set<String> adjacentRooms() {
        Set<String> availableRooms = new HashSet<>();

        // retrieve all the short names of all the adjacent rooms
        for (Room room : rooms.adjacent(nameToRoom.get(currRoom))) {
            availableRooms.add(room.getShortName());
        }

        return availableRooms;
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
