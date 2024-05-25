
/*
 *
 *  * @project : DeliX
 *  * @created : 14/05/2024, 21:49
 *  * @modified : 14/05/2024, 21:49
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.util.Constants;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.nio.file.Paths;

@Service
public class S3ServiceImpl {

    private final S3Client s3Client;

    public S3ServiceImpl() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(Constants.AWS_ACCESS, Constants.AWS_SECRET);
        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }

    public PutObjectResponse uploadDocument(String bucketName, String key, String documentPath) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        return s3Client.putObject(putObjectRequest, Paths.get(documentPath));

    }

    public void deleteDocument(String bucketName, String key) {
        s3Client.deleteObject(builder -> builder.bucket(bucketName).key(key));
    }


}