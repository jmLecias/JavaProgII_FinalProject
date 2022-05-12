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

	// method for showing and choosing options
	public String showOptions() {
		String chosenOption = "";

		// Display layout
		Main.divider('=', this.dividerLength);

		Main.centerStart(this.dividerLength, this.title.length());
		System.out.println(this.title);
		for (int i = 0; i < this.options.length; i++) {
			Main.centerStart(this.dividerLength, 15);
			System.out.println("[" + (i + 1) + "] " + this.options[i]);
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

	// method for getting inputs from specific option
	public String[] optionFields(String title, String[] optionFields) {
		Scanner sc = new Scanner(System.in);
		String[] fieldInputs = new String[optionFields.length];

		Main.divider('-', this.dividerLength);

		Main.centerStart(this.dividerLength, title.length());
		System.out.println(title);
		for (int i = 0; i < optionFields.length; i++) {
			System.out.print(" " + optionFields[i] + ": ");
			fieldInputs[i] = sc.nextLine();
		}

		Main.divider('-', this.dividerLength);

		return fieldInputs;
	}

}
