package com.hsbc.library.controller;

import com.hsbc.library.domain.dto.ItemDto;
import com.hsbc.library.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/library")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(value = "item")
    public ItemDto createItem(@RequestBody ItemDto itemDto) {
        return itemService.saveItem(itemDto);
    }

    @PutMapping(value = "item")
    public ItemDto updateStatus(@RequestBody ItemDto itemDto) {
        return itemService.updateStatus(itemDto);
    }

    @GetMapping(value = "items/count")
    public Long getNumberOfItemsByTitle(@RequestParam String title) {
        return itemService.getNumberOfItemsByTitle(title);
    }
}
