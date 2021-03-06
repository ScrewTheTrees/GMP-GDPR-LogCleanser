package com.gmpsystems.logcleaner.Services;

import com.gmpsystems.logcleaner.Config.CleanerCleanseInformation;
import com.gmpsystems.logcleaner.Config.CleanerDatabaseUnit;
import com.gmpsystems.logcleaner.Config.CleanerFieldType;
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

        new File(fileToSave.getParent()).mkdirs();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToClean));
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave));
            String line;
            int lineNum = 0;

            System.out.println("Doing file: " + fileToClean.getName());
            while ((line = br.readLine()) != null) {
                lineNum += 1;
                line = handleLine(cleanerCleanseInformation, line, lineNum, fileToClean.getName());

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

    String handleLine(CleanerCleanseInformation cleanerCleanseInformation, String line, int lineNum, String fileName) {
        if (cleanerCleanseInformation.getFieldType() == CleanerFieldType.EMAIL) {
            List<String> emails = getEmailsFromString(line);

            if (emails.size() > 0) {
                line = handleEmailString(lineNum, line, emails, cleanerCleanseInformation, fileName);
            }
        } else if (cleanerCleanseInformation.getFieldType() == CleanerFieldType.WORD) {
            line = handleWordString(lineNum, line, cleanerCleanseInformation, fileName);
        }
        return line;
    }


    String handleEmailString(int lineNum, String line, List<String> strings, CleanerCleanseInformation cleanerCleanseInformation, String fileName) throws NotImplementedException {

        switch (cleanerCleanseInformation.getCleanerFieldMode()) {
            case NONE:
                break; //Do nothing, as intended
            case REMOVE:
                line = handleRemoveCase(line, cleanerCleanseInformation, strings, lineNum, fileName);
                break;
            case ADD:
                throw new NotImplementedException();
            case REPLACE:
                line = handleReplaceCase(line, cleanerCleanseInformation, strings, lineNum, fileName);
                break;
            case MOCK_LOG:
                System.out.println("In \"" + fileName + "\"  At line \"" + lineNum + "\", " + strings.size() + " email/s has been found: " + strings.stream().collect(Collectors.joining()));
                break;
        }

        return line;
    }

    String handleWordString(int lineNum, String line, CleanerCleanseInformation cleanerCleanseInformation, String fileName) throws NotImplementedException {
        ArrayList<String> strings = new ArrayList<>();

        switch (cleanerCleanseInformation.getCleanerFieldMode()) {
            case NONE:
                break; //Do nothing, as intended
            case REMOVE:
                line = handleRemoveCase(line, cleanerCleanseInformation, strings, lineNum, fileName);
                break;
            case ADD:
                throw new NotImplementedException();
            case REPLACE:
                line = handleReplaceCase(line, cleanerCleanseInformation, strings, lineNum, fileName);
                break;
            case MOCK_LOG:
                System.out.println("In \"" + fileName + "\"  Processed Line: " + lineNum);
                break;
        }

        return line;
    }

    private String handleRemoveCase(String line, CleanerCleanseInformation cleanerCleanseInformation, List<String> strings, int lineNum, String fileName) {
        for (CleanerDatabaseUnit user : cleanerCleanseInformation.getUsers()) {
            line = line.replaceAll(user.ReplaceFrom, "");
        }
        if (cleanerCleanseInformation.isEmailReplaceUndefinedEmails() && cleanerCleanseInformation.getFieldType() == CleanerFieldType.EMAIL) {
            for (String s : strings) {
                if (line.contains(s)) {
                    line = line.replaceAll(s, cleanerCleanseInformation.getEmailReplaceUndefinedString());
                    System.out.println("In \"" + fileName + "\"  At line \"" + lineNum + "\", the email \"" + s + "\" was removed as it could not be found in the database.");
                }
            }
        }
        return line;
    }

    private String handleReplaceCase(String line, CleanerCleanseInformation cleanerCleanseInformation, List<String> strings, int lineNum, String fileName) {
        for (CleanerDatabaseUnit user : cleanerCleanseInformation.getUsers()) {
            line = line.replaceAll(user.ReplaceFrom, user.ReplaceTo);
        }
        for (String s : strings) {
            if (line.contains(s)) {
                if (cleanerCleanseInformation.isEmailReplaceUndefinedEmails() && cleanerCleanseInformation.getFieldType() == CleanerFieldType.EMAIL) {
                    line = line.replaceAll(s, cleanerCleanseInformation.getEmailReplaceUndefinedString());
                    System.out.println("In \"" + fileName + "\"  At line \"" + lineNum + "\", the email \"" + s + "\" was replaced with default as it could not be found in the database.");
                } else {
                    System.out.println("In \"" + fileName + "\"  At line \"" + lineNum + "\", the string \"" + s + "\" was not replaced, could not be found in the database.");
                }
            }
        }
        return line;
    }


    List<String> getEmailsFromString(String getEmailString) {
        ArrayList<String> emails = new ArrayList<>();
        Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(getEmailString);
        while (m.find()) {
            if (EmailValidator.getInstance().isValid(m.group())) {
                emails.add(m.group());
            }
        }
        return emails;
    }

    List<String> getWordsFromString(String getWordString) {
        ArrayList<String> emails = new ArrayList<>();
        Matcher m = Pattern.compile("[a-zA-Z0-9]+").matcher(getWordString);
        while (m.find()) {
            emails.add(m.group());
        }
        return emails;
    }


    String getFileExtension(String file) throws Exception {
        return getFileExtension(new File(file));
    }

    private String getFileExtension(File file) throws Exception {
        int fileOffset = file.getAbsolutePath().lastIndexOf(".");
        int backslashOffset = file.getAbsolutePath().lastIndexOf("\\");

        if (fileOffset < 0) throw new Exception("No file extension found, no dot found: " + file.getAbsolutePath());
        if (fileOffset < backslashOffset)
            throw new Exception("No file extension found, no dot after last \\: " + file.getAbsolutePath());

        return (file.getPath().substring(fileOffset, file.getPath().length()));
    }
}
