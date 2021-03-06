package com.hsbc.library.mapper;

import com.hsbc.library.domain.Book;
import com.hsbc.library.domain.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear()
        );
    }
}
