package com.jkelany.todo.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String storeFile(MultipartFile multipartFile, String pathType, Object id) throws Exception;

}
