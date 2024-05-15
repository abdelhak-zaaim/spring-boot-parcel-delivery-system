/*
 *
 *  * @project : DeliX
 *  * @created : 14/05/2024, 21:50
 *  * @modified : 14/05/2024, 21:50
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;


import com.fsdm.pfe.delix.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final S3ServiceImpl s3Service;

    public DocumentService(S3ServiceImpl s3Service) {
        this.s3Service = s3Service;
    }

    public void handleDocumentUpload(String documentPath) {
        String bucketName = Constants.BUCKET_NAME;
        String key = "your-key";

        s3Service.uploadDocument(bucketName, key, documentPath);
    }
}
