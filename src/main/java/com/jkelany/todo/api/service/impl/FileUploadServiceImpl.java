package com.jkelany.todo.api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.jkelany.todo.api.config.AppProperties;
import com.jkelany.todo.api.exception.FileStorageException;
import com.jkelany.todo.api.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private AppProperties appProperties;
    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public String storeFile(MultipartFile multipartFile, String pathType, Object id) throws Exception {
        switch (appProperties.getFileUploads().getProvider()) {
            case "s3":
                return storeToAwsS3Provider(multipartFile, pathType, id);
            case "gcp":
                return storeToGoogleStorageProvider(multipartFile, pathType, id);
            case "local":
            default:
                return storeToLocalProvider(multipartFile, pathType, id);
        }
    }

    private String storeToGoogleStorageProvider(MultipartFile multipartFile, String pathType, Object id)
            throws Exception {
        String fileName = generateFileName(multipartFile.getName(), id);
        Credentials credentials = GoogleCredentials.fromStream(getClass().getResourceAsStream(
                appProperties.getFileUploads().getGcs().getCredentialsPath()));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(
                appProperties.getFileUploads().getGcs().getProjectId()
        ).build().getService();
        BlobId blobId = BlobId.of(appProperties.getFileUploads().getGcs().getBucketName(),
                pathType + "/" + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();
        byte[] fileAsBytes = multipartFile.getInputStream().readAllBytes();
        storage.create(blobInfo, fileAsBytes, 0, fileAsBytes.length);

        return fileName;
    }

    private String storeToAwsS3Provider(MultipartFile multipartFile, String pathType, Object id) throws Exception {
        String fileName = generateFileName(multipartFile.getName(), id);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(IOUtils.toByteArray(multipartFile.getInputStream()).length);
        amazonS3.putObject(new PutObjectRequest(appProperties.getFileUploads().getS3().getBucketName(),
                pathType + "/" + fileName, multipartFile.getInputStream(), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));


        return fileName;
    }

    private String storeToLocalProvider(MultipartFile multipartFile, String pathType, Object id) {
        Path fileLocationPath = Paths.get(appProperties.getFileUploads().getBaseDir()
                + "/" + pathType).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileLocationPath);
        } catch (Exception ex) {
            throw new FileStorageException("Can't create directories");
        }

        String fileName = generateFileName(multipartFile.getOriginalFilename(), id);
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Invalid file name: " + fileName);
            }

            Files.copy(multipartFile.getInputStream(), fileLocationPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception ex) {
            throw new FileStorageException("Can't store file: " + fileName);
        }
    }

    private String generateFileName(String name, Object id) {
        return StringUtils.cleanPath(String.format("%s_%s_%s", id, UUID.randomUUID(), name));
    }
}
