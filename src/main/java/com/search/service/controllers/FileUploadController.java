package com.search.service.controllers;

import com.search.service.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/storage")
@Slf4j
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public List<Path> listUploadedFiles() {
        return storageService.loadAll().collect(Collectors.toList());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "http://localhost:8080")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {

        storageService.store(file);
        log.info(file.getOriginalFilename() +" saved");
    }


}