package org.os;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;

public class rm {
    // rm
    //remove files

    public void parts(String[] paths){
        for (int i=1;i<paths.length; i++) {
            removeFile(paths[i]);
        }

    }


    public boolean removeFile(String path) {
        Path fullPath;
        if (Path.of(path).isAbsolute()) {
            // If path is absolute, use it directly
            fullPath = Path.of(path);
        } else {
            // If path is relative, resolve it against currentPath
            fullPath = ChangeDirectory.currentPath.resolve(path);
        }


        File file=fullPath.toFile();
        // check if it path of file

        // file wrong or not exist
        if (!file.exists() && !file.isFile()) {
            System.out.println("Please enter file name correct or path of file correct");
            return false;
        }
        //folder
        if (file.isDirectory()) {
            System.out.println("cannot remove "+ file + "Is a directory");
            return false;
        }
        //file name correct
        else if (file.isFile()) {
            //so delete it
            if (file.delete()) {
                System.out.println("File is deleted ");
                return true;
            } else {
                System.out.println("Failed to delete the file");
                return false;
            }
        }


        return false;
    }



}
