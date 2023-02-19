package fr.joul.cie.test.springtechnicaltest.domain;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OfferTest {
    static Offer offer;
    @BeforeAll
    static void setUp() {
        offer = new Offer("GAS", "EKWATEST", "UNE OFFRE TEST");
    }

    @AfterAll
    static void tearDown() {
        offer = null;
    }

    @Test
    void addPromoCodeToOffer() {
        Code codeToAdd = new Code("EKWA_TEST", 1.75, LocalDate.parse("2019-10-04"));
        offer.addPromoCodeToOffer(codeToAdd);
        assertTrue(offer.getValidPromoCodeList().contains(codeToAdd));
    }
}