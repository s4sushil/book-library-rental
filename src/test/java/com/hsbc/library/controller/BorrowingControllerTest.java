package com.hsbc.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.library.domain.dto.BorrowingDto;
import com.hsbc.library.service.BorrowingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BorrowingControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BorrowingService service;

    @Test
    public void whenCallPOSTMethod_thenSaveNewReaderAndReceiveJSONRespond() throws Exception {
        BorrowingDto dto = new BorrowingDto(1l, 1l, 1l, Boolean.TRUE);
        when(service.saveBorrowing(dto)).thenReturn(dto);

        mvc.perform(post("/api/v1/library/borrow").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenCallPUTMethod_thenSaveNewReaderAndReceiveJSONRespond() throws Exception {
        BorrowingDto dto = new BorrowingDto(1l, 1l, 1l, Boolean.TRUE);
        when(service.returnBook(dto)).thenReturn(dto);

        mvc.perform(put("/api/v1/library/return").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}