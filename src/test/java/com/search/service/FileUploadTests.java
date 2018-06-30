package com.search.service;

import com.search.service.services.StorageService;
import exceptions.StorageFileNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadTests {

    private static final String STORAGE = "/storage";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StorageService storageServiceMock;


    @Test
    public void shouldListAllFiles() throws Exception {
        given(this.storageServiceMock.loadAll())
                .willReturn(Stream.of(Paths.get("first.csv"), Paths.get("second.csv")));

        this.mvc.perform(get(STORAGE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("file:///")))
                .andExpect(content().string(containsString("searchservice/first.csv")))
                .andExpect(content().string(containsString("searchservice/second.csv")));
    }

    @Test
    public void SHouldExpectExceptionIfFileNameDoesNotContainCsv() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.csv",
                "text/plain", "Spring Framework".getBytes());
        this.mvc.perform(multipart(STORAGE).file(multipartFile))
                .andDo(print())
                .andExpect(status().isCreated());

        then(this.storageServiceMock).should().store(multipartFile);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void should404WhenMissingFile() throws Exception {
        given(this.storageServiceMock.loadAsResource("test.txt"))
                .willThrow(StorageFileNotFoundException.class);

        this.mvc.perform(get("/files/test.txt")).andDo(print()).andExpect(status().isNotFound());
    }

}
