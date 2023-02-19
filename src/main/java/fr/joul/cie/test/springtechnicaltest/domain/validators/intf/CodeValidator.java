package fr.joul.cie.test.springtechnicaltest.domain.validators.intf;

import fr.joul.cie.test.springtechnicaltest.domain.Code;

import java.util.Set;

public interface CodeValidator {
    boolean validate(Set<Code> offerValidCodes, Code code);
}
