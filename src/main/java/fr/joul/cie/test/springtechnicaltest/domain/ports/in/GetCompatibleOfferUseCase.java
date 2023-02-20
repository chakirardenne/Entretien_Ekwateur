package fr.joul.cie.test.springtechnicaltest.domain.ports.in;

import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferRequest;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferResponse;

public interface GetCompatibleOfferUseCase {
    GetCompatibleOfferResponse getOffers(GetCompatibleOfferRequest getCompatibleOfferRequest );
    void setOfferValidationStrategy(String validator);
}
