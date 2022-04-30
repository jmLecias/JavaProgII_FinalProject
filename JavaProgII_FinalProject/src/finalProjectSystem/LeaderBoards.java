package finalProjectSystem;

import java.io.File;
import java.io.IOException;

public class LeaderBoards {
	// All added players are located in C: drive
	final File directoryPath = new File("C:/GuessTheNumber_Players");
	final int dividerLength = 62;

	// Class attributes
	String gamemode;

	// Class constructor
	public LeaderBoards(String gamemode) {
		this.gamemode = gamemode;
	}

	// method for showing leaderboard depending on gamemode
	public void show() throws IOException {
		Player[] players = Main.getPlayers(directoryPath);

		String output = "";

		// bubble sort
		for (int i = 0; i < players.length; i++) {
			for (int j = 1; j < (players.length - i); j++) {
				if (getCondition(players[j - 1], players[j])) {
					Player temp = players[j - 1];
					players[j - 1] = players[j];
					players[j] = temp;
				}
			}
		}

		// shows leader board
		Main.divider('-', this.dividerLength);
		Main.centerStart(this.dividerLength, this.gamemode.length());
		System.out.println(this.gamemode);

		for (int i = 0; i < players.length; i++) {
			if (getAttempt(players[i]) != 0) {
				String playerLine = players[i].userName + " - " + String.valueOf(getAttempt(players[i]));

				Main.centerStart(this.dividerLength, playerLine.length());
				System.out.println(playerLine);
			}
		}

		Main.divider('-', this.dividerLength);
		for (int i = 0; i < players.length; i++) {

		}

	}

	// function for getting bubble sort condition depending on game mode
	private Boolean getCondition(Player player1, Player player2) {
		Boolean condition = false;

		switch (this.gamemode) {
			case "Easy":
				condition = player1.easyBestAttempt > player2.easyBestAttempt;
				break;
			case "Normal":
				condition = player1.normalBestAttempt > player2.normalBestAttempt;
				break;
			case "Difficult":
				condition = player1.difficultBestAttempt > player2.difficultBestAttempt;
				break;
			case "Hardcore":
				condition = player1.hardcoreBestAttempt > player2.hardcoreBestAttempt;
				break;
		}

		return condition;
	}

	// function for getting the best attempt depending on gamemode
	private int getAttempt(Player player) {
		int attempt = 0;

		switch (this.gamemode) {
			case "Easy":
				attempt = player.easyBestAttempt;
				break;
			case "Normal":
				attempt = player.normalBestAttempt;
				break;
			case "Difficult":
				attempt = player.difficultBestAttempt;
				break;
			case "Hardcore":
				attempt = player.hardcoreBestAttempt;
				break;
		}

		return attempt;
	}

}
