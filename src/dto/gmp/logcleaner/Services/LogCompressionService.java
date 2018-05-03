package dto.gmp.logcleaner.Services;

import dto.gmp.logcleaner.Services.GZip.GZipService;

import java.io.File;
import java.util.List;

public class LogCompressionService {

    //@Inject
    private DirectoryService directoryService = new DirectoryService();
    //@Inject
    private GZipService gZipService = new GZipService();

    public void ExtractAllFiles(String fromDirectory, String toDirectory) {
        List<File> files = directoryService.getAllFilesInDirectoryAndSubdirectories(fromDirectory);
        List<File> directories = directoryService.getAllDirectoriesInDirectoryAndSubdirectories(fromDirectory);


        for (File directory : directories) {
            File newDir = new File(directory.getAbsolutePath().replace(fromDirectory, toDirectory));
            newDir.mkdirs();
        }

        for (File file : files) {
            int fileOffset = file.getAbsolutePath().replace(fromDirectory, toDirectory).lastIndexOf(".");
            String fileNewName = file.getPath().replace(fromDirectory, toDirectory).substring(0, fileOffset) + ".txt";

            gZipService.DecompressFile(file, new File(fileNewName));
        }
    }


}
