package com.search.service.services;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface InMemoryDataGrid {

    void get(String key);
    List<String> search(Set<String> searchData);

    void add(String key, TreeSet value);

}
