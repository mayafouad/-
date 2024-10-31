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

class mvCommandTest {

    private mvCommand mvCommandInstance;
    private File testFileWithContent;
    private File testFileWithContent2;
    private File testFileWithContent3;
    private File testFileWithContent4;

    private File testEmptyFile;

    private File testDirectory;
    private File testDirectory2;

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws IOException {
        mvCommandInstance = new mvCommand();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));  // Redirect System.out for capturing output

        // Creating temporary files and directory for testing
        testFileWithContent = new File(System.getProperty("user.dir") + "/t1.txt");
        testFileWithContent2 = new File(System.getProperty("user.dir") + "/t2.txt");
        testFileWithContent3 = new File(System.getProperty("user.dir") + "/t3.txt");
        testFileWithContent4 = new File(System.getProperty("user.dir") + "/t4.txt");

        try (FileWriter writer = new FileWriter(testFileWithContent)) {
            writer.write("Iam menna.");
        }
        try (FileWriter writer = new FileWriter(testFileWithContent2)) {
            writer.write("file 2.");
        }

        testEmptyFile = new File(System.getProperty("user.dir") + "/testFileEmpty.txt");

        testDirectory = new File(System.getProperty("user.dir") + "/kkkkk");
        testDirectory.deleteOnExit();

        testDirectory2 = new File(System.getProperty("user.dir") + "/menna");
        testDirectory2.deleteOnExit();

    }



    private String captureOutput(String paths,String paths2) throws IOException {
        outputStream.reset();  // Clear previous output
        mvCommandInstance.moveAndRename(paths,paths2);
        return outputStream.toString().trim();  // Return captured output as trimmed string
    }

//    error in file name or file not exist
    @Test
    public void testMoveFileNotExistInToFile() throws IOException {
        // file not exist-> file
        String fakePath = System.getProperty("user.dir") + "/nonExistent.txt";

        String output = captureOutput(fakePath, testFileWithContent2.getPath());
        assertEquals("error in file name or file not exist", output);
    }
//------------------------------------------------------------------------------------------------------------------


    @Test
    public void testMoveFileExistInToFileExist() throws IOException {
        // file-> file
        String output = captureOutput(testFileWithContent.getPath(), testFileWithContent2.getPath());
        assertEquals("Content copied to existing file and old file deleted.", output);
    }
//------------------------------------------------------------------------------------------------------------------

    @Test
    public void testMoveEmptyFileExistInToFileExist() throws IOException {
        //file empty-> file
        String output = captureOutput(testEmptyFile.getPath(), testFileWithContent2.getPath());
        assertEquals("Content copied to existing file and old file deleted.", output);
    }
    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void testMoveFileExistInToFileNotExist() throws IOException {
        // file->filenot exist
        String fakePath = System.getProperty("user.dir") + "/nonExistentFilee.txt";

        String output = captureOutput(testFileWithContent4.getPath(), fakePath);
        assertEquals("File renamed successfully.", output);
    }
//------------------------------------------------------------------------------------------------------------------

    @Test
    public void testMoveFileExistInToDirectory() throws IOException {
        //file->folder

        String output = captureOutput(testFileWithContent.getPath(), testDirectory.getPath());
        assertEquals("File moved successfully to directory: "+testDirectory.getPath()+"\\"+testFileWithContent.getName(), output);
    }
//------------------------------------------------------------------------------------------------------------------

    @Test
    public void testMoveFolderExistInToFile() throws IOException {
        //folder->file

        String output = captureOutput(testDirectory.getPath(), testFileWithContent.getPath());
        assertEquals("cannot overwrite non-directory",output);
    }
//------------------------------------------------------------------------------------------------------------------

    @Test
    public void testMoveFolderExistInToFolderNotExist() throws IOException {
        // folder->folder not exist
        String fakePath = System.getProperty("user.dir") + "/nonExistentFile";

        String output = captureOutput(testDirectory2.getPath(), fakePath);
        assertEquals("Folder renamed successfully.", output);
    }
//------------------------------------------------------------------------------------------------------------------

    @Test
    public void testMoveFolderExistInToDirectoryExist() throws IOException {
        //folder->folder

        String output = captureOutput(testDirectory.getPath(), testDirectory2.getPath());
        assertEquals("Directory created successfully and Directory moved successfully", output);
    }
//------------------------------------------------------------------------------------------------------------------





}