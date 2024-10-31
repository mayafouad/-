package org.os;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class mkdir {

    String curr = ChangeDirectory.getCurrentPath().toString();

	public mkdir(String[] commandParts) {
		if (commandParts.length > 1) {
			run(commandParts);
        } 
        else {
            System.out.println("missing directory name, Usage: mkdir <directory_name>");
        }
	}
	
	private void run(String[] commandParts) {
		for (int i = 1; i < commandParts.length; i++) {
            String dirPathStr = commandParts[i];
            Path dirPath;
            if (!Path.of(dirPathStr).isAbsolute()) {
                // If path is relative, resolve it against currentPath
                dirPath = ChangeDirectory.currentPath.resolve(Paths.get(dirPathStr));
            }
            else dirPath = Paths.get(curr+dirPathStr);
            try {
            	if(!Files.isDirectory(dirPath)) {
            		// Create the directory 
                    Files.createDirectories(dirPath);
                    System.out.println("Directory created successfully: " + dirPath);
            	}
            	else System.out.println("Existing Directory  " + dirPath);
            } 
            catch (IOException e) {
                System.out.println("Failed to create directory " + dirPath );
            }
        }
	}
}
