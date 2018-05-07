package com.gmpsystems.logcleaner.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryService {

    
    public List<File> getAllFilesInDirectoryAndSubdirectories(String directoryName) {
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

    public List<File> getAllDirectoriesInDirectoryAndSubdirectories(String directoryName) {
        File directory = new File(directoryName);
        ArrayList<File> directories = new ArrayList<>();
        directories.add(new File(directoryName));

        // get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    //Ignore
                } else if (file.isDirectory()) {
                    directories.addAll(getAllDirectoriesInDirectoryAndSubdirectories(file.getAbsolutePath()));
                }
            }
        }
        return directories;
    }


    public void deleteDirectoryRecursively(String directoryToDelete) throws IOException {
        deleteDirectoryRecursively(new File(directoryToDelete));
    }

    public void deleteDirectoryRecursively(File directoryToDelete) throws IOException {
         Files.walk(Paths.get(directoryToDelete.getAbsolutePath()))
                .map(Path::toFile)
                .sorted((o1, o2) -> -o1.compareTo(o2))
                .forEach(File::delete);
    }
}
