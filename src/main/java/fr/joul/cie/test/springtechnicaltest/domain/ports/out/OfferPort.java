package fr.joul.cie.test.springtechnicaltest.domain.ports.out;

import fr.joul.cie.test.springtechnicaltest.domain.Offer;

import java.util.List;

public interface OfferPort {
    List<Offer> getAll();
}
