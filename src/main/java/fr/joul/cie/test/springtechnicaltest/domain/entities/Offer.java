package fr.joul.cie.test.springtechnicaltest.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class Offer {
    private OfferType offerType;
    private String offerName;
    private String offerDescription;
    private Set<String> validPromoCodeList;

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
