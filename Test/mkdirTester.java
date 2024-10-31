package org.os;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class mkdirTester {
	@BeforeEach
	public void setUp() {
		new ChangeDirectory(Paths.get(System.getProperty("user.home")));
	}
	private String captureOutput(String[] name) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Create an instance of LSR
        new mkdir(name);
        // Restore original output
        System.setOut(originalOut);
        // Capture the output and check expected results
        return outputStream.toString();
	}
	
		@Test
		public void testCreateNewDirectory() {
			String [] command = {"mkdir", "createTest"};
			String output = captureOutput(command);
			File dir = new File("C:\\Users\\AYA\\createTest");
			assertTrue(dir.exists() && dir.isDirectory());
			dir.delete();
		}
		
		@Test 
		public void testCreateNestedDirectories() {
			String [] name = {"mkdir", "TD1/TD2/TD3"};
			String output = captureOutput(name);
			assertTrue(new File("C:\\Users\\AYA\\TD1/TD2/TD3").exists());
			String[] toDelete = {"rmdir", "TD1/TD2/TD3"};
			new rmdir(toDelete);
			toDelete[1] =  "TD1/TD2";
			new rmdir(toDelete);
			toDelete [1] = "TD1";
			new rmdir(toDelete);
		}
		
		@Test
		public void testExistingDirectory() {
			String [] name = {"mkdir", "new5/new55"};
			String output = captureOutput(name);
			assertTrue(output.contains("Existing Directory "));
		}
}
