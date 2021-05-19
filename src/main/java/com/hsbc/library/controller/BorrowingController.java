package com.hsbc.library.controller;

import com.hsbc.library.domain.dto.BorrowingDto;
import com.hsbc.library.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/library")
public class BorrowingController {

    private final BorrowingService borrowingService;

    @Autowired
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping(value = "borrow")
    public BorrowingDto createBorrowing(@RequestBody BorrowingDto borrowingDto) {
        return borrowingService.saveBorrowing(borrowingDto);
    }

    @PutMapping(value = "return")
    public BorrowingDto returnBook(@RequestBody BorrowingDto borrowingDto) {
        return borrowingService.returnBook(borrowingDto);
    }
}
