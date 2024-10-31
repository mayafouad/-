package org.os;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import org.os.ChangeDirectory;
public class ChangeDirectoryTest {
    private ChangeDirectory changeDirectory;

    @BeforeEach
    public void setUp() {
        changeDirectory = new ChangeDirectory(Paths.get(System.getProperty("user.home")));
    }

    @Test
    public void testChangeToHomeDirectory() {
        changeDirectory.change("cd");
        assertEquals(Paths.get(System.getProperty("user.home")), ChangeDirectory.getCurrentPath());
    }

    @Test
    public void testChangeToParentDirectory() {
        changeDirectory.change("..");
        assertEquals(Paths.get(System.getProperty("user.home")).getParent(), ChangeDirectory.getCurrentPath());
    }

    @Test
    public void testChangeToInvalidDirectory() {
        changeDirectory.change("invalidPath");
        assertNotEquals(Paths.get("invalidPath").toAbsolutePath(), ChangeDirectory.getCurrentPath());
    }
    @Test
    void testChangeTovalidDirectory() {

        Path existingDir = Paths.get(System.getProperty("user.home"), "Documents");
        changeDirectory.change(existingDir.toString());
        assertEquals(existingDir.toAbsolutePath(), ChangeDirectory.getCurrentPath());
    }
}