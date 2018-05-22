package com.gmpsystems.logcleaner.Config.Credentials;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class AWSRawCredentialsProvider implements AWSCredentialsProvider {

    AWSRawCredentials credentials;

    public AWSRawCredentialsProvider(String userkey, String secretkey) {
        credentials = new AWSRawCredentials(userkey, secretkey);
    }

    @Override
    public AWSCredentials getCredentials() {
        return credentials;
    }

    @Override
    public void refresh() {
        //Not needed
    }
}
