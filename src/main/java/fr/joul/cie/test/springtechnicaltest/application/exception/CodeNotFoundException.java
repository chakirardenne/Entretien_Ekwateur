package fr.joul.cie.test.springtechnicaltest.application.exception;

public class CodeNotFoundException extends RuntimeException {
    public CodeNotFoundException(String codeNotFound) {
        super(codeNotFound);
    }
}
