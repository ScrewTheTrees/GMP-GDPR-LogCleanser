package dto.gmp.logcleaner;

import dto.gmp.logcleaner.Config.CleanerMode;
import dto.gmp.logcleaner.Config.LogCleanerConfig;
import dto.gmp.logcleaner.Services.LogCompressionService;

public class LogCleaner {
    public static void main(String[] args) {
        LogCleaner logCleaner = new LogCleaner(args);
        logCleaner.Run();
    }

    private String[] args;
    private LogCleanerConfig logCleanerConfig;

    private LogCleaner(String[] args) {
        this.args = args;
        this.logCleanerConfig = new LogCleanerConfig();
        this.logCleanerConfig.setCleanerMode(CleanerMode.REPLACE);
    }


    private void Run() {
        for (String arg : args) {
            System.out.println(arg);
        }

        LogCompressionService logCompressionService = new LogCompressionService();
        logCompressionService.ExtractAllFiles(logCleanerConfig.getLogDirectory(), logCleanerConfig.getLogOutputDirectory());
    }
}
