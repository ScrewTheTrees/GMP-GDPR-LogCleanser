package com.gmpsystems.logcleaner;

import com.gmpsystems.logcleaner.Config.*;
import com.gmpsystems.logcleaner.Repository.DatabaseRepository;
import com.gmpsystems.logcleaner.Repository.MongoConnection;
import com.gmpsystems.logcleaner.Services.CleanerService;
import com.gmpsystems.logcleaner.Services.DirectoryService;
import com.gmpsystems.logcleaner.Services.LogCompressionService;

import java.io.File;

public class LogCleaner {
    public static void main(String[] args) {
        LogCleaner logCleaner = new LogCleaner(args);
        logCleaner.Run();
    }

    private String[] args;
    private LogCleanerConfig logCleanerConfig = new LogCleanerConfig();
    private CleanerCleanseInformation cleanerCleanseInformation = new CleanerCleanseInformation();
    private DatabaseRepository repository;

    private LogCompressionService logCompressionService = new LogCompressionService();
    private CleanerService cleanerService = new CleanerService();
    private DirectoryService directoryService = new DirectoryService();


    private LogCleaner(String[] args) {
        this.args = args;
        this.cleanerCleanseInformation.setCleanerFieldMode(CleanerFieldMode.MOCK_LOG);
        Configure();
    }


    public void Run() {

        System.out.println("Commencing work.");
        directoryService.deleteDirectoryRecursively(logCleanerConfig.getWorkingDirectory());
        directoryService.deleteDirectoryRecursively(logCleanerConfig.getLogOutputDirectory());
        directoryService.deleteDirectoryRecursively(logCleanerConfig.getAWSOutputDirectory());

        System.out.println("Launching investigation on orbital body.");
        ExtractLogs();
        System.out.println("Heretics identified. Purging heretics.");
        ClearLogs();
        System.out.println("Everyone is a heretic! Regrouping with main fleet.");
        MakeLogOutput();

        System.out.println("Committing orbital bombardment.");
        directoryService.deleteDirectoryRecursively(logCleanerConfig.getWorkingDirectory());
        System.out.println("Orbital body obliterated.");

        System.out.println("Awaiting further orders.");
    }

    private void Configure() {
        //Firstly, make sure we aren't using another config file.
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-configfile"))
                logCleanerConfig = new LogCleanerConfig(args[i + 1]);


        //Handle basic configurations
        for (int i = 0; i < args.length; i++) {
            switch (args[i].toLowerCase()) {
                case "-configfile":
                    i += 1; //Already processed
                    break;
                case "-useaws":
                    logCleanerConfig.setCleanerMode(CleanerMode.AMAZONAWS);
                    break;
                case "-gzipoutput":
                    cleanerCleanseInformation.setOutputToGzip(true);
                    break;
                case "-replacefromfield":
                    i += 1;
                    cleanerCleanseInformation.setReplaceFromField(args[i]);
                    break;
                case "-replacetofield":
                    i += 1;
                    cleanerCleanseInformation.setReplaceToField(args[i]);
                    break;
                case "-isemail":
                    cleanerCleanseInformation.setFieldType(CleanerFieldType.EMAIL);
                    break;
                case "-isword":
                    cleanerCleanseInformation.setFieldType(CleanerFieldType.WORD);
                    break;
                case "-fieldmode":
                    i += 1;
                    ConfigureHandleFieldMode(args[i]);
                    break;
                case "-email-replace-undefined-emails":
                    i += 1;
                    cleanerCleanseInformation.setEmailReplaceUndefinedEmails(true);
                    cleanerCleanseInformation.setEmailReplaceUndefinedString(args[i]);
                    break;
                default:
                    System.out.println("Unknown parameter: " + args[i]);
            }
        }

        PopulateDatabaseUsers();
    }


    private void ConfigureHandleFieldMode(String fieldMode) {
        switch (fieldMode.toLowerCase()) {
            case "mocklog":
            case "mock_log":
                cleanerCleanseInformation.setCleanerFieldMode(CleanerFieldMode.MOCK_LOG);
                break;
            case "add":
                cleanerCleanseInformation.setCleanerFieldMode(CleanerFieldMode.ADD);
                break;
            case "remove":
                cleanerCleanseInformation.setCleanerFieldMode(CleanerFieldMode.REMOVE);
                break;
            case "replace":
                cleanerCleanseInformation.setCleanerFieldMode(CleanerFieldMode.REPLACE);
                break;
            default:
                System.out.println("Unknown FieldMode: " + fieldMode);
                cleanerCleanseInformation.setCleanerFieldMode(CleanerFieldMode.NONE);
        }
    }


    private void ExtractLogs() {
        logCompressionService.ExtractAllLogFiles(logCleanerConfig.getLogDirectory(), logCleanerConfig.getWorkingDirectory() + "\\Raw");
    }

    private void ClearLogs() {
        cleanerService.cleanAllLogFiles(new File(logCleanerConfig.getWorkingDirectory() + "\\Raw"), new File(logCleanerConfig.getWorkingDirectory() + "\\Cleaned"), cleanerCleanseInformation);
    }

    private void MakeLogOutput() {
        if (cleanerCleanseInformation.isOutputToGzip()) {
            logCompressionService.CompressAllLogFiles(logCleanerConfig.getWorkingDirectory() + "\\Cleaned", logCleanerConfig.getLogOutputDirectory());
        } else {
            directoryService.copyAllFiles(logCleanerConfig.getWorkingDirectory() + "\\Cleaned", logCleanerConfig.getLogOutputDirectory());
        }
    }


    private void PopulateDatabaseUsers() {
        //Create connection.
        switch (logCleanerConfig.getDatabaseType()) {
            case MONGODB:
                repository = new MongoConnection(logCleanerConfig);
                break;
        }

        cleanerCleanseInformation.getUsers().addAll(repository.getUsers(cleanerCleanseInformation));
    }
}
