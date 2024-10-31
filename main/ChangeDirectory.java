
package org.os;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class ChangeDirectory {
    public static Path currentPath;

    public ChangeDirectory(Path initialPath) {
        this.currentPath = initialPath;
    }


    public void change(String commandParts) {
        commandParts = commandParts.trim();  // Trim any extra whitespace

        try {
            if (commandParts.equals("cd")) {
                // Case 1: No arguments, change to home directory
                currentPath = Paths.get(System.getProperty("user.home"));
                System.out.println("Changed directory to home: " + currentPath);
            } else if (commandParts.equals("..")) {
                // Case 2: Argument is "..", change to parent directory
                Path parentPath = currentPath.getParent();
                if (parentPath != null) {
                    currentPath = parentPath;
                    System.out.println("Changed directory to: " + currentPath);
                } else {
                    System.out.println("Already at the root directory.");
                }
            } else {
                // Case 3: Argument is a full or relative path
                Path newPath = Paths.get(commandParts);

                if (!newPath.isAbsolute()) {
                    // Treat it as a relative path if it's not absolute
                    newPath = currentPath.resolve(newPath).normalize();
                }

                if (newPath.toFile().exists() && newPath.toFile().isDirectory()) {
                    currentPath = newPath.normalize(); // Normalize to remove any . or ..
                    System.out.println("Changed directory to: " + currentPath);
                } else {
                    System.out.println("Error: Directory does not exist: " + newPath);
                }
            }
        } catch (Exception e) {
            System.out.println("Error changing directory: " + e.getMessage());
        }
    }


    public static Path getCurrentPath() {
        return currentPath;
    }
}