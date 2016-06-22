package ua.kiev.unicyb.util;

import java.io.File;

import ua.kiev.unicyb.exception.DeleteFileException;
import ua.kiev.unicyb.exception.EmptyFolderException;

public class FolderCleaner
{
    public static void deleteAllFilesFolder(String path) throws EmptyFolderException, DeleteFileException {
        File[] files = new File(path).listFiles();
        if (files != null) {
            for (File myFile : files) {
                if (myFile.isFile() && !myFile.delete()) {
                    throw new DeleteFileException();
                }
            }
        } else {
            throw new EmptyFolderException();
        }
    }
}
