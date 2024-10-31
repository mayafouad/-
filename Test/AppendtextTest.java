package org.os;//package org.os;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.os.Appendtext;
//
//public class AppendtextTest {
//    public AppendtextTest() {
//    }
//
//    @Test
//    public void testPathIsCorrectWithCorrectPath() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        Appendtext appendtext = new Appendtext("C:\\test", "test");
//
//        try {
//            appendtext.appendtexttofile();
//        } catch (IOException var4) {
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        Assertions.assertNotEquals("Check Your Path", outContent.toString().trim());
//    }
//
//    @Test
//
//    public void testIfThereIsProblemToWrite() {
//        //test if file is not writeable
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        Appendtext appendtext = new Appendtext("C:\\test2", "test");
//
//        try {
//            appendtext.appendtexttofile();
//        } catch (Exception var4) {
//        }
//
//        Assertions.assertEquals("Error writing to the file (Access denied)", outContent.toString().trim());
//        System.setOut(System.out);
//    }
//}


import org.junit.jupiter.api.*;
import org.os.Appendtext;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;

public class AppendtextTest {
//    private static final String TEST_DIRECTORY = "testDir";
//    private static final String TEST_FILE = "testFile.txt";
//    private static final String INVALID_PATH = "invalid:/path/testFile.txt";
//    private static final String CONTENT = "Hello, World!";
//
//    private Path testDirPath;
//    private Path testFilePath;
//
//    @BeforeEach
//    public void setup() throws IOException {
//        testDirPath = Paths.get(TEST_DIRECTORY);
//        testFilePath = testDirPath.resolve(TEST_FILE);
//
//        System.out.println(testDirPath);
//        System.out.println(testFilePath);
//
//        // Create test directory if it doesn't exist
//        if (Files.notExists(testDirPath)) {
//            Files.createDirectory(testDirPath);
//        }
//    }
//
//    @AfterEach
//    public void cleanup() throws IOException {
//        // Delete test file and directory after each test
//        if (Files.exists(testFilePath)) {
//            Files.delete(testFilePath);
//        }
//        if (Files.exists(testDirPath)) {
//            Files.delete(testDirPath);
//        }
//    }

    @Test
    public void testAppendTextToFileNotExsistSuccess() throws Exception {
        Appendtext appendText = new Appendtext("c:\\test2\\mine.txt", "test");

        assertTrue(appendText.appendtexttofile());
    }

     @Test
    public void testAppendTextToFile_FileAlreadyExists() throws Exception {
         // The file is already exist
         Appendtext appendText = new Appendtext("c:\\test2\\nice2.txt", "test");
        assertTrue(appendText.appendtexttofile());



    }

    @Test
    public void testAppendTextToFile_InvalidPath() throws Exception {
        Appendtext appendText = new Appendtext("c:\\gg", "yay");
        assertFalse(appendText.appendtexttofile());
    }
}
