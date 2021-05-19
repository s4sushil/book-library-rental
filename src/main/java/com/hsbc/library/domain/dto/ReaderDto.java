package com.hsbc.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDto implements Serializable {
    private String name;
    private String lastName;
    private LocalDate accountCreated;
}
