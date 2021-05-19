package com.hsbc.library.domain.dto;

import com.hsbc.library.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long itemId;
    private Long bookId;
    private Status status;
}
