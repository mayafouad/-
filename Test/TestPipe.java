package org.os;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;

import com.beust.ah.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.os.Pipe;
import org.os.ChangeDirectory;


public class TestPipe {



    @BeforeEach
    public void setUp() {
        new ChangeDirectory(Paths.get(System.getProperty("user.home")));
    }



    @Test
//test pwd with >>
    void testSuccessfulPWDWithAppendAndCorrectPath() throws Exception {

        // Simulated user input for the path
        final ByteArrayInputStream testIn = new ByteArrayInputStream("c:\\test2\\nice.txt\n".getBytes());
        //String userInput = "C:\\test2\\nice.txt\n"; // The path you want to test with

        String com1="pwd";
        String[] commandParts2 = com1.split("\\s+");

        String com2=">> c:\\test2\\nice.txt";
        String[] commandParts3 = com2.split("\\s+");

        // Create an instance of Pipe with commands
        Pipe pipe = new Pipe(commandParts2, commandParts3);


        // Set System.in to simulate user input

        // Call the pipe method and capture its return value
        Boolean k = pipe.pipe();

        // Assert that the return value is true
        Assertions.assertTrue(k);
    }


    @Test
//test pwd with >
    void testSuccessfulPWDCommandOverrideAndCorrectPath() throws Exception {
        // Simulated user input for the path
        final ByteArrayInputStream testIn = new ByteArrayInputStream("c:\\test2\\nice.txt\n".getBytes());
        //String userInput = "C:\\test2\\nice.txt\n"; // The path you want to test with

        String com1="pwd";
        String[] commandParts2 = com1.split("\\s+");

        String com2="> c:\\test2\\nice.txt";
        String[] commandParts3 = com2.split("\\s+");

        // Create an instance of Pipe with commands
        Pipe pipe = new Pipe(commandParts2, commandParts3);

        // Set System.in to simulate user input


        // Call the pipe method and capture its return value
        Boolean k = pipe.pipe();

        // Assert that the return value is true
        Assertions.assertTrue(k);
    }

//test cat with >>
    @Test
    void testSuccessfulCatWithAppendtxtAndAtLeastOneCorrectPath() throws Exception {
        final ByteArrayInputStream testIn = new ByteArrayInputStream("c:\\test2\\ok2.txt c:\\test2\\nice.txt\nc:\\test2\\myfinalmine.txt\n".getBytes());
        //String userInput = "C:\\test2\\nice.txt\n"; // The path you want to test with

        String com1="cat c:\\test2\\ok2.txt c:\\test2\\myfinalmineee.txt ";
        String[] commandParts2 = com1.split("\\s+");

        String com2=">> c:\\test2\\nice.txt";
        String[] commandParts3 = com2.split("\\s+");

        // Create an instance of Pipe with commands
        Pipe pipe = new Pipe(commandParts2, commandParts3);

        // Set System.in to simulate user input


        // Call the pipe method and capture its return value
        Boolean k = pipe.pipe();

        // Assert that the return value is true
        Assertions.assertTrue(k);


    }

// test cat with >
    @Test
    void testSuccessfulCatWithCommandOverrideWithAtleastOnePathIsCorrect() throws Exception {
        final ByteArrayInputStream testIn = new ByteArrayInputStream("c:\\test2\\nice.txt\nc:\\test2\\myfinalmine.txt\n".getBytes());
        //String userInput = "C:\\test2\\nice.txt\n"; // The path you want to test with

        String com1="cat c:\\test2\\bb.txt c:\\test2\\myfinalmine.txt ";
        String[] commandParts2 = com1.split("\\s+");

        String com2="> c:\\test2\\nice.txt";
        String[] commandParts3 = com2.split("\\s+");

        // Create an instance of Pipe with commands
        Pipe pipe = new Pipe(commandParts2, commandParts3);
        // Set System.in to simulate user input


        // Call the pipe method and capture its return value
        Boolean k = pipe.pipe();

        // Assert that the return value is true
        Assertions.assertTrue(k);


    }

// test ls -a with >>
    @Test
    void testSuccessfulLSAWithAppendtxtAndCorrectPath() throws Exception {
       // final ByteArrayInputStream testIn = new ByteArrayInputStream("c:\\test2\nc:\\test2\\myfinalmine.txxxt\n".getBytes());
        //String userInput = "C:\\test2\\nice.txt\n"; // The path you want to test with
        String com1="ls -a";
        String[] commandParts2 = com1.split("\\s+");

       String com2=">> c:\\test2\\ok2.txt";
       String[] commandParts3 = com2.split("\\s+");

        // Create an instance of Pipe with commands
        Pipe pipe = new Pipe(commandParts2, commandParts3);

        // Set System.in to simulate user input


        // Call the pipe method and capture its return value
        Boolean k = pipe.pipe();

        // Assert that the return value is true
        Assertions.assertTrue(k);


    }


    @Test
    void testSuccessfulLSAWithCommandOverrideAndCorrectPath() throws Exception {
       // final ByteArrayInputStream testIn = new ByteArrayInputStream("c:\\test2\nc:\\test2\\myfinalmine.txt\n".getBytes());
        //String userInput = "C:\\test2\\nice.txt\n"; // The path you want to test with

        String com1="ls -a";
        String[] commandParts2 = com1.split("\\s+");

        String com2="> c:\\test2\\ok2.txt";
        String[] commandParts3 = com2.split("\\s+");

        // Create an instance of Pipe with commands
        Pipe pipe = new Pipe(commandParts2, commandParts3);

        // Set System.in to simulate user input


        // Call the pipe method and capture its return value
        Boolean k = pipe.pipe();

        // Assert that the return value is true
        Assertions.assertTrue(k);


    }



}
