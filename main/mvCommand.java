package org.os;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class mvCommand {


    public void moveAndRename(String pathFrom,String pathTo)throws IOException {
        Path fullPathFrom,fullPathTo;
        if (Path.of(pathFrom).isAbsolute()) {
            // If path is absolute, use it directly
            fullPathFrom = Path.of(pathFrom);
        } else {
            // If path is relative, resolve it against currentPath
            fullPathFrom = ChangeDirectory.currentPath.resolve(pathFrom);
        }

        if (Path.of(pathTo).isAbsolute()) {
            // If path is absolute, use it directly
            fullPathTo = Path.of(pathTo);
        } else {
            // If path is relative, resolve it against currentPath
            fullPathTo = ChangeDirectory.currentPath.resolve(pathTo);
        }


        File fileFrom=fullPathFrom.toFile();
        File fileTo=fullPathTo.toFile();



        try {
            if (!fileFrom.exists()) {
                System.out.println(" error in file name or file not exist");
            }

            if(fileFrom.isFile()){

                moveFile(fileFrom,fileTo);
            }

            // 2 files is directory
            else if(fileFrom.isDirectory()){
                moveDirectory(fileFrom,fileTo);

            }

        }
        catch (IOException e) {
            System.err.println("An error occurred during the file operation.");
            e.printStackTrace();
        }


    }

    private void moveFile(File fileFrom, File fileTo) throws IOException {
        if (!fileTo.exists()) {
            fileFrom.renameTo(fileTo);
            System.out.println("File renamed successfully.");

        }
        else if (fileTo.isFile()) {
            // Copy content from old file to new file
            try (FileReader reader = new FileReader(fileFrom);
                 FileWriter writer = new FileWriter(fileTo, false)) {

                int character;
                while ((character = reader.read()) != -1) {
                    writer.write(character);
                }
            }

            // Delete the old file after copying
            if (fileFrom.delete()) {
                System.out.println("Content copied to existing file and old file deleted.");
            } else {
                System.out.println("Failed to delete the old file.");
            }
        }
        else if (fileTo.isDirectory()) {
            File newFileLocation = new File(fileTo, fileFrom.getName());
            if (fileFrom.renameTo(newFileLocation)) {
                System.out.println("File moved successfully to directory: " + newFileLocation.getPath());
            } else {
                System.out.println("Failed to move the file to the directory.");
            }
        }
    }
    private void moveDirectory(File fileFrom, File fileTo) throws IOException{

        if(fileTo.isFile()){
            System.out.println(" cannot overwrite non-directory");
        }
        else{
            if(!fileTo.exists()){
                fileFrom.renameTo(fileTo);
                System.out.println("Folder renamed successfully.");
            }
            else{
                File newDirLocation = new File(fileTo, fileFrom.getName());


                //copy content of old file and rename it
                copyDirectory(fileFrom, newDirLocation);

                // delete directory of old file
                deleteDirectory(fileFrom);
                System.out.println("Directory created successfully and Directory moved successfully" );

            }

        }

    }
    private void copyDirectory(File source, File destination) throws IOException {
        if (!destination.exists()) {
            //create new directory
            createDirectory(destination);
        }

        for (File file : source.listFiles()) {
            //move files in old folder to new distenation
            File destFile = new File(destination, file.getName());
            if (!file.exists()) {
                System.out.println("Warning File " + file.getAbsolutePath() + " does not exist, skipping...");
                // if there is not files continue
                continue;
            }
            if (file.isDirectory()) {
                // copy file
                copyDirectory(file, destFile);
            } else {

                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private void deleteDirectory(File directoryy) {
        for (File file : directoryy.listFiles()) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                file.delete();
            }
        }
        directoryy.delete();
    }
    // This fn Create a directory

    private static void createDirectory(File directoryy) {
        Path dirPath = directoryy.toPath();
        try {
            // Create the directory
            Files.createDirectories(dirPath);
        } catch (IOException e) {
            System.out.println("Failed to create directory " + dirPath + ": " + e.getMessage());
        }
    }


//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        mvCommand MV=new mvCommand();
//        System.out.println("Enter file paths or names :");
//        String input = scanner.nextLine().trim();
//        String[] paths = input.split("\\s+");
//        MV.moveAndRename(paths[0],paths[1]);
//        scanner.close();
//
//    }
}

