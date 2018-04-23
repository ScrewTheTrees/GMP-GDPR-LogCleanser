package dto.gmp.logcleaner;

import dto.gmp.logcleaner.Config.CleanerMode;
import dto.gmp.logcleaner.Config.LogCleanerConfig;

import java.io.File;
import java.io.IOException;
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

        String workingDir = Paths.get("").toAbsolutePath().toString();
        System.out.println("Current relative path is: " + workingDir);

        File input = new File(workingDir+"\\in\\LocalLogZips\\2016-11-19.tsv.gz");
        File output = new File(workingDir+"\\in\\test.txt");
        try {
            GZipFile.decompressGzip(input, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
