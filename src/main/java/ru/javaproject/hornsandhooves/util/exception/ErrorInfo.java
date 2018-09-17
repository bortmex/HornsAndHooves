package ru.javaproject.hornsandhooves.util.exception;

public class ErrorInfo {
    private final String code;
    private final String message;

    public ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
