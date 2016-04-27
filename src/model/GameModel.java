package model;

import java.util.HashSet;
import java.util.Set;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Offers an interface with the game state.
 * 
 * @author Sean Wammer
 */
public class GameModel {

    private GameState gameState;

    /**
     * Constructs a new GameModel
     */
    public GameModel() {
        // TODO: instantiate fields

        // when we want to load from a file, we won't make a new GameState,
        // we will call GameParser.loadGameState(filepath) to return a populated
        // GameState
        // the code below is subject to change:
        // String filepath = "saves/save" + fileNum + ".tsv";
        // GameState gameState = GameParser.loadGameState(filepath)
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

    public boolean go(String roomName) {
        // TODO implement
        throw new NotImplementedException();
    }

    public void look() {
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
    public Set<String> adjacentRooms() {
        return gameState.adjacentRooms();
    }

    /**
     * Returns the GameState for the game this models
     * 
     * @return the GameState
     */
    public GameState getGameState() {
        return gameState;
    }
}