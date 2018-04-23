package dto.gmp.logcleaner.Config;

import java.net.URL;

public class LogCleanerConfig {

    private CleanerMode cleanerMode = CleanerMode.NONE;
    private String logDirectory;
    private URL AWSServer;



    public CleanerMode getCleanerMode() {
        return cleanerMode;
    }

    public void setCleanerMode(CleanerMode cleanerMode) {
        this.cleanerMode = cleanerMode;
    }

    public String getLogDirectory() {
        return logDirectory;
    }

    public void setLogDirectory(String logDirectory) {
        this.logDirectory = logDirectory;
    }

    public URL getAWSServer() {
        return AWSServer;
    }

    public void setAWSServer(URL AWSServer) {
        this.AWSServer = AWSServer;
    }
}


