package org.os;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.os.PrintWorkingDirectory;
import org.os.ChangeDirectory;
import static org.junit.jupiter.api.Assertions.*;

public class PrintWorkingDirectoryTest {
    private PrintWorkingDirectory pwd;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        pwd = new PrintWorkingDirectory();
        new ChangeDirectory(Paths.get(System.getProperty("user.home")));
    }

    @Test
    public void testPrintWorkingDirectory() {
        pwd.print();
        assertTrue(outputStreamCaptor.toString().trim().contains(System.getProperty("user.home")));
    }
}
