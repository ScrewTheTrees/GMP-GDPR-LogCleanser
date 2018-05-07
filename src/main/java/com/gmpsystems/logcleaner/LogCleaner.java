package com.gmpsystems.logcleaner;

import com.gmpsystems.logcleaner.Config.CleanerMode;
import com.gmpsystems.logcleaner.Config.LogCleanerConfig;
import com.gmpsystems.logcleaner.Services.DirectoryService;
import com.gmpsystems.logcleaner.Services.LogCompressionService;
import com.gmpsystems.logcleaner.Services.CleanerService;

import java.io.File;

public class LogCleaner {
    public static void main(String[] args) {
        LogCleaner logCleaner = new LogCleaner(args);
        logCleaner.Run();
    }

    private String[] args;
    private LogCleanerConfig logCleanerConfig;
    private LogCompressionService logCompressionService = new LogCompressionService();
    private CleanerService cleanerService = new CleanerService();


    public LogCleaner(String[] args) {
        this.args = args;
        this.logCleanerConfig = new LogCleanerConfig();
        this.logCleanerConfig.setCleanerMode(CleanerMode.REPLACE);
    }


    public void Run() {
        for (String arg : args) {
            System.out.println(arg);
        }


        logCompressionService.ExtractAllFiles(logCleanerConfig.getLogDirectory(), logCleanerConfig.getWorkingDirectory()+"\\Raw");
        cleanerService.cleanAllLogFiles(new File(logCleanerConfig.getWorkingDirectory()+"\\Raw"), new File(logCleanerConfig.getWorkingDirectory()+"\\Cleaned"));
        logCompressionService.CompressAllFiles(logCleanerConfig.getWorkingDirectory()+"\\Cleaned", logCleanerConfig.getLogOutputDirectory());

    }
}
