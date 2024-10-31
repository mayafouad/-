
package org.os;

public class Help {
    public Help() {
    }

    public void myprint() {
        System.out.println("pwd\nDisplays the current working directory.\n");
        System.out.println("cd\nChanges the current directory to the specified path.\n");
        System.out.println("ls\nLists all files and directories in the current directory.\n");
        System.out.println("ls -a\nLists all files, including hidden files, in the current directory.\n");
        System.out.println("ls -r\nLists all files in the current directory in reverse order.\n");
        System.out.println("mkdir\nCreates a new directory with the specified name.\n");
        System.out.println("rmdir\nDeletes the specified directory if it is empty.\n");
        System.out.println("touch\nCreates an empty file with the specified name, or updates the timestamp if it already exists.\n");
        System.out.println("mv\nMoves or renames a file or directory to the specified destination.\n");
        System.out.println("rm\nDeletes the specified file.\n");
        System.out.println("cat\nDisplays the contents of the specified file.\n");
        System.out.println(">\nRedirects output to a specified file, overwriting existing content.\n");
        System.out.println(">>\nRedirects output to a specified file, appending to existing content.\n");
        System.out.println("|\nPipes the output of one command as input to another command.\n");
        System.out.println("exit\nExits the command-line simulator.\n");
    }
}
