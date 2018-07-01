package com.search.service.services;

import exceptions.StorageFileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class InMemoryDataGridService implements InMemoryDataGrid {

    /**
     * using TreeMap and TreeSet to get O(log(n)) complexity
     */
    private final TreeMap<String, TreeSet<String>> storage = new TreeMap<>();

    @Override
    public void get(String key) {
        storage.get(key);
    }

    @Override
    public List<String> search(Set<String> searchData) {
        if (storage.isEmpty()) {
            throw new StorageFileNotFoundException("storage is empty");
        }
        List<String> result = new ArrayList<>();
        storage.forEach((s, strings) -> {
            if (strings.containsAll(searchData)) {
                result.add(s);
            }
        });
        if(result.isEmpty()){
            throw new StorageFileNotFoundException(searchData + " not found");
        }
        return result;
    }

    @Override
    public void add(String key, TreeSet value) {
        storage.put(key, value);
        log.info(key +" added to memory");
    }
}
