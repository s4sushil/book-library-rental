package com.hsbc.library.mapper;

import com.hsbc.library.domain.Borrowing;
import com.hsbc.library.domain.dto.BorrowingDto;
import org.springframework.stereotype.Component;

@Component
public class BorrowingMapper {

    public BorrowingDto mapToBorrowingDto(final Borrowing borrowing) {
        return new BorrowingDto(
                borrowing.getReader().getId(),
                borrowing.getItem().getId(),
                borrowing.getId(),
                borrowing.isPaidForDamaged()
        );
    }
}
