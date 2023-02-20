package fr.joul.cie.test.springtechnicaltest.domain.ports.out;

import fr.joul.cie.test.springtechnicaltest.domain.entities.Offer;

import java.util.List;

public interface OfferPort {
    List<Offer> getAll();
}
