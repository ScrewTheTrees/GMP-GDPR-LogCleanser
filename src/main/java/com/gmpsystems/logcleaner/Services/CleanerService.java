package com.gmpsystems.logcleaner.Services;

import com.gmpsystems.logcleaner.Config.CleanerCleanseInformation;
import org.apache.commons.validator.routines.EmailValidator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CleanerService {

    //@Inject
    private DirectoryService directoryService = new DirectoryService();

    public boolean cleanAllLogFiles(String fromDirectory, String toDirectory, CleanerCleanseInformation cleanerCleanseInformation) {
        return cleanAllLogFiles(new File(fromDirectory), new File(toDirectory), cleanerCleanseInformation);
    }

    public boolean cleanAllLogFiles(File fromDirectory, File toDirectory, CleanerCleanseInformation cleanerCleanseInformation) {
        List<File> logFiles = directoryService.getAllFilesInDirectoryAndSubdirectories(fromDirectory.getAbsolutePath());
        boolean allFilesExported = true;

        for (File f : logFiles) {

            boolean success = cleanLogFile(f, new File(f.getAbsolutePath().replace(fromDirectory.getPath(), toDirectory.getPath())), cleanerCleanseInformation);
            if (!success) {
                allFilesExported = false;
            }
        }

        return allFilesExported;
    }

    public boolean cleanLogFile(File fileToClean, File fileToSave, CleanerCleanseInformation cleanerCleanseInformation) {
        boolean success = true;
        //TODO: Actually clean the log file

        new File(fileToSave.getParent()).mkdirs();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToClean));
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave));
            String line;
            int lineNum = 0;

            while ((line = br.readLine()) != null) {
                lineNum += 1;
                List<String> emails = getEmailsFromString(line);
                if (emails.size() > 0) {
                    line = handleEmailString(lineNum, line, emails, cleanerCleanseInformation);
                }

                emails.clear();


                bw.write(line + "\n");
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    private String handleEmailString(int lineNum, String line, List<String> emails, CleanerCleanseInformation cleanerCleanseInformation) throws NotImplementedException {

        switch (cleanerCleanseInformation.getCleanerMode()) {
            case NONE:
                throw new NotImplementedException();
            case REMOVE:
                line = handleRemoveCase(line, emails);
                break;
            case ADD:
                throw new NotImplementedException();
            case REPLACE:
                throw new NotImplementedException();
            case MOCK_LOG:
                System.out.println("At line \"" + lineNum + "\", " + emails.size() + " email/s has been found: " + emails.stream().collect(Collectors.joining()));
                break;
        }

        return line;
    }

    private String handleRemoveCase(String line, List<String> emails) {
        for (String s : emails) {
            line = line.replaceAll(s, "");
        }
        return line;
    }


    public List<String> getEmailsFromString(String getEmailString) {
        ArrayList<String> emails = new ArrayList<String>();
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(getEmailString);
        while (m.find()) {
            if (EmailValidator.getInstance().isValid(m.group())) {
                emails.add(m.group());
            }
        }
        return emails;
    }


    public String getFileExtension(String file) throws Exception {
        return getFileExtension(new File(file));
    }

    public String getFileExtension(File file) throws Exception {
        int fileOffset = file.getAbsolutePath().lastIndexOf(".");
        int backslashOffset = file.getAbsolutePath().lastIndexOf("\\");

        if (fileOffset < 0) throw new Exception("No file extension found, no dot found: " + file.getAbsolutePath());
        if (fileOffset < backslashOffset)
            throw new Exception("No file extension found, no dot after last \\: " + file.getAbsolutePath());

        return (file.getPath().substring(fileOffset, file.getPath().length()));
    }
}
