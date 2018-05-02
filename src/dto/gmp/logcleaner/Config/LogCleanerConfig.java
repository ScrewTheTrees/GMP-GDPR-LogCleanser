package dto.gmp.logcleaner.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LogCleanerConfig {

    private static String configFile = "config.properties";

    private CleanerMode cleanerMode = CleanerMode.NONE;
    private String workingDirectory = "";
    private String logDirectory = "";
    private String logOutputDirectory = "";
    private String AWSServer = "";
    private String AWSPassword = "";
    private String AWSDirectory = "";
    private String AWSOutputDirectory = "";

    public LogCleanerConfig() {
        this(configFile);
    }

    public LogCleanerConfig(String configFileToReadFrom) {
        try {
            InputStream stream = new FileInputStream(configFileToReadFrom);
            Properties properties = new Properties();
            properties.load(stream);
            matchPropertiesToThis(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void matchPropertiesToThis(Properties properties) {
        workingDirectory = properties.getProperty("working-directory", "");
        logDirectory = properties.getProperty("log-directory", "");
        logOutputDirectory = properties.getProperty("log-output-directory", "");
        AWSServer = properties.getProperty("aws-server", "");
        AWSPassword = properties.getProperty("aws-password", "");
        AWSDirectory = properties.getProperty("aws-directory", "");
        AWSOutputDirectory = properties.getProperty("aws-output-directory", "");
    }

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

    public String getAWSServer() {
        return AWSServer;
    }

    public void setAWSServer(String AWSServer) {
        this.AWSServer = AWSServer;
    }

    public String getAWSPassword() {
        return AWSPassword;
    }

    public void setAWSPassword(String AWSPassword) {
        this.AWSPassword = AWSPassword;
    }

    public String getAWSDirectory() { return AWSDirectory; }

    public void setAWSDirectory(String AWSDirectory) { this.AWSDirectory = AWSDirectory; }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public String getLogOutputDirectory() {
        return logOutputDirectory;
    }

    public void setLogOutputDirectory(String logOutputDirectory) {
        this.logOutputDirectory = logOutputDirectory;
    }

    public String getAWSOutputDirectory() {
        return AWSOutputDirectory;
    }

    public void setAWSOutputDirectory(String AWSOutputDirectory) {
        this.AWSOutputDirectory = AWSOutputDirectory;
    }
}


