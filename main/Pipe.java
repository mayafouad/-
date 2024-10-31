
package org.os;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Pipe {
    Scanner scanner;
    Boolean k;
    private String[] firstCommand;
    private String[] secondCommand;

    public Pipe(String[] f, String[] s) {
        this.firstCommand = f;
        this.secondCommand = s;
        scanner = new Scanner(System.in);
    }





    public Boolean pipe() throws Exception {

        if (!secondCommand[0].equals(">") && !secondCommand[0].equals(">>")) {
            System.out.println("Wrong Check Your Commands");
            return false;
        }
        String combine = firstCommand[0];
        if(firstCommand.length>1&&firstCommand[0].equals("ls")) {
            combine=firstCommand[0]+firstCommand[1];
          //  System.out.println(combine);
        }

        if (!firstCommand[0].equals("pwd") && !combine.equals("ls-a") && !firstCommand[0].equals("cat")) {
            System.out.println("Wrong Check Your Commands ");
            return false;
        }


        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        PrintStream originalOut = System.out;

        System.setOut(originalOut);

        if (firstCommand[0].equals("pwd")) {
            System.setOut(printStream);
            PrintWorkingDirectory pwd = new PrintWorkingDirectory();
            pwd.print();

        }
        else if (combine.equals("ls-a")) {
//            System.out.println("Enter your first path ");
//            String path = scanner.nextLine();
            System.setOut(printStream);
            if(firstCommand.length>2){
                for(int i=2 ; i<firstCommand.length ; i++){
                    ListWithHidden list = new ListWithHidden(firstCommand[i]);
                    k = list.listFiles();
                    if (!k) {
                        System.setOut(originalOut);
                        System.out.println(output.toString());
                        return false; // wrong message
                    }
                }
            }
            else{
                ListWithHidden list=new ListWithHidden(ChangeDirectory.getCurrentPath().toString());
                k = list.listFiles();
                if (!k) {
                    System.setOut(originalOut);
                    System.out.println(output.toString());
                    return false; // wrong message
                }
            }


        }


        else if (firstCommand[0].equals("cat")) { // take path of file and output the content
            cat catt = new cat();
            int numberofcorrect=0;
            for (int i=1 ; i<firstCommand.length ; i++) {
                System.setOut(printStream);
                k = catt.displayDataOfFile(firstCommand[i]);
                if (!k) {
                    System.setOut(originalOut);
                    System.out.println(output.toString());

                }
                else {
                    numberofcorrect++;
                }

            }
            if(numberofcorrect==0) return false;


        }
             else{
        System.out.println("There is Error in the First Command");
        return false;
    }


        System.setOut(originalOut);
        String capturedOutput = output.toString();

        PrintStream originalOut2 = System.out;
        // Step 2: Create a ByteArrayOutputStream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream2 = new PrintStream(outputStream);
        //////////////////////////////////////////////////////////////////////


         if (secondCommand[0].equals(">>")) {
           // System.out.println("Enter your path");
            String path = secondCommand[1];  // Ensure this line is present for reading input
            Appendtext appendtext = new Appendtext(path, capturedOutput);
            System.setOut(printStream2); //Start to take the print
            k = appendtext.appendtexttofile();
            System.setOut(originalOut2); // end taking print
            if (!k) {
                System.out.println(outputStream.toString());
                return false;
            } else return true;


        }
        else if (secondCommand[0].equals(">")) {

            command_override v = new command_override();
            System.setOut(printStream2);
            k = v.override_txt_to_file(secondCommand[1], capturedOutput);
            System.setOut(originalOut2);
            if (!k) {
                System.out.println(outputStream.toString());
                return false;
            }
            return true;


        }
        else {
            System.out.println("Wrong with your commands (Check your commands)");
            return false;
        }



    }
}





