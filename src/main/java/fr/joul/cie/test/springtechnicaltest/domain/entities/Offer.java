package fr.joul.cie.test.springtechnicaltest.domain.entities;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Offer {
    private final OfferType offerType;
    private final String offerName;
    private final String offerDescription;
    private final Set<String> validPromoCodeList;

    public Offer(OfferType offerType, String offerName, String offerDescription) {
        this.offerType = offerType;
        this.offerName = offerName;
        this.offerDescription = offerDescription;
        this.validPromoCodeList = new HashSet<>();
    }

    public void addPromoCodeToOffer(String promoCode) {
        validPromoCodeList.add(promoCode);
    }
}
