package com.gmpsystems.logcleaner;

import com.gmpsystems.logcleaner.Config.CleanerMode;
import com.gmpsystems.logcleaner.Config.LogCleanerConfig;
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
    LogCompressionService logCompressionService = new LogCompressionService();
    CleanerService cleanerService = new CleanerService();


    private LogCleaner(String[] args) {
        this.args = args;
        this.logCleanerConfig = new LogCleanerConfig();
        this.logCleanerConfig.setCleanerMode(CleanerMode.REPLACE);
    }


    private void Run() {
        for (String arg : args) {
            System.out.println(arg);
        }


        logCompressionService.ExtractAllFiles(logCleanerConfig.getLogDirectory(), logCleanerConfig.getLogOutputDirectory());
        cleanerService.cleanLogFile(new File(logCleanerConfig.getLogOutputDirectory()+"\\2017-05-26.tsv.txt"),new File(""));

    }
}
