package org.os;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Appendtext {
    private String path;
    private String txt;
    public Appendtext(String p,String t){
        path=p;
        txt=t;
    }
    public Boolean appendtexttofile() throws Exception {
        //Check on the format of the path first


        //Check if i can modify on the path file or not

        Path filePath=Paths.get(path);

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

        // Try creating the file only if it doesn't exist
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            //when path not correct
            System.out.println("Error: Unable to create file Check your path ");
           return false;

        }

        // Write content to file


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String content = String.valueOf(txt); // Convert to string
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to the file (Access denied)");
            return false;
        }

        return true;
    }



}
