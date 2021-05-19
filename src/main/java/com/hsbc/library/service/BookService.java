package com.hsbc.library.service;

import com.hsbc.library.domain.Book;
import com.hsbc.library.domain.dto.BookDto;
import com.hsbc.library.exception.BookNotFoundException;
import com.hsbc.library.mapper.BookMapper;
import com.hsbc.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public BookDto saveBook(final BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        bookRepository.save(book);
        return bookMapper.mapToBookDto(book);
    }

    public Book getBookById(final Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<BookDto> findAll() {
        List<BookDto> bookDtos = new ArrayList<>();
        bookRepository.findAll()
                .forEach(book -> bookDtos.add(bookMapper.mapToBookDto(book)));

        return bookDtos;
    }

    public BookDto findById(Long bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::mapToBookDto)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public void deleteById(Long id) {
        //Check if present in DB, else throws Error.
        getBookById(id);
        //Since present, it deletes the book
        bookRepository.deleteById(id);
    }
}
