package finalProjectSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Player {
	// All added players are located in C: drive
	final String DIRECTORY = "C:/GuessTheNumber_Players";
	
	// Player class attributes
	String userName;
	String password;
	
	int easyBestAttempt;
	int normalBestAttempt;
	int difficultBestAttempt;
	int hardcoreBestAttempt;
	
	// Player class constructor
	public Player (String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	// method for logging in a player
	public String login(String message) throws IOException {
		File directoryPath = new File(DIRECTORY);
	    File filesList[] = directoryPath.listFiles(); 
	    
	    for(int i = 0; i < filesList.length; i++) {
	    	String fileName = Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(0);
	    	String password = Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(1);
	    	
	    	if(this.userName.equals(fileName.substring(10, fileName.length()))) {
	    		if(this.password.equals(password.substring(10, password.length()))) {
	    			String decision = Main.getInput(message, "[y Y n N]");
		    		if(decision.matches("[y Y]")) {
		    			String easyBestAttempt = 
		    					Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(2);
		    			String normalBestAttempt = 
		    					Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(3);
		    			String difficultBestAttempt = 
		    					Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(4);
		    			String hardcoreBestAttempt = 
		    					Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(5);
		    			
		    			this.easyBestAttempt = 
		    					Integer.valueOf(easyBestAttempt.substring(13, easyBestAttempt.length()));
		    			this.normalBestAttempt = 
		    					Integer.valueOf(normalBestAttempt.substring(13, normalBestAttempt.length()));
		    			this.difficultBestAttempt = 
		    					Integer.valueOf(difficultBestAttempt.substring(13, difficultBestAttempt.length()));
		    			this.hardcoreBestAttempt = 
		    					Integer.valueOf(hardcoreBestAttempt.substring(13, hardcoreBestAttempt.length()));
		    			return "Y";
		    		}
		    		else {
		    			return "N";
		    		}
	    		}
	    		else {
	    			System.out.println(" Incorrect password.");
		    		break;
	    		}
	    	}
	    	else {
	    		System.out.println(" Player not found.");
	    		break;
	    	}
	    }
		return null;
	}
	
	// method for creating a file for the player
	public String add(String message) throws IOException {
		File directoryPath = new File(DIRECTORY);
	    File filesList[] = directoryPath.listFiles(); 
	    
	    for(File file: filesList) {
	    	String fileName = file.getName();
	    	if (this.userName.equals(fileName.substring(0, (fileName.length()-4)))) {
	    		System.out.println(" Username is already taken.");
	    		return null;
	    	}
	    }
	    
		String decision = Main.getInput(message, "[y Y n N]");
		if(decision.matches("[y Y]")) {
			File file = new File(this.DIRECTORY);
			file.mkdirs();
			FileWriter fw = new FileWriter(new File(
				this.DIRECTORY,
				this.userName+".txt"
			));
			try {
				fw.write("userName: "+userName);
				fw.write("\nPassword: "+password);
				fw.write("\nEasy       - "+easyBestAttempt);
				fw.write("\nNormal     - "+normalBestAttempt);
				fw.write("\nDifficult  - "+difficultBestAttempt);
				fw.write("\nHardcore   - "+hardcoreBestAttempt);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "Y";
		}
		else {
			return "N";
		}
		
	}
	
	// method for updating a player and its file
	public void update() throws IOException {
		FileWriter fw = new FileWriter(new File(
				this.DIRECTORY,
				this.userName+".txt"
			));
		try {
			fw.write("userName: "+userName);
			fw.write("\nPassword: "+password);
			fw.write("\nEasy       - "+easyBestAttempt);
			fw.write("\nNormal     - "+normalBestAttempt);
			fw.write("\nDifficult  - "+difficultBestAttempt);
			fw.write("\nHardcore   - "+hardcoreBestAttempt);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
