package main;

import java.util.Scanner;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import model.GameModel;

/**
 * <b>GameMain</b> lets players load, play, and save text adventure games.
 * 
 * @author Sean Wammer
 */
public class GameMain {

    private static GameModel model;

    public static void main(String[] args) {
        System.out.println("Welcome to your very own adventure!");

        Scanner in = new Scanner(System.in);
        // You might ask for the save file here
        setup();

        interact(in);
        save();
        System.exit(0);
    }

    /**
     * Sets up the GameModel to prepare the game.
     */
    private static void setup() {
        // we may want to accept a file number here to load a particular save
        model = new GameModel();
    }

    /**
     * Asks GameModel to save the game for this file
     */
    private static void save() {
        // TODO this is where we ask the game model to save its state to the
        // file we specified in setup
        print("Save not implemented. We have not saved your game.");
    }

    /**
     * Macro for printing. Allows for less verbose typing.
     * 
     * @param s the string to pretty print
     */
    private static void print(String s) {
        Printer.print(s);
    }

    /**
     * Handles main interaction with user.
     */
    private static void interact(Scanner in) {
        boolean quit = false;
        while (!quit) {
            // TODO ask for input and redirect to appropriate command method
            String line = in.nextLine().toLowerCase().trim();
            if (!line.equals("")) {
                String[] tokens = line.split(" ");
                String command = tokens[0];
                if (command.equals("m") || command.equals("menu")
                        || command.equals("h") || command.equals("help")
                        || command.equals("?")) {
                    menu();
                } else if (command.equals("q")) {
                    quit = true;
                    /*
                     * } else if () {
                     * 
                     * } else if () {
                     * 
                     * } else if () {
                     * 
                     * } else if () {
                     */
                } else {
                    print("Command not recognized. Try \"menu,\""
                            + "\"help,\" or \"?\" to see valid commands.");
                }
            }
        }
    }

    /**
     * Displays the menu of available commands to the user
     */
    private static void menu() {
        print("Menu");
        print("====");
        print("\t(m)enu or (h)elp or ? - displays this menu");
        // TODO: rest of the commands
    }

    /**
     * Static utility class for formating output to be printed by GameMain.
     */
    private static class Printer {

        private static final int MAX_LINE_LENGTH = 80;

        /**
         * Prints the given String s on a new line, nicely formatted so that
         * words wrap to the next line.
         * 
         * @param s the string to pretty print
         */
        public static void print(String s) {
            String[] words = s.split("/[ -/_]/");
            int lineLength = 0;

            // print out all words, and a new line each time the line length
            // gets too long.
            for (String word : words) {
                if (lineLength >= MAX_LINE_LENGTH) {
                    System.out.println();
                }
                
                System.out.print(word);
                lineLength += word.length();

            }
        }
    }
}
