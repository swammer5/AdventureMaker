package main;

import java.util.Scanner;

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
		// TODO Auto-generated method stub
		
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
				if (command.equals("m") || command.equals("menu") ||
						command.equals("h") || command.equals("help") || 
						command.equals("?")) {
					menu();
				} else if (command.equals("q")) {
					quit = true;
	          /*
				} else if () {
					
				} else if () {
					
				} else if () {
					
				} else if () {
	          */
				} else {
					System.out.println("Command not recognized. Try \"menu,\""
							+ "\"help,\" or \"?\" to see valid commands.");
				}
			}
		}
	}

	/**
	 * Displays the menu of available commands to the user
	 */
	private static void menu() {
		System.out.println("Menu");
		System.out.println("====");
		System.out.println("\t(m)enu or (h)elp or ? - displays this menu");
		// TODO: rest of the commands
	}
}
