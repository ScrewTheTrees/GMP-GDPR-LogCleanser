package dto.gmp.logcleaner.Services;

import dto.gmp.logcleaner.Config.LogCleanerConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogCompressionService {

    private final LogCleanerConfig logCleanerConfig;

    public LogCompressionService(LogCleanerConfig logCleanerConfig) {
        this.logCleanerConfig = logCleanerConfig;
    }

    public void ExtractAllFiles(String fromDirectory, String toDirectory) {
        List<File> files = getAllFilesInDirectory(logCleanerConfig.getLogDirectory());

    }







    private List<File> getAllFilesInDirectory(String directoryName) {
        File directory = new File(directoryName);
        ArrayList<File> files = new ArrayList<>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                files.addAll(getAllFilesInDirectory(file.getAbsolutePath()));
            }
        }
        return files;
    }


}
