package com.project.yjshop.web.api;

import com.project.yjshop.service.image.ImageServiceImpl;
import com.project.yjshop.service.image.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ImageTestController {
    private final S3Service s3Service;
    private final ImageServiceImpl imageService;

    @PostMapping("upload")
    public String imageUpload(@ModelAttribute MultipartFile image) throws IOException {
        String imagePath = s3Service.upload(image);
        return imageService.imageUpload(imagePath);
    }

    @DeleteMapping("delete")
    public String imageDelete(@RequestParam(name = "imagePath") String imagePath) throws IOException {
        return s3Service.delete(imagePath);
    }
}
