package com.jkelany.todo.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    private JwtToken jwtToken;
    private FileUploads fileUploads;

    @Data
    public static class FileUploads {
        private String baseDir;
        private String provider;
        private S3 s3;
        private GCS gcs;

        @Data
        public static class S3 {
            private String accessKey;
            private String secretKey;
            private String region;
            private String bucketName;
        }

        @Data
        public static class GCS {
            private String projectId;
            private String credentialsPath;
            private String bucketName;
        }
    }

    @Data
    public static class JwtToken {
        private long expiration = 604800L;
        private String secret = "randSecret@2022";
        private String headerKey = "Authorization";
    }
}
