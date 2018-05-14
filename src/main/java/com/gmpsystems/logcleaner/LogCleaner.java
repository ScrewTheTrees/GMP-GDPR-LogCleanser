package com.gmpsystems.logcleaner;

import com.gmpsystems.logcleaner.Config.*;
import com.gmpsystems.logcleaner.Repository.MongoConnection;
import com.gmpsystems.logcleaner.Services.DirectoryService;
import com.gmpsystems.logcleaner.Services.LogCompressionService;
import com.gmpsystems.logcleaner.Services.CleanerService;

import java.io.File;
import java.io.IOException;

public class LogCleaner {
    public static void main(String[] args) {
        LogCleaner logCleaner = new LogCleaner(args);
        logCleaner.Configure();
        logCleaner.Run();
    }

    private String[] args;
    private LogCleanerConfig logCleanerConfig = new LogCleanerConfig();
    private CleanerCleanseInformation cleanerCleanseInformation = new CleanerCleanseInformation();


    private LogCompressionService logCompressionService = new LogCompressionService();
    private CleanerService cleanerService = new CleanerService();
    private DirectoryService directoryService = new DirectoryService();


    private LogCleaner(String[] args) {
        this.args = args;
        this.cleanerCleanseInformation.setCleanerFieldMode(CleanerFieldMode.MOCK_LOG);
    }


    private void Run() {
        if (logCleanerConfig.getDatabaseType() == DatabaseType.MONGODB)
            MongoConnection.getInstance().getUsers();


        System.out.println("Launching investigation on orbital body.");
        logCompressionService.ExtractAllFiles(logCleanerConfig.getLogDirectory(), logCleanerConfig.getWorkingDirectory() + "\\Raw");
        System.out.println("Heretics identified. Purging heretics.");
        cleanerService.cleanAllLogFiles(new File(logCleanerConfig.getWorkingDirectory() + "\\Raw"), new File(logCleanerConfig.getWorkingDirectory() + "\\Cleaned"), cleanerCleanseInformation);
        System.out.println("Everyone is a heretic! Regrouping with main fleet.");
        logCompressionService.CompressAllFiles(logCleanerConfig.getWorkingDirectory() + "\\Cleaned", logCleanerConfig.getLogOutputDirectory());

        try {
            System.out.println("Committing orbital bombardment.");
            directoryService.deleteDirectoryRecursively(logCleanerConfig.getWorkingDirectory());
            System.out.println("Orbital body obliterated.");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                case "--useaws":
                    logCleanerConfig.setCleanerMode(CleanerMode.AMAZONAWS);
                    break;
                case "-deletefield":
                    i += 1;
                    cleanerCleanseInformation.setDeleteFromField(args[i]);
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
                    cleanerCleanseInformation.setFieldIsEmail(true);
                    break;
                case "-fieldmode":
                    i += 1;
                    ConfigureHandleFieldMode(args[i]);
                    break;
                default:
                    System.out.println("Unknown parameter: " + args[i]);
            }
        }

        //Create connection.
        if (logCleanerConfig.getDatabaseType() == DatabaseType.MONGODB)
            MongoConnection.CreateConnection(logCleanerConfig);
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
}
