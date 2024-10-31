package org.os;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class LSR_Tester {


	@BeforeEach
	public void setUp() {
		new ChangeDirectory(Paths.get(System.getProperty("user.home")));
	}



	// Helper method to capture console output
	private String captureOutput(String [] path) {

	//	new ChangeDirectory(Paths.get(System.getProperty("user.home")));
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        // Create an instance of LSR
        new LSR(path);
        // Restore original output
        System.setOut(originalOut);
        // Capture the output and check expected results
        return outputStream.toString();
	}

	
	@Test
	public void testListing() {
		String[] commandParts = {"ls" , "-r"};
	    String output = captureOutput(commandParts);
	    assertTrue(output.contains("ok"));
	    assertTrue(output.contains("ok2"));
	    assertTrue(output.contains("Desktop"));
	}
	
	@Test  // empty output for empty directory
	public void testEmptyDirectory() {
		String[] commandParts = {"ls" , "-r", "C:\\Users\\AYA\\test2"};
	    String output = captureOutput(commandParts);
	    assertEquals(output,"");
	}
	
	@Test  // no output for hidden files
	public void testHiddenFilesExcluded() {
		//System.out.println(ChangeDirectory.getCurrentPath());
		String[] commandParts = {"ls" , "-r", "C:\\Users\\AYA\\test\\hiddedn"};
	    String output = captureOutput(commandParts);
	    assertFalse(output=="");
	}
	
    @Test 
    public void testSortingOrder() {
    	String[] commandParts = {"ls" , "-r"};
        String output = captureOutput(commandParts);
        assertTrue(output.indexOf("ok") < output.indexOf("Desktop"));
    }

    @Test 
    public void testNonExistentDirectory() {
    		String[] commandParts = {"ls" , "-r", "/home/maya/Desktop/OS_A1/OS_assignment/./not"};
        String output = captureOutput(commandParts);
        assertTrue(output.contains("Invalid directory"));
    }
}
