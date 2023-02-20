package fr.joul.cie.test.springtechnicaltest.domain.ports.in;

import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferRequest;
import fr.joul.cie.test.springtechnicaltest.application.dto.GetCompatibleOfferResponse;
import fr.joul.cie.test.springtechnicaltest.domain.validators.intf.CodeValidator;

import java.util.List;

public interface GetCompatibleOfferUseCase {
    GetCompatibleOfferResponse getOffers(GetCompatibleOfferRequest getCompatibleOfferRequest );
    void setOfferValidationStrategy(CodeValidator validator);
}
