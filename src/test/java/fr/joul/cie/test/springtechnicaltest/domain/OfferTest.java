package fr.joul.cie.test.springtechnicaltest.domain;


import fr.joul.cie.test.springtechnicaltest.domain.entities.Code;
import fr.joul.cie.test.springtechnicaltest.domain.entities.Offer;
import fr.joul.cie.test.springtechnicaltest.domain.entities.OfferType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OfferTest {
    static Offer offer;
    @BeforeAll
    static void setUp() {
        offer = new Offer(OfferType.GAS, "EKWATEST", "UNE OFFRE TEST");
    }

    @AfterAll
    static void tearDown() {
        offer = null;
    }

    @Test
    void addPromoCodeToOffer() {
        Code codeToAdd = new Code("EKWA_TEST", 1.75,"2019-10-04");
        offer.addPromoCodeToOffer(codeToAdd.code());
        assertTrue(offer.getValidPromoCodeList().contains(codeToAdd.code()));
    }
}