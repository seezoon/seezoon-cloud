package com.seezoon;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
class HelloContollerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void stub() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/").param("name", "stub")).andDo(print())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        log.debug("response:{}", contentAsString);

    }

    @Test
    void blockingStub() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/blockingStub").param("name", "blockingStub"))
                .andDo(print()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        log.debug("response:{}", contentAsString);
    }

    @Test
    void futureStub() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/futureStub").param("name", "futureStub"))
                .andDo(print()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        log.debug("response:{}", contentAsString);
    }
}