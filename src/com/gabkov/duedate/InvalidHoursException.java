package com.gabkov.duedate;

public class InvalidHoursException extends Exception{
    public InvalidHoursException() {
    }

    public InvalidHoursException(String message) {
        super(message);
    }
}
