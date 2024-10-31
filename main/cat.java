package org.os;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class cat {
    // cat print content of any file
    //Can take emptyString or file name or list of filename
    Boolean t=true;
    public void parts(String[] paths){

        for (int i=1;i<paths.length; i++) {
            //if not empty
            displayDataOfFile(paths[i]);
            t=true;
        }

    }

    public boolean displayDataOfFile(String path){

        Path fullPath;
        if (Path.of(path).isAbsolute()) {
            // If path is absolute, use it directly
            fullPath = Path.of(path);
        } else {
            // If path is relative, resolve it against currentPath
            fullPath = ChangeDirectory.currentPath.resolve(path);
        }


        MyPath myPath=new MyPath(fullPath.toString());

        if(!myPath.checkpath()) {
            System.out.println("Check Your Path");
            return false;

        }
        File file=fullPath.toFile();

        //folder
        if (file.isDirectory()) {
            // i want file
            System.out.println("The input is a directory, i want file");
            return false;
        }
        else if(!file.exists()){
            //file not exist
            //or file name incorrect
            System.out.println(" error in file name or file not exist");
            return false;


        }

        else if (file.isFile() ) {
            // read from file and print line by line
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                // Check if file has content
                boolean fileHasContent = false;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    //file not empty
                    fileHasContent = true;
                }
                //file is empty
                if (!fileHasContent) {
                    System.out.println("The file '" + fullPath + "' is empty.");
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file.");
                e.printStackTrace();
                return false;

            }

        }
        return true;



    }





//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        cat c=new cat();
//        System.out.println("Enter file paths or names :");
//        String input = scanner.nextLine().trim();
//        String[] paths = input.split("\\s+");
//        c.parts(paths);
//        scanner.close();
//
//    }

}
