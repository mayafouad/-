package org.os;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.os.ListWithHidden;

public class TestListWithHidden {
    private File file;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();



    @Test
    public void testForCorrectPath() throws Exception {
        System.setOut(new PrintStream(this.outContent));
        ListWithHidden list = new ListWithHidden("");
        list.listFiles();
        Assertions.assertNotEquals("Check Your Path", this.outContent.toString().trim());
    }

    @Test
    public void testIfThereIsFileOrFolder() throws Exception {
        System.setOut(new PrintStream(this.outContent));
        ListWithHidden list = new ListWithHidden("c:\\test2\\lllllll.txt");
        list.listFiles();
        Assertions.assertEquals("There is not File or Folder has that name", this.outContent.toString().trim());
    }

    @Test
    public void testIfOutputRightdataIfEnterPathEmpty() throws Exception {
        System.setOut(new PrintStream(this.outContent));
        ListWithHidden list = new ListWithHidden("");
        list.listFiles();
        File currentDir = new File(System.getProperty("user.dir"));
        String[] currentFiles = currentDir.list();
        String output = this.outContent.toString().trim();
        String[] var5 = currentFiles;
        int var6 = currentFiles.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String filename = var5[var7];
            Assertions.assertTrue(output.contains(filename));
        }

    }

    @Test
    public void testIfOutputRightdataIfEnterCorrectPath() throws Exception {
        System.setOut(new PrintStream(this.outContent));
        this.file = Files.createTempDirectory("testDir").toFile();
        (new File(this.file, "visibleFile.txt")).createNewFile();
        (new File(this.file, ".hiddenFile.txt")).createNewFile();
        ListWithHidden list = new ListWithHidden(this.file.getPath());
        list.listFiles();
        Assertions.assertTrue(this.outContent.toString().contains("visibleFile.txt"));
        Assertions.assertTrue(this.outContent.toString().contains(".hiddenFile.txt"));
    }

    @Test
    public void testIfOutputRightdataIfEnterFileName() throws Exception {
        System.setOut(new PrintStream(this.outContent));
        ListWithHidden list = new ListWithHidden("c:\\test2\\bb.txt");
        list.listFiles();
        Assertions.assertEquals(this.outContent.toString().trim(), "bb.txt");
    }

    @Test
    public void testOnSorting() throws IOException {
        System.setOut(new PrintStream(this.outContent));
        this.file = Files.createTempDirectory("testDir").toFile();
        (new File(this.file, "visibleFile.txt")).createNewFile();
        (new File(this.file, "zhiddenFile.txt")).createNewFile();
        ListWithHidden list = new ListWithHidden(this.file.getPath());
        List<String> files = list.getAllFileNames(this.file);
        Assertions.assertEquals("visibleFile.txt", files.get(0));
        Assertions.assertEquals("zhiddenFile.txt", files.get(1));
    }
}
