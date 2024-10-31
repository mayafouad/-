package org.os;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.os.ChangeDirectory;
import org.os.ListFiles;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
public class ListFilesTest {
    private ListFiles listFiles;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        listFiles = new ListFiles();
        new ChangeDirectory(Paths.get(System.getProperty("user.home")));
    }

    @Test
    public void testListFilesInCurrentDirectory() {
        listFiles.list(null);
        assertFalse(outputStreamCaptor.toString().trim().isEmpty());
    }

    @Test
    public void testListFilesInSpecificDirectory() {
        String testPath = System.getProperty("user.home");
        listFiles.list(testPath);
        assertTrue(outputStreamCaptor.toString().contains("Visible contents in"));
    }

    @Test
    public void testListFilesInvalidPath() {
        listFiles.list("invalidPath");
        assertTrue(outputStreamCaptor.toString().contains("Error: Directory does not exist or is not a directory."));
    }
    @Test
    public void testFilesAreSorted() {
        listFiles.list(null);  // Call the function without passing a path to use the current path
        // Extract file names from the output and store them in a list
        List<String> fileNames = Arrays.stream(outputStreamCaptor.toString().split("\n"))
                .skip(1) // Skip title line
                .collect(Collectors.toList());

        // Create an alphabetized version of the original list.
        List<String> sortedFileNames = fileNames.stream().sorted(String::compareToIgnoreCase).collect(Collectors.toList());
// Ensure that the original list is indeed alphabetically sorted.
        assertEquals(sortedFileNames, fileNames);
    }
}

