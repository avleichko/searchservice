package com.search.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ApplicationIT {
    @Autowired
    private MockMvc mvc;

    private static final String HEALTH = "/actuator/health";
    private static final String SWAGGER_UI = "/swagger-ui.html";

    @Test
    public void when_heartbeat_requested_expected_heartbeatUP_and_databaseUP_and_diskUP_and_hystrixUP()
            throws Exception {
        this.mvc.perform(get(HEALTH)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"status\":\"UP\"")));
    }

    @Test
    public void when_swagger_requested_and_expect_200() throws Exception {
        this.mvc.perform(get(SWAGGER_UI)).andExpect(status().isOk());
    }
}
