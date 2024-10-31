package org.os;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class command_override{

    //   >
    boolean override_txt_to_file(String path,String txt) throws IOException {
        Path fullPath;
        if (Path.of(path).isAbsolute()) {
            // If path is absolute, use it directly
            fullPath = Path.of(path);
        } else {
            // If path is relative, resolve it against currentPath
            fullPath = ChangeDirectory.currentPath.resolve(path);
        }


        File file=fullPath.toFile();

        try {
            if (file.isDirectory()) {
                // i want file
                System.out.println("Access is denied ,The input is a directory");
                return false;
            }
            else if (file.isFile() ||!file.exists()) {
                if(!file.exists()){
                    //creatnewfile and append txt in here
                    file.createNewFile();
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(txt);
                    }
                    System.out.println("File created and text added.");
                    return true;
                }

                else {
                    // override txt and append new txt
                    try (FileWriter writer = new FileWriter(file, false)) {
                        writer.write(txt);
                    }
                    //print file cleared and
                    // new text added
                    System.out.println("File content cleared and text added.");
                    return true;
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error: The syntax is not correct. Please check the path and permissions.");
            return false;
        }
        return true;
    };

//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        command_override v=new command_override();
//
//
//        System.out.print("Enter the file path or name: ");
//        String inputPath = scanner.nextLine();
//        System.out.print("Enter the text to write into the file: ");
//        String textToWrite = scanner.nextLine();
//
//        v.override_txt_to_file(inputPath,textToWrite);
//    }


};

