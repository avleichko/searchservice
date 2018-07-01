package com.search.service;

import com.search.service.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class SearchTests {

    private static final String SEARCH = "/search?searchParams=";
    public static final String FILE_CSV = "file.csv";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StorageService storageService;

    Charset utf8 = StandardCharsets.UTF_8;
    List<String> lines = Arrays.asList("1st, line", "2nd, line");

    public SearchTests() {
        try {
            Files.write(Paths.get(FILE_CSV), lines, utf8);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void shouldFindFileWithValidParams() throws Exception {
        saveFileToStorage();

        this.mvc.perform(get(SEARCH + "1st"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("\"file.csv\"")));
    }


    @Test
    public void shouldNotFindFileWhenNotValidParams() throws Exception {
        saveFileToStorage();


        this.mvc.perform(get(SEARCH + "test"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(content().string(containsString("[test] not found")));
    }


    private void saveFileToStorage() throws IOException {
        File file = new File("file.csv");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", Files.readAllBytes(file.toPath()));

        storageService.store(multipartFile);
    }


}
