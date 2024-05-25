///*
// *
// *  * @project : DeliX
// *  * @created : 24/05/2024, 13:47
// *  * @modified : 24/05/2024, 13:47
// *  * @description : This file is part of the DeliX project.
// *  * @license : MIT License
// *
// */
//
//package com.fsdm.pfe.delix.config;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.secretsmanager.AWSSecretsManager;
//import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
//import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
//import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecretConfig {
//    Gson gson = new Gson();
//    @Value("${cloud.aws.credentials.access-key}")
//    private String accessKey;
//    @Value("${cloud.aws.credentials.secret-key}")
//    private String secretkey;
//
//    @Bean
//    public DataSource getSecrets() {
//        AwsSecrets.AwsSecretsDatabase secrets = getSecret();
//        return DataSourceBuilder
//                .create()
//                .driverClassName("com.mysql.cj.jdbc.driver")
//                .url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" + secrets.getPort() + "/delix2?createDatabaseIfNotExist=true&useSSL=false&useTimezone=true&serverTimezone=UTC")
//                .username(secrets.getUsername())
//                .password(secrets.getPassword())
//                .build();
//    }
//
//    private AwsSecrets.AwsSecretsDatabase getSecret() {
//
//        String secretName = "delix-database";
//        String region = "us-east-2";
//
//        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretkey)))
//                .build();
//
//        String secret, decodedBinarySecret;
//        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
//                .withSecretId(secretName);
//        GetSecretValueResult getSecretValueResult = null;
//
//        try {
//            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
//        } catch (Exception e) {
//            throw e;
//        }
//        if (getSecretValueResult.getSecretString() != null) {
//            secret = getSecretValueResult.getSecretString();
//            return gson.fromJson(secret, AwsSecrets.AwsSecretsDatabase.class);
//        }
//        return null;
//    }
//
//
//}
