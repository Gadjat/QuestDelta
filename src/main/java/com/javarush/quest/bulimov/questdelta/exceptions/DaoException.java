package com.javarush.quest.bulimov.questdelta.exceptions;

public class DaoException extends RuntimeException{

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
