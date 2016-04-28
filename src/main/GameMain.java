package main;

import java.util.List;
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
    private static int saveFile;

    public static void main(String[] args) {
        print("Welcome to your very own adventure!");

        Scanner in = new Scanner(System.in);

        setup(in);
        interact(in);
        save();

        System.exit(0);
    }

    /**
     * Sets up the GameModel to prepare the game.
     */
    private static void setup(Scanner in) {
        boolean saveChosen = false;
        while (!saveChosen) {
            // print available save files
            print("Available save files:");
            List<Integer> saveFiles = GameModel.getSaveFiles();
            String saves = "";
            for (int saveFile : saveFiles) {
                saves = saveFile + " ";
            }
            print(saves.trim());
            
            // prompt for desired file or create new save file
            print("Please type in your desired file number (or press return for new game)");
            int fileNumber;
            String input = in.nextLine();
            if (!input.equals("")) {
                // load game
                fileNumber = Integer.parseInt(input);
                if (!saveFiles.contains(fileNumber)) {
                    print("Invalid file number. Cannot load file.");
                    continue;
                } else {
                    model = GameModel.loadGame(fileNumber);
                }
            } else {
                // new game
                model = GameModel.newGame();
            }
            saveFile = model.getSaveFileNumber();
            saveChosen = true;
        }
    }

    /**
     * Asks GameModel to save the game for this file.
     */
    private static void save() {
        model.saveGame();
        print("Your save file number is " + saveFile);
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
                String[] tokens = line.split(" ", 2);
                String command = tokens[0];
                if (command.equals("m") || command.equals("menu")
                        || command.equals("h") || command.equals("help")
                        || command.equals("?")) {
                    menu();
                } else if (command.equals("q")) {
                    quit = true;
                } else if (command.equals("p") || command.equals("print")) {
                    if (tokens.length > 1) {
                        String printString = tokens[1];
                        print(printString.trim());

                    }
                    /*
                     * } else if () {
                     * 
                     * } else if () {
                     * 
                     * } else if () {
                     */
                } else {
                    // for anything else we should just call execute(input) on
                    // GameModel, then if the result is null we print command
                    // not recognized.
                    String result = model.execute(line);
                    if (result == null) {
                        print("Command not recognized. Try \"menu,\""
                                + "\"help,\" or \"?\" to see valid commands.");
                    } else {
                        print(result);
                    }
                }
            }
        }
    }

    /**
     * Displays the menu of available commands to the user
     */
    private static void menu() {
        print("Menu");
        print("============================================");
        print("\t(m)enu or (h)elp or ? - displays this menu");
        print("\t(p)rint - leave a note for yourself in the console");
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
            String[] words = s.split("[\\t ]+");
            int lineLength = 0;

            // print out all words, and a new line each time the line length
            // gets too long.
            for (String word : words) {
                if (lineLength >= MAX_LINE_LENGTH) {
                    System.out.println();
                    lineLength = 0;
                }

                System.out.print(word + " ");
                lineLength += word.length() + 1;

                // if main tries to print new lines manually, reset line length
                if (word.contains("\n")) {
                    lineLength = 0;
                }
            }

            System.out.println();
        }
    }
}
