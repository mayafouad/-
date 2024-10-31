package org.os;
import java.io.File;
import java.util.Arrays;

public class LSR {
	String curr = ChangeDirectory.currentPath.toString();
	public LSR(String [] commandParts) {
		File dir;
		if (commandParts.length > 2) {
            dir = new File(commandParts[2]);
        } 
		else {
            dir = new File(curr);
        }
		if (dir.isDirectory() && dir.exists()) {
        	run(dir);
        } 
        else {
        	System.out.println("Invalid directory: " + dir);
        }
        
    }
	
	private void run(File dir) {
		File[] files = dir.listFiles();
        if (files != null) {
        	// sort files in reverse order based on their names
        	Arrays.sort(files, (f1, f2) -> f2.getName().compareTo(f1.getName()));
            for (File file : files) {
                // check if the file is not hidden
                if (!file.isHidden()) {
                   // Print the name of the file
                   System.out.println(file.getName()); 
                }
            }	
        }
	}
	
}
