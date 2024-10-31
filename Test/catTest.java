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


public class catTest {
    private cat catInstance;
    private File testFileWithContent;
    private File testEmptyFile;
    private File testDirectory;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws IOException {
        catInstance = new cat();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));  // Redirect System.out for capturing output

        // Creating temporary files and directory for testing
        testFileWithContent = new File(System.getProperty("user.dir") + "/t1.txt");

        try (FileWriter writer = new FileWriter(testFileWithContent)) {
            writer.write("Iam menna.");
        }

        testEmptyFile = new File(System.getProperty("user.dir") + "/t2.txt");

        testDirectory = new File(System.getProperty("user.dir") + "/menna");

    }

//    @AfterEach
//    public void tearDown() {
//        System.setOut(originalOut);  // Restore original System.out
//        if (testFileWithContent.exists()) testFileWithContent.delete();
//        if (testEmptyFile.exists()) testEmptyFile.delete();
//        if (testDirectory.exists()) testDirectory.delete();
//    }

    private String captureOutput(String paths) {
        outputStream.reset();  // Clear previous output
        catInstance.displayDataOfFile(paths);
        return outputStream.toString().trim();  // Return captured output as trimmed string
    }




    @Test
    public void testDisplayDataOfFileWithContent() {
        //input file with content
        String output = captureOutput(testFileWithContent.getPath());
        assertEquals("Iam menna.", output);
    }

    @Test
    public void testDisplayDataOfEmptyFile() {
        //input file empty
        String output = captureOutput(testEmptyFile.getPath());
        assertEquals("The file '" + testEmptyFile.getPath() + "' is empty.", output);
    }

    @Test
    public void testDisplayDataOfDirectory() {
        //folder
        String output = captureOutput(testDirectory.getPath());
        assertEquals("The input is a directory, i want file", output);
    }

    @Test
    public void testDisplayDataOfNonExistentFile() {
        //not existentfile
        String fakePath = System.getProperty("user.dir") + "/nonExistentFile.txt";
        String output = captureOutput(fakePath);
        assertEquals("error in file name or file not exist", output);
    }
}
