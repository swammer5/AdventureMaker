package utility;

import java.io.File;
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
        // filepath is the directory in which we find out file
        String playerFilePath = filePath + "/player.tsv";
        File playerFile = new File(playerFilePath);
        // TODO implement actual parsing

        // Stubbed out
        Player player = new Player("bob", 20, 20, new ArrayList<String>());
        String currentRoom = "kitchen";
        return new PlayerData(player, currentRoom);
    }

    private RoomData loadRooms(GameModel gameModel, String filePath) {
        // Stubbed out with a mock room that has a knife and 1 command, 'jump'.
        // TODO implement actual parsing. We'll read in a room edge file of all
        // room connections, and a file of all room data (including scripts
        // accepted by each room)

        Graph<Room> rooms = new Graph<>();

        // construct item list
        List<String> items = new ArrayList<>();
        items.add("knife");

        // construct accepted input map
        Map<String, Script> acceptedInput = new HashMap<>();

        // jump
        Script script1 = new Script();
        String[] args1 = new String[1];
        args1[0] = "You jump with joy!";
        script1.add(new Command(gameModel, CommandType.PRINT, args1));

        // take knife
        Script script2 = new Script();
        String[] args2 = new String[1];
        args2[0] = "knife";
        script2.add(new Command(gameModel, CommandType.REMOVE_ITEM, args2));

        String[] args3 = new String[1];
        args3[0] = "knife";
        script2.add(new Command(gameModel, CommandType.GIVE_ITEM, args3));

        String[] args4 = new String[1];
        args4[0] = "You take the knife off the small table.";
        script2.add(new Command(gameModel, CommandType.PRINT, args4));

        String[] args5 = new String[1];
        args5[0] = "There is a small oven and stove combination appliance nearby. The room is brightly lit and clean. The floor is made up of black and white glossy, checkered tiles.";
        script2.add(new Command(gameModel, CommandType.SET_DESC, args5));

        String[] args6 = new String[1];
        args6[0] = "There is a small oven and stove combination appliance nearby.";
        script2.add(new Command(gameModel, CommandType.SET_SHORT_DESC, args6));

        String[] args7 = new String[7];
        args7[0] = "use knife";
        args7[1] = "PRINT";
        args7[2] = "You accidentally cut your finger!";
        args7[3] = "SET_DESC";
        args7[4] = "There is a small oven and stove combination appliance nearby. The room is brightly lit and clean. The floor is made up of black and white glossy, checkered tiles. There's a small pool of blood on the floor now.";
        args7[5] = "ADD_HEALTH";
        args7[6] = "-1";
        script2.add(new Command(gameModel, CommandType.ADD_SCRIPT, args7));
        
        String[] args8 = new String[1];
        args8[0] = "take knife";
        script2.add(new Command(gameModel, CommandType.REMOVE_SCRIPT, args8));
        
        String[] args9 = new String[4];
        args9[0] = "take knife";
        args9[1] = "PRINT";
        args9[2] = "You already took the knife";
        args9[3] = "END_SCRIPT";
        script2.add(new Command(gameModel, CommandType.ADD_SCRIPT, args9));
             
        
        // at this point in a file we would see "END SCRIPT" so we would stop
        // adding this one and add the next command to the outer script.
        
        // note: if, while adding this script, we see another add script
        // command, we just add it and all it's arguments blindly as above, but
        // we need to see two end scripts now. every add script needs and end
        // script associated with it. When we find the last end script, we stop
        // and don't add this end script as an argument.

        acceptedInput.put("jump", script1);
        acceptedInput.put("take knife", script2);

        // construct room
        Room room = new Room(
                "Kitchen",
                "The West Wing Kitchen",
                "There is a small oven and stove combination appliance nearby. On a small table you see a knife.",
                "There is a small oven and stove combination appliance nearby. The room is brightly lit and clean. The floor is made up of black and white glossy, checkered tiles. On a small table you see a small but sharp knife.",
                items, acceptedInput);
        rooms.addNode(room);

        // we would also have to add edges between connected rooms to the room
        // graph here

        // fill name map with each room
        Map<String, Room> nameToRoom = new HashMap<>();
        nameToRoom.put(room.getShortName().toLowerCase(), room);
        // NOTE: THE toLowerCase() HERE IS VERY VERY IMPORTANT! WHEN YOU
        // IMPLEMENT THE REAL THING, MAKE SURE TO FLATTEN THE SHORT NAMES IN
        // THIS MAP TO LOWER CASE SO THAT INPUT CAN IGNORE CASE. LEAVE THE ROOM
        // DATA ALONE THOUGH. THAT WAY THE ROOM NAME DISPLAYS RIGHT, BUT WE MAP
        // THE FLATTENED NAME.

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
