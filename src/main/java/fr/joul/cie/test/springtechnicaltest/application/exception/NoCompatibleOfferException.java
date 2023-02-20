package fr.joul.cie.test.springtechnicaltest.application.exception;

public class NoCompatibleOfferException extends RuntimeException {
    public NoCompatibleOfferException(String s) {
        super(s);
    }
}
