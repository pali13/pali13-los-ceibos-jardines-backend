package com.palisuar.losceibosjardines.exceptions;

public class CustomExceptions {
    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class NameAlreadyExistsException extends RuntimeException {
        public NameAlreadyExistsException(String message) {
            super(message);
        }
    }
}
