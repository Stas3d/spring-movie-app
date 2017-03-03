package com.epam.springapp.exception;

/**
 * @author Stanislav_Kryzhanovs
 */
public class AppException extends RuntimeException {

    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }
}
