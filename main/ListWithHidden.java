
package org.os;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ListWithHidden {
    private String path;


    public ListWithHidden(String p) {
        this.path = p;
    }

    public boolean listFiles() throws Exception {
        if (this.path != null && !this.path.isEmpty()) {
            MyPath myPath = new MyPath(this.path);

            if (!myPath.checkpath()) {
                System.out.println("Check Your Path");
              return false;

            }

        } else {
            this.path = System.getProperty("user.dir");
        }

        Path fullPath;
        if (Path.of(path).isAbsolute()) {
            // If path is absolute, use it directly
            fullPath = Path.of(path);
        } else {
            // If path is relative, resolve it against currentPath
            fullPath = ChangeDirectory.currentPath.resolve(path);
        }


        File file=fullPath.toFile();
        if (file.isDirectory()) {
            List<String> filenames = this.getAllFileNames(file);
            if (filenames != null) {
                Iterator var3 = filenames.iterator();

                while(var3.hasNext()) {
                    String filename = (String)var3.next();
                    System.out.println(filename);
                }
            }
        } else if (file.isFile()) {
            System.out.println(file.getName());
        } else {
            System.out.println("There is not File or Folder has that name");
            return false;
        }

        return true;

    }

    public List<String> getAllFileNames(File file) {
        List<String> fileNames = new ArrayList();
        File[] files = file.listFiles();
        if (files != null) {
            File[] var4 = files;
            int var5 = files.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                File file1 = var4[var6];
                if (file1.isDirectory() || file1.isFile() || file1.isHidden()) {
                    fileNames.add(file1.getName());
                }
            }
        }

        fileNames.sort((Comparator)null);
        return fileNames;
    }
}
