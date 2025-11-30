package org.project.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
public class FileService {
    
    private final MinioClient minioClient;
    
    @Value("${minio.bucket.name}")
    private String bucketName;

    public FileService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String uploadFile(MultipartFile file) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException{
        String fileName = file.getOriginalFilename();
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );
        return fileName;
    }

    public List<String> listFiles() throws JsonMappingException, JsonParseException, InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
        List<String> fileNames = new ArrayList<>();
        Iterable<Result<Item>> items = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucketName).build()
        );
        for (Result<Item> item : items) {
            fileNames.add(item.get().objectName());
        }
        return fileNames;
    }

    public void getResourceInfoByPath(String objectPath) {
        try {
            StatObjectResponse stat = minioClient.statObject(
                StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectPath)
                    .build());
        throw new UnsupportedOperationException("Unimplemented method 'getResourceInfoByPath'");
    }catch(Exception e ){
        System.out.print(e);
    }

}
}
