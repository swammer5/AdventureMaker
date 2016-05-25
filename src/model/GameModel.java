package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utility.GameParser;
import utility.GameSaver;
import org.apache.commons.io.FileUtils;

/**
 * Offers an interface with the game data for main, and offers access to the
 * game state for objects of the model package and unit tests.
 * 
 * @author Sean Wammer
 */
public class GameModel {

    private int saveFile;
    private static GameState gameState;

    private static final int MAX_SAVES = 100;
    private static final String FRESH_DIR = "new-game";

    /**
     * Constructs a new GameModel
     */
    private GameModel(int fileNumber) {
        saveFile = fileNumber;

        // load game state for given save file.
        String filepath = "saves/" + fileNumber + "/save.tsv";
        gameState = new GameParser().loadGameState(this, filepath);
    }

    /**
     * Picks a new save file number, and creates a new save file directory for
     * this new game. Returns a GameModel with the new save file loaded.
     * 
     * Returns null if we save directory was not created successfully.
     * 
     * @throws IOException
     */
    public static GameModel newGame() throws IOException {
        // we pick a new save file, make sure that it isn't taken, then return
        List<Integer> saveFiles = getSaveFiles();
        int fileNumber = -1;
        for (int i = 1; i <= MAX_SAVES; i++) {
            if (!saveFiles.contains(i)) {
                // we found an unused save slot! Lets copy the new game files
                // into a new directory with this file number.
                fileNumber = i;
                File freshGameDir = new File(FRESH_DIR);
                File savesDir = new File("saves/" + fileNumber);
                boolean success = savesDir.mkdir();
                if (!success) {
                    return null;
                } else {
                    FileUtils.copyDirectory(freshGameDir, savesDir);
                }
            }
        }
        return new GameModel(fileNumber);
    }

    /**
     * Returns a GameModel with the file associated with the given file number
     * loaded if save file is successfully loaded, or null if this save file
     * does not exist.
     * 
     * @param fileNumber the file number to attempt to load
     * @return a GameModel if the save file is successfully loaded or null if
     *         the file was not
     * @throws IOException
     */
    public static GameModel loadGame(int fileNumber) throws IOException {
        if (getSaveFiles().contains(fileNumber)) {
            return new GameModel(fileNumber);
        } else {
            return null;
        }
    }

    /**
     * Returns a list of all the existing save file numbers. Returns an empty
     * list if there are no save files available.
     * 
     * @return a list of all existing save file numbers
     * @throws IOException
     */
    public static List<Integer> getSaveFiles() throws IOException {
        File savesDir = new File("saves");
        File[] fileList = savesDir.listFiles();
        List<Integer> fileNumbers = new ArrayList<>();
        for (File file : fileList) {
            fileNumbers.add(Integer.parseInt(file.getName()));
        }
        return fileNumbers;
    }

    /**
     * Returns the save file number that has been loaded for this.
     * 
     * @return the save file number that has been loaded for this
     */
    public int getSaveFileNumber() {
        return saveFile;
    }

    /**
     * Saves the loaded game, or has no effect if there is no loaded game.
     */
    public void saveGame() {
        String filePath = "save/" + saveFile;
        GameSaver.saveGameState(filePath, gameState);
    }

    /**
     * Moves the player to the room with the given name if that room is adjacent
     * to the current room. Returns true if the player is moved successfully or
     * false if the given room is not adjacent to the player's current room.
     * 
     * @param roomName the short name of the room to travel to.
     * @return true if the player is moved successfully and false if the given
     *         room is not adjacent to the player's current room.
     */
    public boolean go(String roomName) {
        return gameState.go(roomName);
    }

    /**
     * Returns the short name of the room the player is currently in.
     * 
     * @return the short name of the room the player is currently in
     */
    public String shortName() {
        return gameState.getCurrentRoom().getShortName();
    }

    /**
     * Returns the name of the room the player is currently in.
     * 
     * @return the name of the room the player is currently in
     */
    public String name() {
        return gameState.getCurrentRoom().getName();
    }

    /**
     * Returns the short description of the room the player is currently in.
     * 
     * @return the short description of the room the player is currently in
     */
    public String shortDesc() {
        return gameState.getCurrentRoom().getShortDesc();
    }

    /**
     * Returns the long description of the room the player is currently in.
     * 
     * @return the long description of the room the player is currently in.
     */
    public String longDesc() {
        return gameState.getCurrentRoom().getLongDesc();
    }

    /**
     * Returns the long description of the room the player is currently in and a
     * list of the places the player can travel to (nicely formatted in a comma
     * separated list).
     * 
     * @return the long description of the room the player is in and a list of
     *         places this player can travel to
     */
    public String look() {
        // add long description
        String ret = longDesc();
        ret += "\n";

        // build list of travel locations
        ret += "You can go to";
        for (String shortName : adjacentRooms()) {
            ret += " " + shortName;
        }

        return ret;
    }

    /**
     * Returns the short names of all the rooms the player can travel to from
     * their current location. Returns an empty set if there are no paths
     * leading from this room.
     * 
     * @return all the short names of all the rooms the player can travel to
     *         from their current location
     */
    public List<String> adjacentRooms() {
        return gameState.adjacentRooms();
    }

    /**
     * Runs the script associated with the command in the player's current room.
     * Returns the output that the command produces, or null if the command is
     * not recognized.
     * 
     * @param input the command to attempt to run in this room
     * @return the output produced by the command input
     */
    public String execute(String input) {
        return gameState.execute(input);
    }

    /**
     * Returns the GameState for the game this model. Only members of this
     * package or unit tests should use this method! Do not call this method
     * from main or elsewhere.
     * 
     * @return the GameState for this
     */
    public GameState getGameState() {
        return gameState;
    }
}