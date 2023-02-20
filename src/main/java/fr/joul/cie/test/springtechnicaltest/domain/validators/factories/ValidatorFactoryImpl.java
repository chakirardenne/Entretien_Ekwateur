package fr.joul.cie.test.springtechnicaltest.domain.validators.factories;

import fr.joul.cie.test.springtechnicaltest.domain.exception.InvalidValidatorException;
import fr.joul.cie.test.springtechnicaltest.domain.validators.impl.DefaultCodeValidator;
import fr.joul.cie.test.springtechnicaltest.domain.validators.impl.ValidatorType;
import fr.joul.cie.test.springtechnicaltest.domain.validators.intf.CodeValidator;

public class ValidatorFactoryImpl implements ValidatorFactory {
    @Override
    public CodeValidator createValidator(ValidatorType validatorType) {
        return switch (validatorType) {
            case DEFAULT -> new DefaultCodeValidator();
            default -> throw new InvalidValidatorException("Not known validator strategy");
        };
    }
}
