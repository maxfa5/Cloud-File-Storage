package org.project.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class MinioConfig {
    @Value("${minio.endpoint:http://localhost:9000}")

    private String endpoint;

    @Value("${minio.access-key:admin}")
    private String accessKey;

    @Value("${minio.secret-key:password}")
    private String secretKey;

    @Value("${minio.bucket:user-files}")
    private String bucketName;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
    @PostConstruct
    public void init() {
        try {
            // Инициализация bucket при старте приложения
            MinioClient client = minioClient();
            boolean bucketExists = client.bucketExists(
                io.minio.BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build()
            );

            if (!bucketExists) {
                client.makeBucket(
                    io.minio.MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build()
                );
                System.out.println("MinIO bucket created: " + bucketName);
            }
        } catch (Exception e) {
            System.err.println("MinIO initialization error: " + e.getMessage());
        }
    }

}