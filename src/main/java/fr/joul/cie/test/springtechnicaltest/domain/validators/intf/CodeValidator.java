package fr.joul.cie.test.springtechnicaltest.domain.validators.intf;

import fr.joul.cie.test.springtechnicaltest.domain.entities.Code;

import java.util.Set;

public interface CodeValidator {
    boolean validate(Set<String> offerValidCodes, Code code);
}
