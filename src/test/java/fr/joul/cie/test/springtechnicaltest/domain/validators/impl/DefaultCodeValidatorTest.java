package fr.joul.cie.test.springtechnicaltest.domain.validators.impl;

import fr.joul.cie.test.springtechnicaltest.domain.Code;
import fr.joul.cie.test.springtechnicaltest.domain.Offer;
import fr.joul.cie.test.springtechnicaltest.domain.OfferType;
import fr.joul.cie.test.springtechnicaltest.domain.validators.intf.CodeValidator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DefaultCodeValidatorTest {

    static Offer offer;
    static CodeValidator codeValidator;
    @BeforeAll
    static void setUp() {
        codeValidator = new DefaultCodeValidator();
        offer = new Offer(OfferType.GAS, "EKWATEST", "UNE OFFRE TEST");
    }

    @AfterAll
    static void tearDown() {
        offer = null;
    }

    @ParameterizedTest
    @MethodSource("providerForShouldReturnTrueForValidCode")
    void shouldReturnTrueForValidCode(Code code, boolean expected) {
        offer.addPromoCodeToOffer(code.code());
        assertEquals(codeValidator.validate(offer.getValidPromoCodeList(), code), expected);
    }

    @Test
    void shouldReturnFalseForPassedEndDateCode() {
        Code code = new Code("EKWA_WELCOME", 2.0,"2019-10-04");
        offer.addPromoCodeToOffer(code.code());
        assertFalse(codeValidator.validate(offer.getValidPromoCodeList(), code));
    }

    @Test
    void shouldReturnFalseForCodeNotFoundInList() {
        Code code = new Code("EKWA_WELCOME", 2.0,"2019-10-04");
        assertFalse(codeValidator.validate(offer.getValidPromoCodeList(), code));
    }

    @ParameterizedTest
    @MethodSource("providerForShouldThrowDateTimeParseException")
    void shouldThrowDateTimeParseException(Code code) {
        Set<String> validPromoCodeList = offer.getValidPromoCodeList();
        Assertions.assertThrows(DateTimeParseException.class , () -> {
            codeValidator.validate(validPromoCodeList, code);
        });
    }

    @Test
    void shouldThrowNullPointerException() {
        Set<String> validPromoCodeList = offer.getValidPromoCodeList();
        Assertions.assertThrows(NullPointerException.class , () -> {
            codeValidator.validate(validPromoCodeList, null);
        });
    }

    public static Stream<Arguments> providerForShouldReturnTrueForValidCode() {
        return Stream.of(
        Arguments.of(new Code("ALL_2000", 2.75, "2023-03-05"), true),
                Arguments.of(new Code("EKWA_WELCOME", 2.0,"2023-10-04"), true)
        );
    }

    public static Stream<Arguments> providerForShouldThrowDateTimeParseException() {
        return Stream.of(
                Arguments.of(new Code("ALL_2000", 2.75, "")),
                Arguments.of(new Code("ALL_2000", 2.75,"10")),
                Arguments.of(new Code("ALL_2000", 2.75,"test"))
        );
    }
}