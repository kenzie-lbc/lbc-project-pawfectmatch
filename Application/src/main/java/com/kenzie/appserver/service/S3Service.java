package com.kenzie.appserver.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.kenzie.appserver.repositories.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.io.Files.getFileExtension;

@Service
public class S3Service {
    private final AmazonS3 s3Client;
    private static final String BUCKET_NAME = "pet-profile";

    private static final Map<String, String> CONTENT_TYPES = new HashMap<String, String>() {{
            put("txt", "text/plain");
            put("html", "text/html");
            put("htm", "text/html");
            put("css", "text/css");
            put("js", "application/javascript");
            put("png", "image/png");
            put("jpg", "image/jpeg");
            put("jpeg", "image/jpeg");
            put("gif", "image/gif");
            put("svg", "image/svg+xml");
        }};
    private static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";


    public S3Service(@Autowired AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(Pet pet, MultipartFile file) throws IOException {
        String keyName = "IMG" + java.util.UUID.randomUUID();
        String originalFilename = Optional.ofNullable(file.getOriginalFilename()).orElse("");

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(getContentType(originalFilename));

        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, keyName, file.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        s3Client.putObject(putObjectRequest);

        // Set the URL of the image to the pet Profile picture
        String imageUrl = s3Client.getUrl(BUCKET_NAME, keyName).toString();
        pet.setImageUrl(imageUrl);

        return s3Client.getUrl(BUCKET_NAME, keyName).toString();
    }

    public S3Object downloadFile(String filename) {
        return s3Client.getObject(BUCKET_NAME, filename);
    }

    private String getContentType(String filename) {
        return CONTENT_TYPES.getOrDefault(getFileExtension(filename), DEFAULT_CONTENT_TYPE);
    }
}
