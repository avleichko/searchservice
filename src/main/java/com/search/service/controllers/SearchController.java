package com.search.service.controllers;

import com.search.service.services.InMemoryDataGrid;
import com.search.service.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    private InMemoryDataGrid inMemoryDataGrid;
    private StorageService storageService;

    // I know that it will work without Autowired, but i just used to write it
    @Autowired
    public SearchController(InMemoryDataGrid inMemoryDataGrid, StorageService storageService) {
        this.inMemoryDataGrid = inMemoryDataGrid;
        this.storageService = storageService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public List<String> search(String[] searchParams) {

        return inMemoryDataGrid.search(Arrays.asList(searchParams));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(String file) throws IOException {
        ResponseEntity<Resource> result = storageService.download(file);
        log.info(file + "downloading");
        return result;
    }
}
