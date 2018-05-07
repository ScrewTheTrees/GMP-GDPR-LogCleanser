package com.gmpsystems.logcleaner;

import com.gmpsystems.logcleaner.Config.CleanerCleanseInformation;
import com.gmpsystems.logcleaner.Config.CleanerMode;
import com.gmpsystems.logcleaner.Config.LogCleanerConfig;
import com.gmpsystems.logcleaner.Services.DirectoryService;
import com.gmpsystems.logcleaner.Services.LogCompressionService;
import com.gmpsystems.logcleaner.Services.CleanerService;

import java.io.File;
import java.io.IOException;

public class LogCleaner {
    public static void main(String[] args) {
        LogCleaner logCleaner = new LogCleaner(args);
        logCleaner.Run();
    }

    private String[] args;
    private LogCleanerConfig logCleanerConfig;
    private CleanerCleanseInformation cleanerCleanseInformation;


    private LogCompressionService logCompressionService = new LogCompressionService();
    private CleanerService cleanerService = new CleanerService();
    private DirectoryService directoryService = new DirectoryService();


    public LogCleaner(String[] args) {
        this.args = args;
        this.logCleanerConfig = new LogCleanerConfig();
        this.cleanerCleanseInformation = new CleanerCleanseInformation();
        this.cleanerCleanseInformation.setCleanerMode(CleanerMode.REPLACE);
    }


    public void Run() {
        for (String arg : args) {
            System.out.println(arg);
        }

        System.out.println("Launching investigation on orbital body.");
        logCompressionService.ExtractAllFiles(logCleanerConfig.getLogDirectory(), logCleanerConfig.getWorkingDirectory()+"\\Raw");
        System.out.println("Heretics identified. Purging heretics.");
        cleanerService.cleanAllLogFiles(new File(logCleanerConfig.getWorkingDirectory()+"\\Raw"), new File(logCleanerConfig.getWorkingDirectory()+"\\Cleaned"), cleanerCleanseInformation);
        System.out.println("Everyone is a heretic! Regrouping with main fleet.");
        logCompressionService.CompressAllFiles(logCleanerConfig.getWorkingDirectory()+"\\Cleaned", logCleanerConfig.getLogOutputDirectory());

        try {
            System.out.println("Committing orbital bombardment.");
            directoryService.deleteDirectoryRecursively(logCleanerConfig.getWorkingDirectory());
            System.out.println("Orbital body obliterated.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Awaiting further orders.");
    }
}
