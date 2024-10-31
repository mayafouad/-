package org.os;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyPath {
    String path;

    MyPath(String p) {
        this.path = p;
    }

    public Boolean checkpath() {
        if (this.path.length() > 260) {
            return false;
        } else if (!this.path.matches("^[a-zA-Z]:.*")) {
            return false;
        } else {
            Path filePath;
            try {
                filePath = Paths.get(this.path);
            } catch (InvalidPathException var3) {
                return false;
            }

            File parentDir = filePath.toFile().getParentFile();
            return parentDir != null && !parentDir.exists() ? false : true;
        }
    }
}
