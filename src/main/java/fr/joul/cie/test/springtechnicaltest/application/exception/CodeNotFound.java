package fr.joul.cie.test.springtechnicaltest.application.exception;

public class CodeNotFound extends RuntimeException {
    public CodeNotFound(String codeNotFound) {
        super(codeNotFound);
    }
}
