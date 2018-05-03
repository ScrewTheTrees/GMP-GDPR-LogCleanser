package dto.gmp.logcleaner;

import dto.gmp.logcleaner.Config.CleanerMode;
import dto.gmp.logcleaner.Config.LogCleanerConfig;
import dto.gmp.logcleaner.Services.CleanerService;
import dto.gmp.logcleaner.Services.LogCompressionService;

import java.nio.file.Paths;

public class Program {
    public static void main(String[] args) {
        Program program = new Program(args);
        program.Run();
    }

    private String[] args;
    private LogCleanerConfig logCleanerConfig;

    private Program(String[] args) {
        this.args = args;
        this.logCleanerConfig = new LogCleanerConfig();
        this.logCleanerConfig.setCleanerMode(CleanerMode.REPLACE);
    }


    private void Run() {
        for (String arg : args) {
            System.out.println(arg);
        }

        String workingDir = logCleanerConfig.getBaseDirectory();
        String loggingDir = logCleanerConfig.getLogDirectory();
        System.out.println("Current path is: " + workingDir);
        System.out.println("Current logging path is: " + loggingDir);

        LogCompressionService logCompressionService = new LogCompressionService();
        logCompressionService.ExtractAllFiles(logCleanerConfig.getLogDirectory(), logCleanerConfig.getLogOutputDirectory());
    }
}
