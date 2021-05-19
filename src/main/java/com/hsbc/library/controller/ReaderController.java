package com.hsbc.library.controller;

import com.hsbc.library.domain.dto.ReaderDto;
import com.hsbc.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/library")
public class ReaderController {

    @Autowired
    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping(value = "reader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReaderDto createReader(@RequestBody ReaderDto readerDto) {
        return readerService.saveReader(readerDto);
    }
}
