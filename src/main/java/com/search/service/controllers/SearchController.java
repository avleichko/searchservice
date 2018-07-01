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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    private final InMemoryDataGrid inMemoryDataGrid;
    private StorageService storageService;

    // I know that it will work without Autowired, but i just used to write it
    @Autowired
    public SearchController(InMemoryDataGrid inMemoryDataGrid, StorageService storageService) {
        this.inMemoryDataGrid = inMemoryDataGrid;
        this.storageService = storageService;
    }

    /**
     * METHOD : GET
     * search file name by given Array of strings
     *
     * @param searchParams String[] of search param
     * @return List<String>
     */

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<String> search(String[] searchParams) {

        log.info("search via following params" + Arrays.toString(searchParams));

        //using set here to avoid iterating over duplicate values in future

        Set<String> searchSet = Stream.of(searchParams).collect(Collectors.toSet());
        return inMemoryDataGrid.search(searchSet);
    }

    /**
     * METHOD : GET
     * search file name by given Array of strings
     *
     * @param file file which needs to be downloaded
     * @return resource
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/download")
    public ResponseEntity<Resource> download(String file) throws IOException {
        ResponseEntity<Resource> result = storageService.download(file);
        log.info(file + "downloading");
        return result;
    }
}
