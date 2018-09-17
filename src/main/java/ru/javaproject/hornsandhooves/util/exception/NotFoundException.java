package ru.javaproject.hornsandhooves.util.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException{
    public static final String NOT_FOUND_EXCEPTION = "not found exception";

    //  http://stackoverflow.com/a/22358422/548473
    public NotFoundException(String arg) {
        super(ErrorType.DATA_NOT_FOUND, NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND, arg);
    }
}
