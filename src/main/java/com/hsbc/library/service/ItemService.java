package com.hsbc.library.service;

import com.hsbc.library.domain.Book;
import com.hsbc.library.domain.Item;
import com.hsbc.library.domain.Status;
import com.hsbc.library.domain.dto.ItemDto;
import com.hsbc.library.exception.ItemNotFoundException;
import com.hsbc.library.mapper.ItemMapper;
import com.hsbc.library.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final BookService bookService;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, BookService bookService) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.bookService = bookService;
    }

    public ItemDto saveItem(final ItemDto itemDto) {
        Book book = bookService.getBookById(itemDto.getBookId());
        Item item = new Item(book);
        Item savedItem = itemRepository.save(item);
        return itemMapper.mapToItemDto(savedItem);
    }

    public ItemDto updateStatus(final ItemDto itemDto) {
        Item item = getItemById(itemDto.getItemId());
        Book book = item.getBook();
        Status status = itemDto.getStatus();
        item.setStatus(status);
        item.setBook(book);
        itemRepository.save(item);
        return itemMapper.mapToItemDto(item);
    }

    public Long getNumberOfItemsByTitle(String title) {
        return itemRepository.countAllByBookTitle(title);
    }

    public Item getItemById(final Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }
}
