package com.javabro.springs3app.controllers;

import com.javabro.springs3app.service.TestS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class TestS3Controller {

    TestS3Service testS3Service;

    public TestS3Controller(TestS3Service testS3Service) {
        this.testS3Service = testS3Service;
    }

    @PostMapping("/users/{userId}/upload")
    public String uploadFile(@PathVariable String userId,
                             @RequestParam("file") MultipartFile file) {
        log.debug("Uploading file {} for user {}", file.getOriginalFilename(), userId);
        int version = testS3Service.uploadFile(userId, file);
        return "File uploaded: " + file.getOriginalFilename() + " (v" + version + ")";
    }
}
