package com.hsbc.library.service;

import com.hsbc.library.domain.Reader;
import com.hsbc.library.domain.dto.ReaderDto;
import com.hsbc.library.exception.ReaderNotFoundException;
import com.hsbc.library.mapper.ReaderMapper;
import com.hsbc.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    @Autowired
    public ReaderService(ReaderRepository readerRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    public ReaderDto saveReader(final ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        reader.setAccountCreated(LocalDate.now());
        Reader savedReader = readerRepository.save(reader);
        return readerMapper.mapToReaderDto(savedReader);
    }

    public Reader getReaderById(final Long id) {
        return readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException(id));
    }

    public Reader getReaderByName(final String name) {
        return readerRepository.findByName(name)
                .stream().findFirst().orElseThrow(() -> new ReaderNotFoundException(name));
    }
}
