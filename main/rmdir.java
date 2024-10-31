package org.os;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class rmdir {
	Path path = ChangeDirectory.currentPath;
	public rmdir(String[] commandParts) {
		if (commandParts.length > 1) {
			run(commandParts);
        } 
		else {
            System.out.println("Usage: rmdir <directory_name>");
        }
	}
	
	private void run(String[] commandParts) {
        String arg = commandParts[1];
        if (arg.equals("*")) {
            // Remove all empty directories in the current directory
            try {
            	// lists all entries in the current directory, filter : only the directories, then loop over them.
                Files.list(path).filter(Files::isDirectory).forEach(path -> {
                     try {
                    	 // deleting each directory
                        Files.delete(path);
                        System.out.println("Removed empty directory: " + path);
                     } 
                     catch (IOException e) {
                         System.out.println("Failed to remove directory: " + path);
                     }
               });
            } catch (IOException e) {
                System.out.println("Error listing current directory: " + e.getMessage());
            }
        } 
        else {
            // Remove the specified directory if it is empty
            Path dirPath = Paths.get(arg);
            if (!dirPath.isAbsolute()) {
                // If path is relative, resolve it against currentPath
                dirPath = ChangeDirectory.currentPath.resolve(Paths.get(arg));
            }
            if(Files.isDirectory(dirPath)) {
	            try {
	                Files.delete(dirPath);
	                System.out.println("Removed directory: " + dirPath);
	            } 
	            catch (DirectoryNotEmptyException e) {
	                System.out.println("Failed to remove directory " + dirPath + ": Directory is not empty.");
	            } 
	            catch (IOException e) {
	                System.out.println("Failed to remove directory " + dirPath + ": " + e.getMessage());
	            }
            }
            else {
            	System.out.println("Not Directory: " + dirPath);
            }
        }
	}
}
