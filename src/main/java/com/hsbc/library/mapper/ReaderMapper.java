package com.hsbc.library.mapper;

import com.hsbc.library.domain.Reader;
import com.hsbc.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Component;

@Component
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getName(),
                readerDto.getLastName()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getName(),
                reader.getLastName(),
                reader.getAccountCreated()
        );
    }
}
