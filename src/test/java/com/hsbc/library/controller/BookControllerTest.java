package com.hsbc.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hsbc.library.domain.dto.BookDto;
import com.hsbc.library.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    public void givenBooksURL_whenCallGETMethod_thenReceiveOkRespond() throws Exception {
        BookDto book1 = new BookDto("Sushil's title", "Sushil as author", 1983);
        BookDto book2 = new BookDto("Dance with Dragon", "R. Martin", 1913);
        when(bookService.findAll()).thenReturn(Arrays.asList(book1, book2));

        mvc.perform(
                get("/api/v1/library/books/").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Sushil's title")))
                .andExpect(jsonPath("$[0].author", is("Sushil as author")))
                .andExpect(jsonPath("$[0].publicationYear", is(1983)))
                .andExpect(jsonPath("$[1].title", is("Dance with Dragon")))
                .andExpect(jsonPath("$[1].author", is("R. Martin")))
                .andExpect(jsonPath("$[1].publicationYear", is(1913)));

        verify(bookService, times(1)).findAll();
        verifyNoMoreInteractions(bookService);

    }

    @Test
    public void givenTenthUserId_whenCallGETMethod_thenReceiveJSONOkRespond() throws Exception {
        BookDto book1 = new BookDto("Sushil's title", "Sushil as author", 1983);
        when(bookService.findById(1l)).thenReturn(book1);

        mvc.perform(
                get("/api/v1/library/books/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Sushil's title")))
                .andExpect(jsonPath("$.author", is("Sushil as author")))
                .andExpect(jsonPath("$.publicationYear", is(1983)));

        verify(bookService, times(1)).findById(1l);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getInvalidAsMethodNotAllowed() throws Exception{
        mvc.perform(get("/api/v1/library/book"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenNewBook_whenCallPOSTMethod_thenSaveNewBookAndReceiveJSONRespond() throws Exception {
        BookDto book1 = new BookDto("Sushil Choudhary", "Sushil Athr", 1983);

        mvc.perform(post("/api/v1/library/book").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book1)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidUserIdWithNoReferenceToOtherTable_whenCallDELETEMethod_thenDeleteUserAndReceiveNoContentRespond() throws Exception {
        mvc.perform(delete("/api/v1/library/books/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

}