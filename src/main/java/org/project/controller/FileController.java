package org.project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.project.DTO.ResourceInfoRequest;
import org.project.service.FileService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/resource")
@RequiredArgsConstructor
public class FileController {
    
    private final  FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            String fileName = fileService.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        try {
            List<String> files = fileService.listFiles();
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR )
                    .body(null);
        }
    }

    @GetMapping
    public ResponseEntity<?>getResourceInfo(@Valid @RequestParam ResourceInfoRequest resourceInfoRequest ) {
        fileService.getResourceInfoByPath(resourceInfoRequest.getPath());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(null);
    }
}
