package com.hsbc.library.service;

import com.hsbc.library.domain.Item;
import com.hsbc.library.domain.Reader;
import com.hsbc.library.domain.Status;
import com.hsbc.library.domain.dto.BookDto;
import com.hsbc.library.domain.dto.BorrowingDto;
import com.hsbc.library.domain.dto.ItemDto;
import com.hsbc.library.domain.dto.ReaderDto;
import com.hsbc.library.exception.BookNotFoundException;
import com.hsbc.library.exception.ItemNotFoundException;
import com.hsbc.library.exception.ReaderNotFoundException;
import com.hsbc.library.mapper.ItemMapper;
import com.hsbc.library.service.BookService;
import com.hsbc.library.service.BorrowingService;
import com.hsbc.library.service.ItemService;
import com.hsbc.library.service.ReaderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTestSuite {

    @Autowired
    BookService bookService;
    @Autowired
    BorrowingService borrowingService;
    @Autowired
    ItemService itemService;
    @Autowired
    ReaderService readerService;

    @Test
    public void testBookService() {
        BookDto book1 = new BookDto("Test title", "Test author", 1973);
        BookDto savedBook = bookService.saveBook(book1);

        assertThat(book1.getTitle(), is(savedBook.getTitle()));
    }

    @Test
    public void testBookFound() {
        BookDto book1 = new BookDto("Sushil's title", "Sushil as author", 1983);
        bookService.saveBook(book1);

        BookDto foundBook = bookService.findById(1l);
        assertNotNull(foundBook);
        assertThat(book1.getTitle(), is(foundBook.getTitle()));
    }

    @Test(expected = BookNotFoundException.class)
    public void testBookNotFound() {
        bookService.findById(123456l);
    }

    @Test
    public void testItemService() {
        BookDto book1 = new BookDto("Test title", "At author", 1986);
        bookService.saveBook(book1);

        ItemDto item1 = new ItemDto(1l, 1l, Status.AVAILABLE);
        ItemDto itemSaved = itemService.saveItem(item1);
        Item itemRetreived = itemService.getItemById(itemSaved.getItemId());

        assertThat(itemSaved.getItemId(), is(itemRetreived.getId()));

        ItemDto item2 = new ItemMapper().mapToItemDto(itemRetreived);
        ItemDto item = new ItemDto(item2.getItemId(), item2.getBookId(), Status.LOST);
        ItemDto itemDtoUpdatedInDB = itemService.updateStatus(item);

        assertNotNull(itemDtoUpdatedInDB);
        assertThat(itemDtoUpdatedInDB.getStatus(), is(Status.LOST));
    }

    @Test(expected = ItemNotFoundException.class)
    public void testItemNotFoundException() {
        itemService.getItemById(100l);
    }

    @Test
    public void readerServiceTest() {
        ReaderDto reader1 = new ReaderDto("Sushil", "Choudhary", LocalDate.now());
        ReaderDto readerDto = readerService.saveReader(reader1);

        Assert.assertNotNull(readerDto.getAccountCreated());
        Reader readerById = readerService.getReaderById(2l);
        assertThat(readerById.getName(), is(reader1.getName()));
        assertThat(readerById.getLastName(), is(reader1.getLastName()));

        assertThat(readerById.getName(), is(readerDto.getName()));
        assertThat(readerById.getLastName(), is(readerDto.getLastName()));
    }

    @Test(expected = ReaderNotFoundException.class)
    public void testReaderNotFoundException() {
        readerService.getReaderById(100l);
    }


    @Test
    public void testBorrowingRepository() {
        BookDto book1 = new BookDto("Test title 1", "Test author 1", 1992);
        bookService.saveBook(book1);

        ItemDto item2 = new ItemDto(2l, 1l, Status.DESTROYED);
        ItemDto savedItem = itemService.saveItem(item2);

        ReaderDto reader = new ReaderDto("John", "Smith", LocalDate.now());
        readerService.saveReader(reader);
        Reader readerByName = readerService.getReaderByName("John");
        assertNotNull(readerByName);

        BorrowingDto borrowing1 = new BorrowingDto(3l, savedItem.getItemId(), readerByName.getId(), true);

        BorrowingDto borrowingRetrieved = borrowingService.saveBorrowing(borrowing1);

        assertThat(borrowingRetrieved.getItemId(), is(borrowing1.getItemId()));

        long id1 = borrowing1.getBorrowingId();
        Assert.assertNotEquals(0, id1);

    }
}
