package generator;

import java.io.File;

/**
 * Created by just1ce-PC on 11.05.2016.
 */
public class FolderCleaner
{
    public static void deleteAllFilesFolder(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }
}
