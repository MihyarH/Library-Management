package org.example.Exceptions;

public class DuplicateBookNameException extends Exception {
    public DuplicateBookNameException(String message) {
        super(message);
    }
}
