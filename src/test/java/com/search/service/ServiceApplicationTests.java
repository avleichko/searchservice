package com.search.service;

import com.search.service.controllers.FileUploadController;
import com.search.service.controllers.SearchController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {


    @Autowired
    private SearchController controller;

    @Autowired
    private FileUploadController fileUploadController;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(fileUploadController).isNotNull();
    }

}
