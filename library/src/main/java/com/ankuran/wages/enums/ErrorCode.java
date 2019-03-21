package com.ankuran.wages.enums;

/**
 * @author Sushil Mittal.
 * @Created At 18/02/19.
 */
public enum ErrorCode {
    INVALID_INPUT(100, "Invalid input");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
