package com.search.service.controllers;

import com.search.service.services.InMemoryDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private  InMemoryDataGrid inMemoryDataGrid;

    // I know that it will work without Autowired, but i just used to write it
    @Autowired
    public SearchController(InMemoryDataGrid inMemoryDataGrid) {
        this.inMemoryDataGrid = inMemoryDataGrid;
    }

    @GetMapping
    public List<String> search(String[] searchParams){

        return inMemoryDataGrid.search(Arrays.asList(searchParams));
    }
}
