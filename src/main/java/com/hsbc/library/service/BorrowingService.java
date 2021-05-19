package com.hsbc.library.service;

import com.hsbc.library.domain.Borrowing;
import com.hsbc.library.domain.Item;
import com.hsbc.library.domain.Reader;
import com.hsbc.library.domain.Status;
import com.hsbc.library.domain.dto.BorrowingDto;
import com.hsbc.library.mapper.BorrowingMapper;
import com.hsbc.library.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class BorrowingService {

    private final BorrowingMapper borrowingMapper;
    private final BorrowingRepository borrowingRepository;
    private final ReaderService readerService;
    private final ItemService itemService;

    @Autowired
    public BorrowingService(BorrowingMapper borrowingMapper, BorrowingRepository borrowingRepository,
                            ReaderService readerService, ItemService itemService) {
        this.borrowingMapper = borrowingMapper;
        this.borrowingRepository = borrowingRepository;
        this.readerService = readerService;
        this.itemService = itemService;
    }

    public BorrowingDto saveBorrowing(final BorrowingDto borrowingDto) {
        Reader reader = readerService.getReaderById(borrowingDto.getReaderId());
        Item item = itemService.getItemById(borrowingDto.getItemId());
        Borrowing borrowing = new Borrowing(item, reader);
        borrowing.setBorrowedFrom(LocalDate.now());
        borrowingRepository.save(borrowing);
        return borrowingMapper.mapToBorrowingDto(borrowing);
    }

    public BorrowingDto returnBook(final BorrowingDto borrowingDto) {
        Borrowing borrowing = getBorrowingById(borrowingDto.getBorrowingId());
        borrowing.setBorrowedTo(LocalDate.now());
        if (borrowingDto.isPaidForDamaged()) {
            borrowing.getItem().setStatus(Status.AVAILABLE);
            borrowing.setPaidForDamaged(true);
        }
        borrowingRepository.save(borrowing);
        return borrowingMapper.mapToBorrowingDto(borrowing);
    }

    public Borrowing getBorrowingById(final Long id) {
        return borrowingRepository.findById(id).orElse(null);
    }
}
