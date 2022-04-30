package finalProjectSystem;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
	// Coded by: Victor Lopez
	final int dividerLength = 62;

	// GuessingGame class attributes
	String title;
	int maxAttempts;
	int range;

	// Guessing game class constructor
	public GuessingGame(String title, int maxAttempts, int range) {
		this.title = title;
		this.maxAttempts = maxAttempts;
		this.range = range;
	}

	// function for starting the game
	public int start() {
		Random rand = new Random();
		int numToGuess = rand.nextInt(this.range);

		int numOfAttempts = 0;
		int guess;
		Boolean win = false;

		Main.divider('-', this.dividerLength);
		Main.centerStart(this.dividerLength, this.title.length());
		System.out.println(this.title);

		System.out.println("\n Guess a number from 1 - " + this.range);
		System.out.println(" Max Attempts: " + this.maxAttempts + "\n");

		for (int i = 1; i <= this.maxAttempts; i++) {
			guess = getGuessInput(" Enter your guess here> ");

			// Logic
			if (guess > 0 && guess < numToGuess) {
				System.out.println(" You guessed low. Attempts left: " + (this.maxAttempts - i));
				numOfAttempts = i;
			} else if (guess > numToGuess && guess <= this.range) {
				System.out.println(" You guessed high. Attempts left: " + (this.maxAttempts - i));
				numOfAttempts = i;
			} else if (guess == numToGuess) {
				System.out.println("\n Congratulations! You guessed the number: " + numToGuess);
				numOfAttempts = i;
				System.out.println(" It only took you " + numOfAttempts + " attempts to guess the right number.");
				win = true;
				Main.divider('-', this.dividerLength);
				break;
			} else {
				System.out.println(" Your guess is out of range, guess the number from 1 to "
						+ this.range);
			}
		}

		if (!win) {
			System.out.println("\n Sorry, you lost. You failed to guess the right number.");
			Main.divider('-', this.dividerLength);
			return 0;
		}

		return numOfAttempts;
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
