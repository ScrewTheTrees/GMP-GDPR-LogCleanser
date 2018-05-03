package dto.gmp.logcleaner.Services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryService {

    
    List<File> getAllFilesInDirectoryAndSubdirectories(String directoryName) {
        File directory = new File(directoryName);
        ArrayList<File> files = new ArrayList<>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    files.addAll(getAllFilesInDirectoryAndSubdirectories(file.getAbsolutePath()));
                }
            }
        }
        return files;
    }

    List<File> getAllDirectoriesInDirectoryAndSubdirectories(String directoryName) {
        File directory = new File(directoryName);
        ArrayList<File> directories = new ArrayList<>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    //Ignore
                } else if (file.isDirectory()) {
                    directories.add(file);
                    directories.addAll(getAllDirectoriesInDirectoryAndSubdirectories(file.getAbsolutePath()));
                }
            }
        }
        return directories;
    }
}
