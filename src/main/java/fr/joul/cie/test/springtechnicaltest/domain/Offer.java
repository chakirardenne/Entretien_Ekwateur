package fr.joul.cie.test.springtechnicaltest.domain;

import java.util.HashSet;
import java.util.Set;

public class Offer {
    private final String offerType;
    private final String offerName;
    private final String offerDescription;
    private final Set<Code> validPromoCodeList;

    public Offer(String offerType, String offerName, String offerDescription) {
        this.offerType = offerType;
        this.offerName = offerName;
        this.offerDescription = offerDescription;
        this.validPromoCodeList = new HashSet<>();
    }

    public void addPromoCodeToOffer(Code promoCode) {
        validPromoCodeList.add(promoCode);
    }
}
