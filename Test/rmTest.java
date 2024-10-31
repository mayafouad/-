package org.os;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class rmTest {
    private rm remover;
    private File testFile1;
    private File testFile2;
    private File testFile3;
    private File testFile4;
    private File testDir;

    @BeforeEach
    public void setUp() throws IOException {
        remover = new rm();

        testFile1=new File(System.getProperty("user.dir") + "/rm1.txt");

        testFile2=new File(System.getProperty("user.dir") + "/rm2.txt");
        testFile3=new File(System.getProperty("user.dir") + "/rm3.txt");
        testFile4=new File(System.getProperty("user.dir") + "/rm4.txt");
        // Ensure deletion on exit for cleanup



        testDir = new File(System.getProperty("user.dir") + "/dirnew");
        testDir.deleteOnExit();
    }
//     @AfterEach
//        public void tearDown() {
//            // Ensure test cleanup
//            if (testFile1.exists()) testFile1.delete();
//            if (testFile2.exists()) testFile2.delete();
//            if (testDir.exists()) testDir.delete();
//        }

        @Test
        public void testRemoveFileSuccessfully() {
            assertTrue(testFile4.exists(), "File should exist before deletion");
            assertTrue(remover.removeFile(testFile4.getPath()), "File deletion should return true");
            assertFalse(testFile4.exists(), "File should not exist after deletion");
        }
//------------------------------------------------------------------------------------------------------

    @Test
    public void testRemoveMoreFileSuccessfully() {
        assertTrue(testFile2.exists(), "File should exist before deletion");
        assertTrue(testFile3.exists(), "File should exist before deletion");
        assertTrue(remover.removeFile(testFile2.getPath()), "File deletion should return true");
        assertTrue(remover.removeFile(testFile3.getPath()), "File deletion should return true");
        assertFalse(testFile2.exists(), "File should not exist after deletion");
        assertFalse(testFile3.exists(), "File should not exist after deletion");

    }
//------------------------------------------------------------------------------------------------------

    @Test
    public void testRemoveNonExistentFile() {
        String fakePath = System.getProperty("user.dir") + "/rm66.txt";
        assertFalse(remover.removeFile(fakePath), "Deleting non-existent file should return false");
    }
//------------------------------------------------------------------------------------------------------

    @Test
    public void testRemoveNonExistentFileAndExistent() {
        String fakePath = System.getProperty("user.dir") + "/rm66.txt";
        assertTrue(remover.removeFile(testFile1.getPath()), "File deletion should return true");
        assertFalse(remover.removeFile(fakePath), "Deleting non-existent file should return false");
    }
//------------------------------------------------------------------------------------------------------

    @Test
    public void testRemoveDirectory() {
        assertTrue(testDir.exists(), "Directory should be exist");
        assertFalse(remover.removeFile(testDir.getPath()), "Directory deletion should return false");
        assertTrue(testDir.exists(), "Directory should still exist after failed deletion");
    }


//------------------------------------------------------------------------------------------------------


}