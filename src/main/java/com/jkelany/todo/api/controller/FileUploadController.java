package com.jkelany.todo.api.controller;

import com.jkelany.todo.api.service.FileUploadService;
import com.jkelany.todo.api.validator.ImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/file")
@CrossOrigin
@Validated
public class FileUploadController extends BaseController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/{pathType}/{id}")
    public ResponseEntity<?> upload(@Valid @PathVariable String pathType, @PathVariable Long id,
                                    @ImageFile MultipartFile file) throws Exception {
        String fileName = fileUploadService.storeFile(file, pathType, id);
        return ResponseEntity.ok(fileName);
    }

}
