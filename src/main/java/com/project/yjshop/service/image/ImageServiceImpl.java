package com.project.yjshop.service.image;

import com.project.yjshop.domain.image.Image;
import com.project.yjshop.domain.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    @Value("${cloud.aws.cloudFront}")
    private String cloudFront;

    @Override
    @Transactional
    public String imageUpload(String imagePath) {
        imageRepository.save(Image.builder()
                .imagePath(imagePath)
                .imageFullPath(getFullPath(imagePath))
                .build());
        return imagePath;
    }

    @Override
    public String getFullPath(String path) {
        return "https://" + cloudFront + "/" + path;
    }


}
