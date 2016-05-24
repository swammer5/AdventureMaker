package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utility.GameSaver;

/**
 * Offers an interface with the game data for main, and offers access to the
 * game state for objects of the model package.
 * 
 * @author Sean Wammer
 */
public class GameModel {

    private int saveFile;
    private GameState gameState;

    /**
     * Constructs a new GameModel
     */
    private GameModel(int fileNumber) {
        // TODO: instantiate fields
        saveFile = fileNumber;

        // when we want to load from a file, we won't make a new GameState,
        // we will call GameParser.loadGameState(filepath) to return a populated
        // GameState.
        // the code below is subject to change:
        // String filepath = "saves/save" + fileNum + ".tsv";
        // GameState gameState = GameParser.loadGameState(filepath)
    }

    /**
     * Picks a new save file number, and creates a new save file directory for
     * this new game. Returns a GameModel with the new save file loaded.
     */
    public static GameModel newGame() {
        // TODO implement
        // we pick a new save file, make sure that it isn't taken, then return
        // loadGame(fileNumber)
        throw new NotImplementedException();
    }

    /**
     * Returns a GameModel with the file associated with the given file number
     * loaded if save file is successfully loaded, or null if this save file
     * does not exist.
     * 
     * @param fileNumber the file number to attempt to load
     * @return a GameModel if the save file is successfully loaded or null if
     *         the file was not
     */
    public static GameModel loadGame(int fileNumber) {
        // TODO implement
        if (getSaveFiles().contains(fileNumber)) {
            // gameState = GameParser.loadGameState(filePath);
            // return true;
        } else {
            return null;
        }
        throw new NotImplementedException();
    }

    /**
     * Returns a list of all the existing save file numbers. Returns an empty
     * list if there are no save files available.
     * 
     * @return a list of all existing save file numbers
     */
    public static List<Integer> getSaveFiles() {
        // we are in '.' working directory
        // we need to look in 'saves'
        // the path of a particular save file directory '1' will be
        // 'saves/1/'
        // to get all the saves, google 'get all sub-directories in java'. I
        // think we
        // are looking for something similar to glob
        throw new NotImplementedException();
    }

    /**
     * Returns the save file number that has been loaded for this GameModel
     * 
     * @return
     */
    public int getSaveFileNumber() {
        return saveFile;
    }

    /**
     * Saves the loaded game, or has no effect if there is no loaded game.
     */
    public void saveGame() {
        // TODO implement
        // String filePath = "";
        // GameSaver.saveGameState(filePath, gameState);
        throw new NotImplementedException();
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
     * Moves the player to the room with the given name if that room is adjacent
     * to the current room. Returns true if the player is moved successfully or
     * false if the given room is not adjacent to the player's current room.
     * 
     * @param roomName the short name of the room to travel to.
     * @return true if the player is moved successfully and false if the given
     *         room is not adjacent to the player's current room.
     */
    public boolean go(String roomName) {
        // TODO implement
        throw new NotImplementedException();
    }

    public String shortDesc() {
        // TODO implement
        throw new NotImplementedException();
    }

    public String longDesc() {
        // TODO implement
        throw new NotImplementedException();
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