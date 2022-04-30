package finalProjectSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class LeaderBoards {
	// All added players are located in C: drive
	final String DIRECTORY = "C:/GuessTheNumber_Players";
	final int dividerLength = 62;
	
	String gamemode;
	
	public LeaderBoards(String gamemode) {
		this.gamemode = gamemode;
	}
	
    File directoryPath = new File(DIRECTORY);
    
    File filesList[] = directoryPath.listFiles();     
    
    public void show(int gamemode) throws IOException {
    	Integer[] bestAttempts = new Integer[filesList.length];
    	String[] userNames = new String[filesList.length];
    	
    	for(int i = 0; i < filesList.length; i++) {
    		try {
    			String bestAttempt = 
    					Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(gamemode);
    			String userName = 
    					Files.readAllLines(Paths.get(filesList[i].getAbsolutePath())).get(0);
				
    			bestAttempts[i] = Integer.valueOf(bestAttempt.substring(13, bestAttempt.length()));
				userNames[i] = userName.substring(9, userName.length());
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    	}
    	
    	Arrays.sort(bestAttempts);
    	
    	for(int i = 0; i < filesList.length; i++){
			for(int j = 1; j < (filesList.length - i); j++){
                if(bestAttempts[j-1] < bestAttempts[j]) {  
                	String temp = userNames[j-1];  
                	userNames[j-1] = userNames[j];  
                	userNames[j] = temp;  
                }         
            } 
		}
    	
    	
    	
    	// Shows leader board
    	
    	Main.divider('-', this.dividerLength);
		// content
    	Main.centerStart(this.dividerLength, 12);
    	System.out.println("LEADERBOARDS");
		Main.centerStart(this.dividerLength, this.gamemode.length());
		System.out.println(this.gamemode);
		
		for(int i = 0; i < filesList.length; i++) {
			String playerLine = userNames[i]+"\t- "+bestAttempts[i];
			
			Main.centerStart(this.dividerLength, playerLine.length());
			System.out.println(playerLine);
		}
		// end of content
		Main.divider('-', this.dividerLength);
    	for(int i = 0; i < filesList.length; i++) {
    		
    	}
    	
    }
}
