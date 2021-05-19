package com.hsbc.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.library.domain.Status;
import com.hsbc.library.domain.dto.ItemDto;
import com.hsbc.library.service.ItemService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService service;

    @Test
    public void whenCallPOSTMethod_thenSaveNewReaderAndReceiveJSONRespond() throws Exception {
        ItemDto itemDto = new ItemDto(1l, 1l, Status.AVAILABLE);
        when(service.saveItem(itemDto)).thenReturn(itemDto);

        mvc.perform(post("/api/v1/library/item").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(itemDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenCallPUTMethod_thenSaveNewReaderAndReceiveJSONRespond() throws Exception {
        ItemDto itemDto = new ItemDto(1l, 1l, Status.AVAILABLE);
        when(service.saveItem(itemDto)).thenReturn(itemDto);

        mvc.perform(put("/api/v1/library/item").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(itemDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenCallGETMethod_thenSaveNewReaderAndReceiveJSONRespond() throws Exception {
        ItemDto itemDto = new ItemDto(1l, 1l, Status.AVAILABLE);
        when(service.getNumberOfItemsByTitle("test")).thenReturn(1l);

        mvc.perform(get("/api/v1/library/items/count?title=test").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(itemDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}