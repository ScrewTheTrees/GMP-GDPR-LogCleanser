package com.gmpsystems.logcleaner.Services;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
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
                if (file.isDirectory()) {
                    directories.addAll(getAllDirectoriesInDirectoryAndSubdirectories(file.getAbsolutePath()));
                }
            }
        }
        return directories;
    }


    public void deleteDirectoryRecursively(String directoryToDelete) {
        deleteDirectoryRecursively(new File(directoryToDelete));
    }

    public void deleteDirectoryRecursively(File directoryToDelete) {
        System.out.println("Deleting path: " + directoryToDelete.getAbsolutePath());
        if (directoryToDelete.exists()) {
            if (!directoryToDelete.getPath().equals("")) {
                try {
                    Files.walk(Paths.get(directoryToDelete.getAbsolutePath()))
                            .map(Path::toFile)
                            .sorted((o1, o2) -> -o1.compareTo(o2))
                            .forEach(File::delete);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    void copyFile(File fromFile, File toFile) {
        toFile.getParentFile().mkdirs();
        try (InputStream is = new FileInputStream(fromFile); OutputStream os = new FileOutputStream(toFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyAllFiles(String fromDirectory, String toDirectory) {
        copyAllFiles(new File(fromDirectory), new File(toDirectory));
    }

    public void copyAllFiles(File fromDirectory, File toDirectory) {
        List<File> files = getAllFilesInDirectoryAndSubdirectories(fromDirectory.getAbsolutePath());
        List<File> directories = getAllDirectoriesInDirectoryAndSubdirectories(fromDirectory.getAbsolutePath());

        for (File directory : directories) {
            File newDir = new File(directory.getAbsolutePath().replace(fromDirectory.getAbsolutePath(), toDirectory.getAbsolutePath()));
            newDir.mkdirs();
        }

        for (File file : files) {
            String fileNewName = file.getPath().replace(fromDirectory.getAbsolutePath(), toDirectory.getAbsolutePath());

            copyFile(file, new File(fileNewName));
        }
    }
}
