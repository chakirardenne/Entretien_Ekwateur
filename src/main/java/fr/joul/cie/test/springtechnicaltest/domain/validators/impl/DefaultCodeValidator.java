package fr.joul.cie.test.springtechnicaltest.domain.validators.impl;

import fr.joul.cie.test.springtechnicaltest.domain.entities.Code;
import fr.joul.cie.test.springtechnicaltest.domain.validators.intf.CodeValidator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class DefaultCodeValidator implements CodeValidator {
    @Override
    public boolean validate(Set<String> offerValidCodes, Code code) {
        return checkCodeEndDate(code) && containsCode(offerValidCodes, code);
    }

    private boolean containsCode(Set<String> offerValidCodes, Code code) {
        return offerValidCodes.contains(code.code());
    }

    private boolean checkCodeEndDate(Code code) {
        return LocalDate.parse(code.endDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(LocalDate.now(
                ZoneId.of("Europe/Paris"))
        );
    }
}
