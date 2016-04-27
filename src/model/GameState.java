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
    private Room currentPlayerRoom;
    private Map<String, Room> nameToRoom;

    /*
     * Constructs a new GameState with the given data. GameState behavior not
     * specified if any of the given parameters are mutated. Internal state is
     * exposed.
     * 
     * In general, parser utility classes should load this data and construct a
     * new GameState.
     */
    public GameState(Graph<Room> rooms, Player player, Room currentPlayerRoom,
            Map<String, Room> nameToRoom) {
        this.rooms = rooms;
        this.player = player;
        this.currentPlayerRoom = currentPlayerRoom;
        this.nameToRoom = nameToRoom;
    }

    /**
     * Returns all the short names of the rooms the the player can travel to
     * from their current room. Returns an empty set if there are no paths
     * leading from this room.
     */
    public Set<String> adjacentRooms() {
        Set<String> availableRooms = new HashSet<>();

        for (Room room : rooms.adjacent(currentPlayerRoom)) {
            availableRooms.add(room.getShortName());
        }

        return availableRooms;
    }
}
