package com.example.StudentLibraryManagement.Exceptions;

public class CardLimitReached extends Exception{
    public CardLimitReached(String message) {
        super(message);
    }
}
