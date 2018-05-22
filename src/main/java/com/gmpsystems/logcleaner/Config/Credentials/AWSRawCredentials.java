package com.gmpsystems.logcleaner.Config.Credentials;

import com.amazonaws.auth.AWSCredentials;

public class AWSRawCredentials implements AWSCredentials {

    private String AWSAccessKeyId;
    private String AWSSecretKey;

    public AWSRawCredentials(String accessKey, String secretKey) {
        AWSAccessKeyId = accessKey;
        AWSSecretKey = secretKey;
    }

    @Override
    public String getAWSAccessKeyId() {
        return AWSAccessKeyId;
    }

    @Override
    public String getAWSSecretKey() {
        return AWSSecretKey;
    }
}
