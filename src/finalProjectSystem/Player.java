package finalProjectSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Player {
	// Coded by: John Mark Lecias BSIT -AI11

	// Player class attributes
	String userName;
	String password;

	int easyBestAttempt;
	int normalBestAttempt;
	int difficultBestAttempt;
	int hardcoreBestAttempt;

	// Player class constructor
	public Player(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	// method for creating a file for the player
	public String register() throws IOException {
		Player[] players = Main.getPlayers();

		if(players != null) {
			for (Player player : players) {
				if (this.userName.equals(player.userName)) {
					System.out.println(" Username is already taken.");
					return null;
				}
			}
		}
		
		String decision = Main.getInput(" Continue? (y/n): ", "[y Y n N]");
		if (decision.matches("[y Y]")) {

			writeFile();

			return "Y";
		} else {
			return "N";
		}

	}
	
	// method for logging in a player
	public String login() throws IOException {
		Player[] players = Main.getPlayers();

		Boolean isPlayerFound = false;
		Boolean isPasswordCorrect = false;

		if(players != null) {
			for (int i = 0; i < players.length; i++) {
				if (this.userName.equals(players[i].userName)) {
					isPlayerFound = true;

					if (this.password.equals(players[i].password)) {
						isPasswordCorrect = true;

						String decision = Main.getInput(" Continue? (y/n): ", "[y Y n N]");
						if (decision.matches("[y Y]")) {
							this.easyBestAttempt = players[i].easyBestAttempt;
							this.normalBestAttempt = players[i].normalBestAttempt;
							this.difficultBestAttempt = players[i].difficultBestAttempt;
							this.hardcoreBestAttempt = players[i].hardcoreBestAttempt;
							return "Y";
						} else {
							return "N";
						}
					}
				}
			}
		}
		
		if (!isPlayerFound) {
			System.out.println(" Player not found.");
		}

		if (isPlayerFound && !isPasswordCorrect) {
			System.out.println(" Password is incorrect.");
		}

		return null;
	}
	
	// method for getting best attempt depending on game mode
	public int getBestAttempt(String gamemode) {
		switch (gamemode) {
			case "EASY":
				return this.easyBestAttempt;
			case "NORMAL":
				return this.normalBestAttempt;
			case "DIFFICULT":
				return this.difficultBestAttempt;
			case "HARDCORE":
				return this.hardcoreBestAttempt;
		}
		return 0;
	}

	// method for updating a player and its file
	public void update(String gamemode, int newAttempt) throws IOException {
		int attempt = 0;

		switch (gamemode) {
			case "EASY":
				attempt = this.easyBestAttempt;
				if (attempt == 0 || newAttempt != 0 && newAttempt < attempt) {
					this.easyBestAttempt = newAttempt;}
				break;
			case "NORMAL":
				attempt = this.normalBestAttempt;
				if (attempt == 0 || newAttempt != 0 && newAttempt < attempt) {
					this.normalBestAttempt = newAttempt;}
				break;
			case "DIFFICULT":
				attempt = this.difficultBestAttempt;
				if (attempt == 0 || newAttempt != 0 && newAttempt < attempt) {
					this.difficultBestAttempt = newAttempt;}
				break;
			case "HARDCORE":
				attempt = this.hardcoreBestAttempt;
				if (attempt == 0 || newAttempt != 0 && newAttempt < attempt) {
					this.hardcoreBestAttempt = newAttempt;}
				break;
		}

		writeFile();
	}

	// function for writing file with layout
	private void writeFile() throws IOException {
		FileWriter fw = new FileWriter(new File(
				Main.DIRECTORY,
				this.userName + ".txt"));
		try {
			fw.write("userName: " + this.userName);
			fw.write("\nPassword: " + this.password);
			fw.write("\nEasy       - " + this.easyBestAttempt);
			fw.write("\nNormal     - " + this.normalBestAttempt);
			fw.write("\nDifficult  - " + this.difficultBestAttempt);
			fw.write("\nHardcore   - " + this.hardcoreBestAttempt);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
// end of Player class