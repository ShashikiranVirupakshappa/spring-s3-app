package com.javabro.springs3app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class TestS3Service {

    private final S3Client s3Client;

    @Value("${s3.bucketName}")
    private String bucketName;

    TestS3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public int uploadFile(String userId, MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            int nextVersion = getNextVersion(userId, filename);
            String key = "users/" + userId + "/" + filename + "/v" + nextVersion;

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    RequestBody.fromBytes(file.getBytes()));

            return nextVersion;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    private int getNextVersion(String userId, String filename) {
        String prefix = "users/" + userId + "/" + filename + "/v";

        ListObjectsV2Response response = s3Client.listObjectsV2(
                ListObjectsV2Request.builder()
                        .bucket(bucketName)
                        .prefix(prefix)
                        .build());

        int maxVersion = 0;
        for (S3Object obj : response.contents()) {
            String key = obj.key();
            String versionStr = key.substring(key.lastIndexOf("/v") + 2);
            try {
                int version = Integer.parseInt(versionStr);
                maxVersion = Math.max(maxVersion, version);
            } catch (NumberFormatException ignored) {
            }
        }

        return maxVersion + 1;
    }

}
