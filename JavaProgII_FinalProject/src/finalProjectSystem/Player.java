package finalProjectSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Player {
	// All added players are located in C: drive
	final String DIRECTORY = "C:/GuessTheNumber_Players";
	final File directoryPath = new File(DIRECTORY);

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

	// method for logging in a player
	public String login(String message) throws IOException {
		Player[] players = Main.getPlayers(directoryPath);

		for (Player player : players) {
			if (this.userName.equals(player.userName)) {
				if (this.password.equals(player.userName)) {

					String decision = Main.getInput(message, "[y Y n N]");
					if (decision.matches("[y Y]")) {
						this.easyBestAttempt = player.easyBestAttempt;
						this.normalBestAttempt = player.normalBestAttempt;
						this.difficultBestAttempt = player.difficultBestAttempt;
						this.hardcoreBestAttempt = player.hardcoreBestAttempt;
						return "Y";
					} else {
						return "N";
					}
				} else {
					System.out.println(" Incorrect password.");
					break;
				}
			} else {
				System.out.println(" Player not found.");
				break;
			}
		}
		return null;
	}

	// method for creating a file for the player
	public String register(String message) throws IOException {
		Player[] players = Main.getPlayers(directoryPath);

		for (Player player : players) {
			if (this.userName.equals(player.userName)) {
				System.out.println(" Username is already taken.");
				return null;
			}
		}

		String decision = Main.getInput(message, "[y Y n N]");
		if (decision.matches("[y Y]")) {
			File file = new File(this.DIRECTORY);
			file.mkdirs();
			FileWriter fw = new FileWriter(new File(
					this.DIRECTORY,
					this.userName + ".txt"));
			try {
				fw.write("userName: " + userName);
				fw.write("\nPassword: " + password);
				fw.write("\nEasy       - " + easyBestAttempt);
				fw.write("\nNormal     - " + normalBestAttempt);
				fw.write("\nDifficult  - " + difficultBestAttempt);
				fw.write("\nHardcore   - " + hardcoreBestAttempt);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "Y";
		} else {
			return "N";
		}

	}

	// method for updating a player and its file
	public void update() throws IOException {
		FileWriter fw = new FileWriter(new File(
				this.DIRECTORY,
				this.userName + ".txt"));
		try {
			fw.write("userName: " + userName);
			fw.write("\nPassword: " + password);
			fw.write("\nEasy       - " + easyBestAttempt);
			fw.write("\nNormal     - " + normalBestAttempt);
			fw.write("\nDifficult  - " + difficultBestAttempt);
			fw.write("\nHardcore   - " + hardcoreBestAttempt);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
