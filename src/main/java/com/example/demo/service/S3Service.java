package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String S3Bucket;

    @Autowired
    AmazonS3Client amazonS3Client;

    public String uploadS3FileAndReturnUrl(String imgUUIDName, MultipartFile multipartFile) throws IOException {
        long size = multipartFile.getSize(); // 파일 크기
        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(size);

        // S3에 업로드
        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, imgUUIDName, multipartFile.getInputStream(), objectMetaData)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        return amazonS3Client.getUrl(S3Bucket, imgUUIDName).toString();
    }

    public void deleteS3File(String imgUUIDName) {
        amazonS3Client.deleteObject(S3Bucket, imgUUIDName);
    }

}
