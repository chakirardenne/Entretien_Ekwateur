package fr.joul.cie.test.springtechnicaltest.application.exception;

public class NotValidCodeException extends RuntimeException {
    public NotValidCodeException(String codeNotFound) {
        super(codeNotFound);
    }
}
