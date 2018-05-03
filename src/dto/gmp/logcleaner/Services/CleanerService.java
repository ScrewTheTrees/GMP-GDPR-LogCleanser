package dto.gmp.logcleaner.Services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanerService {

    //@Inject
    private DirectoryService directoryService = new DirectoryService();


    public boolean cleanAllLogFiles(File fromDirectory, File toDirectory) {
        List<File> logFiles = directoryService.getAllFilesInDirectoryAndSubdirectories(fromDirectory.getAbsolutePath());
        boolean allFilesExported = true;


        for (File f : logFiles) {
            boolean success = cleanLogFile(f, new File(f.getAbsolutePath().replace(fromDirectory.getPath(), toDirectory.getPath())));
            if (!success) {
                allFilesExported = false;
            }
        }


        return allFilesExported;
    }

    public boolean cleanLogFile(File fileToClean, File fileToSave) {
        //TODO:

        return true;
    }


    List<String> getEmailsFromString(String getEmailString) {
        ArrayList<String> emails = new ArrayList<String>();
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(getEmailString);
        while (m.find()) {
            emails.add(m.group());
        }
        return emails;
    }
}
