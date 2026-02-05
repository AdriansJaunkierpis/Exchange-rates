package com.adrians.rates.error;

public class FxRateFetchException extends RuntimeException {
    public FxRateFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
