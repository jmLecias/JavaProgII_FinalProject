package finalProjectSystem;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Player currentPlayer = null;
		
		final String[] HOME_OPTIONS = {"LOGIN", "REGISTER", "LEADERBOARDS"};
		final String[] GAME_OPTIONS = {"EASY", "NORMAL", "DIFFICULT", "HARDCORE", "Quit"};
		final String[] LOGIN_FIELDS = {"Username", "Password"};
		final String[] REGISTER_FIELDS = {"Username", "Password"};
		
		OptionSelector homeOS = new OptionSelector("The Guessing Game", HOME_OPTIONS, 62);
		OptionSelector gameOS = new OptionSelector("GAMEMODES", GAME_OPTIONS, 62);
		
		while(true) {
			switch(homeOS.showOptions()) {
				case "LOGIN":
					String[] playerLogin = homeOS.optionFields("Login", LOGIN_FIELDS);
					Player loginPlayer = new Player(playerLogin[0], playerLogin[1]);
					
					if(loginPlayer.login(" Continue? (y/n): ") == "Y") {
						currentPlayer = loginPlayer;
						
						System.out.println("\n Welcome back "+currentPlayer.userName+",\n What gamemode would you like to play? ");
						
						guessingGame(gameOS, currentPlayer);
						break;
					}
					else {
						currentPlayer = null;
						break;
					}
				case "REGISTER":
					String[] playerInfo = homeOS.optionFields("Register Player", REGISTER_FIELDS);
					Player player = new Player(playerInfo[0], playerInfo[1]);
					
					if(player.add(" Continue? (y/n): ") == "Y") {
						currentPlayer = player;
						
						System.out.println("\n Hello "+currentPlayer.userName+",\n What gamemode would you like to play? ");
						
						guessingGame(gameOS, currentPlayer);
						break;
					}
					else {
						currentPlayer = null;
						break;
					}
				case "LEADERBOARDS":
					LeaderBoards lb = new LeaderBoards("Easy");
					lb.show(2);
					
			    	
					break;
			}
		}


	}
	
	// function for the guessing game
	public static void guessingGame(OptionSelector os, Player player) throws IOException {
		Boolean quit = false;
		while(quit == false) {
			switch(os.showOptions()) {
				case "EASY":
					GuessingGame easyMode = new GuessingGame("EASY", 5, 10);
					int easyAttempt = easyMode.start();
					if(player.easyBestAttempt == 0) {
						player.easyBestAttempt = easyAttempt;
						player.update();
					}
					else if(easyAttempt < player.easyBestAttempt) {
						player.easyBestAttempt = easyAttempt;
						player.update();
					}
					break;
				case "NORMAL":
					GuessingGame normalMode = new GuessingGame("NORMAL", 10, 100);
					int normalAttempt = normalMode.start();
					if(player.normalBestAttempt == 0) {
						player.normalBestAttempt = normalAttempt;
						player.update();
					}
					else if(normalAttempt < player.normalBestAttempt) {
						player.normalBestAttempt = normalAttempt;
						player.update();
					}
					break;
				case "DIFFICULT":
					GuessingGame difficultMode = new GuessingGame("DIFFICULT", 30, 1000);
					int difficultAttempt = difficultMode.start();
					if(player.difficultBestAttempt == 0) {
						player.difficultBestAttempt = difficultAttempt;
						player.update();
					}
					else if(difficultAttempt < player.difficultBestAttempt) {
						player.difficultBestAttempt = difficultAttempt;
						player.update();
					}
					break;
				case "HARDCORE":
					GuessingGame hardcoreMode = new GuessingGame("HARDCORE", 3, 1000);
					int hardcoreAttempt = hardcoreMode.start();
					if(player.hardcoreBestAttempt == 0) {
						player.hardcoreBestAttempt = hardcoreAttempt;
						player.update();
					}
					else if(hardcoreAttempt < player.hardcoreBestAttempt) {
						player.hardcoreBestAttempt = hardcoreAttempt;
						player.update();
					}
					break;
				case "Quit":
					quit = true;
					break;
			}
		}
	}
	
	// input utility
	public static String getInput(String message, String validationRegex) {
		Scanner sc = new Scanner(System.in);
		String in;
		while(true) {
			System.out.print(message);
			in = sc.nextLine();
			if (in.matches(validationRegex)) {
				return in;
			}
		}	
	}
	
	// center spacer utility
	public static void centerStart(int parentLength, int itemLength) {
		for(int i = 1; i <= ((parentLength/2)-(itemLength/2)); i++) {
			System.out.print(" ");
		}
	}
	
	// divider utility
	public static void divider(char divider, int dividerLength) {
		for(int i = 1; i <= (dividerLength); i++) {
			System.out.print(divider);
		}
		System.out.println();
	}

}
