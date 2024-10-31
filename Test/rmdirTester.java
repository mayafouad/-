package org.os;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class rmdirTester {
	@BeforeEach

	public void setUp() {
		new ChangeDirectory(Paths.get(System.getProperty("user.home")));
	}
	
//	public rmdirTester() {
//		//new ChangeDirectory(Paths.get("."));
//	}
	
	private String captureOutput(String[] name) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Create an instance of LSR
        new rmdir(name);
        // Restore original output
        System.setOut(originalOut);
        // Capture the output and check expected results
        return outputStream.toString();
	}
	
	@Test
	public void testEmptyDirectory() {
		String [] command = {"mkdir", "removeTesting"};
		new mkdir(command);
		command[0] = "rmdir"; 
		String output = captureOutput(command);
		File dir = new File("removeTesting");
		assertTrue(!dir.exists() && output.contains("Removed directory:"));
	}
	
	@Test
	public void testNotEmptyDirectory() {
		String [] command = {"mkdir", "removeTesting1/removeTesting11"};
		new mkdir(command);
		command[0] = "rmdir";
		command[1] = "removeTesting1";
		String output = captureOutput(command);
		File dir = new File("C:\\Users\\AYA\\removeTesting1");
		assertTrue(dir.exists());
		command[1] = "removeTesting11";
		new rmdir(command);
		command[1] = "removeTesting1";
		new rmdir(command);
	}
	
	@Test
	public void testNotDirectory() {
		String [] command = {"touch", "remove50.txt"};
		new touch(command);
		command[0] = "rmdir"; 
		String output = captureOutput(command);
		File dir = new File("C:\\Users\\AYA\\remove50.txt");
		assertTrue(dir.exists());
		dir.delete();
	}
}
