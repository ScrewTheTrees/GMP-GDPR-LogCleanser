package com.gmpsystems.logcleaner.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LogCleanerConfig {

    private static String defaultConfigFile = "config.properties";

    private String baseDirectory = "";
    private String workingDirectory = "";
    private String logDirectory = "";
    private String logOutputDirectory = "";
    private String AWSBucketName = "";
    private String AWSBucketRegion = "";
    private String AWSAccessKey = "";
    private String AWSAccessKeySecret = "";
    private String AWSDirectory = "";
    private String AWSOutputDirectory = "";
    private String databaseHostname = "";
    private String databaseUsername = "";
    private String databasePassword = "";
    private String databasePort = "";
    private String databaseName = "";
    private String databaseCollection = "";

    private CleanerMode cleanerMode = CleanerMode.LOCAL;
    private DatabaseType databaseType = DatabaseType.NONE;


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
        baseDirectory = properties.getProperty("base.directory", "");
        workingDirectory = properties.getProperty("working.directory", "");
        logDirectory = properties.getProperty("log.directory", "");
        logOutputDirectory = properties.getProperty("log.output.directory", "");

        AWSAccessKey = properties.getProperty("aws.accesskey", "");
        AWSAccessKeySecret = properties.getProperty("aws.accesskey.secret", "");
        AWSBucketName = properties.getProperty("aws.bucket.name", "");
        AWSBucketRegion = properties.getProperty("aws.bucket.region", "");
        AWSDirectory = properties.getProperty("aws.directory", "");
        AWSOutputDirectory = properties.getProperty("aws.output.directory", "");

        parseDatabaseType(properties.getProperty("database.type", ""));
        databaseHostname = properties.getProperty("database.hostname", "localhost");
        databaseUsername = properties.getProperty("database.username", "");
        databasePassword = properties.getProperty("database.password", "");
        databasePort = properties.getProperty("database.port", "27017");
        databaseName = properties.getProperty("database.name", "");
        databaseCollection = properties.getProperty("database.collection", "");

        readjustProperties();
    }

    private void parseDatabaseType(String property) {
        if (property.toLowerCase().equals("mongodb")) {
            databaseType = DatabaseType.MONGODB;
        } else databaseType = DatabaseType.NONE;
    }

    private void readjustProperties() {
        baseDirectory = new File(baseDirectory).getAbsolutePath();

        if (workingDirectory.startsWith("\\")) workingDirectory = baseDirectory + workingDirectory;
        if (logDirectory.startsWith("\\")) logDirectory = baseDirectory + logDirectory;
        if (logOutputDirectory.startsWith("\\")) logOutputDirectory = baseDirectory + logOutputDirectory;
        if (AWSDirectory.startsWith("\\")) AWSDirectory = baseDirectory + AWSDirectory;
        if (AWSOutputDirectory.startsWith("\\")) AWSOutputDirectory = baseDirectory + AWSOutputDirectory;
    }

    public String getLogDirectory() {
        return logDirectory;
    }

    public String getAWSDirectory() {
        return AWSDirectory;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public String getLogOutputDirectory() {
        return logOutputDirectory;
    }

    public String getAWSOutputDirectory() {
        return AWSOutputDirectory;
    }

    public String getBaseDirectory() {
        return baseDirectory;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public String getDatabaseHostname() {
        return databaseHostname;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseCollection() {
        return databaseCollection;
    }

    public CleanerMode getCleanerMode() {
        return cleanerMode;
    }

    public void setCleanerMode(CleanerMode cleanerMode) {
        this.cleanerMode = cleanerMode;
    }

    public String getAWSBucketName() { return AWSBucketName; }

    public String getAWSBucketRegion() {
        return AWSBucketRegion;
    }

    public String getAWSAccessKeySecret() {
        return AWSAccessKeySecret;
    }

    public void setAWSAccessKeySecret(String AWSAccessKeySecret) {
        this.AWSAccessKeySecret = AWSAccessKeySecret;
    }

    public String getAWSAccessKey() {
        return AWSAccessKey;
    }

    public void setAWSAccessKey(String AWSAccessKey) {
        this.AWSAccessKey = AWSAccessKey;
    }
}


