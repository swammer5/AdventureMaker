package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * <b>Room</b> represents a a mutable room in a text adventure game. Rooms can
 * register user commands associated with Scripts that run Commands.
 * 
 * @author Sean Wammer
 */
public class Room {

    /** Acts as the identifier for this room. Should never be modified */
    private final String shortName;

    /** mutable parts of this room, can all be changed with setter methods */
    private String name;
    private String shortDesc;
    private String longDesc;
    private List<String> items;
    private Map<String, Script> acceptedInput;

    private static final String DEFAULT_STRING = "...";

    public Room(String shortName) {
        this(shortName, shortName, DEFAULT_STRING, DEFAULT_STRING);
    }

    public Room(String shortName, String name, String shortDesc, String longDesc) {
        this(shortName, name, shortDesc, longDesc, new ArrayList<String>(),
                new HashMap<String, Script>());
    }

    /**
     * Constructs a new Room with the given parameters.
     * 
     * @param shortName the unique identifier of this
     * @param name the descriptive or long name of this
     * @param shortDesc short description of this
     * @param longDesc long description of this
     * @param items a list of items currently in this room
     * @param acceptedInput a map from accepted inputs to Scripts to execute
     * @throws IllegalArgumentException if shortName is null
     */
    public Room(String shortName, String name, String shortDesc,
            String longDesc, List<String> items,
            Map<String, Script> acceptedInput) {
        this.shortName = shortName;
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.items = items;
        this.acceptedInput = acceptedInput;

        checkRep();
    }

    /**
     * Returns the short name of this room. This acts as the room ID and must be
     * unique.
     * 
     * @return the short name of this room
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Returns the long (descriptive) name of this room.
     * 
     * @return the long name of this room.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the short description of this room.
     * 
     * @return the short description of this room.
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * Returns the long description of this room.
     * 
     * @return the long description of this room.
     */
    public String getLongDesc() {
        return longDesc;
    }

    /**
     * Returns a list of String representing all the items in this room.
     * 
     * (We use a list so we can have duplicates. More precisely, we are
     * returning an abstract bag of items)
     * 
     * @return a list of items in this room.
     */
    public List<String> getItems() {
        List<String> retList = new ArrayList<>();
        for (String item : items) {
            retList.add(item);
        }
        return retList;
    }

    /**
     * Returns the Script associated with the given input for this room or null
     * if there is no such Script.
     * 
     * @param input the user command to retrieve the Script of
     * @return the Script associated with the given input or null if there is no
     *         script associated with this input.
     * @throws IllegalArgumentException if input == null
     */
    public Script getScript(String input) {
        return acceptedInput.get(fix(input));
    }

    /**
     * Sets the long (descriptive) name of this room.
     * 
     * @param name the new name
     * @throws IllegalArgumentException if name == null
     */
    public void setName(String name) {
        this.name = name;
        checkRep();
    }

    /**
     * Sets the short description of this room.
     * 
     * @param shortDesc the new short description
     * @throws IllegalArgumentException if shortDesc == null
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
        checkRep();
    }

    /**
     * Sets the long description of this room.
     * 
     * @param longDesc the new long description
     * @throws IllegalArgumentException if longDesc == null
     */
    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
        checkRep();
    }

    /**
     * Adds the given Script to this room associated with the given user input
     * command. Overwrites any previous script that may have been associated
     * with the given command. Given input is not case-sensitive. Room will not
     * remember the case of the command.
     * 
     * If this room acceptsInput(input), the old script will be returned or null
     * otherwise.
     * 
     * @param input the user command to register that triggers the given script
     * @param script the script to add to this room
     * @throws IllegalArgumentException if input == null or script == null
     */
    public Script addScript(String input, Script script) {
        input = fix(input);
        if (script == null) {
            throw new IllegalArgumentException("Given script must not be null.");
        }
        checkRep();

        return acceptedInput.put(input, script);
    }

    /**
     * Removes the Script associated with the given user input from this room.
     * 
     * @param input the user command associated with the Script to remove
     * @throws IllegalArgumentException if input == null
     */
    public void removeScript(String input) {
        acceptedInput.remove(fix(input));
        checkRep();
    }

    /**
     * Returns true if and only if this room has the given input registered to a
     * script. Not case sensitive.
     * 
     * @param input the user command to check
     * @return true iff this room has the given input registered to a script
     * @throws IllegalArgumentException if input == null
     */
    public boolean acceptsInput(String input) {
        checkRep();
        return acceptedInput.containsKey(fix(input));
    }

    /**
     * Runs the Script associated with the given command. Returns the output
     * that the commands produce to be printed by main or the empty string if
     * there is no output. Returns null if the command was not recognized in
     * this room.
     * 
     * @param input the command to attempt to run
     * @return the output that these commands produce to be printed by main or
     *         an empty String if there is no output, or null if the command is
     *         not recognized in this Room.
     * @throws IllegalArgumentException if input == null
     */
    public String execute(String input) {
        input = fix(input);
        if (!acceptsInput(input)) {
            return null;
        } else {
            String result = acceptedInput.get(input).execute();
            checkRep();
            return result;
        }
    }

    @Override
    public int hashCode() {
        // IMPLEMENTATION NOTE, HASH CODE CAN ONLY DEPEND ON THE *IMMUTABLE*
        // PARTS OF THIS ROOM, SO THAT WE CAN SET ALL THESE FIELDS LATER, AND IT
        // WON'T VIOLATE THE STATE OF THE GRAPH BY RETURNING DIFFERENT HASH
        // CODES LATER. IF YOU ARE CHANGING THIS FUNCTION, THINK HARD BEFORE YOU
        // DO SO.
        return getShortName().hashCode();
    }

    /**
     * Returns true if and only if this room is equal to the given object. Two
     * Rooms are equal if their short names are equal. This is because the short
     * name acts as the room's ID and should be unique in the game.
     * 
     * @param o the object to be tested for equality with this room
     * @return true iff this room's short name is equal to o's short name
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Room)) {
            return false;
        }

        Room other = (Room) o;

        return other.getShortName().equals(getShortName());
    }

    /**
     * Standardizes the input so that we are more likely to recognize commands.
     * 
     * Implementation note: We currently flatten to lower case and trim
     * whitespace, so 'get axe' will be the same as ' Get Axe ' and ' GEt AxE'.
     * 
     * @param input the input String to standardize
     * @return the standardized input string
     */
    private String fix(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null.");
        }
        return input.toLowerCase().trim();
    }

    private void checkRep() {
        // ensure good room representation with null checks
        if (shortName == null) {
            throw new IllegalArgumentException("short name must not be null.");
        }
        if (name == null) {
            throw new IllegalArgumentException("name must not be null.");
        }
        if (shortDesc == null) {
            throw new IllegalArgumentException(
                    "short description must not be null.");
        }
        if (longDesc == null) {
            throw new IllegalArgumentException(
                    "long description must not be null.");
        }
        if (items == null) {
            throw new IllegalArgumentException("items list must not be null.");
        }
        if (acceptedInput == null) {
            throw new IllegalArgumentException(
                    "accepted input map must not be null.");
        }
    }
}
