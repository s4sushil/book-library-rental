package com.hsbc.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hsbc.library.domain.dto.ReaderDto;
import com.hsbc.library.service.ReaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReaderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ReaderService readerService;

    @Test
    public void whenCallPOSTMethod_thenSaveNewReaderAndReceiveJSONRespond() throws Exception {
        ReaderDto readerDto = new ReaderDto("Sushil Choudhary", "Sushil Athr", null);

        mvc.perform(post("/api/v1/library/reader").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(readerDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Sushil Choudhary")))
                .andExpect(jsonPath("$.lastName", is("Sushil Athr")));
    }
}