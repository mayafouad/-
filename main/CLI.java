
package org.os;
import org.os.ListFiles;
import org.os.PrintWorkingDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CLI {

    public static void main(String[] args) throws Exception {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        ChangeDirectory changeDir = new ChangeDirectory(currentPath);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to CLI! Type 'exit' to quit.");

        while (true) {
            System.out.print("--> ");
//            // take input from user
            String input = scanner.nextLine().trim();
            if(input.equals("exit")){
                System.out.println("Exiting CLI!");
                break;
            }
//            // split input to array of words
            String[] commandParts = input.split("\\s+");
//            // command = first word in the array
            String command = commandParts[0].toLowerCase();
//            if ("exit".equals(command)) {
//                System.out.println("Exiting CLI!");
//                break;
//            }
            // choose command action
            switch (command) {

                case "cat":
                    cat catt=new cat();
                    if(commandParts.length==1){
                        Scanner n = new Scanner(System.in);
                        String inputt = n.nextLine().trim();
                        System.out.println(inputt);
                    }
                    else{
                        catt.parts(commandParts);

                    }
                    break;

                case "rm":
                    rm remove = new rm();
                    remove.parts(commandParts);
                    break;

                case "mv":
                    mvCommand MV=new mvCommand();
                    if(commandParts.length==2){
                        System.out.println( "You should enter 2 paths");
                    }
                    else{
                        MV.moveAndRename(commandParts[1],commandParts[2]);
                    }

                    break;


                case ">":
                    command_override v = new command_override();
                    System.out.print("Enter the text to write into the file: ");
                    String textToWrite = scanner.nextLine();
                    System.out.print("Enter the file path or name: ");
                    String inputPath = scanner.nextLine();

                    v.override_txt_to_file(inputPath, textToWrite);

                    break;

                case "help":
                    Help help = new Help();
                    help.myprint();
                    break;


                case "|":
                    System.out.println("Enter your first command :");
                    String com1 = scanner.nextLine();
                    String[] commandParts2 = com1.split("\\s+");

                    System.out.println("Enter your second command :");
                    String com2 = scanner.nextLine();
                    String[] commandParts3 = com2.split("\\s+");

                    Pipe pipe = new Pipe(commandParts2, commandParts3);
                    pipe.pipe();
                    break;


                case ">>":
                    System.out.println("Enter your text :");
                    String txt = scanner.nextLine();
                    System.out.println("Enter your path :");
                    String path = scanner.nextLine();
                    Appendtext appendtext = new Appendtext(path, txt);
                    appendtext.appendtexttofile();
                    break;

                case "ls":
                    if (commandParts.length > 1) {

                        if (commandParts[1].equals("-a")) {

                            path = commandParts.length > 2 ? commandParts[2] : ChangeDirectory.getCurrentPath().toString();


                            ListWithHidden list = new ListWithHidden(path);
                            list.listFiles();

                        } else if (commandParts.length > 1 && "-r".equals(commandParts[1])) {
                            // call LSR with the current directory
                            LSR lsr = new LSR(commandParts);
                            //some code here
                        }

                        else{
                            ListFiles listFiles = new ListFiles();
                            // إذا لم يتم تحديد مسار، استخدم currentPath من ChangeDirectory
                            String Path = commandParts.length > 1 ? commandParts[1] : changeDir.getCurrentPath().toString();
                            listFiles.list(Path);

                        }

                    } else {

                        ListFiles listFiles = new ListFiles();
                        // إذا لم يتم تحديد مسار، استخدم currentPath من ChangeDirectory
                        String Path = commandParts.length > 1 ? commandParts[1] : changeDir.getCurrentPath().toString();
                        listFiles.list(Path);
                    }

                    break;
                //////////////////////////////////////////////////////
                case "mkdir":

                    mkdir mdir = new mkdir(commandParts);

                    break;
                case "rmdir":

                    rmdir rdir = new rmdir(commandParts);

                    break;


                case "touch":

                    touch t = new touch(commandParts);

                    break;


                case "cd":
                    changeDir.change(commandParts.length > 1 ? commandParts[1] : "cd");
                    currentPath = changeDir.getCurrentPath(); // Update current path
                    break;

                case "pwd":
                    PrintWorkingDirectory pwd = new PrintWorkingDirectory();
                    pwd.print();
                    break;

                default:
                    System.out.println("Unknown command: " + command);
            }
        }


    }
    /////////////////////////////////////////////////////////

    // Command ls -r : list files and directories recursively
}