package finalProjectSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;

public class Main {
	// Coded by: John Mark T. lecias BSIT - AI11

	// All registered players are located in C: drive
	final static String DIRECTORY = "C:/GuessTheNumber_Players";
	final static File directoryPath = new File(DIRECTORY);

	public static void main(String[] args) throws IOException {
		// Creates the GuessTheNumber_Players in C:/
		directoryPath.mkdirs();

		Player currentPlayer = null;

		final String[] HOME_OPTIONS = { "LOGIN", "REGISTER", "LEADERBOARDS" };
		final String[] GAME_OPTIONS = { "EASY", "NORMAL", "DIFFICULT", "HARDCORE", "Quit" };
		final String[] LOGIN_FIELDS = { "Username", "Password" };
		final String[] REGISTER_FIELDS = { "Username", "Password" };

		OptionSelector homeOS = new OptionSelector("The Guessing Game", HOME_OPTIONS, 62);
		OptionSelector gameOS = new OptionSelector("GAMEMODES", GAME_OPTIONS, 62);

		while (true) {
			switch (homeOS.showOptions()) {
				case "LOGIN":
					String[] playerLogin = homeOS.optionFields("Login", LOGIN_FIELDS);
					Player loginPlayer = new Player(playerLogin[0], playerLogin[1]);

					if (loginPlayer.login(" Continue? (y/n): ") == "Y") {
						currentPlayer = loginPlayer;

						System.out.println("\n Welcome back " + currentPlayer.userName
								+ ",\n What gamemode would you like to play? ");

						guessingGame(gameOS, currentPlayer);
						break;
					} else {
						currentPlayer = null;
						break;
					}
				case "REGISTER":
					String[] playerInfo = homeOS.optionFields("Register Player", REGISTER_FIELDS);
					Player player = new Player(playerInfo[0], playerInfo[1]);

					if (player.register(" Continue? (y/n): ") == "Y") {
						currentPlayer = player;

						System.out.println(
								"\n Hello " + currentPlayer.userName + ",\n What gamemode would you like to play? ");

						guessingGame(gameOS, currentPlayer);
						break;
					} else {
						currentPlayer = null;
						break;
					}
				case "LEADERBOARDS":
					System.out.println();
					Main.centerStart(62, 12);
					System.out.println("LEADERBOARDS");

					LeaderBoards easyLB = new LeaderBoards("Easy");
					easyLB.show();
					LeaderBoards normalLB = new LeaderBoards("Normal");
					normalLB.show();
					LeaderBoards difficultLB = new LeaderBoards("Difficult");
					difficultLB.show();
					LeaderBoards hardcoreLB = new LeaderBoards("Hardcore");
					hardcoreLB.show();

					break;
			}
		}

	}

	// function for the guessing game
	public static void guessingGame(OptionSelector os, Player player) throws IOException {
		Boolean quit = false;
		while (!quit) {
			switch (os.showOptions()) {
				case "EASY":
					GuessingGame easy = new GuessingGame("EASY", 5, 10);
					int easyAttempt = easy.play(player);
					player.update(easy.mode, easyAttempt);
					break;
				case "NORMAL":
					GuessingGame normal = new GuessingGame("NORMAL", 10, 100);
					int normalAttempt = normal.play(player);
					player.update(normal.mode, normalAttempt);
					break;
				case "DIFFICULT":
					GuessingGame difficult = new GuessingGame("DIFFICULT", 20, 1000);
					int difficultAttempt = difficult.play(player);
					player.update(difficult.mode, difficultAttempt);
					break;
				case "HARDCORE":
					GuessingGame hardcore = new GuessingGame("HARDCORE", 3, 1000);
					int hardcoreAttempt = hardcore.play(player);
					player.update(hardcore.mode, hardcoreAttempt);
					break;
				case "Quit":
					quit = true;
					break;
			}
		}
	}

	// function for getting all registered players
	public static Player[] getPlayers() throws IOException {
		File[] filesList = directoryPath.listFiles();

		Player[] players = (filesList != null)? new Player[filesList.length] : null;
		
		if(filesList != null) {
			for (int i = 0; i < filesList.length; i++) {
				String userName = 
						Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(0).substring(10);
				String password = 
						Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(1).substring(10);

				int easyBestAttempt = Integer.valueOf(
						Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(2).substring(13));
				int normalBestAttempt = Integer.valueOf(
						Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(3).substring(13));
				int difficultBestAttempt = Integer.valueOf(
						Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(4).substring(13));
				int hardcoreBestAttempt = Integer.valueOf(
						Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(5).substring(13));

				Player player = new Player(userName, password);
				player.easyBestAttempt = easyBestAttempt;
				player.normalBestAttempt = normalBestAttempt;
				player.difficultBestAttempt = difficultBestAttempt;
				player.hardcoreBestAttempt = hardcoreBestAttempt;

				players[i] = player;
			}
			
			return players;
		}
		
		return players;
	}

	/* ------------------------ UTILITIES ---------------------------- */

	// Input utility
	// loops while input does not match Regex, else returns input in String
	public static String getInput(String message, String validationRegex) {
		Scanner sc = new Scanner(System.in);
		String in;
		while (true) {
			System.out.print(message);
			in = sc.nextLine();
			if (in.matches(validationRegex)) {
				return in;
			}
		}
	}

	// Center Spacer utility
	// prints spaces to center a println
	public static void centerStart(int parentLength, int itemLength) {
		for (int i = 1; i <= ((parentLength / 2) - (itemLength / 2)); i++) {
			System.out.print(" ");
		}
	}

	// Divider utility
	// prints a character line (divider) depending on its specified length
	public static void divider(char divider, int dividerLength) {
		for (int i = 1; i <= (dividerLength); i++) {
			System.out.print(divider);
		}
		System.out.println();
	}

}
// end of Main class