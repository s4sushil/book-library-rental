package com.hsbc.library.exception;

public class ReaderNotFoundException extends RuntimeException {

    public ReaderNotFoundException(Long id) {
        super("Could not find reader with id: " + id);
    }

    public ReaderNotFoundException(String name) {
        super("Could not find reader with name: " + name);
    }

}
