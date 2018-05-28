package com.gmpsystems.logcleaner.Services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.gmpsystems.logcleaner.Config.LogCleanerConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AmazonService {


    public ArrayList<String> GetBucketFiles(AmazonS3 amazonS3, LogCleanerConfig logCleanerConfig) {
        ArrayList<String> files = new ArrayList<>();
        if (amazonS3.doesBucketExistV2(logCleanerConfig.getAWSBucketName())) {
            ListObjectsV2Result a = amazonS3.listObjectsV2(logCleanerConfig.getAWSBucketName(), logCleanerConfig.getAWSDirectory());

            for (S3ObjectSummary s3 : a.getObjectSummaries()) {
                if (s3.getSize() > 0) {
                    files.add(s3.getKey());
                }
            }
        }
        return files;
    }


    public void DownloadAllBucketFiles(AmazonS3 amazonS3, LogCleanerConfig logCleanerConfig, List<String> files) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (String s : files) {
            executor.execute(() -> DownloadBucketFile(amazonS3, logCleanerConfig, s));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished downloading all files.");
    }

    public void DownloadBucketFile(AmazonS3 amazonS3, LogCleanerConfig logCleanerConfig, String file) {
        try {
            S3Object a = amazonS3.getObject(logCleanerConfig.getAWSBucketName(), file);
            File newFile = new File(logCleanerConfig.getAWSOutputDirectory() + "\\" + file.replace(logCleanerConfig.getAWSDirectory(), "").replace("/", "\\"));
            newFile.getParentFile().mkdirs();
            FileOutputStream fs = new FileOutputStream(newFile);
            System.out.println("Downloading file from bucket: " + newFile.getPath());
            for (long i = 0; i < a.getObjectMetadata().getContentLength(); i += 1) {
                fs.write(a.getObjectContent().read());
            }

            fs.flush();
            fs.close();
            a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UploadAllBucketFiles2(AmazonS3 amazonS3, LogCleanerConfig logCleanerConfig, List<File> files) {
        List<String> newFiles = new ArrayList<>();
        for (File f : files)
            newFiles.add(f.getAbsolutePath());

        UploadAllBucketFiles(amazonS3, logCleanerConfig, newFiles);
    }

    public void UploadAllBucketFiles(AmazonS3 amazonS3, LogCleanerConfig logCleanerConfig, List<String> files) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (String s : files) {
            executor.execute(() -> UploadBucketFile(amazonS3, logCleanerConfig, s));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished uploading all files.");
    }

    public void UploadBucketFile(AmazonS3 amazonS3, LogCleanerConfig logCleanerConfig, String file) {
        File f = new File(file);
        System.out.println("Uploading file to bucket: " + f.getAbsolutePath());
        String out = file.replace(logCleanerConfig.getLogOutputDirectory(), "").replace("\\", "/");
        amazonS3.putObject(logCleanerConfig.getAWSBucketName(), logCleanerConfig.getAWSDirectory() + "New" + out, f);
    }
}
