package org.os;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class touchTester {

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
        new touch(name);
        // Restore original output
        System.setOut(originalOut);
        // Capture the output and check expected results
        return outputStream.toString();
	}
	
	@Test
	public void testCreateSingleFile() {
		String [] command = {"touch", "hihi"};
		String output = captureOutput(command);
		File file = new File("C:\\Users\\AYA\\hihi");
		assertTrue(file.exists());

	}
	
	@Test
	public void testCreateMultFiles() {
		String [] command = {"touch", "ok22","ok222"};
		String output=captureOutput(command);
		File dir1 = new File("ok22");
		File dir2 = new File("ok222");
		assertTrue(output.contains("File created successfully: ")&&output.contains("ok22")&&output.contains("ok222"));
	}
	
	@Test
	public void testExistFile() {
		String [] command = {"touch", "ok2"};
		new touch(command);
		String output = captureOutput(command);
		assertTrue(output.contains("File already exists: "));

		
	}
	
}
