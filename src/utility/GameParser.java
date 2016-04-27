package utility;

import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import model.GameState;
import model.Graph;
import model.Player;
import model.Room;

/**
 * <b>GameParser</b> is a utility class that handles loading game data from
 * files on disc and populating useful Java objects.
 * 
 * @author Sean Wammer
 */
public class GameParser {

    /**
     * We load in the room data from rooms.tsv, player data from player.tsv.
     * 
     * @param filePath the path to the directory where the save files are found
     * @return a newly constructed GameState loaded from the files stored at the
     *         given filePath
     */
    public static GameState loadGameState(String filePath) {
        // load each parameter of the GameState

        // load rooms
        RoomData rd = loadRooms(filePath);
        Graph<Room> rooms = rd.graph;
        Map<String, Room> nameToRoom = rd.map;

        // load player data
        PlayerData pd = loadPlayer(filePath);
        Player player = pd.player;
        String playerCurrentRoom = pd.currentRoom;

        // construct and return GameState
        return new GameState(rooms, player, playerCurrentRoom, nameToRoom);
    }

    private static PlayerData loadPlayer(String filePath) {
        throw new NotImplementedException();
    }

    private static RoomData loadRooms(String filePath) {
        throw new NotImplementedException();
    }

    /**
     * Encapsulation helper class to keep track of player status and current
     * location
     */
    private class PlayerData {

        public Player player;
        public String currentRoom;

        public PlayerData(Player player, String currentRoom) {
            this.player = player;
            this.currentRoom = currentRoom;
        }
    }

    /**
     * Encapsulation helper class to keep track of the room graph and the room
     * name map.
     */
    private class RoomData {

        public Graph<Room> graph;
        public Map<String, Room> map;

        public RoomData(Graph<Room> rooms, Map<String, Room> nameToRoom) {
            graph = rooms;
            map = nameToRoom;
        }
    }
}
