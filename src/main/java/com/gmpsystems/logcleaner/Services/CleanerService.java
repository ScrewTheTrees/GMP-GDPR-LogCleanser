package com.gmpsystems.logcleaner.Services;

import org.apache.commons.validator.routines.EmailValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanerService {

    //@Inject
    private DirectoryService directoryService = new DirectoryService();

    public boolean cleanAllLogFiles(String fromDirectory, String toDirectory) {
        return cleanAllLogFiles(new File(fromDirectory), new File(toDirectory));
    }

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
                    System.out.println("At line \"" + lineNum + "\", " + emails.size() + " email/s has been found: " + emails.get(0));
                }

                emails.clear();


                bw.write(line);
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
