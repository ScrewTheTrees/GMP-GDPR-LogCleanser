package com.gmpsystems.logcleaner.Services;

import com.gmpsystems.logcleaner.Services.GZip.GZipService;

import java.io.File;
import java.util.List;

public class LogCompressionService {

    //@Inject
    private DirectoryService directoryService = new DirectoryService();
    //@Inject
    private GZipService gZipService = new GZipService();

    public void ExtractAllLogFiles(String fromDirectory, String toDirectory) {
        List<File> files = directoryService.getAllFilesInDirectoryAndSubdirectories(fromDirectory);
        List<File> directories = directoryService.getAllDirectoriesInDirectoryAndSubdirectories(fromDirectory);


        for (File directory : directories) {
            File newDir = new File(directory.getAbsolutePath().replace(fromDirectory, toDirectory));
            newDir.mkdirs();
        }


        for (File file : files) {

            String fileNewName = file.getPath().replace(fromDirectory, toDirectory);
            if (fileNewName.endsWith(".gz")) {
                int fileOffset = fileNewName.lastIndexOf(".");
                fileNewName = fileNewName.substring(0, fileOffset);
            }

            if (file.getAbsolutePath().endsWith(".gz") && fileNewName.endsWith(".tsv")) {
                gZipService.DecompressFile(file, new File(fileNewName));
            } else if (file.getAbsolutePath().endsWith(".tsv")) {
                directoryService.copyFile(file, new File(fileNewName));
            } else {
                System.out.println("File: " + file.getAbsolutePath() + ";   is not a gz or tsv file.");
            }

        }
    }

    public void CompressAllLogFiles(String fromDirectory, String toDirectory) {
        List<File> files = directoryService.getAllFilesInDirectoryAndSubdirectories(fromDirectory);
        List<File> directories = directoryService.getAllDirectoriesInDirectoryAndSubdirectories(fromDirectory);


        for (File directory : directories) {
            File newDir = new File(directory.getAbsolutePath().replace(fromDirectory, toDirectory));
            newDir.mkdirs();
        }

        for (File file : files) {
            String fileNewName = file.getPath().replace(fromDirectory, toDirectory) + ".gz";

            gZipService.CompressFile(file, new File(fileNewName));
        }
    }


}
