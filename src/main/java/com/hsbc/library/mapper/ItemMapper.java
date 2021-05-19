package com.hsbc.library.mapper;

import com.hsbc.library.domain.Item;
import com.hsbc.library.domain.dto.ItemDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDto mapToItemDto(final Item item) {
        return new ItemDto(
                item.getId(),
                item.getBook().getId(),
                item.getStatus()
        );
    }
}
