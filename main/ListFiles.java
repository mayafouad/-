package org.os;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.nio.file.Paths;

public class ListFiles {
    // Method to list and sort all contents in the specified directory, excluding hidden files and folders
    public void list(String path) {
        Path currentPath = (path == null || path.isEmpty())
                ? ChangeDirectory.getCurrentPath()
                : Paths.get(path).toAbsolutePath();

        File directory = new File(currentPath.toString());

        // Check if the directory exists
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Error: Directory does not exist or is not a directory.");
            return;
        }

        File[] contents = directory.listFiles(); // Get the list of files and directories

        if (contents != null) {
            // Filter to exclude hidden files and directories, and sort the remaining files and directories
            File[] visibleContents = Arrays.stream(contents)
                    .filter(file -> !file.isHidden())
                    .toArray(File[]::new);
            Arrays.sort(visibleContents, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));

            System.out.println("Visible contents in " + currentPath + ":");
            for (File file : visibleContents) { // Iterate and print each file and folder name
                System.out.println(file.getName() + (file.isDirectory() ? " (Directory)" : ""));
            }
        } else {
            System.out.println("Error: Could not list directory contents.");
        }
    }
}