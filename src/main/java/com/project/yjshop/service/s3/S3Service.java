package com.project.yjshop.service.s3;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
    String upload(MultipartFile image) throws IOException;
    String delete(String imagePath);
}
