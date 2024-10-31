package org.os;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class touch {
	String curr = ChangeDirectory.getCurrentPath().toString();
	public touch(String[] commandParts) {
		if (commandParts.length > 1) {
			run(commandParts);
        } 
        else {
            System.out.println("Usage: touch <file_name>");
        }
	}
	
	private void run(String[] commandParts) {
		for(int i=1; i<commandParts.length; i++) {
			String filePathStr = commandParts[i];
            Path filePath;
            if (!Path.of(filePathStr).isAbsolute()) {
                // If path is relative, resolve it against currentPath
                filePath = ChangeDirectory.currentPath.resolve(Paths.get(filePathStr));
            }
            else filePath = Paths.get(curr+filePathStr);
	        try {
	            // create the file, if it doesn't exist
	            if (!Files.exists(filePath)) {
	                Files.createFile(filePath);
	                System.out.println("File created successfully: " + filePath);
	            } 
	            else {
	                System.out.println("File already exists: " + filePath);
	            }
	        } 
	        catch (IOException e) {
	            System.out.println("Failed to create file " + filePath + ": " + e.getMessage());
	        }
		}
        
	}
}
