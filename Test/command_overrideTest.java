package org.os;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class command_overrideTest {
    private command_override command_overrideInstance;
    private File testFileWithContent;
    private File testEmptyFile;
    private File testDirectory;
    private String txt;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws IOException {
        //create object
        command_overrideInstance = new command_override();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));  // Redirect System.out for capturing output

        // Creating  files  for testing
        testFileWithContent = new File(System.getProperty("user.dir") + "/testFile1.txt");

        try (FileWriter writer = new FileWriter(testFileWithContent)) {
            writer.write("File content 1.");
        }
        testEmptyFile = new File(System.getProperty("user.dir") + "/testFile2.txt");

        // Creating  directory for testing
        testDirectory = new File(System.getProperty("user.dir") + "/kkkkk");
        testDirectory.deleteOnExit();

        txt="This is a file";
    }



    private String captureOutput(String paths,String txt) throws IOException {
        outputStream.reset();  // Clear previous output
        command_overrideInstance.override_txt_to_file(paths,txt);
        return outputStream.toString().trim();  // Return captured output as trimmed string
    }



    @Test
    public void testOverrideFileWithContent() throws IOException {
        String output = captureOutput(testFileWithContent.getPath(), txt);
        assertEquals("File content cleared and text added.", output);
        assertTrue(testFileWithContent.exists(), "File should exist after overriding content.");
    }

    @Test
    public void testOverrideFileEmpty() throws IOException {
        //input file empty
        String output = captureOutput(testEmptyFile.getPath(),txt);
        assertEquals("File content cleared and text added.", output);
        assertTrue(testFileWithContent.exists(), "File should exist after overriding content.");
    }

    @Test
    public void testOverrideDirectory() throws IOException {
        String output = captureOutput(testDirectory.getPath(),txt);
        assertEquals("Access is denied ,The input is a directory", output);
    }

    @Test
    public void testOverrideNonExistentFile() throws IOException {
        String fakePath = System.getProperty("user.dir") + "/nonExistentFile.txt";
        String output = captureOutput(fakePath, txt);
        assertEquals("File created and text added.", output);
        assertTrue(new File(fakePath).exists(), "Non-existent file should be created.");
    }

}