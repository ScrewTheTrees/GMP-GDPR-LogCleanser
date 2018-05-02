package dto.gmp.logcleaner.Services;

import dto.gmp.logcleaner.Config.LogCleanerConfig;
import dto.gmp.logcleaner.Services.GZip.GZipService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogCompressionService {

    private final LogCleanerConfig logCleanerConfig;

    public LogCompressionService(LogCleanerConfig logCleanerConfig) {
        this.logCleanerConfig = logCleanerConfig;
    }

    public void ExtractAllFiles(String fromDirectory, String toDirectory) {
        List<File> files = getAllFilesInDirectory(fromDirectory);
        GZipService gZipService = new GZipService();

        for (File file : files) {
            String directoryPath = file.getAbsolutePath().replace(fromDirectory, toDirectory);
            directoryPath = directoryPath.substring(0, directoryPath.lastIndexOf("\\"));

            int fileOffset = file.getAbsolutePath().replace(fromDirectory, toDirectory).lastIndexOf(".");
            String fileNewName = file.getAbsolutePath().replace(fromDirectory, toDirectory).substring(0, fileOffset) + ".txt";

            new File(directoryPath).mkdirs();
            gZipService.DecompressFile(file, new File(fileNewName));
        }
    }


    private List<File> getAllFilesInDirectory(String directoryName) {
        File directory = new File(directoryName);
        ArrayList<File> files = new ArrayList<>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    files.addAll(getAllFilesInDirectory(file.getAbsolutePath()));
                }
            }
        }
        return files;
    }


}
