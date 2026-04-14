package com.pragma.ms_technology.domain.exception;

public class TechnologyAlreadyExistsException extends RuntimeException {
    public TechnologyAlreadyExistsException(String name) {
        super("Technology with name '" + name + "' already exists");
    }
}
