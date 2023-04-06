package com.example.foodexpress.customExceptions;

public class OfferNotFoundException extends Throwable{
    public OfferNotFoundException(String message) {
        super(message);
    }
}
