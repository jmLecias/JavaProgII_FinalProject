package finalProjectSystem;

import java.util.Scanner;

public class OptionSelector {
	// Coded by: Ruby Lingo BSIT -AI11

	// Class attributes
	String title;
	String[] options;
	int dividerLength;

	// Class constructor
	public OptionSelector(String title, String[] options, int dividerLength) {
		this.title = title;
		this.options = options;
		this.dividerLength = dividerLength;
	}

	// method for showing and choosing options, returns the chosen option in String
	public String showOptions() {
		String chosenOption = "";

		// Display layout
		Main.divider('=', this.dividerLength);

		Main.centerStart(this.dividerLength, this.title, 0);

		for (int i = 0; i < this.options.length; i++) {
			Main.centerStart(this.dividerLength, ("[" + (i + 1) + "] " + this.options[i]), 16);
		}

		Main.divider('=', this.dividerLength);

		// Selection
		int chosenNumber = Integer.valueOf(Main.getInput(" Enter a number to select an option: ", "^\\d+"));
		if (chosenNumber > 0 && chosenNumber <= this.options.length) {
			chosenOption = this.options[chosenNumber - 1];
		} else {
			System.out.println(" There is no such option");
		}
		return chosenOption;
	}

	// method for getting inputs from specific option, returns input of users in a String[]
	public String[] optionFields(String title, String[] optionFields) {
		Scanner sc = new Scanner(System.in);
		String[] fieldInputs = new String[optionFields.length];

		Main.divider('-', this.dividerLength);

		Main.centerStart(this.dividerLength, title, 0);
		
		for (int i = 0; i < optionFields.length; i++) {
			System.out.print(" " + optionFields[i] + ": ");
			fieldInputs[i] = sc.nextLine();
		}

		Main.divider('-', this.dividerLength);

		return fieldInputs;
	}

}
// end of Option Selector class