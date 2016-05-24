package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import model.Command;
import model.CommandType;
import model.GameModel;
import model.GameState;
import model.Graph;
import model.Player;
import model.Room;
import model.Script;

/**
 * <b>GameParser</b> is a utility class that handles loading game data from
 * files on disc and populating useful Java objects.
 * 
 * @author Sean Wammer
 */
public class GameParser {

    // TODO get *static* GameParser to work.
    // When I tried it, I wasn't able to return objects in the static methods
    // because there was no GameParser to save it into I guess.

    // we just use the default constructor because we don't need any fields.

    /**
     * We load in the room data from rooms.tsv, player data from player.tsv.
     * 
     * Name to room map flattens short names to lowercase! However, the names'
     * actual case is preserved in the Room itself. This way we can print room
     * names with correct case, but we ignore case for input.
     * 
     * @param filePath the path to the directory where the save files are found
     * @return a newly constructed GameState loaded from the files stored at the
     *         given filePath
     */
    public GameState loadGameState(GameModel gameModel, String filePath) {
        // load each parameter of the GameState

        // load rooms
        RoomData rd = loadRooms(gameModel, filePath);
        Graph<Room> rooms = rd.graph;
        Map<String, Room> nameToRoom = rd.map;

        // load player data
        PlayerData pd = loadPlayer(filePath);
        Player player = pd.player;
        String playerCurrentRoom = pd.currentRoom;

        // construct and return GameState
        return new GameState(rooms, player, playerCurrentRoom, nameToRoom);
    }

    private PlayerData loadPlayer(String filePath) {
        // Stubbed out
        // TODO implement actual parsing
        Player player = new Player("bob", 20, 20, new ArrayList<String>());
        String currentRoom = "Kitchen";
        return new PlayerData(player, currentRoom);
    }

    private RoomData loadRooms(GameModel gameModel, String filePath) {
        // Stubbed out with a mock room that has a knife and 1 command, 'jump'.
        // TODO implement actual parsing

        Graph<Room> rooms = new Graph<>();

        // construct item list
        List<String> items = new ArrayList<>();
        items.add("knife");

        // construct accepted input map
        Map<String, Script> acceptedInput = new HashMap<>();
        String[] args = new String[1];
        args[0] = "You jump with joy!";
        Script script = new Script();
        script.add(new Command(gameModel, CommandType.PRINT, args));
        acceptedInput.put("jump", script);

        // construct room
        Room room = new Room(
                "Kitchen",
                "The West Wing Kitchen",
                "There is a small oven and stove combination appliance nearby. On a small table you see a knife.",
                "There is a small oven and stove combination appliance nearby. The room is brightly lit and clean. The floor is made up of black and white glossy, checkered tiles. On a small table you see a small but sharp knife.",
                items, acceptedInput);
        rooms.addNode(room);
        // we would also have to add edges between connected rooms to the room graph here

        // fill name map with each room
        Map<String, Room> nameToRoom = new HashMap<>();
        nameToRoom.put(room.getShortName().toLowerCase(), room);
        // NOTE: THE toLowerCase() HERE IS VERY VERY IMPORTANT! WHEN YOU
        // IMPLEMENT THE REAL THING, MAKE SURE TO FLATTEN THE SHORT NAMES IN
        // THIS MAP TO LOWER CASE SO THAT INPUT CAN IGNORE CASE. LEAVE THE ROOM
        // DATA ALONE THOUGH.

        return new RoomData(rooms, nameToRoom);
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
