package finalProjectSystem;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
	// Coded by: Victor Lopez

	// GuessingGame class attributes
	String mode;
	int maxAttempts;
	int range;

	// Guessing game class constructor
	public GuessingGame(String mode, int maxAttempts, int range) {
		this.mode = mode;
		this.maxAttempts = maxAttempts;
		this.range = range;
	}

	// method for starting the game return guess attempts
	public int play(Player player) {
		Random rand = new Random();
		int numToGuess = rand.nextInt(this.range) + 1;

		int guessAttempts = 0;
		Boolean win = false;
		int guess;

		Main.divider('-', Main.dividerLength);

		Main.centerStart(Main.dividerLength, this.mode, 0);

		System.out.println("\n Guess a number from 1 - " + this.range);
		System.out.println(" Max Attempts: " + this.maxAttempts + "\n");

		for (int i = 1; i <= this.maxAttempts; i++) {
			guess = getGuessInput(" Enter your guess here> ");

			// The Guessing Game Logic

			if (guess > 0 && guess < numToGuess) {
				// when player guess is lower than the number to be guessed
				System.out.println(" You guessed low. Attempts left: " + (this.maxAttempts - i));
				guessAttempts = i;
			} else if (guess > numToGuess && guess <= this.range) {
				// when player guess is higher than the number to be guessed
				System.out.println(" You guessed high. Attempts left: " + (this.maxAttempts - i));
				guessAttempts = i;
			} else if (guess == numToGuess) {
				// when player guessed the right number
				guessAttempts = i;
				System.out.println("\n Congratulations! You guessed the number: " + numToGuess);
				System.out.println(" It only took you " + guessAttempts + " attempts to guess the right number.\n");

				if (guessAttempts < player.getBestAttempt(this.mode)) {
					// when new best attempt is acquired
					System.out.println(" Wow! You've outdid your previous record of " +
							player.getBestAttempt(this.mode) + " attempts.");
					System.out.println(" Your new best record is now " +
							guessAttempts + " attempt(s).");
				} else if (player.getBestAttempt(this.mode) != 0) {
					// when no new best attempt is acquired
					System.out.println(" Your best record is " +
							player.getBestAttempt(this.mode) + " attempt(s).");
				}

				guessAttempts = i;
				win = true;

				Main.divider('-', Main.dividerLength);

				break;
			} else {
				// when guess is not in the range
				System.out.println(" Your guess is out of range, guess the number from 1 to " + this.range);
			}
		}

		if (!win) {
			// when player fails to guess the number and reaches the max number of attempts
			System.out.println("\n Sorry, you've lost. You failed to guess the right number.");
			Main.divider('-', Main.dividerLength);
			return 0;
		}

		return guessAttempts;
	}

	// function for getting guess input
	private static int getGuessInput(String message) {
		Scanner sc = new Scanner(System.in);
		String in;

		int guess = 0;

		System.out.print(message);
		in = sc.nextLine();
		if (in.matches("^\\d+")) {
			guess = Integer.valueOf(in);
		} else {
			System.out.println(" Invalid input: Please enter a number.");
		}
		return guess;
	}
}
// end of Guessing Game class