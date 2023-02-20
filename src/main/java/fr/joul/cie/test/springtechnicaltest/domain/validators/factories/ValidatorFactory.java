package fr.joul.cie.test.springtechnicaltest.domain.validators.factories;

import fr.joul.cie.test.springtechnicaltest.domain.validators.impl.ValidatorType;
import fr.joul.cie.test.springtechnicaltest.domain.validators.intf.CodeValidator;

public interface ValidatorFactory {
    CodeValidator createValidator(ValidatorType validatorType);
}
