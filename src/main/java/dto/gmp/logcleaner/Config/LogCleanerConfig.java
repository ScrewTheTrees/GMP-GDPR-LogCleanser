package dto.gmp.logcleaner.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LogCleanerConfig {

    private static String defaultConfigFile = "config.properties";

    private CleanerMode cleanerMode = CleanerMode.NONE;
    private String baseDirectory = "";
    private String workingDirectory = "";
    private String logDirectory = "";
    private String logOutputDirectory = "";
    private String AWSServer = "";
    private String AWSPassword = "";
    private String AWSDirectory = "";
    private String AWSOutputDirectory = "";

    public LogCleanerConfig() {
        this(defaultConfigFile);
    }

    public LogCleanerConfig(String configFileToReadFrom) {
        try {
            InputStream stream = new FileInputStream(new File(configFileToReadFrom).getAbsoluteFile());
            Properties properties = new Properties();
            properties.load(stream);
            matchPropertiesToThis(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void matchPropertiesToThis(Properties properties) {
        baseDirectory = properties.getProperty("base-directory", "");
        workingDirectory = properties.getProperty("working-directory", "");
        logDirectory = properties.getProperty("log-directory", "");
        logOutputDirectory = properties.getProperty("log-output-directory", "");
        AWSServer = properties.getProperty("aws-server", "");
        AWSPassword = properties.getProperty("aws-password", "");
        AWSDirectory = properties.getProperty("aws-directory", "");
        AWSOutputDirectory = properties.getProperty("aws-output-directory", "");

        readjustProperties();
    }

    private void readjustProperties() {
        baseDirectory = new File(baseDirectory).getAbsolutePath();
        
        if (workingDirectory.startsWith("\\")) workingDirectory = baseDirectory + workingDirectory;
        if (logDirectory.startsWith("\\")) logDirectory = baseDirectory + logDirectory;
        if (logOutputDirectory.startsWith("\\")) logOutputDirectory = baseDirectory + logOutputDirectory;
        if (AWSDirectory.startsWith("\\")) AWSDirectory = baseDirectory + AWSDirectory;
        if (AWSOutputDirectory.startsWith("\\")) AWSOutputDirectory = baseDirectory + AWSOutputDirectory;
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

    public String getAWSDirectory() {
        return AWSDirectory;
    }

    public void setAWSDirectory(String AWSDirectory) {
        this.AWSDirectory = AWSDirectory;
    }

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

    public String getBaseDirectory() {
        return baseDirectory;
    }
}


